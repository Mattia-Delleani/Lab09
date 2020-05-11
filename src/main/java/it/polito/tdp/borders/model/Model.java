package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private Map<Integer, Country> idMap;
	private BordersDAO bdao;
	Graph<Country, DefaultEdge> grafo;
	List<Border> archi;
	public Model() {
		bdao = new BordersDAO();
		idMap = new HashMap<>();
		grafo = new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);

	}
	
	public  void creaGrafo(int year) {
		
		
		bdao.loadAllCountries(idMap, year);
		//aggiungo i vertici
		Graphs.addAllVertices(grafo, idMap.values());
		//aggiungo gli archi
		archi = bdao.getCountryPairs(year);
		for(Border b: archi) {
			grafo.addEdge(idMap.get(b.getIdS1()), idMap.get(b.getIdS2()));
		}
		
		
	}
	public List<Country> getAllCountries(){
		
		
		return bdao.getAllCountries();
	}

	public List<Set<Country>> getNumberOfConnectedComponents() {
		ConnectivityInspector<Country, DefaultEdge> componentiConnesse = new ConnectivityInspector<Country, DefaultEdge>(this.grafo);
		
		List<Set<Country>> result = componentiConnesse.connectedSets();
		
		return result;
	}
	
	public int getNumeroVicini(Country stato) {
		
		
		List<Country> confinanti = new ArrayList<>();
		for(Border b: archi) {
			if(b.getIdS1()==stato.getId()) {
				confinanti.add(idMap.get(b.getIdS2()));
			}else if(b.getIdS2()==stato.getId()){
				confinanti.add(idMap.get(b.getIdS1()));
			}
		
		}
		
		return confinanti.size();
			
	}
	
	public String stampaStati(int year) {
		
		String risultato="";
		this.creaGrafo(year);
		
		for(Country c: this.grafo.vertexSet()) {
			risultato+="\n"+c.getAbbName()+"|"+this.grafo.degreeOf(c)+"";
			
		}
		return risultato;
		
		
	}
	
	public List<Country> getNumeroViciniConnessi(String s, int year) {
		
		this.creaGrafo(year);
		int id= Integer.parseInt(s.substring(s.indexOf("(")+1, s.indexOf(")")));		
		Country stato = idMap.get(id);
		System.out.println(stato.getName());
		List<Country> vicini = new ArrayList<>();
		
		GraphIterator<Country, DefaultEdge> bfv = new BreadthFirstIterator<Country, DefaultEdge>(this.grafo, stato);
		
		while(bfv.hasNext()) {
			
			vicini.add(bfv.next());
		}
	
		return vicini;
			
	}

}
