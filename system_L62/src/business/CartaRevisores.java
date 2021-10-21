package business;

import java.util.List;

import persistence.DataBaseRevisor;

public class CartaRevisores {
	
	DataBaseRevisor bd = new DataBaseRevisor();
	
	public List<Revisor>getRevisoresPorTema(String tema) {
		return bd.getRevisores(tema);
	}
}
