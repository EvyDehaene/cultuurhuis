package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import be.vdab.entities.Voorstelling;

public class VoorstellingDAO  extends AbstractDAO {
	
	private static final String READ_SQL = "select VoorstellingsNr, Titel, Uitvoerders, Datum, GenreNr, Prijs, VrijePlaatsen"
			+ " from voorstellingen where GenreNr = ?";
	private static final String FIND_BY_NUMBER_SQL = "select VoorstellingsNr, Titel, Uitvoerders, Datum, GenreNr, Prijs, VrijePlaatsen"
			+ " from voorstellingen where VoorstellingsNr = ?";
	private static final String UPDATE_SQL = "update voorstellingen set VrijePlaatsen = VrijePlaatsen - ? where VoorstellingsNr = ?";
	private static final String CREATE_RESERVATIE_SQL ="insert into reservaties(KlantNr, VoorstellingsNr, Plaatsen) values (?, ?, ?)";
	
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
		
	public void update (int plaatsen, int voorstellingsNr) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_SQL);) {
			statement.setInt(1, plaatsen);
			statement.setInt(2, voorstellingsNr);
			statement.executeUpdate();
		} catch (SQLException ex) {
			throw new DAOException("Kan vrije plaatsen niet updaten in database", ex);
		}
	}
	
	public void createReservatie(int klantNr, int voorstellingsNr, int plaatsen) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(CREATE_RESERVATIE_SQL);) {
			statement.setInt(1,  klantNr);
			statement.setInt(2, voorstellingsNr);
			statement.setInt(3, plaatsen);
			statement.executeUpdate();
		} catch (SQLException ex) {
			throw new DAOException("Kan de reservatie niet toevoegen aan de database", ex);
		}
	}
}
