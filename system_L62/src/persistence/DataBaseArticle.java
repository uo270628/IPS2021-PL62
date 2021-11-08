package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import business.Articulo;
import business.Articulo.ArticleState;
import business.Autor;

public class DataBaseArticle {
	private static String URL = "jdbc:hsqldb:hsql://localhost";
	private static String USER = "sa";

	/**
	 * Sube un artículo a la base de datos
	 * 
	 * @param article
	 * @return si se subió el artículo
	 */
	public static boolean uploadArticle(Articulo article) {
		String queryInsertArticle = "insert into articles (id_articles, title, author, other_authors, summary, keywords, srcfile, presentation_card, cv_authors, state) values ('"
				+ article.getId() + "', '" + article.getTitle() + "', '" + article.getAuthor().getName() + "', '"
				+ article.listAuthors() + "', '" + article.getResumen() + "', '" + article.listKeywords() + "', '"
				+ article.getSrcFile() + "', '" + article.getPresentationCard() + "', '" + article.listCVAuthors()
				+ "', '" + article.getState() + "')";

		Connection conn = null;
		Statement st = null;

		boolean result;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			st = conn.createStatement();

			st.executeUpdate(queryInsertArticle);
			result = true;
		} catch (SQLException e) {
			result = false;
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		return result;
	}

	/**
	 * Busca y devuelve un artículo con un id dado de la base de datos
	 * 
	 * @param id
	 * @return artículo con el id dado
	 */
	public static Articulo searchArticle(String id) {
		String querySearchArticle = "select * from articles where id_articles = '" + id + "'";

		Articulo article = null;

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			st = conn.createStatement();

			rs = st.executeQuery(querySearchArticle);

			if (rs.next()) {
				article = new Articulo(rs.getString("id_articles"), rs.getString("title"),
						new Autor(rs.getString("author")), authorsToList(rs.getString("other_authors")),
						rs.getString("summary"), toList(rs.getString("keywords")), rs.getString("presentation_card"),
						rs.getString("srcfile"), toList(rs.getString("cv_authors")),
						toArticleState(rs.getString("state")));
			}
		} catch (SQLException e) {
			article = null;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		return article;
	}

	/**
	 * Envía un artículo creado para ser evaluado
	 * 
	 * @param id
	 * @return si se envió el artículo
	 */
	public static boolean sendArticleToAprove(String id) {
		String queryUpdateArticle = "update articles set state = '" + Articulo.ArticleState.SENT
				+ "' where id_articles = '" + id + "'";

		Connection conn = null;
		Statement st = null;

		boolean result;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			st = conn.createStatement();

			st.executeUpdate(queryUpdateArticle);
			result = true;
		} catch (SQLException e) {
			result = false;
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		return result;
	}

	/**
	 * Envía un artículo al editor para que decida publicarlo
	 * 
	 * @param id
	 * @return si se publica el artículo o no
	 */
	public static boolean publishArticle(String id) {
		String queryPublishArticle = "update articles set state = '" + Articulo.ArticleState.IN_EDITION
				+ "' where id_articles = '" + id + "'";

		Connection conn = null;
		Statement st = null;

		boolean result;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			st = conn.createStatement();

			st.executeUpdate(queryPublishArticle);
			result = true;
		} catch (SQLException e) {
			result = false;
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		return result;
	}

	/**
	 * Modifica los valores de los campos de un artículo dado en la base de datos
	 * 
	 * @param article
	 * @return si se actualizó el artículo
	 */
	public static boolean updateArticle(Articulo article) {
		String queryUpdateArticle = "update articles set";

		int elements = 0;

		if (article.getTitle() != null) {
			queryUpdateArticle += " title = '" + article.getTitle() + "'";
			elements++;
		}
		if (article.getAuthor() != null) {
			if (elements > 0)
				queryUpdateArticle += ",";
			queryUpdateArticle += " author = '" + article.getAuthor().getName() + "'";
			elements++;
		}
		if (article.getAuthors().size() != 0) {
			if (elements > 0)
				queryUpdateArticle += ",";
			queryUpdateArticle += " other_authors = '" + article.listAuthors() + "'";
			elements++;
		}
		if (article.getResumen() != null) {
			if (elements > 0)
				queryUpdateArticle += ",";
			queryUpdateArticle += " summary = '" + article.getResumen() + "'";
			elements++;
		}
		if (article.getKeywords().size() != 0) {
			if (elements > 0)
				queryUpdateArticle += ",";
			queryUpdateArticle += " keywords = '" + article.listKeywords() + "'";
			elements++;
		}
		if (article.getSrcFile() != null) {
			if (elements > 0)
				queryUpdateArticle += ",";
			queryUpdateArticle += " srcfile = '" + article.getSrcFile() + "'";
			elements++;
		}
		if (article.getPresentationCard() != null) {
			if (elements > 0)
				queryUpdateArticle += ",";
			queryUpdateArticle += " presentation_card = '" + article.getPresentationCard() + "'";
			elements++;
		}
		if (article.getCvAuthors().size() != 0) {
			if (elements > 0)
				queryUpdateArticle += ",";
			queryUpdateArticle += " cv_authors = '" + article.listCVAuthors() + "'";
			elements++;
		}

		if (elements > 0)
			queryUpdateArticle += ",";
		queryUpdateArticle += " state = '" + article.getState() + "'";

		queryUpdateArticle += " where id_articles = '" + article.getId() + "'";

		Connection conn = null;
		Statement st = null;

		boolean result;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			st = conn.createStatement();

			st.executeUpdate(queryUpdateArticle);
			result = true;
		} catch (SQLException e) {
			result = false;
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		return result;
	}

	/**
	 * Devuelve los artículos asociados a un determinado autor junto con sus datos
	 * 
	 * @param author author de los artículos
	 * @return lista de artículos asociados al autor
	 */
	public static List<Articulo> findArticlesByAuthor(Autor author) {
		List<Articulo> listOfArticulos = new ArrayList<>();
		List<Articulo> listOfArticulosFiltrados = new ArrayList<>();

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			StringBuilder query = new StringBuilder();
			st = conn.createStatement();
			query.append(
					"select id_articles, title, author, other_authors, summary, keywords, srcfile, presentation_card, cv_authors, state from articles");

			rs = st.executeQuery(query.toString());

			while (rs.next()) {
				listOfArticulos.add(new Articulo(rs.getString("id_articles"), rs.getString("title"),
						new Autor(rs.getString("author")), authorsToList(rs.getString("other_authors")),
						rs.getString("summary"), toList(rs.getString("keywords")), rs.getString("presentation_card"),
						rs.getString("srcfile"), toList(rs.getString("cv_authors")),
						toArticleState(rs.getString("state"))));
			}
			rs.close();
			conn.close();
		} catch (SQLException e) {
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		for (Articulo a : listOfArticulos) {
			if (a.getAuthor().getName().equals(author.getName()) || a.getAuthors().contains(author)) {
				listOfArticulosFiltrados.add(a);
			}
		}
		return listOfArticulosFiltrados;
	}

	public static List<Articulo> loadArticles() {
		List<Articulo> listOfArticulos = new ArrayList<>();

		Connection con;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			StringBuilder query = new StringBuilder();
			Statement st = con.createStatement();
			query.append("select title, author, summary, keywords, srcfile, state from articles");

			ResultSet rs = st.executeQuery(query.toString());

			while (rs.next()) {
				listOfArticulos.add(new Articulo(rs.getString("title"), new Autor(rs.getString("author")),
						rs.getString("summary"), toList(rs.getString("keywords")), rs.getString("srcfile"), rs.getString("state")));
			}
			rs.close();
			con.close();
			return listOfArticulos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfArticulos;
	}
	

	/**
	 * Transforma una cadena de texto en una lista de cadenas de texto que estaban
	 * separadas por comas
	 * 
	 * @param str
	 * @return lista de cadenas de texto
	 */
	private static List<String> toList(String str) {
		List<String> list = new LinkedList<>();
		if (str == null) {
			return list;
		}
		String[] strs = str.split(",");
		for (int i = 0; i < strs.length; i++) {
			if (!strs[i].isEmpty())
				list.add(strs[i]);
		}
		return list;
	}

	/**
	 * Transforma una cadena de texto en una lista de autores que estaban separados
	 * por comas
	 * 
	 * @param str
	 * @return lista de autores
	 */
	private static List<Autor> authorsToList(String str) {
		List<Autor> list = new LinkedList<>();
		if (str == null) {
			return list;
		}
		String[] strs = str.split(",");
		for (int i = 0; i < strs.length; i++) {
			if (!strs[i].isEmpty())
				list.add(new Autor(strs[i]));
		}
		return list;
	}

	/**
	 * Transforma una cadena de texto en un estado de artículo
	 * 
	 * @param state
	 * @return estado del artículo
	 */
	private static ArticleState toArticleState(String state) {
		switch (state) {
		case "CREATED":
			return ArticleState.CREATED;
		case "SENT":
			return ArticleState.SENT;
		case "WITH_EDITOR":
			return ArticleState.WITH_EDITOR;
		case "IN_REVISION":
			return ArticleState.IN_REVISION;
		case "ACCEPTED":
			return ArticleState.ACCEPTED;
		case "ACCEPTED_WITH_CHANGES":
			return ArticleState.ACCEPTED_WITH_CHANGES;
		case "REJECTED":
			return ArticleState.REJECTED;
		case "IN_EDITION":
			return ArticleState.IN_EDITION;
		case "PUBLISHED":
			return ArticleState.PUBLISHED;
		default:
			return null;
		}
	}

	private static String PASSWORD = "";
}
