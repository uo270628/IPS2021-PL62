package business;

public class Comentario {
	private int id;
	private String texto;
	private String idRevisor;
	private String recomendacion;
	private String idArticulo;

	public Comentario(String texto) {
		this.texto = texto;
	}

	public Comentario(int id, String texto, String idRevisor, String recomendacion) {
		this.id = id;
		this.texto = texto;
		this.idRevisor = idRevisor;
		this.recomendacion = recomendacion;
	}

	public Comentario(int id, String texto, String idRevisor, String recomendacion, String idArticulo) {
		this.id = id;
		this.texto = texto;
		this.idRevisor = idRevisor;
		this.recomendacion = recomendacion;
		this.idArticulo = idArticulo;
	}
	
	public String getTexto() {
		return texto;
	}

	public int getId() {
		return id;
	}

	public String getIdRevisor() {
		return idRevisor;
	}

	public String getRecomendacion() {
		return recomendacion;
	}
	
	public String getIdArticulo() {
		return idArticulo;
	}
}
