package be.vdab.entities;

import java.math.BigDecimal;
import java.util.Date;

public class Voorstelling {
	private int voorstellingsNr;
	private String titel;
	private Date datum;
	private int genreNr;
	private BigDecimal prijs;
	private int vrijePlaatsen;
	
	public int getVoorstellingsNr() {
		return voorstellingsNr;
	}
	public void setVoorstellingsNr(int voorstellingsNr) {
		this.voorstellingsNr = voorstellingsNr;
	}
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public int getGenreNr() {
		return genreNr;
	}
	public void setGenreNr(int genreNr) {
		this.genreNr = genreNr;
	}
	public BigDecimal getPrijs() {
		return prijs;
	}
	public void setPrijs(BigDecimal prijs) {
		this.prijs = prijs;
	}
	public int getVrijePlaatsen() {
		return vrijePlaatsen;
	}
	public void setVrijePlaatsen(int vrijePlaatsen) {
		this.vrijePlaatsen = vrijePlaatsen;
	}

}
