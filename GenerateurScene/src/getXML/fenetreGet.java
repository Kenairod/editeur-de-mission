package getXML;

import javax.swing.*;

public class fenetreGet {

	public String inXML ="";
	
	public fenetreGet(){
		
		// creation de la fenetre
		JTextField xyzField = new JTextField();
		String input =  JOptionPane.showInputDialog(xyzField ,"Enter here a correct XML file:");
		int tailleInput = input.length(); // taille de string tapé
		
		// verification de l'extension
		if (input.charAt(tailleInput-4) == '.' && input.charAt(tailleInput-3) == 'x' && input.charAt(tailleInput-2) == 'm' && input.charAt(tailleInput-1) == 'l')
			inXML = input;
		else {
			while (input.charAt(tailleInput-4) != '.' || input.charAt(tailleInput-3) != 'x' || input.charAt(tailleInput-2) != 'm' || input.charAt(tailleInput-1) != 'l') {
				input =  JOptionPane.showInputDialog(xyzField ,"Wrong file extension. Enter here a correct XML file:"); 
				tailleInput = input.length();
			}
			inXML = input;
			}
	}
	
	
	
}
