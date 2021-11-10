package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

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

			StringBuilder query = new StringBuilder();

			query.append("insert into recomendaciones_revisores VALUES (?,?");

			for (Revisor r : revisores) {
				query.append(",?");
			}
			query.append(")");

			PreparedStatement st = con.prepareStatement(query.toString());

			st.setString(1, UUID.randomUUID().toString());
			st.setString(2, a.getId());

			int param = 3;
			for (Revisor r : revisores) {
				st.setString(param, r.getId());
				param++;
			}
			st.close();
			con.close();

		} catch (

		SQLException e) {
			e.printStackTrace();
		}
	}

}
