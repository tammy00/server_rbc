package br.ufam.rest.resource;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.ufam.rest.model.Caso;
import br.ufam.rest.model.Solucao;
import br.ufam.rest.model.recuperacao.QueryConfig;
import br.ufam.rest.model.recuperacao.Recuperacao;
import br.ufam.rest.model.recuperacao.Similaridade;
import br.ufam.rest.model.retencao.Retencao;
import br.ufam.rest.model.reutilizacao.Reutilizacao;
import br.ufam.rest.model.revisao.Revisao;

import jcolibri.casebase.LinealCaseBase;
import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.Connector;
import jcolibri.connector.DataBaseConnector;
import jcolibri.exception.ExecutionException;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import jcolibri.method.retrieve.selection.SelectCases;
import jcolibri.util.FileIO;


@Path("/ServerRBC")
public class CasoResource implements StandardCBRApplication{
	static private CasoResource _instance = null;
	private Connector _connector;
	private static CBRCaseBase _caseBase;
	
	public CasoResource(){
		
	}
	
	public static CasoResource getInstance() {
		if(_instance == null)
			   _instance = new CasoResource();
			return _instance;
	}
		
	public void configure() throws ExecutionException {
			
			try{
				System.out.println("Antes DataBaseConnector");
				_connector = new DataBaseConnector();
				System.out.println("Antes ler xml");
				_connector.initFromXMLfile(FileIO.findFile("database/databaseconfig.xml"));
				System.out.println("Antes lineal case");
				_caseBase = new LinealCaseBase();
				System.out.println("Antes do exception");
			} catch (Exception e) {
				throw new ExecutionException(e);
			}
		}
		
		public void postCycle() throws ExecutionException {
			_connector.close();
		}
		
		
		public void cycle(CBRQuery query) throws ExecutionException {
			
			Similaridade sim = new Similaridade();
			
			NNConfig simConfig = sim.getSimilarityConfig();
			
			Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
			
			// Etapa de Recuperação
			//Selecao dos k casos - K = ao número de casos recuperados
			Collection<CBRCase> casoSelecionado = SelectCases.selectTopK(eval, sim.getK());
			System.out.println("\n\n ******** Casos Selecionados ******** \n\n"+casoSelecionado);
			System.out.println("\n\n ************************************ \n\n");
			
			//Usado para exibir o caso passado recuperado mais similar
			Recuperacao recuperar = new Recuperacao(eval, casoSelecionado, sim.getK());
			
			//Mostrar na tela o caso mais similar recuperado
			recuperar.exibirCasosRecuperado();
			
		}
		
		
		public CBRCaseBase preCycle() throws ExecutionException {			
			_caseBase.init(_connector);
			Collection<CBRCase> cases = _caseBase.getCases();
			//for(CBRCase c: cases)
				//System.out.println("Casos Contidos na Base: "+c);			
			return _caseBase;			
		}
		
		
		@GET
		@Path("/casos")
		@Produces("application/json")
		public ArrayList<Caso> listarcasos() throws ExecutionException
		{
			System.out.println("GET Todos os Casos");
			CasoResource gerenciadorRBC = CasoResource.getInstance();
			gerenciadorRBC.configure();
			gerenciadorRBC.preCycle();
			Collection<CBRCase> cases = _caseBase.getCases();
			ArrayList<Caso> casos = new ArrayList<Caso>();		    
			for(CBRCase c: cases){
				Caso caso = new Caso();
				System.out.println("PARTE II - Descrições contidas na Base: "+c.getID());
				System.out.println();
				
				casos.add(caso);
			}	
			gerenciadorRBC.postCycle();
			return casos;	
		}
		
/*
		//É usado pelo método: adicionaCaso
		public void adicionarCasoDataBase(CBRQuery query, Solucao solucao) throws ExecutionException 
		{		
			Similaridade sim = new Similaridade();
			
			NNConfig simConfig = sim.getSimilarityConfig();
			
			Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
			
			// Etapa de Recuperação
			//Selecao dos k casos - K = ao número de casos recuperados
			Collection<CBRCase> casoSelecionado = SelectCases.selectTopK(eval, sim.getK());
			System.out.println("\n\n ******** Casos Selecionados ******** \n\n"+casoSelecionado);
			System.out.println("\n\n ************************************ \n\n");
			
			//Usado para exibir o caso passado recuperado mais similar
			Recuperacao recuperar = new Recuperacao(eval, casoSelecionado, sim.getK());
			
			System.out.println("\n\n *****************Etapa de revisão******************* \n\n");			
			//Etapa de revisão
			Revisao revisao = new Revisao(casoSelecionado,query);
			//Executa o processo de revisão
			System.out.println("\n\n *****************Etapa de revisão.executarRevisao******************* \n\n");
			revisao.executarRevisao2(solucao);
			
			System.out.println("\n\n *****************Etapa de retencao******************* \n\n");
			//Etapa de retenção 
			Retencao reter = new Retencao(casoSelecionado,_caseBase);
				
			System.out.println("\n\n *****************Similaridade******************* \n\n");
			double similaridade = recuperar.getSimilaridade(0);
			//if (similaridade < 1.0){
				//Reter o Caso na base de casos
			System.out.println("\n\n ****************Retencao addCasoRetido******************* \n\n");
			reter.addCasoRetido(0);	//adiciona o novo caso na lista de casos para serem salvos na base de casos
			System.out.println("\n\n *****************Retencao reter.learn******************* \n\n");
			reter.learn(); // salva o novo caso na base de casos
			//}
		}
		
		
		
		
		@POST
		@Path("/casos")
		@Produces("application/json")
		@Consumes("application/json")
		public String adicionarCaso(Caso caso) throws ExecutionException 
		{
			System.out.println("POST Adicionar Caso");
			CasoResource gerenciadorRBC = CasoResource.getInstance();
			try {
				gerenciadorRBC.configure();
				gerenciadorRBC.preCycle();
				
				QueryConfig qf = new QueryConfig();
				
				  String naturezaProblema = caso.getNaturezaProblema();
				  Date dataCriacao = new Date(0);
				  String dateCreated = dataCriacao.toString();
				  String relator = caso.getRelator();				  			  
				  String descricaoProblema = caso.getDescricaoProblema();
				  String problema = caso.getProblema();
				  String palavrasChavesProblema = caso.getPalavrasChavesProblema();
				  Solucao solucao = new Solucao();
				  solucao.setDiagnostico(caso.getDiagnostico());
				  solucao.setSolucao(caso.getSolucao());
				  //solucao.setPalavrasChavesSolucao(caso.getPalavrasChavesSolucao());
				  solucao.setAcaoImplementada(caso.getAcaoImplementada());
				  solucao.setEfetividade(caso.getEfetividade()); 
				  solucao.setCustos(caso.getCustos());
				  solucao.setAtoresEnvolvidos(caso.getAtoresEnvolvidos());
		 			  
				
				//Parametros=>Tema, Topico, Estilo de Aprendizagem, DescricaoProblema, NaturezaProblema
				  System.out.println("Novo Caso: "+naturezaProblema+";"+dateCreated+";"+relator+";"
						  +descricaoProblema+";"+problema+";"+ palavrasChavesProblema+";"+solucao.getDiagnostico()+";"
						  +solucao.getSolucao()+";"+solucao.getAcaoImplementada()+";"
						  +solucao.getEfetividade()+";"+solucao.getCustos()+";"+solucao.getAtoresEnvolvidos()+".");
				  
				//qf.setQuery(tema,topico,estiloDeAprendizagem,descricaoDoProblema,naturezaDoProblema);
				//qf.setQuery(naturezaProblema,dateCreated,relator,descricaoProblema,problema,palavrasChavesProblema);
				
				
				CBRQuery query = qf.getQuery();		
				
				gerenciadorRBC.adicionarCasoDataBase(query,solucao);
						
				gerenciadorRBC.postCycle();
			}catch(Exception e) {
				org.apache.commons.logging.LogFactory.getLog(CasoResource.class).error(e);
			}			
			return caso.getNaturezaProblema() + " adicionado.";
		}   */
		
		
		//É usado pelo método: recuperarCaso
		public Caso recupera(CBRQuery query) throws ExecutionException 
		{
			
			Similaridade sim = new Similaridade();
			
			NNConfig simConfig = sim.getSimilarityConfig();
			
			Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
			
			// Etapa de Recuperação
			//Selecao dos k casos - K = ao número de casos recuperados
			Collection<CBRCase> casoSelecionado = SelectCases.selectTopK(eval, sim.getK());
			System.out.println("\n\n ******** Casos Selecionados ******** \n\n"+casoSelecionado);
			System.out.println("\n\n ************************************ \n\n");
			
			//Usado para exibir o caso passado recuperado mais similar
			Recuperacao recuperar = new Recuperacao(eval, casoSelecionado, sim.getK());
			
			System.out.println("\n\n **************PASSEI POR RECUPERAR********************** \n\n");
			//Mostrar na tela o caso mais similar recuperado
			Caso caso = recuperar.exibirCasosRecuperado2();			
			
			return caso;

		}
		
		// Recupera o caso mais similar, relacionado a action searchPost.php
		
		@POST
		@Path("/casos/caso")
		@Produces("application/json")
		@Consumes("application/json")
		public Caso recuperarCaso(Caso caso) 
		{
			System.out.println("Entrou em recuperarCaso() Recuperar Caso");
			CasoResource gerenciadorRBC = CasoResource.getInstance();
			Caso casoRecuperado = new Caso();
			try {
				gerenciadorRBC.configure();
				gerenciadorRBC.preCycle();
				
				QueryConfig qf = new QueryConfig();				
				String naturezaProblema = caso.getNaturezaProblema();
				String tema = caso.getTema();				
				String topico = caso.getTopico();
				String estiloDeAprendizagem = caso.getEstiloDeAprendizagem();
				String descricaoProblema = caso.getDescricaoProblema();
				 			  
				
				//Parametros=>Tema, Topico, Estilo de Aprendizagem, DescricaoProblema, NaturezaProblema
				  System.out.println("Novo Caso: "+tema+";"+topico+";"+estiloDeAprendizagem+";"+descricaoProblema+";"+ naturezaProblema);
				  
				qf.setQuery(tema,topico,estiloDeAprendizagem,descricaoDoProblema,naturezaDoProblema);
				//qf.setQuery(naturezaProblema,relator,cursoId,disciplinaId,descricaoProblema,problema,palavrasChavesProblema);
				CBRQuery query = qf.getQuery();		
				
				casoRecuperado = gerenciadorRBC.recupera(query);
						
				gerenciadorRBC.postCycle();
			}catch(Exception e) {
				org.apache.commons.logging.LogFactory.getLog(CasoResource.class).error(e);
			}
			
			return casoRecuperado;

		}
		
	}
