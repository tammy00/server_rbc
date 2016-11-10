package br.ufam.rest.model;


import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;

/**
 * Classe que descreve o problema do caso (os atributos)
 */


public class Descricao implements CaseComponent{


	int caseId;
	String tema; 
	String topico; 
	String estiloDeAprendizagem; 
	String descricaoProblema; 
	String naturezaProblema; 
		
	/*
	 * Exemplo de valores para os atributos
	 * tema: Estrutura Condicional
	 * topico: Estrutura Condicional Composta
	 * estiloDeAprendizagem: Visual
	 * descricaoProblema:  nao sabe como empregar
	 * naturezaProblema:  logica
	 * */
	
	public Attribute getIdAttribute() {
		return new Attribute("caseId",this.getClass());
	}


	public int getCaseId() {
		return caseId;
	}

	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}


	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String getTopico() {
		return topico;
	}

	public void setTopico(String topico) {
		this.topico = topico;
	}

	public String getEstiloDeAprendizagem() {
		return estiloDeAprendizagem;
	}

	public void setEstiloDeAprendizagem(String estiloDeAprendizagem) {
		this.estiloDeAprendizagem = estiloDeAprendizagem;
	}

	public String getDescricaoProblema() {
	    return descricaoProblema;
	}

	public void setDescricaoProblema(String descricaoProblema) {
	    this.descricaoProblema = descricaoProblema;
	}

	public String getNaturezaProblema() {
	    return naturezaProblema;
	}

	public void setNaturezaProblema(String naturezaProblema) {
	    this.naturezaProblema = naturezaProblema;
	}
	
}

