package it.polito.tdp.borders.model;

import java.util.List;
import java.util.Set;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();

		System.out.println("TestModel -- TODO");
		
		System.out.println("Creo il grafo relativo al 2000");
		model.creaGrafo(2000);
		
	
		List<Set<Country>> result = model.getNumberOfConnectedComponents();
		int cont =1;
		for(Set<Country> c: result) {
			String r="";
			if(c.size()>1) {
				for(Country temp: c) {
					r+="\n"+temp.getAbbName();
				}
				//System.out.println("Numero: "+ cont+ r);
				cont++;
			}
			
			
		}
		System.out.println(model.stampaStati(2000));
		
	//	System.out.format("Numero componenti connesse: %d\n", );
		
//		Map<Country, Integer> stats = model.getCountryCounts();
//		for (Country country : stats.keySet())
//			System.out.format("%s %d\n", country, stats.get(country));		
		
		model.getNumeroViciniConnessi("USA (2)", 2000);
		
	}

}
