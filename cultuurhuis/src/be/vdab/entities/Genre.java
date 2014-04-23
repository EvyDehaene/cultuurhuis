package be.vdab.entities;

public class Genre {
	private int genreNr;
	private String naam;
	
	public Genre (int genreNr, String naam) {
		setGenreNr(genreNr);
		setNaam(naam);
	}
	
	public int getGenreNr() {
		return genreNr;
	}
	public void setGenreNr(int genreNr) {
		this.genreNr = genreNr;
	}
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}

}
