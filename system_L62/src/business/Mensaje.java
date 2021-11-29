package business;

public class Mensaje {
	private int idMensaje;
	private String idArticulo;
	private String texto;
	@Override
	public String toString() {
		return redactor + "-" + texto;
	}
	public Mensaje(int idMensaje, String idArticulo, String texto, String redactor) {
		super();
		this.idMensaje = idMensaje;
		this.idArticulo = idArticulo;
		this.texto = texto;
		this.redactor = redactor;
	}
	public int getIdMensaje() {
		return idMensaje;
	}
	public void setIdMensaje(int idMensaje) {
		this.idMensaje = idMensaje;
	}
	public String getidArticulo() {
		return idArticulo;
	}
	public void setidArticulo(String idDebate) {
		this.idArticulo = idDebate;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getRedactor() {
		return redactor;
	}
	public void setRedactor(String redactor) {
		this.redactor = redactor;
	}
	private String redactor;
}
