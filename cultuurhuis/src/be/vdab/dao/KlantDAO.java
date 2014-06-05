package be.vdab.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import be.vdab.entities.Klant;

public class KlantDAO extends AbstractDAO {
	private static final String FIND_CLIENT_SQL = "select KlantNr, Voornaam, Familienaam, Straat, HuisNr, Postcode, Gemeente, GebruikersNaam, Paswoord"
			+ " from klanten where Gebruikersnaam = ? AND Paswoord = ?";
	private static final String ADD_CLIENT_SQL= "insert into klanten(Voornaam, Familienaam, Straat, HuisNr, Postcode, Gemeente, GebruikersNaam"
			+ ", Paswoord) values (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SEARCH_CLIENT_SQL = "select KlantNr, Voornaam, Familienaam, Straat, HuisNr, Postcode, Gemeente, GebruikersNaam, Paswoord"
			+ " from klanten where Gebruikersnaam = ?";
	
	private Klant resultSetRijNaarKlant (ResultSet resultSet) throws SQLException {
		return new Klant(resultSet.getInt("KlantNr"), resultSet.getString("Voornaam"), resultSet.getString("Familienaam"), 
							resultSet.getString("Straat"), resultSet.getString("HuisNr"), resultSet.getString("Postcode"), 
							resultSet.getString("Gemeente"), resultSet.getString("Gebruikersnaam"), resultSet.getString("Paswoord"));
	}
	
	public Klant getBestaandeKlant (String gebruikersnaam, String paswoord) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_CLIENT_SQL);) {
			Klant bestaandeKlant = null;
			statement.setString(1, gebruikersnaam);
			statement.setString(2, paswoord);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					bestaandeKlant = resultSetRijNaarKlant(resultSet);
				}
				return bestaandeKlant;
			}
		} catch (SQLException ex) {
			throw new DAOException("Kan de klant niet vinden in database", ex);
		}
	}
	
	public boolean gebruikersnaamBestaatAl(String gebruikersnaam) {
		try (Connection connection = getConnection();
			 PreparedStatement statement = connection.prepareStatement(SEARCH_CLIENT_SQL);){
			boolean bestaat=false;
			statement.setString(1, gebruikersnaam);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					bestaat=true;
				}
				return bestaat;
			}
		} catch (SQLException ex) {
			throw new DAOException ("Kan de gebruikersnaam niet vinden in de database", ex);
		}
	}
	
	public Klant klantToegevoegd (String voornaam, String familienaam, String straat, String huisNr, String postcode, String gemeente, String gebruikersnaam, String paswoord) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(ADD_CLIENT_SQL, Statement.RETURN_GENERATED_KEYS)) {
				 int klantNr=0;
				 Klant nieuweKlant = null;
				 statement.setString(1, voornaam);
				 statement.setString(2, familienaam);
				 statement.setString(3, straat);
				 statement.setString(4, huisNr);
				 statement.setString(5, postcode);
				 statement.setString(6, gemeente);
				 statement.setString(7, gebruikersnaam);
				 statement.setString(8, paswoord);
				 statement.executeUpdate();
				 try (ResultSet resultSet = statement.getGeneratedKeys()) {
					 if (!resultSet.next()) {
						 throw new DAOException("Kan nummer toegevoegde klant niet lezen uit database");
					 }
					 klantNr=resultSet.getInt(1);
					 nieuweKlant=new Klant(klantNr, voornaam, familienaam, straat, huisNr, postcode, gemeente, gebruikersnaam, paswoord);
				 }
				 return nieuweKlant;
			 } catch (SQLException ex) {
				 throw new DAOException("Kan klant niet toevoegen aan database", ex);
			 }
		
	}
}
