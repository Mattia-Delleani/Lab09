package it.polito.tdp.borders.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Border;

public class TestDAO {

	public static void main(String[] args) {

		BordersDAO dao = new BordersDAO();
		Map<Integer, Country> idMap = new HashMap<>();

		int year = 1850;
		dao.loadAllCountries(idMap, year);
		System.out.println("Lista di tutte le nazioni con confine prima dell'anno "+year+" sono " +idMap.size()+". Elenco:");
		for(Integer i:idMap.keySet()) {
			
			System.out.println(""+ idMap.get(i).getAbbName());
		}
	}
}
