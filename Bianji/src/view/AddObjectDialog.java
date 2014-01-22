package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import data.Objet;

public class AddObjectDialog extends JDialog {
	
	private Fenetre fenetre;
	private JButton artefactButton;
	private JTextField urlArtefact, nomArtefact, nomScript;
	private JLabel artefactLabel, scriptLabel;	
	
	public AddObjectDialog(Fenetre parent, String title, boolean modal){
		super(parent,title,modal);
		this.fenetre = parent;
		this.setSize(500, 270);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.initComponent();
		this.setVisible(true);
	}
	
	private void initComponent(){
	
		JPanel panArtefact = new JPanel();
		panArtefact.setBackground(Color.white);
		panArtefact.setPreferredSize(new Dimension(440, 60));
		this.urlArtefact = new JTextField();
		this.urlArtefact.setPreferredSize(new Dimension(200, 25));
		panArtefact.setBorder(BorderFactory.createTitledBorder("Path to the Artefact"));
		this.artefactButton = new JButton("URL Artefact Image");
		this.artefactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooserArtefact();    
			}
		});
		panArtefact.add(this.artefactButton);
		panArtefact.add(this.urlArtefact);
	
		
		JPanel panNomArtefact = new JPanel();
		panNomArtefact.setBackground(Color.white);
		panNomArtefact.setPreferredSize(new Dimension(440, 60));
		this.nomArtefact = new JTextField();
		this.nomArtefact.setPreferredSize(new Dimension(200, 25));
		panNomArtefact.setBorder(BorderFactory.createTitledBorder("Artefact's Name"));
		this.artefactLabel = new JLabel("Enter the Artefact's name :");
		panNomArtefact.add(this.artefactLabel);
		panNomArtefact.add(this.nomArtefact);
		

		JPanel panNomAgent = new JPanel();
		panNomAgent.setBackground(Color.white);
		panNomAgent.setPreferredSize(new Dimension(440, 60));
		this.nomScript = new JTextField();
		this.nomScript.setPreferredSize(new Dimension(200, 25));
		panNomAgent.setBorder(BorderFactory.createTitledBorder("Script's name (Optional)"));
		this.scriptLabel = new JLabel("Enter the Script's name :");
		panNomAgent.add(this.scriptLabel);
		panNomAgent.add(this.nomScript);
		
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panArtefact);
		content.add(panNomArtefact);
		content.add(panNomAgent);
		
		JPanel control = new JPanel();
		JButton okBouton = new JButton("OK");
	
		okBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (nomArtefact.getText().trim().length() != 0 && urlArtefact.getText().trim().length() != 0) {	
					Objet o = new Objet(fenetre, nomArtefact.getText().trim(), 
							urlArtefact.getText().trim(), nomScript.getText().trim());
					List<Objet> listeObjets = fenetre.getObjets();
					boolean alreadyCreated = false;
					int i = 0;
					while (i < listeObjets.size() && !alreadyCreated) {
						if (o.equals(listeObjets.get(i))){
							alreadyCreated = true;
						}
						i++;
					}
					if (!alreadyCreated) {
						File f = new File(urlArtefact.getText().trim());
						if (f.exists()) {
							ajoutObjet(o);
							setVisible(false);
						}
						else {
					    	JOptionPane.showMessageDialog(null,
					    			"Wrong Path...", "Failure", JOptionPane.ERROR_MESSAGE);
						}
					}
					else {
				    	JOptionPane.showMessageDialog(null,
				    			"Object Already Created...", "Failure", JOptionPane.ERROR_MESSAGE);
					}
				}  
			}
		}); 
	   
		JButton cancelBouton = new JButton("Cancel");
		cancelBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}      
		});
		
		control.add(okBouton);
		control.add(cancelBouton);
		    
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
		
		}  
	  
		public void JFileChooserArtefact() {
			
			FileNameExtensionFilter imagesFilter = new FileNameExtensionFilter("Images", "bmp", "gif ", "jpg", "jpeg", "png");
			
			JFileChooser chooser = new JFileChooser(".");

	        chooser.addChoosableFileFilter(imagesFilter);

	        chooser.setFileFilter(imagesFilter);
	
			if (chooser.showOpenDialog(null) == 0) {
				this.urlArtefact.setText(this.relativePath(chooser.getSelectedFile().toString()));
			}   
		}
		
		public String relativePath(String absolutePath) {
			String relative = new String();
			ArrayList<Character> copie = new ArrayList<Character>();
			ArrayList<Character> listeRelative = new ArrayList<Character>();
			boolean first = false;
			boolean stop = false;
			
			for (int i=0; i < absolutePath.length(); i++) {
				copie.add(i, absolutePath.charAt(i));
			}
			
			int taille = copie.size()-1;
			while (taille >= 0 && !stop) {
				if (copie.contains("/")) {
					if (copie.get(taille) == '/' && !first) {
						first = true;
					}
					
					else if (copie.get(taille) == '/' && first) {
						stop = true;
					}
				}
				else {
					if (copie.get(taille) == '\\' && !first) {
						first = true;
					}
					
					else if (copie.get(taille) == '\\' && first) {
						stop = true;
					}
				}
				listeRelative.add(0, copie.get(taille));
				taille--;
			}
			
			for (int i=1; i < listeRelative.size(); i++) {
				relative += listeRelative.get(i);
			}
			
			if (relative.contains("/")) {
				return relative;
			}
			else {
				return setBackToSlash(relative);
			}
		}
		
		public String setBackToSlash(String back) {
			String slash = new String();
			for (int i=0; i < back.length(); i++) {
				if (back.charAt(i) == '\\') {
					slash += '/';
				}
				else slash += back.charAt(i);
			}
			
			return slash;
		}
	  
		public void ajoutObjet(Objet o) {
			this.fenetre.ajouterObjet(o);
		}

}
