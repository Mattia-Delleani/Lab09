package it.polito.tdp.borders.model;

public class Border {
	
	private int idS1;
	private int idS2;
	private int year;
	/**
	 * @param idS1
	 * @param idS2
	 * @param year
	 */
	public Border(int idS1, int idS2) {
		super();
		this.idS1 = idS1;
		this.idS2 = idS2;
	}
	public int getIdS1() {
		return idS1;
	}
	public int getIdS2() {
		return idS2;
	}
	
	

}
