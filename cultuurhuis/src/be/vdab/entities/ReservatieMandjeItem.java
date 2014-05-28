package be.vdab.entities;

import java.math.BigDecimal;
import java.util.Date;

public class ReservatieMandjeItem {
	private int nummer;
	private Date datum;
	private String titel;
	private String uitvoerders;
	private BigDecimal prijs;
	private int plaatsen;
	
	public ReservatieMandjeItem(int aantalPlaatsen, Voorstelling voorstelling){
		super();
		setNummer(voorstelling.getVoorstellingsNr());
		setDatum(voorstelling.getDatum());
		setTitel(voorstelling.getTitel());
		setUitvoerders(voorstelling.getUitvoerders());
		setPrijs(voorstelling.getPrijs());
		setPlaatsen(aantalPlaatsen);
	}
	
	public int getNummer() {
		return nummer;
	}

	public void setNummer(int nummer) {
		this.nummer = nummer;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
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

	public BigDecimal getPrijs() {
		return prijs;
	}

	public void setPrijs(BigDecimal prijs) {
		this.prijs = prijs;
	}

	public int getPlaatsen() {
		return plaatsen;
	}

	public void setPlaatsen(int plaatsen) {
		this.plaatsen = plaatsen;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datum == null) ? 0 : datum.hashCode());
		result = prime * result + nummer;
		result = prime * result + plaatsen;
		result = prime * result + ((prijs == null) ? 0 : prijs.hashCode());
		result = prime * result + ((titel == null) ? 0 : titel.hashCode());
		result = prime * result
				+ ((uitvoerders == null) ? 0 : uitvoerders.hashCode());
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
		ReservatieMandjeItem other = (ReservatieMandjeItem) obj;
		if (datum == null) {
			if (other.datum != null)
				return false;
		} else if (!datum.equals(other.datum))
			return false;
		if (nummer != other.nummer)
			return false;
		if (plaatsen != other.plaatsen)
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
		return true;
	}

	@Override
	public String toString() {
		return "ReservatieMandjeItem [nummer=" + nummer + ", datum=" + datum
				+ ", titel=" + titel + ", uitvoerders=" + uitvoerders
				+ ", prijs=" + prijs + ", plaatsen=" + plaatsen + "]";
	}

	

	
}
