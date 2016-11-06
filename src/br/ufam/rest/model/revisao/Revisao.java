package br.ufam.rest.model.revisao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import br.ufam.rest.model.Descricao;
import br.ufam.rest.model.Solucao;

import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.method.retrieve.RetrievalResult;

/*
 * Classe que implementa a etapa de Revisão ou Adapatcacao 
 * 
 * */
public class Revisao {
	
	private Collection<CBRCase> casosSelecionados;
	private List<CBRCase> casos;
	CBRQuery query;
	
	
	public Revisao(Collection<CBRCase> _casosSelecionados, CBRQuery _query) {
		casosSelecionados = _casosSelecionados;
		casos = new ArrayList<CBRCase>(casosSelecionados);
		query = _query;
	}
	
	
	public void executarRevisao() 
	{	    	    
		    String diagnostico = "diagnostico";
		    String acaoPedagogica = "diagnostico";
		    String objetoDeAprendizagem = "diagnostico";
		    
		    

		    for(int i=0;i<casosSelecionados.size();i++) 
		    {
		    	CBRCase _case = casos.get(i);
			    //System.out.println(casos.get(i));
			    Descricao desc = (Descricao) query.getDescription();
			    
			    Descricao desc2 = (Descricao) _case.getDescription();
			
			    salvarCaso(0,desc2.getCaseId(),
			    		desc.getNaturezaProblema(),						
						desc.getTema(),
						desc.getDescricaoProblema(),
						desc.getTopico(),
						desc.getEstiloDeAprendizagem(),
						diagnostico,
						objetoDeAprendizagem,
						acaoPedagogica
				);
			}
			
	}
	
	public void executarRevisao2(Solucao solucaoArg) 
	{
		    	    
		    String diagnostico = solucaoArg.getDiagnostico();
		    String acaoPedagogica = solucaoArg.getAcaoPedagogica();
		    String objetoDeAprendizagem = solucaoArg.getObjetoDeAprendizagem();
		    

		    for(int i=0;i<casosSelecionados.size();i++) {
		    	CBRCase _case = casos.get(i);
			    //System.out.println(casos.get(i));
			    Descricao desc = (Descricao) query.getDescription();
			    
			    Descricao desc2 = (Descricao) _case.getDescription();
			
			    salvarCaso(0,desc2.getCaseId(),
			    		desc.getNaturezaProblema(),						
						desc.getTema(),
						desc.getDescricaoProblema(),
						desc.getTopico(),
						desc.getEstiloDeAprendizagem(),
						diagnostico,
						objetoDeAprendizagem,
						acaoPedagogica
				);
				
			}
			
	}

	
	
	public void printCasosRevisado() {
		for(int i=0;i<this.casosSelecionados.size();i++) {
			Descricao desc = getCaseDescription(i);//descricao do caso passado por parametro
			//System.out.println("Descricao Caso: "+desc);
			Solucao sol = getSolucaoCaso(i);
			String informacoes = "                             <<< ETAPA DE REVISÃO >>> \n\n O caso foi Revisado, e sera Retido na base de casos: \n"+
					";\n Tema: "+desc.getTema()+
					";\n Topico: "+desc.getTopico()+
					";\n Estilo de Aprendizagem: "+desc.getEstiloDeAprendizagem()+
					";\n Descrição do problema: "+desc.getDescricaoProblema()+
					";\n Natureza do problema: "+desc.getNaturezaProblema()+		
					" \n \n  ------ Solucao Empregada ------- \n"+
					"\nDiagnostico: "+sol.getDiagnostico()+
					//"\n"+"Solucao: "+sol.getSolucao()+
				//	"\n"+"Palavras chaves solucao : "+sol.getPalavrasChavesSolucao()+
					"\n"+"Ação Pedagógica: "+sol.getAcaoPedagogica()+
					"\n"+"Objeto de Aprendizagem: "+sol.getObjetoDeAprendizagem();
			JOptionPane.showMessageDialog(null, informacoes);
			
		}
	}
	
	
		
	public void salvarCaso(int _caseNumber,String _caseId, String _tema, String _topico, String _estiloDeAprendizagem, String _descricaoProblema,
			String _naturezaProblema,String _diagnostico, String _acaoPedagogica, String objetoDeAprendizagem) {
			
			CBRCase _caso = casos.get(_caseNumber);
			//System.out.println("CASO: "  +_caso);
			Descricao desc = (Descricao) _caso.getDescription();//descrição do caso recuperado
			//System.out.println("CASOS RECUPERADO: "  +desc);
			
			desc.setCaseId(_caseId);
			desc.setTema(_tema);
			desc.setTopico(_topico);
			desc.setEstiloDeAprendizagem(_estiloDeAprendizagem);
			desc.setDescricaoProblema(_descricaoProblema);
			desc.setNaturezaProblema(_naturezaProblema);
			
			Solucao sol = (Solucao) _caso.getSolution();
			sol.setDiagnostico(_diagnostico);
			sol.setAcaoPedagogica(_acaoPedagogica);
			sol.setObjetoDeAprendizagem(objetoDeAprendizagem);
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
	
	public CBRCase getCase(int i) {
		CBRCase _caso = casos.get(i);
		return _caso;
	}
	
	public Descricao getCaseDescription(int _caseNumber) {
		CBRCase _caso = getCase(_caseNumber);
		return (Descricao) _caso.getDescription();
	}
	
	public Solucao getSolucaoCaso(int _caseNumber) {
		CBRCase _caso = getCase(_caseNumber);
		return (Solucao) _caso.getSolution();
	}
	
	

	
}
