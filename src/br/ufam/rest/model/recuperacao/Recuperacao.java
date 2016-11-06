package br.ufam.rest.model.recuperacao;

import java.util.ArrayList;
import java.util.Collection;

import java.util.List;

import javax.swing.JOptionPane;

import br.ufam.rest.model.Caso;
import br.ufam.rest.model.Descricao;
import br.ufam.rest.model.Solucao;

import jcolibri.cbrcore.CBRCase;
import jcolibri.method.retrieve.RetrievalResult;

/*
 * Classe que implementa a etapa de Recuperacao
 * 
 * */

public class Recuperacao {
	
	private Collection<RetrievalResult> eval;
	private Collection<CBRCase> casos_selecionados;
	private List<RetrievalResult> casos;
	private int k;
	
	public Recuperacao(Collection<RetrievalResult> eval, Collection<CBRCase> casos_selecionados, int k) {
		
		casos = new ArrayList<RetrievalResult>();
		this.k = k;
		this.casos_selecionados = casos_selecionados;
		this.eval = eval;
		
		for(RetrievalResult recuperacao: this.eval) {
			if(this.casos_selecionados.contains(recuperacao.get_case())) {
				this.casos.add(recuperacao);
			}
		}
		
	}
	//responsavel por imprimir o resultado
	public void exibirCasosRecuperado() {
		for(int i=0;i<this.k;i++) {
			Descricao desc = getCaseDescription(i);
			Solucao sol = getSolucaoCaso(i);
			//if(getSimilaridade(i) == 0.0){
				//JOptionPane.showMessageDialog(null,"Não foi encontrado nenhum caso similar");
			    //System.exit(0); // Finaliza o programa - Isso deve ser feito de outra forma
			//}else{
			String informacoes = "                 <<< ETAPA DE RECUPERACAO >>> \n\n O caso passado mais similar ao novo caso recuperado da base de casos\n"+
			"Valor da Similaridade:"+getSimilaridade(i) +
			" \n Caso iD: "+ getCaso(i).getID() +
			";\n Tema: "+desc.getTema()+
			";\n Topico: "+desc.getTopico()+
			";\n Estilo de Aprendizagem: "+desc.getEstiloDeAprendizagem()+
			";\n Descricao do proglema: "+desc.getDescricaoProblema()+
			";\n Natureza do problema: "+desc.getNaturezaProblema()+
			" \n \n  ------ Solucao Empregada ------- \n"+
			"\nDiagnostico: "+sol.getDiagnostico()+
			"\n"+"Acao Pedagogica: "+sol.getAcaoPedagogica()+
			"\n"+"Objeto de Aprendizagem: "+sol.getObjetoDeAprendizagem();
			JOptionPane.showMessageDialog(null, informacoes);
			//}
			
		}
	}
	
	//responsavel por setar o Caso e poder voltar via json para PHP
		public Caso exibirCasosRecuperado2() {
			System.out.println("\n\n **************Entrei em exibir 2********************** \n\n");
			Caso caso = new Caso();
			for(int i=0;i<this.k;i++) 
			{
				System.out.println("\n\n ......Entrei no for \n\n");
				Descricao desc = getCaseDescription(i);				
				Solucao sol = getSolucaoCaso(i);				
				System.out.println("\n\n ......Entrei no for  Ates de Setar\n\n");
				String idString = (getCaso(i).getID().toString());
				System.out.println("\n\n ......Entrei no for  idString\n\n"+idString);
				int idInt = Integer.parseInt(idString);
				System.out.println("\n\n ......Entrei no for  idInt\n\n"+idInt);

				caso.setCaseId(idInt);
				caso.setNaturezaProblema(desc.getNaturezaProblema());
				caso.setTema(desc.getTema());
				caso.setTopico(desc.getTopico());
				caso.setEstiloDeAprendizagem(desc.getEstiloDeAprendizagem());
				caso.setDescricaoProblema(desc.getDescricaoProblema());

				System.out.println("\n\n ......Entrei no for  Antes de SolucaoID\n\n"+idInt);
				caso.setSolucaoId(idInt);
				System.out.println("\n\n ......Vai setar o Diagnostico\n\n");
				caso.setDiagnostico(sol.getDiagnostico());
				System.out.println("\n\n ......Diagnostico setado, setando Ação Pedagógica\n\n");
				
				caso.setAcaoPedagogica(sol.getAcaoPedagogica());


				caso.setObjetoDeAprendizagem (sol.getObjetoDeAprendizagem());

				System.out.println("\n\n ......OA setado. Entrei no for  Antes de Similaridade\n\n"+idInt);
				caso.setSimilaridade(getSimilaridade(i));
				System.out.println("\n\n ......Entrei no for Depois de Setar a similaridade \n\n");
			}
			System.out.println("\n\n ......Vou dar return\n\n");
			return caso;
			//}
		}
	
	public CBRCase getCaso(int i) {
		RetrievalResult recuperacao = casos.get(i);	
		//System.out.println(recuperacao);
		CBRCase _caso = recuperacao.get_case();
		//System.out.println(_caso);
		return _caso;
	}

	/*
	 * Parte da Solucao do Problema  
	 */
	
	public String getDiagnostico(int _caso) {
		Solucao sol = getSolucaoCaso(_caso);
		return sol.getDiagnostico();
	}
	
	public String getObjetoDeAprendizagem(int _caso) {
		Solucao sol = getSolucaoCaso(_caso);
		return sol.getObjetoDeAprendizagem();
	}
	 
	public String getAcaoPedagogica(int _caso) {
		Solucao sol = getSolucaoCaso(_caso);
		return sol.getAcaoPedagogica();
	}


	
	/*
	 * Parte da Decrição do Problema  
	 */
	
	public String getNaturezaProblema(int _caso) {
		Descricao desc  = getCaseDescription(_caso);
		return desc.getNaturezaProblema();
	}
	
	public String getTema(int _caso) {
		Descricao desc = getCaseDescription(_caso);
		return desc.getTema();
	}
	
	public String getTopico(int _caso) {
		Descricao desc  = getCaseDescription(_caso);
		return desc.getTopico();
	}
	
	public String getEstiloDeAprendizagem(int _caso) {
		Descricao desc  = getCaseDescription(_caso);
		return desc.getEstiloDeAprendizagem();
	}
	
	public String getDescricaoProblema(int _caso) {
		Descricao desc  = getCaseDescription(_caso);
		return desc.getDescricaoProblema();
	}

	/*** Fim da Descrição do Problema ***/

	public Descricao getCaseDescription(int _casoNumero) {
		CBRCase _case = getCaso(_casoNumero);
		return (Descricao) _case.getDescription();
	}
	
	public Solucao getSolucaoCaso(int _casoNumero) {
		CBRCase _case = getCaso(_casoNumero);
		return (Solucao) _case.getSolution();
	}
	
	public double getSimilaridade(int _caso) {
		RetrievalResult rr = casos.get(_caso);
		return rr.getEval();
	}
}