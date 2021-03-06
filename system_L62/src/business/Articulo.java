package business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Articulo {
	public enum ArticleState {
		CREATED, SENT, WITH_EDITOR, IN_REVISION, ACCEPTED, ACCEPTED_WITH_MINOR_CHANGES, ACCEPTED_WITH_GREATER_CHANGES,
		REJECTED, IN_EDITION, PUBLISHED
	}

	public enum ArticleVersion {
		NEW, GREATER_CHANGES, MINOR_CHANGES, FINAL
	}

	private String id;
	private String title;
	private Autor author;
	private List<Autor> authors;
	private String resumen;
	private List<String> keywords;
	private String presentationCard;
	private String srcFile;
	private List<String> cvAuthors;
	private ArticleState state;
	private int tiempoMaximoRevision;
	private String doi;
	private String volumen;
	private Date fechaPublicacion;
	private Tema tema;
	private ArticleVersion version;

	private List<Revisor> listOfRevisoresParaRevisar;

	private int revisoresRestantes = 3;
	private Carta carta;
	private List<Comentario> comentarios;

	public Articulo(String id, String title, Autor author, List<Autor> authors, String resumen, List<String> keywords) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.authors = authors;
		this.resumen = resumen;
		this.keywords = keywords;
		this.state = ArticleState.CREATED;
		this.version = ArticleVersion.NEW;
		this.listOfRevisoresParaRevisar = new ArrayList<Revisor>();

		
	}

	public Articulo(String id, String title, Autor author, List<Autor> authors, String resumen, List<String> keywords,
			Tema tema) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.authors = authors;
		this.resumen = resumen;
		this.keywords = keywords;
		this.state = ArticleState.SENT;
		this.version = ArticleVersion.NEW;
		this.tema = tema;
		this.listOfRevisoresParaRevisar = new ArrayList<Revisor>();
	}

	public Articulo(String id, String title, Autor author, List<Autor> authors, String resumen, List<String> keywords,
			List<String> cvAuthors, Tema tema) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.authors = authors;
		this.resumen = resumen;
		this.keywords = keywords;
		this.state = ArticleState.SENT;
		this.version = ArticleVersion.NEW;
		this.tema = tema;
		this.cvAuthors = cvAuthors;
		this.listOfRevisoresParaRevisar = new ArrayList<Revisor>();
		this.comentarios = new ArrayList<Comentario>();
		
	}

	public Articulo(String id, String title, Autor author, List<Autor> authors, String resumen, List<String> keywords,
			String presentationCard, String srcFile, List<String> cvAuthors, ArticleState state) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.authors = authors;
		this.resumen = resumen;
		this.keywords = keywords;
		this.presentationCard = presentationCard;
		this.srcFile = srcFile;
		this.cvAuthors = cvAuthors;
		this.state = state;
		this.version = ArticleVersion.NEW;
		this.comentarios = new ArrayList<Comentario>();
		this.listOfRevisoresParaRevisar = new ArrayList<Revisor>();
		


	}

	public Articulo(String title, Autor author, String resumen, List<String> keywords, String srcFile, String state) {
		this.title = title;
		this.author = author;
		this.resumen = resumen;
		this.keywords = keywords;
		this.srcFile = srcFile;
		setEstado(state);
		this.version = ArticleVersion.NEW;
		this.comentarios = new ArrayList<Comentario>();
		this.authors=new ArrayList<>();
		this.listOfRevisoresParaRevisar = new ArrayList<Revisor>();


	}

	private void setEstado(String state) {
		switch (state) {
		case "CREATED":
			this.state = ArticleState.CREATED;
			break;
		case "SENT":
			this.state = ArticleState.SENT;
			break;
		case "WITH_EDITOR":
			this.state = ArticleState.WITH_EDITOR;
			break;
		case "IN_REVISION":
			this.state = ArticleState.IN_REVISION;
			break;
		case "ACCEPTED":
			this.state = ArticleState.ACCEPTED;
			break;
		case "ACCEPTED_WITH_MINOR_CHANGES":
			this.state = ArticleState.ACCEPTED_WITH_MINOR_CHANGES;
			break;
		case "ACCEPTED_WITH_GREATER_CHANGES":
			this.state = ArticleState.ACCEPTED_WITH_GREATER_CHANGES;
			break;
		case "REJECTED":
			this.state = ArticleState.REJECTED;
			break;
		case "IN_EDITION":
			this.state = ArticleState.IN_EDITION;
			break;
		case "PUBLISHED":
			this.state = ArticleState.PUBLISHED;
			break;
		}

	}

	public Articulo(Tema tema, String id) {
		this.tema = tema;
		this.id = id;
		this.listOfRevisoresParaRevisar = new ArrayList<Revisor>();
		this.comentarios = new ArrayList<>();
		this.comentarios = new ArrayList<Comentario>();

	}

	public String toStringAuthor() {
		return title + " - " + state.toString();
	}

	public String getId() {
		return id;
	}

	public Articulo(String id, String title, Autor author, List<Autor> authors, String resumen, List<String> keywords,
			String presentationCard, String srcFile, List<String> cvAuthors, ArticleState state, String Tema) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.authors = authors;
		this.resumen = resumen;
		this.keywords = keywords;
		this.presentationCard = presentationCard;
		this.srcFile = srcFile;
		this.cvAuthors = cvAuthors;
		this.state = state;
		this.tema = new Tema(Tema);
		this.listOfRevisoresParaRevisar = new ArrayList<Revisor>();
		this.comentarios = new ArrayList<Comentario>();

	}

	public String getTitle() {
		return title;
	}

	public Autor getAuthor() {
		return author;
	}

	public List<Autor> getAuthors() {
		return new ArrayList<>(authors);
	}

	public String getResumen() {
		return resumen;
	}

	@Override
    public String toString() {
    return "T?tulo: " + title + ". Autor: " + author + ". Estado: " + state.toString();
    }

	public List<String> getKeywords() {
		return new ArrayList<>(keywords);
	}

	public String getPresentationCard() {
		return presentationCard;
	}

	public void setPresentationCard(String presentationCard) {
		this.presentationCard = presentationCard;
	}

	public String getSrcFile() {
		return srcFile;
	}

	public void setSrcFile(String srcFile) {
		this.srcFile = srcFile;
	}

	public void addComentario(Comentario comentario) {
		comentarios.add(comentario);
	}

	public List<String> getCvAuthors() {
		return new ArrayList<>(cvAuthors);
	}

	public void setCvAuthors(List<String> cvAuthors) {
		this.cvAuthors = cvAuthors;
	}

	public String getState() {
		return "" + state;
	}

	public String getVersion() {
		return "" + version;
	}

	public String listAuthors() {
		String str = "";
		int i = 0;
		for (Autor author : authors) {
			str += author.getName();
			if (i != authors.size() - 1) {
				str += ",";
			}
			i++;
		}
		return str;
	}

	public String listKeywords() {
		String str = "";
		int i = 0;
		for (String keyword : keywords) {
			str += keyword;
			if (i != keywords.size() - 1) {
				str += ",";
			}
			i++;
		}
		return str;
	}

	public String listCVAuthors() {
		String str = "";
		int i = 0;
		for (String cv : cvAuthors) {
			str += cv;
			if (i != cvAuthors.size() - 1) {
				str += ",";
			}
			i++;
		}
		return str;
	}

	public boolean isComplete() {
		if (this.title == null || this.title.isEmpty()) {
			return false;
		}
		if (this.author == null || this.author.getName().isEmpty()) {
			return false;
		}
		if (this.resumen == null || this.resumen.isEmpty()) {
			return false;
		}
		if (this.srcFile == null || this.srcFile.isEmpty()) {
			return false;
		}
		if (this.presentationCard == null || this.presentationCard.isEmpty()) {
			return false;
		}
		if (cvAuthors.size() != authors.size() + 1) {
			return false;
		}
		return true;
	}

	public void a?adirRevisor(Revisor revisor) {
		revisoresRestantes--;
		listOfRevisoresParaRevisar.add(revisor);
	}

	public void eliminarRevisor(Revisor revisor) {
		revisoresRestantes++;
		listOfRevisoresParaRevisar.remove(revisor);
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = new Tema(tema);
	}

	public void siguiente() {
		state = ArticleState.IN_REVISION;
	}

	public List<Revisor> getListOfRevisoresParaRevisar() {
		return listOfRevisoresParaRevisar;
	}

	public int getRevisoresRestantes() {
		return revisoresRestantes;
	}

	public void aceptarArticulo() {
		state = ArticleState.ACCEPTED;
	}

	public void rechazarArticulo() {
		state = ArticleState.REJECTED;
	}

	public String getCarta() {
		return carta.getTexto();
	}
	public Carta getCartaObject() {
		return carta;
	}

	public void setCarta(Carta carta) {
		this.carta = carta;
	}

	public int getTiempoMaximoRevision() {
		return tiempoMaximoRevision;
	}

	public void setTiempoMaximoRevision(int tiempoMaximoRevision) {
		this.tiempoMaximoRevision = tiempoMaximoRevision;
	}

	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public String getVolumen() {
		return volumen;
	}

	public void setVolumen(String volumen) {
		this.volumen = volumen;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setState(ArticleState state) {
		this.state = state;
	}

	public void setVersion(ArticleVersion version) {
		this.version = version;
	}

	public void setRevisores(List<Revisor> revisores) {
		this.listOfRevisoresParaRevisar = revisores;
		revisoresRestantes -= revisores.size();
	}

	public boolean canBeEditable() {
		return state == ArticleState.CREATED || changesNeeded();
	}

	public boolean changesNeeded() {
		return state == ArticleState.ACCEPTED_WITH_GREATER_CHANGES || state == ArticleState.ACCEPTED_WITH_MINOR_CHANGES;
	}

	public boolean isAccepted() {
		return state == ArticleState.ACCEPTED;
	}


	public boolean isRevised() {
		return state == ArticleState.ACCEPTED || state == ArticleState.ACCEPTED_WITH_GREATER_CHANGES
				|| state == ArticleState.ACCEPTED_WITH_MINOR_CHANGES || state == ArticleState.REJECTED;
	}

	public boolean hasBeenRevised() {
		return version != ArticleVersion.NEW || isRevised();
	}
	public void cambiarComentario(String texto,	Comentario com) {
		for (Comentario comentario : comentarios) {
			if(comentario.getId()==com.getId())
				comentario.setTexto(texto);
		}
	}
	public List<Comentario> getComentariosDeUnRevisor(Revisor revisor) {
		List<Comentario>list = new ArrayList<Comentario>();
		for (Comentario comentario : comentarios) {
			if(comentario.getIdRevisor().equals(revisor.getId()))
				list.add(comentario);
		}
		return list;
	}
	
	public ArticleState getStateEnum() {
		return state;
	}


}