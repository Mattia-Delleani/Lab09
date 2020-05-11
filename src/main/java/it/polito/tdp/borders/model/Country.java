package it.polito.tdp.borders.model;

public class Country {
	
	private int id;
	private String name;
	private String abbName;
	/**
	 * @param id
	 * @param name
	 * @param abbName
	 */
	public Country(int id, String name, String abbName) {
		super();
		this.id = id;
		this.name = name;
		this.abbName = abbName;
	}
	

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAbbName() {
		return abbName;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Country other = (Country) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
