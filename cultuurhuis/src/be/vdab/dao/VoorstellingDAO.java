package be.vdab.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import be.vdab.entities.Voorstelling;

public class VoorstellingDAO  extends AbstractDAO {
	
	private static final String READ_SQL = "select VoorstellingsNr, Titel, Uitvoerders, Datum, GenreNr, Prijs, VrijePlaatsen"
			+ " from voorstellingen where GenreNr = ?";
	private static final String READ_ALL_SQL = "select VoorstellingsNr, Titel, Uitvoerders, Datum, GenreNr, Prijs, VrijePlaatsen"
			+ " from voorstellingen";
	private static final String CREATE_SQL = "insert into voorstellingen (Titel, Uitvoerders, Datum, GenreNr, VrijePlaatsen)"
			+ " values (?, ?, ?, ?, ?)";
	private static final String FIND_BY_NUMBER_SQL = "select VoorstellingsNr, Titel, Uitvoerders, Datum, GenreNr, Prijs, VrijePlaatsen"
			+ " from voorstellingen where VoorstellingsNr = ?";
	//private static final String FIND_VRIJEPLAATSEN_SQL = "select VrijePlaatsen from voorstellingen where VoorstellingsNr= ?";
	
	private Voorstelling resultSetRijNaarVoorstelling (ResultSet resultSet) throws SQLException {
		return new Voorstelling(resultSet.getInt("VoorstellingsNr"), resultSet.getString("Titel"), resultSet.getString("Uitvoerders"),
				resultSet.getDate("Datum"), resultSet.getInt("GenreNr"), resultSet.getBigDecimal("Prijs"), resultSet.getInt("VrijePlaatsen"));
	}
	
	public int getVrijePlaatsen(int voorstellingsNr) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_NUMBER_SQL);) {
			int vrijePlaatsen = 0;
			statement.setInt(1, voorstellingsNr);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					vrijePlaatsen = resultSet.getInt("VrijePlaatsen");
				}
				return vrijePlaatsen;
			}
		} catch (SQLException ex) {
			throw new DAOException("Kan het aantal vrije plaatsen niet lezen uit de database", ex);
		}
	}
	
	public Iterable<Voorstelling> findAll(){
		try (Connection connection = getConnection();
			 Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery(READ_ALL_SQL);) {
			List<Voorstelling> voorstellingen = new ArrayList<>();
			while (resultSet.next()) {
				voorstellingen.add(resultSetRijNaarVoorstelling(resultSet));
			}
			return voorstellingen;
		} catch (SQLException ex) {
			throw new DAOException("Kan voorstellingen niet lezen uit database", ex);
		}
	}
	
	public Voorstelling findByNumber (int voorstellingsNr) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_NUMBER_SQL);) {
			Voorstelling voorstelling = null;
			statement.setInt(1, voorstellingsNr);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
				voorstelling= resultSetRijNaarVoorstelling(resultSet);
				}
				return voorstelling;
			}
		} catch (SQLException ex) {
			throw new DAOException("Kan voorstelling niet lezen uit de database.", ex);
		}
	}
	
	public  Iterable<Voorstelling> findByGenre (int genreNr) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(READ_SQL);) {
			List<Voorstelling> voorstellingen = new ArrayList<>();
			statement.setInt(1, genreNr);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					voorstellingen.add(resultSetRijNaarVoorstelling(resultSet));
				}
				return voorstellingen;
			}
		} catch (SQLException ex) {
			throw new DAOException("Kan voorstellingen niet lezen uit de database.", ex);
		}
	}
	
	public void create (Voorstelling voorstelling) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, voorstelling.getTitel());
			statement.setString(2, voorstelling.getUitvoerders());
			statement.setDate(3, (Date)voorstelling.getDatum());
			statement.setInt(4, voorstelling.getGenreNr());
			statement.setBigDecimal(5, voorstelling.getPrijs());
			statement.setInt(6, voorstelling.getVrijePlaatsen());
			statement.executeUpdate();
			try (ResultSet resultSet = statement.getGeneratedKeys()){
				if (!resultSet.next()){
					throw new DAOException("Kan nummer toegevoegde voorstelling niet lezen uit de database.");
				}
				voorstelling.setVoorstellingsNr(resultSet.getInt(1));
			}
		} catch (SQLException ex) {
			throw new DAOException("Kan voorstellingen niet toevoegen aan de database.", ex);
		}
	}
}
