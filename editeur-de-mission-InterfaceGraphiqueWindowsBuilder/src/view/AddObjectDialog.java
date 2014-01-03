package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

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
		setVisible(true);
	}
	
	private void initComponent(){
	
		//L'artefact
		JPanel panArtefact = new JPanel();
		panArtefact.setBackground(Color.white);
		panArtefact.setPreferredSize(new Dimension(370, 60));
		urlArtefact = new JTextField();
		urlArtefact.setPreferredSize(new Dimension(200, 25));
		panArtefact.setBorder(BorderFactory.createTitledBorder("Lien de l'Artefact"));
		artefactButton = new JButton("URL Image Artefact");
		artefactButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			JFileChooserArtefact();    
		}
		});
		panArtefact.add(artefactButton);
		panArtefact.add(urlArtefact);
	
		//Le nom de l'artefact
		JPanel panNomArtefact = new JPanel();
		panNomArtefact.setBackground(Color.white);
		panNomArtefact.setPreferredSize(new Dimension(370, 60));
		nomArtefact = new JTextField();
		nomArtefact.setPreferredSize(new Dimension(200, 25));
		panNomArtefact.setBorder(BorderFactory.createTitledBorder("Nom de l'Artefact"));
		artefactLabel = new JLabel("Saisir un nom d'Artefact :");
		panNomArtefact.add(artefactLabel);
		panNomArtefact.add(nomArtefact);
		
		//Le nom de l'artefact
		JPanel panNomAgent = new JPanel();
		panNomAgent.setBackground(Color.white);
		panNomAgent.setPreferredSize(new Dimension(370, 60));
		nomScript = new JTextField();
		nomScript.setPreferredSize(new Dimension(200, 25));
		panNomAgent.setBorder(BorderFactory.createTitledBorder("Nom du Script (Optionnel)"));
		scriptLabel = new JLabel("Saisir le nom du Script :");
		panNomAgent.add(scriptLabel);
		panNomAgent.add(nomScript);
		
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
				// Si les deux champs sont remplis
				ajoutObjet();
				setVisible(false);
			}  
			}
			}); 
	   
		JButton cancelBouton = new JButton("Annuler");
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
			
			FileNameExtensionFilter imagesFilter = new FileNameExtensionFilter("Images", "bmp", "gif", "jpg", "jpeg", "png");
			
			JFileChooser chooser = new JFileChooser();

	        chooser.addChoosableFileFilter(imagesFilter);

	        chooser.setFileFilter(imagesFilter);
	
			if (chooser.showOpenDialog(null) == 0) {
				urlArtefact.setText(chooser.getSelectedFile().toString());
			}   
		} 
	  
		public void ajoutObjet() {
			this.fenetre.ajouterObjet(nomArtefact.getText(), urlArtefact.getText(), nomScript.getText());
		}

}