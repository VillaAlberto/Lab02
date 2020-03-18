package it.polito.tdp.alien;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.IllegalFormatException;
import java.util.ResourceBundle;

import it.polito.tdp.model.AlienDictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	private AlienDictionary d= new AlienDictionary();
	

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnTranslate;

	@FXML
	private TextField txtInsert;

	@FXML
	private TextArea txtOutput;

	@FXML
	private Button btnClear;

	@FXML
	void doReset(ActionEvent event) {
		txtOutput.setText("Welcome to Alien Dictionary v2019\n");
		txtInsert.clear();

	}

	@FXML
	void doTranslate(ActionEvent event) throws IllegalFormatException {
		txtOutput.appendText("\n");
		String output = "";
		String input = null;

		// Controllo delle parole e chiamata metodo
		try {
			input = txtInsert.getText().toLowerCase();
			if (isInvalid(input)) {
				throw new InvalidParameterException();
			}
		} catch (Exception e) {
			output = "Hai inserito una parola invalida\n";
			txtOutput.setText(output);
			return;
		}
		
		String parts[]= input.split(" ");
		if (parts.length==0)
		{
			output = "Non hai inserito nulla\n";
			txtOutput.setText(output);
			return;
		}
		
		if(parts.length==1)
		{
			String s= d.translateWord(parts[0]);
			if (s==null)
			output="Parola non in dizionario";
			else output=s;
		}
		
		if(parts.length==2)
		{
			d.addWord(parts[0], parts[1]);
			output="Parola aggiunta al dizionario: "+parts[0].toUpperCase()+"\n";
		}
		
		if(parts.length>2)
			output="Piu'di due parole aggiunte";

		// Gestione dell'output
		txtInsert.clear();
		txtOutput.appendText(output);

	}

	private boolean isInvalid(String input) {
		if (!input.matches("[a-z A-Z]+"))
			return true;
		return false;
	}

	@FXML
	void initialize() {
		assert btnTranslate != null : "fx:id=\"btnTranslate\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtInsert != null : "fx:id=\"txtInsert\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtOutput != null : "fx:id=\"txtOutput\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";

	}
}
