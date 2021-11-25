package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.Revisor;
import business.Tema;

public class DataBaseRevisor {
	private static String URL;
	private static String USER;
	private static String PASSWORD;
	private Connection con;

	public DataBaseRevisor() {
		URL = "jdbc:hsqldb:hsql://localhost";
		USER = "sa";
		PASSWORD = "";
		con = null;
	}

	/**
	 * Devuelve una lista con todos los revisores almacenados
	 * 
	 * @return lista de revisores
	 */
	public List<Revisor> getRevisores() {
		List<Revisor> listOfRevisores = new ArrayList<>();
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			String queryRevisores = "select * from revisores";
			String queryTemas = "select * from temas";
			String queryRevisa = "";

			Statement st = con.createStatement();
			ResultSet rsRevisores = st.executeQuery(queryRevisores);
			ResultSet rsTemas = st.executeQuery(queryTemas);
			ResultSet rsRevisa = null;

			Map<Integer, Tema> listOfTemas = new HashMap<>();
			List<Tema> temasPorRevisor = new ArrayList<>();

			while (rsTemas.next()) {
				listOfTemas.put(rsTemas.getInt("id"), new Tema(rsTemas.getString("nombre")));
			}

			while (rsRevisores.next()) {
				queryRevisa = "select * from revisa where id_revisor = '" + rsRevisores.getInt("idrevisor") + "'";
				rsRevisa = st.executeQuery(queryRevisa);
				temasPorRevisor.clear();
				while (rsRevisa.next()) {
					temasPorRevisor.add(listOfTemas.get(rsRevisa.getInt("id_tema")));
				}
				listOfRevisores.add(new Revisor(new ArrayList<>(temasPorRevisor), rsRevisores.getInt("tiempo"),
						rsRevisores.getString("nombre"), rsRevisores.getString("idrevisor")));
			}

			rsRevisa.close();
			rsTemas.close();
			rsRevisores.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfRevisores;
	}

	public List<Revisor> getAllRevisores() {
		List<Revisor> listOfRevisores = new ArrayList<>();
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			StringBuilder query = new StringBuilder();
			Statement st = con.createStatement();
			query.append(
					"SELECT revisores.idrevisor, revisores.nombre nombre,revisores.tiempo tiempo, temas.nombre tema from revisores "
							+ "inner join revisa on (revisa.id_revisor=revisores.idrevisor) inner join temas on (revisa.id_tema=temas.id)");

			ResultSet rs = st.executeQuery(query.toString());

			List<Tema> listOfTemasPorRevisor = new ArrayList<Tema>();

			int idAnterior = -10000;
			while (rs.next()) {

				if (rs.getInt("idrevisor") == idAnterior) {
					listOfTemasPorRevisor.add(new Tema(rs.getString("tema")));

				} else {
					listOfRevisores.add(new Revisor(listOfTemasPorRevisor, rs.getInt("tiempo"), rs.getString("nombre"),
							rs.getString("idrevisor")));
					listOfTemasPorRevisor.clear();
				}
				idAnterior = rs.getInt("idrevisor");

			}
			rs.close();
			con.close();
			return listOfRevisores;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfRevisores;
	}

	public List<Revisor> getRevisores(String tema) {

		List<Revisor> listOfRevisores = new ArrayList<>();
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			StringBuilder query = new StringBuilder();
			Statement st = con.createStatement();
			query.append(
					"SELECT revisores.idrevisor, revisores.nombre nombre,revisores.tiempo tiempo, temas.nombre tema from revisores "
							+ "inner join revisa on (revisa.id_revisor=revisores.idrevisor) inner join temas on (revisa.id_tema=temas.id) where temas.nombre=  "
							+ "'" + tema + "'" + " order by revisores.idrevisor ");

			ResultSet rs = st.executeQuery(query.toString());

			List<Tema> listOfTemasPorRevisor = new ArrayList<Tema>();

			int idAnterior = -10000;
			while (rs.next()) {

				if (rs.getInt("idrevisor") == idAnterior) {
					listOfTemasPorRevisor.add(new Tema(rs.getString("tema")));

				} else {
					listOfRevisores.add(new Revisor(listOfTemasPorRevisor, rs.getInt("tiempo"), rs.getString("nombre"),
							rs.getString("idrevisor")));
					listOfTemasPorRevisor.clear();
				}
				idAnterior = rs.getInt("idrevisor");

			}
			rs.close();
			con.close();
			return listOfRevisores;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfRevisores;

	}

}