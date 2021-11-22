package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import business.Articulo;
import business.Articulo.ArticleState;
import business.Autor;
import business.Comentario;
import business.Revisor;

public class DataBaseManager {
	private static String URL = "jdbc:hsqldb:hsql://localhost";
	private static String USER = "sa";
	private static String PASSWORD = "";

	public static void InsertReviewComment(String revisor, String comentario, String recomendacion, String idArticulo,
			String code) {

		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "SELECT * FROM revisores WHERE nombre=(?);";
			String sql2 = "INSERT INTO ComentariosRevisor (COMENTARIO, RECOMENDACION,idCOMENTARIOREVISOR,idREVISOR,idArticulo,TYPE) VALUES (?,?,?,?,?,?)";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, revisor);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
				preparedStatement2.setString(1, comentario);
				preparedStatement2.setString(2, recomendacion);
				preparedStatement2.setInt(3, UUID.randomUUID().hashCode());
				preparedStatement2.setString(4, rs.getString("idREVISOR"));
				preparedStatement2.setString(5, idArticulo);
				preparedStatement2.setString(6, code);
				int row = preparedStatement2.executeUpdate();
				System.out.println(row); // 1
			}
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static List<Articulo> SelectAllArticlesForRevisor(String revisor) {
		List<Articulo> ret = new LinkedList<Articulo>();
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "SELECT * FROM revisores WHERE nombre=(?);";
			String sql2 = "SELECT * FROM articles WHERE (idREVISOR1=(?) OR idREVISOR2=(?) OR idREVISOR3=(?)) "
					+ "AND (state ='WITH_EDITOR' OR state ='IN_REVISION');";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, revisor);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
				preparedStatement2.setString(1, rs.getString("idREVISOR"));
				preparedStatement2.setString(2, rs.getString("idREVISOR"));
				preparedStatement2.setString(3, rs.getString("idREVISOR"));
				ResultSet rs2 = preparedStatement2.executeQuery();
				while (rs2.next()) {
					List<Revisor> revisores = new LinkedList<>();
					revisores.add(new Revisor(rs2.getString("idREVISOR1")));
					revisores.add(new Revisor(rs2.getString("idREVISOR2")));
					revisores.add(new Revisor(rs2.getString("idREVISOR3")));
					Articulo aux = new Articulo(rs2.getString("id_articles"), rs2.getString("title"),
							new Autor(rs2.getString("author")), authorsToList(rs2.getString("other_authors")),
							rs2.getString("summary"), toList(rs2.getString("keywords")),
							rs2.getString("presentation_card"), rs2.getString("srcfile"),
							toList(rs2.getString("cv_authors")), toArticleState(rs2.getString("state")));
					aux.setRevisores(revisores);
					ret.add(aux);
				}
			}
		}

		catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
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

	public static void removeArticleFromCandidates(Articulo articulo, String revisor) {
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "SELECT * FROM articles WHERE id_articles=(?);";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, articulo.getId());
			ResultSet rs = preparedStatement.executeQuery();
			String sql3 = "SELECT * FROM revisores WHERE nombre=(?);";
			PreparedStatement preparedStatement3 = conn.prepareStatement(sql3);
			preparedStatement3.setString(1, revisor);
			ResultSet rs3 = preparedStatement3.executeQuery();
			while (rs3.next()) {
				revisor = rs3.getString("idREVISOR");
			}
			while (rs.next()) {
				if (articulo.getListOfRevisoresParaRevisar().get(0).getId().equals(revisor)) {
					String sql2 = "UPDATE ARTICLES SET idREVISOR1=(NULL) WHERE id_articles=(?) ;";
					PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
					preparedStatement2.setString(1, articulo.getId());
					preparedStatement2.execute();
				} else if (articulo.getListOfRevisoresParaRevisar().get(1).getId().equals(revisor)) {
					String sql2 = "UPDATE ARTICLES SET idREVISOR2=(NULL) WHERE id_articles=(?) ;";
					PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
					preparedStatement2.setString(1, articulo.getId());
					preparedStatement2.execute();
				} else if (articulo.getListOfRevisoresParaRevisar().get(2).getId().equals(revisor)) {
					String sql2 = "UPDATE ARTICLES SET idREVISOR3=(NULL) WHERE id_articles=(?) ;";
					PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
					preparedStatement2.setString(1, articulo.getId());
					preparedStatement2.execute();
				}
			}
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<Articulo> SelectAllArticlesForPendingRevisor(String revisor) {
		List<Articulo> ret = new LinkedList<Articulo>();
		ret = SelectAllArticlesForRevisor(revisor);
		for (Articulo articulo : ret) {
			if (!articulo.getState().equals(ArticleState.WITH_EDITOR.toString())) {
				ret.remove(articulo);
			}
		}

		return ret;
	}

	public static List<Comentario> SelectComentsVisibleForRevisor(String revisor) {
		List<Articulo> articulos = SelectAllArticlesForRevisor(revisor);
		List<Comentario> comentarios = new LinkedList<Comentario>();
		String sql = "SELECT * FROM COMENTARIOSREVISOR  WHERE idArticulo=(?) AND TYPE = 'Decision propuesta';";
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			for (Articulo articulo : articulos) {
				preparedStatement.setString(1, articulo.getId());
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					Comentario c = new Comentario(rs.getInt("idCOMENTARIOREVISOR"), rs.getString("Comentario"),
							rs.getString("idRevisor"), rs.getString("recomendacion"), rs.getString("idArticulo"),
							rs.getString("TYPE"));
					comentarios.add(c);
				}
			}
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comentarios;
	}

	public static String getNombreRevisor(String id) {
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "SELECT * FROM revisores WHERE idREVISOR =(?);";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				return rs.getString("nombre");
			}
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<Comentario> SelectComentsVisibleForEditor(String id) {
		List<Comentario> comentarios = new LinkedList<Comentario>();
		String sql = "SELECT * FROM COMENTARIOSREVISOR WHERE idArticulo=(?) AND (TYPE = 'Decision propuesta' OR TYPE='Notas para editor');";
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Comentario c = new Comentario(rs.getInt("idCOMENTARIOREVISOR"), rs.getString("Comentario"),
						rs.getString("idRevisor"), rs.getString("recomendacion"), rs.getString("idArticulo"),
						rs.getString("TYPE"));
				comentarios.add(c);
			}
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comentarios;
	}

	@SuppressWarnings("unused")
	private static List<Articulo> SelectAllArticles() {
		List<Articulo> ret = new LinkedList<Articulo>();
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "SELECT * FROM articles";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				List<Revisor> revisores = new LinkedList<>();
				revisores.add(new Revisor(rs.getString("idREVISOR1")));
				revisores.add(new Revisor(rs.getString("idREVISOR2")));
				revisores.add(new Revisor(rs.getString("idREVISOR3")));
				Articulo aux = new Articulo(rs.getString("id_articles"), rs.getString("title"),
						new Autor(rs.getString("author")), authorsToList(rs.getString("other_authors")),
						rs.getString("summary"), toList(rs.getString("keywords")), rs.getString("presentation_card"),
						rs.getString("srcfile"), toList(rs.getString("cv_authors")),
						toArticleState(rs.getString("state")));
				aux.setRevisores(revisores);
				ret.add(aux);
			}
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static Articulo getArticleFromId(String idArticulo) {
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "SELECT * FROM articles WHERE id_articles = (?)";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, idArticulo);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				List<Revisor> revisores = new LinkedList<>();
				revisores.add(new Revisor(rs.getString("idREVISOR1")));
				revisores.add(new Revisor(rs.getString("idREVISOR2")));
				revisores.add(new Revisor(rs.getString("idREVISOR3")));
				Articulo aux = new Articulo(rs.getString("id_articles"), rs.getString("title"),
						new Autor(rs.getString("author")), authorsToList(rs.getString("other_authors")),
						rs.getString("summary"), toList(rs.getString("keywords")), rs.getString("presentation_card"),
						rs.getString("srcfile"), toList(rs.getString("cv_authors")),
						toArticleState(rs.getString("state")));
				aux.setRevisores(revisores);
				return aux;
			}
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}