package business;

import java.util.ArrayList;
import java.util.List;

public class Articulo {
	
	public enum ArticleState{
		SENT, WITH_EDITOR, IN_REVISION, ACCEPTED, REJECTED, IN_EDITION, PUBLISHED
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
	
	private Tema tema;
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
		this.state = ArticleState.SENT;
	}
	
	public Articulo(String id, String title, Autor author, List<Autor> authors, String resumen, List<String> keywords, Tema tema) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.authors = authors;
		this.resumen = resumen;
		this.keywords = keywords;
		this.state = ArticleState.SENT;
		this.tema = tema;
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
	}

	public Articulo(String title, Autor author, String resumen, List<String> keywords, String srcFile) {
		this.title = title;
		this.author = author;
		this.resumen = resumen;
		this.keywords = keywords;
		this.srcFile = srcFile;
	}
	
	public Articulo(Tema tema,String id) {
		this.tema = tema;
		this.id=id;
		this.listOfRevisoresParaRevisar=new ArrayList<Revisor>();
	}

	public String getId() {
		return id;
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

	public List<String> getCvAuthors() {
		return new ArrayList<>(cvAuthors);
	}

	public void setCvAuthors(List<String> cvAuthors) {
		this.cvAuthors = cvAuthors;
	}
	
	public String getState() {
		return "" + state;
	}

	public String listAuthors() {
		String str = "";
		int i = 0;
		for(Autor author: authors) {
			str += author.getName();
			if(i != authors.size() - 1) {
				str += ",";
			}
			i++;
		}
		return str;
	}
	
	public String listKeywords() {
		String str = "";
		int i = 0;
		for(String keyword: keywords) {
			str += keyword;
			if(i != keywords.size() - 1) {
				str += ",";
			}
			i++;
		}
		return str;
	}
	
	public String listCVAuthors() {
		String str = "";
		int i = 0;
		for(String cv: cvAuthors) {
			str += cv;
			if(i != cvAuthors.size() - 1) {
				str += ",";
			}
			i++;
		}
		return str;
	}
	
	public boolean isComplete() {
		if(this.title == null) {
			return false;
		}
		if(this.author == null) {
			return false;
		}
		if(this.resumen == null) {
			return false;
		}
		if(this.srcFile == null) {
			return false;
		}
		if(this.presentationCard == null) {
			return false;
		}
		if(cvAuthors.size() == authors.size() + 1) {
			return false;
		}
		return true;
	}
	
	public void añadirRevisor(Revisor revisor) {
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

	public void setCarta(Carta carta) {
		this.carta = carta;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setState(ArticleState state) {
		this.state = state;
	}

	public void setRevisores(List<Revisor> revisores) {
		this.listOfRevisoresParaRevisar = revisores;
		revisoresRestantes -= revisores.size();
	}
	
	
}
