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
		return "Nombre: " + nombre + ", tiempo de revision: " + tiempoDeRevision;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Revisor other = (Revisor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
