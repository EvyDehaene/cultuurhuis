package be.vdab.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;

import be.vdab.entities.Genre;

public class GenreDAO extends AbstractDAO {
	private static final String FIND_ALL_SQL = "select genrenr, naam from genres order by genrenr";
	private static final String READ_SQL = "select genrenr, naam from genres where naam = ?";
	private static final String CREATE_SQL = "insert into genres(naam) values(?)";
	
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
	
	public Genre read (String naam) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(READ_SQL)) {
			Genre genre = null;
			statement.setString(1, naam);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					genre=resultSetRijNaarGenre(resultSet);
				}
				return genre;
			}
		} catch (SQLException ex) {
			throw new DAOException("Kan genre niet lezen uit database", ex);
		}
	}
	
	public void create(Genre genre) {
		try (Connection connection=getConnection();
				PreparedStatement statement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS)){
			statement.setString(1, genre.getNaam());
			statement.executeUpdate();
			try (ResultSet resultSet = statement.getGeneratedKeys()) {
				if (!resultSet.next()) {
					throw new DAOException("Kan nummer toegevoegd genre niet lezen uit database");
				}
				genre.setGenreNr(resultSet.getInt(1));
			}
		}
		catch (SQLException ex) {
			throw new DAOException("Kan genres niet toevoegen aan database", ex);
		}
	}
	
	
//	public Genre resultSetRijNaarGenre(ResultSet resultSet) {
//		throws SQLException {
//			
//		}
//	}
	
	
}
