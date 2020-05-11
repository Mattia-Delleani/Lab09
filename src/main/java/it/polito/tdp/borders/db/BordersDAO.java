package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public void loadAllCountries(Map<Integer, Country> idMap, int year) {
		String sql = "SELECT DISTINCT(c.CCode), c.StateNme, c.StateAbb " + 
				"FROM country c, contiguity conf " + 
				"WHERE (c.CCode=conf.state1no OR c.CCode=conf.state2no) AND (conf.year<=?) ";
		List<Country> result = new ArrayList<Country>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, year);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				if(!idMap.containsKey(rs.getInt("ccode")))
					idMap.put(rs.getInt("ccode"), new Country(rs.getInt("ccode"),  rs.getString("StateNme"), rs.getString("StateAbb")));
			}
			
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Border> getCountryPairs(int year) {

		String sql="SELECT state1no AS id1, state2no AS id2 " + 
				"FROM contiguity conf " + 
				"WHERE conf.conttype=1 and conf.year<? " + 
				"GROUP BY dyad" + 
				"";
		List<Border> confini = new ArrayList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, year);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				confini.add(new Border(rs.getInt("id1"), rs.getInt("id2")));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
			
		}
		
		return confini;
	}

	public List<Country> getAllCountries() {
		String sql = "SELECT DISTINCT(c.CCode), c.StateNme, c.StateAbb " + 
				"FROM country c ";
		List<Country> result = new ArrayList<Country>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
					result.add(new Country(rs.getInt("ccode"),  rs.getString("StateNme"), rs.getString("StateAbb")));
			}
			
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
		return result;
	}
	
}
