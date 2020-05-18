package it.polito.tdp.borders;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtAnno;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Button btnTrovaVicini;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	
    	txtResult.clear();
    	int anno = 0;
    	try {
    		 anno = Integer.parseInt(txtAnno.getText());
    	}catch(NumberFormatException nfe) {
    		
    		txtResult.setText("Devi introdurre un numero!!");
    		return;
    	}
    	if(anno>=1816 && anno<=2016) {
    		
    		txtResult.appendText(this.model.stampaStati(anno));
    		txtResult.appendText("\nNumero vertici:"+this.model.getGrafo().vertexSet().size()+" e archi: "+this.model.getGrafo().edgeSet().size());
    		//COMPONENTI CONNESSE
    		List<Set<Country>> componenti =  this.model.getNumberOfConnectedComponents();
    		txtResult.appendText("\nVi sono "+componenti.size()+" gruppi di componenti connesse. Quelle con piu di un arco sono: ");
    		int cont =1;
    		String result="";
    		for(Set<Country> c: componenti) {
    			if(c.size()>1) {
        			result+="Numero "+ cont + ": \n";
    				String str="";
    				for(Country temp: c) {
    					if(str.equals("")) {
    						str+=temp.getAbbName();
    					}else {
    						str+=", " + temp.getAbbName();
    					}
    				}
    				str+="\n";
    				result+=str;
    				cont++;
    			}
    			
    		}
    		txtResult.appendText(result);
    		
    		
    		
    		
    	}else {
    		txtResult.setText("Devi introdurre un numero compreso tra 1816 e 2016");
    	}

    }

    @FXML
    void doTrovaVicini(ActionEvent event) {
    	txtResult.clear();
    	
    	int anno = 0;
    	try {
    		 anno = Integer.parseInt(txtAnno.getText());
    	}catch(NumberFormatException nfe) {
    		
    		txtResult.setText("Devi introdurre un numero!!");
    		return;
    	}
    	
    	String stato = comboBox.getValue();
    	if(anno>=1816 && anno<=2016 && stato!=null) {
    		
    		List<Country> vicini = this.model.getNumeroViciniConnessi(stato, anno);
    		
    		txtResult.appendText("Lo stato "+stato.substring(0,3)+" ha "+vicini.size()+" stati raggiungibili:");
    		for(Country c: vicini) {
    			txtResult.appendText("\n" + c.getAbbName());
    			
    		}
    	}else {
    		txtResult.setText("Devi introdurre un numero compreso tra 1816 e 2016");
    	}
    

    }

    @FXML
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	
    	this.model= model;
    	List<String> statiAbb = new ArrayList<>();
    	for(Country c: this.model.getAllCountries()) {
    		
    		statiAbb.add(c.getAbbName()+" ("+c.getId()+")");
    	}
    	comboBox.getItems().addAll(statiAbb);
    }
}
