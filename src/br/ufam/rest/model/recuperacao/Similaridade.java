package br.ufam.rest.model.recuperacao;


import br.ufam.rest.model.Descricao;
import jcolibri.cbrcore.Attribute;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;

import jcolibri.method.retrieve.NNretrieval.similarity.local.EqualsStringIgnoreCase;
import jcolibri.method.retrieve.NNretrieval.similarity.local.MaxString;

public class Similaridade {
	
	NNConfig config; //Definição da medida de similaridade NN (vizinho mais próximo)
	LocalSimilarityFunction function; //similaridade local
	Attribute atributo; 
	int k;
	
	public Similaridade() {
		this.config = new NNConfig();
		configure();
	}
	
		
	private void configure() {
		//configuração dos atributos e os pesos de cada atributo
		setAttributeConfig("tema", new Double(1)); 
		setAttributeConfig("topico", new Double(1));
		setAttributeConfig("estiloDeAprendizagem", new Double(1));
		setAttributeConfig("descricaoProblema", new Double(1));
		setAttributeConfig("naturezaProblema", new Double(1));
		
		
		config.setDescriptionSimFunction(new Average());
		
		this.k = 1; //definição dos K- Vizinhos
	}
	
		
	private void setAttributeConfig(String name,Double weight) {
		atributo = new Attribute(name,Descricao.class);
		function = new MaxString();
		config.addMapping(atributo, function);
		config.setWeight(atributo, weight);
	}
	
	public int getK() {
		return this.k;
	}
	
	public void setK(int _k) {
		this.k = _k;
	}
	
	public NNConfig getSimilarityConfig() {
		return this.config;
	}
}