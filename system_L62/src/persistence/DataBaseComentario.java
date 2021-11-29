package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import business.Articulo;
import business.Comentario;

public class DataBaseComentario {
	private static String URL = "jdbc:hsqldb:hsql://localhost";
	private static String USER = "sa";
	private static String PASSWORD = "";
	private static Connection con = null;

	public DataBaseComentario() {
		URL = "jdbc:hsqldb:hsql://localhost";
		USER = "sa";
		PASSWORD = "";
		con = null;
	}

	public static List<Comentario> getComentariosDeUnArticulo(Articulo articulo) {
		List<Comentario> listOfComentarios = new ArrayList<>();
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			StringBuilder query = new StringBuilder();
			Statement st = con.createStatement();

			query.append(
					"select IDCOMENTARIOREVISOR,IDREVISOR,IDARTICULO,COMENTARIO,RECOMENDACION,TYPE from comentariosrevisor");
			ResultSet rs = st.executeQuery(query.toString());
			while (rs.next()) {
				if (rs.getString("IDARTICULO").equals(articulo.getId())) {
					listOfComentarios.add(new Comentario(rs.getInt("IDCOMENTARIOREVISOR"), rs.getString("COMENTARIO"),
							rs.getString("IDREVISOR"), rs.getString("RECOMENDACION"), rs.getString("IDARTICULO"),
							rs.getString("TYPE")));
				}
			}
			rs.close();
			con.close();
			return listOfComentarios;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfComentarios;
	}

	public static void enviarCartaAlAutor(Articulo articulo) {
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			StringBuilder query = new StringBuilder();
			query.append("insert into carta (idcarta,idarticulo,texto) values(?,?,?)");
			PreparedStatement ps = con.prepareStatement(query.toString());
			ps.setInt(1, (int) (Math.random() * 100 + 1));
			ps.setString(2, articulo.getId());
			ps.setString(3, articulo.getCarta());
			ps.execute();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void enviarComentariosAlAutor(Articulo articulo) {
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			StringBuilder query = new StringBuilder();

			List<Comentario> list = articulo.getComentarios();
			for (Comentario comentario : list) {
				query.append("INSERT INTO ComentariosRevisor "
						+ "(IDCOMENTARIOREVISOR, IDARTICULO, COMENTARIO, RECOMENDACION ,TYPE) VALUES (?,?,?,?,?)");

				PreparedStatement ps = con.prepareStatement(query.toString());
				ps.setInt(1, comentario.getId());
				ps.setString(2, comentario.getIdArticulo());
				ps.setString(3, comentario.getTexto());
				ps.setString(4, comentario.getRecomendacion());
				ps.setString(5, comentario.getType());
				ps.execute();
				ps.close();

			}
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
