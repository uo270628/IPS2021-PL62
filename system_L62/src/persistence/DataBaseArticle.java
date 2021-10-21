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
import business.Autor;
import business.Articulo.ArticleState;

public class DataBaseArticle {
	private static String URL = "jdbc:hsqldb:hsql://localhost";
	private static String USER = "sa";

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

			result = st.execute(queryInsertArticle);
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
				article = new Articulo(rs.getString("id"), rs.getString("title"), new Autor(rs.getString("author")),
						authorsToList(rs.getString("other_authors")), rs.getString("summary"),
						toList(rs.getString("keywords")), rs.getString("srcfile"), rs.getString("presentation_card"),
						toList(rs.getString("cv_authors")), toArticleState(rs.getString("state")));
			}
		} catch (SQLException e) {

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

	public static boolean sendArticleToAprove(String id) {
		String queryUpdateArticle = "update articles set state = '" + Articulo.ArticleState.WITH_EDITOR
				+ "' where id_articles = '" + id + "'";

		Connection conn = null;
		Statement st = null;

		boolean result;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			st = conn.createStatement();

			result = st.execute(queryUpdateArticle);
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

	public static boolean publishArticle(String id) {
		String queryPublishArticle = "update articles set state = '" + Articulo.ArticleState.IN_EDITION
				+ "' where id_articles = '" + id + "'";

		Connection conn = null;
		Statement st = null;

		boolean result;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			st = conn.createStatement();

			result = st.execute(queryPublishArticle);
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
			queryUpdateArticle += " authors = '" + article.listAuthors() + "'";
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

			result = st.execute(queryUpdateArticle);
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

	public static List<Articulo> loadArticles() {
		List<Articulo> listOfArticulos = new ArrayList<>();
		
		Connection con;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			StringBuilder query = new StringBuilder();
			Statement st = con.createStatement();
			query.append(
					"select title, author, summary, keywords, srcfile from articles");

			ResultSet rs = st.executeQuery(query.toString());

			while (rs.next()) {
				listOfArticulos.add(new Articulo(
						rs.getString("title"), 
						new Autor(rs.getString("author")),
								rs.getString("summary"),
								toList(rs.getString("keywords")),
								rs.getString("srcfile")));
			}
			rs.close();
			con.close();
			return listOfArticulos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfArticulos;
	}

	private static List<String> toList(String str) {
		List<String> list = new LinkedList<>();
		if (str == null) {
			return list;
		}
		String[] strs = str.split(",");
		for (int i = 0; i < strs.length; i++) {
			list.add(strs[i]);
		}
		return list;
	}

	private static List<Autor> authorsToList(String str) {
		List<Autor> list = new LinkedList<>();
		if (str == null) {
			return list;
		}
		String[] strs = str.split(",");
		for (int i = 0; i < strs.length; i++) {
			list.add(new Autor(strs[i]));
		}
		return list;
	}

	private static ArticleState toArticleState(String state) {
		switch (state) {
		case "SENT":
			return ArticleState.SENT;
		case "WITH_EDITOR":
			return ArticleState.WITH_EDITOR;
		case "IN_REVISION":
			return ArticleState.IN_REVISION;
		case "ACCEPTED":
			return ArticleState.ACCEPTED;
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
