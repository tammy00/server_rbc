package br.ufam.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Caso {

	int caseId;
	String naturezaProblema; 	
	String tema; 
	String topico;
	String estiloDeAprendizagem;
	String descricaoProblema;
	
	int solucaoId;
	String diagnostico;
	String acaoPedagogica;
	String objetoDeAprendizagem;

	Double similaridade;

	public int getCaseId() {
		return caseId;
	}
	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}

	public int getSolucaoId() {
		return solucaoId;
	}
	public void setSolucaoId(int solucaoId) {
		this.solucaoId = solucaoId;
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
	public void setTopico (String topico) {
		this.topico = topico;
	}


	public String getNaturezaProblema() {
		return naturezaProblema;
	}
	public void setNaturezaProblema(String naturezaProblema) {
		this.naturezaProblema = naturezaProblema;
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




	public String getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}


	public String getObjetoDeAprendizagem() {
		return objetoDeAprendizagem;
	}
	public void setObjetoDeAprendizagem(String objetoDeAprendizagem) {
		this.objetoDeAprendizagem = objetoDeAprendizagem;
	}


	public String getAcaoPedagogica() {
		return acaoPedagogica;
	}
	public void setAcaoPedagogica(String acaoPedagogica) {
		this.acaoPedagogica = acaoPedagogica;
	}

	
	public Double getSimilaridade() {
		return similaridade;
	}
	public void setSimilaridade(Double similaridade) {
		this.similaridade = similaridade;
	}
	

}
