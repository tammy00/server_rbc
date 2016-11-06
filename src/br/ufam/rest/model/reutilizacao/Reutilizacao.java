package br.ufam.rest.model.reutilizacao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JOptionPane;

import br.ufam.rest.model.Descricao;
import br.ufam.rest.model.Solucao;

import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRQuery;

/*
 * Classe que implementa a etapa de Reutilização ou Adaptação 
 * 
 * */

public class Reutilizacao {
	
	
	CBRQuery query;
	Collection<CBRCase> casosSelecionados;
	
	public Reutilizacao(CBRQuery _query, Collection<CBRCase> _casosSelecionados) {		
		
		query = _query;
		casosSelecionados = _casosSelecionados;
		
	}
		
	public void executaAdapatacaoDoCaso() {
		List<CBRCase> casos = new ArrayList<CBRCase>(casosSelecionados);
		for(int i=0;i<casosSelecionados.size();i++) {
			CBRCase _case = casos.get(i);
			Descricao desc = (Descricao) query.getDescription();
			Solucao sol = (Solucao) _case.getSolution();
						/*
			String informacoes = "                <<< ETAPA DE ADAPTACAO >>> \n\n\n O Novo Caso foi adaptado veja os dados abaixo: \n"+ 
					";\n Tema: "+desc.getNaturezaProblema()+
					";\n Topico: "+desc.getDateCreated()+
					";\n Estilo de Aprendizagem: "+desc.getRelator()+
					//";\n Descricao do proglema: "+desc.getTurmaId()+
					//";\n Natureza do problema: "+desc.getCursoId()+
					//";\n Natureza do problema: "+desc.getDisciplinaId()+
					//";\n Natureza do problema: "+desc.getQtdeAlunos()+
					";\n Natureza do problema: "+desc.getDescricaoProblema()+
					";\n Natureza do problema: "+desc.getProblema()+
					";\n Natureza do problema: "+desc.getPalavrasChavesProblema()+			
					" \n \n  ------ Solucao Empregada ------- \n"+
					"\nDiagnostico: "+sol.getDiagnostico()+
					"\n"+"Acao Pedagogica: "+sol.getSolucao()+
					"\n"+"Objeto de Aprendizagem: "+sol.getPalavrasChavesSolucao()+
					"\n"+"AcaoImplementada: "+sol.getAcaoImplementada()+
					"\n"+"Efetividade: "+sol.getEfetividade()+
					//"\n"+"Objeto de Aprendizagem: "+sol.getSolucaoImplementada()+
					"\n"+"Objeto de Aprendizagem: "+sol.getCustos()+
					//"\n"+"Objeto de Aprendizagem: "+sol.getImpactoPedagogico()+		
					"\n"+"Objeto de Aprendizagem: "+sol.getAtoresEnvolvidos();
					
					JOptionPane.showMessageDialog(null, informacoes);*/
		}
	}
		

	
	
	public CBRQuery getQuery() {
		return query;
	}
	
	public Collection<CBRCase> getCases() {
		return casosSelecionados;
	}
	
	public int getK() {
		return this.casosSelecionados.size();
	}
	
}
