package br.ufam.rest.model.recuperacao;


import br.ufam.rest.model.Descricao;
import jcolibri.cbrcore.CBRQuery;

public class QueryConfig {
	
	private Descricao desc; 
	private CBRQuery query;
	
	public QueryConfig() {
		desc = new Descricao();
		query = new CBRQuery();
	}
	/*
	public void setQuery(String _naturezaProblema, String _relator, String _curso, String _disciplina,
			//String _turma,String _curso,String _disciplina, String _qtdeAlunos,
			String _descricaoProblema, String _problema, String _palavrasChavesProblema) {
	    
		desc.setNaturezaProblema(_naturezaProblema);
		//desc.setDateCreated(_dateCreated);
		desc.setRelator(_relator);
		//desc.setTurmaId(_turma);
		desc.setCursoId(_curso);
		desc.setDisciplinaId(_disciplina);
		//desc.setQtdeAlunos(_qtdeAlunos);
		desc.setDescricaoProblema(_descricaoProblema);
		desc.setProblema(_problema);
		desc.setPalavrasChavesProblema(_palavrasChavesProblema);
		//desc.setTipoCaso(_tipoCaso);
		
		query.setDescription(desc);
	}
	
	public void setQuery(String _naturezaProblema, String _relator, String _disciplina,
			//String _turma,String _curso,String _disciplina, String _qtdeAlunos,
			String _descricaoProblema, String _problema, String _palavrasChavesProblema, String _diagnostico,
			String _solucao, String _palavrasChavesSolucao,String _acaoImplementada,String _efetividade,
			String _custos, String _atoresEnvolvidos) {
	    
		desc.setNaturezaProblema(_naturezaProblema);
		//desc.setDateCreated(_dateCreated);
		desc.setRelator(_relator);
		//desc.setTurmaId(_turma);
		//desc.setCursoId(_curso);
		desc.setDisciplinaId(_disciplina);
		//desc.setQtdeAlunos(_qtdeAlunos);
		desc.setDescricaoProblema(_descricaoProblema);
		desc.setProblema(_problema);
		desc.setPalavrasChavesProblema(_palavrasChavesProblema);
		//desc.setTipoCaso(_tipoCaso);
		
		query.setDescription(desc);
	}  */

	public void setQuery(String _tema, String _topico, String _estiloDeAprendizagem, String descricaoProblema, String naturezaProblema) {
	    
	   	desc.setTema(_tema);
		desc.setTopico(_topico);
		desc.setEstiloDeAprendizagem(_estiloDeAprendizagem);
		desc.setDescricaoProblema(descricaoProblema);
		desc.setNaturezaProblema(naturezaProblema);
		
		query.setDescription(desc);
	}

	
	public CBRQuery getQuery(){
		return query;
	}

}