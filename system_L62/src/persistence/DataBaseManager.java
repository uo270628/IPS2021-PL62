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
import business.Autor;
import business.Revisor;
import business.Articulo.ArticleState;

public class DataBaseManager {
	private static String URL = "jdbc:hsqldb:hsql://localhost";
	private static String USER = "sa";
	private static String PASSWORD = "";

	public static void InsertReviewComment(String revisor, String comentario, String recomendacion,String idArticulo) {

		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "SELECT * FROM revisores WHERE nombre=(?);";
			String sql2 = "INSERT INTO ComentariosRevisor (COMENTARIO, RECOMENDACION,idCOMENTARIOREVISOR,idREVISOR,idArticulo) VALUES (?,?,?,?,?)";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, revisor.toUpperCase());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
				preparedStatement2.setString(1, comentario);
				preparedStatement2.setString(2, recomendacion);
				preparedStatement2.setInt(3, UUID.randomUUID().hashCode());
				preparedStatement2.setString(4, rs.getString("idREVISOR"));
				preparedStatement2.setString(5, idArticulo);
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
			String sql2 = "SELECT * FROM articles WHERE idREVISOR1=(?) OR idREVISOR2=(?) OR idREVISOR3=(?);";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, revisor.toUpperCase());
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
					Articulo aux = new Articulo(rs2.getString("id"), rs2.getString("title"),
							new Autor(rs2.getString("author")), authorsToList(rs2.getString("other_authors")), rs2.getString("summary"),
							toList(rs2.getString("keywords")), rs2.getString("presentation_card"), rs2.getString("srcfile"),
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
}