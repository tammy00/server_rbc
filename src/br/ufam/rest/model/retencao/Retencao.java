package br.ufam.rest.model.retencao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.ufam.rest.model.Descricao;
import br.ufam.rest.model.Solucao;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBase;


/*
 * Classe que implementa a etapa de Retenção
 * 
 * */
public class Retencao {
	
	Collection<CBRCase> casosSelecionados;
	CBRCaseBase casoBase;
	List<CBRCase> casos, casosRetidos;
	int casoBaseSize;
	
	public Retencao(Collection<CBRCase> _casos_selecionados, CBRCaseBase _casoBase) {
		casosSelecionados = _casos_selecionados;
		casos = new ArrayList<CBRCase>(casosSelecionados);
		//System.out.println(casos);
		casosRetidos = new ArrayList<CBRCase>();
		//System.out.println(casosRetidos);
		casoBase = _casoBase;
		casoBaseSize = casoBase.getCases().size();//ultimo caso
		System.out.println("Número de casos "+casoBaseSize);
	}
	
	public void addCasoRetido(int _caseNumber) {
		CBRCase _caso = casos.get(_caseNumber);		
		System.out.println("ETAPA RETENÇÃO");
		casos.remove(_caso);	
		
		// desc é um objeto do tipo Descricao e recebe a descricao do caso recuperado e escolhido para ser inserido como novo caso		
		Descricao desc = (Descricao) _caso.getDescription();
		// no método setCaseId, o atributo caseId é setado para o id_do_ultimo_caso+1
		desc.setCaseId(++casoBaseSize);
		System.out.println("desc"+desc);
		
		// sol é um objeto do tipo Solucao e recebe a solucao do caso recuperado e escolhido para ser inserido como nova solucao do novo caso		
		Solucao sol = (Solucao) _caso.getSolution();
		// no método setCaseId, o atributo solucaoId é setado para o atual valor de casoBaseSize( = id_do_ultimo_caso+1)
		sol.setSolucaoId(casoBaseSize);
		System.out.println("sol"+sol);
		casosRetidos.add(_caso);
	}
	
	public void learn() {
		if(casoBaseSize > 0)
			casoBase.learnCases(casosRetidos);
	}
	
	

	/*
	 * Parte da Solucao do Problema  
	 */
	
	public String getDiagnostico(int _case) {
		Solucao sol = getSolucaoCaso(_case);
		return sol.getDiagnostico();
	}
	
	public String getAcaoPedagocica(int _case) {
		Solucao sol = getSolucaoCaso(_case);
		return sol.getAcaoPedagogica();
	}
	
	public String getObjetoDeAprendizagem(int _case) {
		Solucao sol = getSolucaoCaso(_case);
		return sol.getObjetoDeAprendizagem();
	}

	
	/*
	 * Parte da Decrição do Problema  
	 */
	
	public String getTema(int _case) {
		Descricao desc = getCaseDescription(_case);
		return desc.getTema();
	}
	
	public String getTopico(int _case) {
		Descricao desc  = getCaseDescription(_case);
		return desc.getTopico();
	}
	
	public String getEstiloDeAprendizagem(int _case) {
		Descricao desc  = getCaseDescription(_case);
		return desc.getEstiloDeAprendizagem();
	}
	
	public String getDescricaoProblema(int _case) {
		Descricao desc  = getCaseDescription(_case);
		return desc.getDescricaoProblema();
	}
	
	public String getNaturezaProblema(int _case) {
		Descricao desc  = getCaseDescription(_case);
		return desc.getNaturezaProblema();
	}

/** Fim da Descrição do Problema ***/
	
	public CBRCase getCase(int i) {
		CBRCase _case = casos.get(i);
		return _case;
	}
	
	public Descricao getCaseDescription(int _caseNumber) {
		CBRCase _case = getCase(_caseNumber);
		return (Descricao) _case.getDescription();
	}
	
	public Solucao getSolucaoCaso(int _caseNumber) {
		CBRCase _case = getCase(_caseNumber);
		return (Solucao) _case.getSolution();
	}
	
}
	

