package business;

import java.util.List;

public class Revisor {
	
	private String id;
	private String nombre;
	private List<Tema> listOfTemas;
	private int tiempoDeRevision;

	public Revisor(String id) {
		this.id = id;
	}
	
	public Revisor(List<Tema> temas, int tiempoDeRevision, String nombre, String id) {
		this.listOfTemas = temas;
		this.tiempoDeRevision = tiempoDeRevision;
		this.id = id;
		this.nombre = nombre;
	}

	public List<Tema> getListOfTemas() {
		return listOfTemas;
	}

	public int getTiempoDeRevision() {
		return tiempoDeRevision;
	}

	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return "Nombre:" + nombre + " tiempo de revision : " + tiempoDeRevision;
	}

}
