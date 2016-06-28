package getXML;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class fenetreGet {

	public String inXML ="";
	
	public fenetreGet(){
		
		JFileChooser chooser = new JFileChooser(".");
		
		FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("XML Files (*.xml)", "xml");
		chooser.addChoosableFileFilter(xmlFilter);
		chooser.setFileFilter(xmlFilter);
		chooser.showOpenDialog(null);
		chooser.getSelectedFile();
		System.out.println(chooser.getSelectedFile().toString());
		inXML = chooser.getSelectedFile().toString();
		int i = inXML.length()-1 ;
		String temp = "";
		
		if (System.getProperty("os.name").equals("Linux")){
			while (inXML.charAt(i) != '/')
				i--;
			while (i != inXML.length()-1){
				temp = temp + inXML.charAt(i+1);
				i++;
			}
		}
		else{	
			while (inXML.charAt(i) != '\\')
				i--;
			while (i != inXML.length()-1){
				temp = temp + inXML.charAt(i+1);
				i++;
			}
		}
		System.out.println(temp);
		inXML = temp;
	}
	
	
	
}
