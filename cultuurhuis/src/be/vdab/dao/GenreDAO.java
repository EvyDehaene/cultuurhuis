package be.vdab.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import be.vdab.entities.Genre;

public class GenreDAO extends AbstractDAO {
	private static final String FIND_ALL_SQL = "select GenreNr, Naam from genres order by Naam";
	
	
	public Iterable<Genre> findAll() {
		try (Connection connection = getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(FIND_ALL_SQL);) {
			List<Genre> genres=new ArrayList();
			while (resultSet.next()) {
				genres.add(resultSetRijNaarGenre(resultSet));
			}
			return genres;
		} catch(SQLException ex) {
			throw new DAOException("Kan genres niet lezen uit database", ex);
		}
	}
	
	private Genre resultSetRijNaarGenre(ResultSet resultSet) throws SQLException {
		return new Genre (resultSet.getInt("GenreNr"), resultSet.getString("Naam"));
	}
	
	
}
