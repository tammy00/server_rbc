package br.ufam.rest.model;



import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;


public class Solucao implements CaseComponent{
	
	int solucaoId;
	String diagnostico;
	String acaoPedagogica;
	String objetoDeAprendizagem;
		
	/*
	 * Exemplo de valores para os atributos
	 * diagnostico: incompreenção de Estrutura Condicional Composta
	 * acaoPedagogica: o aluno devera assistir um video sobre estruturas condicionais
	 * objetoDeAprendizagem: Video Khan Academy
	 * 
	 * */
	
	public Attribute getIdAttribute() {
		return new Attribute("solucaoId",this.getClass());
	}


	public int getSolucaoId() {
		return solucaoId;
	}


	public void setSolucaoId(int solucaoId) {
		this.solucaoId = solucaoId;
	}


	public String getDiagnostico() {
		return diagnostico;
	}


	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	
	public String getAcaoPedagogica() {
		return acaoPedagogica;
	}

	public void setAcaoPedagogica(String acaoPedagogica) {
		this.acaoPedagogica = acaoPedagogica;
	}

	public String getObjetoDeAprendizagem() {
		return objetoDeAprendizagem;
	}

	public void setObjetoDeAprendizagem(String objetoDeAprendizagem) {
		this.objetoDeAprendizagem = objetoDeAprendizagem;
	}
	
	

}
