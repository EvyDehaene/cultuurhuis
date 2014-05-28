package be.vdab.entities;

import java.math.BigDecimal;
import java.util.Date;

public class Voorstelling {
	private int voorstellingsNr;
	private String titel;
	private String uitvoerders;
	private Date datum;
	private int genreNr;
	private BigDecimal prijs;
	private int vrijePlaatsen;
	
	public Voorstelling (int voorstellingsNr, String titel, String uitvoerders, Date datum, int genreNr, BigDecimal prijs, int vrijePlaatsen) {
		setVoorstellingsNr(voorstellingsNr);
		setTitel(titel);
		setUitvoerders(uitvoerders);
		setDatum(datum);
		setGenreNr(genreNr);
		setPrijs(prijs);
		setVrijePlaatsen(vrijePlaatsen);
	}
	
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
	public String getUitvoerders() {
		return uitvoerders;
	}

	public void setUitvoerders(String uitvoerders) {
		this.uitvoerders = uitvoerders;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datum == null) ? 0 : datum.hashCode());
		result = prime * result + genreNr;
		result = prime * result + ((prijs == null) ? 0 : prijs.hashCode());
		result = prime * result + ((titel == null) ? 0 : titel.hashCode());
		result = prime * result
				+ ((uitvoerders == null) ? 0 : uitvoerders.hashCode());
		result = prime * result + voorstellingsNr;
		result = prime * result + vrijePlaatsen;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voorstelling other = (Voorstelling) obj;
		if (datum == null) {
			if (other.datum != null)
				return false;
		} else if (!datum.equals(other.datum))
			return false;
		if (genreNr != other.genreNr)
			return false;
		if (prijs == null) {
			if (other.prijs != null)
				return false;
		} else if (!prijs.equals(other.prijs))
			return false;
		if (titel == null) {
			if (other.titel != null)
				return false;
		} else if (!titel.equals(other.titel))
			return false;
		if (uitvoerders == null) {
			if (other.uitvoerders != null)
				return false;
		} else if (!uitvoerders.equals(other.uitvoerders))
			return false;
		if (voorstellingsNr != other.voorstellingsNr)
			return false;
		if (vrijePlaatsen != other.vrijePlaatsen)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Voorstelling [voorstellingsNr=" + voorstellingsNr + ", titel="
				+ titel + ", uitvoerders=" + uitvoerders + ", datum=" + datum
				+ ", genreNr=" + genreNr + ", prijs=" + prijs
				+ ", vrijePlaatsen=" + vrijePlaatsen + "]";
	}
	

}
