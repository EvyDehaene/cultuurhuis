package be.vdab.entities;

public class Genre implements Comparable<Genre>{
	private final int genreNr;
	private final String naam;
	
	public Genre (int genreNr, String naam) {
		super();
		this.genreNr=genreNr;
		this.naam=naam;
	}

	public int getGenreNr() {
		return genreNr;
	}

	public String getNaam() {
		return naam;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + genreNr;
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
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
		Genre other = (Genre) obj;
		if (genreNr != other.genreNr)
			return false;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Genre [genreNr=" + genreNr + ", naam=" + naam + "]";
	}

	@Override
	public int compareTo(Genre g) {
		
		return naam.compareTo(g.naam);
	}
	
	

}
