package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import business.Articulo;
import business.Revisor;

public class DatabaseRecomendacion {

	private static String URL;
	private static String USER;
	private static String PASSWORD;
	private Connection con;
	private Articulo a;

	public DatabaseRecomendacion(Articulo a) {
		this.a = a;
		URL = "jdbc:hsqldb:hsql://localhost";
		USER = "sa";
		PASSWORD = "";
		con = null;
	}

	public void insertRecommendations(List<Revisor> revisores) {
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			StringBuilder query;

			Statement st = con.createStatement();

			if (!revisores.isEmpty()) {
				for (Revisor r : revisores) {
					query = new StringBuilder();
					query.append("insert into recomendaciones_revisores (id_articulo, idREVISOR, nombre, tiempo) "
							+ "VALUES ('" + a.getId() + "','" + r.getId() + "','" + r.getNombre() + "'," + r.getTiempoDeRevision() + ")");

					st.executeUpdate(query.toString());
				}
			}

			st.close();
			con.close();

		} catch (

		SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Revisor> getRecomendaciones() {
		List<Revisor> revisores = new ArrayList<Revisor>();

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			StringBuilder query = new StringBuilder();

			Statement st = con.createStatement();

			query.append(
					"SELECT idREVISOR, nombre, tiempo from recomendaciones_revisores where id_articulo = " + a.getId());

			ResultSet rs = st.executeQuery(query.toString());

			while (rs.next()) {
				revisores.add(new Revisor(rs.getInt(1), rs.getInt(3), rs.getString(2)));
			}

			st.close();
			con.close();

		} catch (

		SQLException e) {
			e.printStackTrace();
		}

		return revisores;

	}

}
