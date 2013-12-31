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

public class ZDialog extends JDialog {
	  private Fenetre fenetre;
	  private JButton artefactButton, agentButton;
	  private JTextField urlArtefact, nomArtefact;
	  private String title;
	  private boolean modal;
	  private JLabel nomLabel;

  public ZDialog(Fenetre parent, String title, boolean modal){
      super();
      this.fenetre = parent;
      this.title = title;
      this.modal = modal;
      this.setSize(500, 210);
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
	  panArtefact.setPreferredSize(new Dimension(350, 60));
	  urlArtefact = new JTextField();
	  urlArtefact.setPreferredSize(new Dimension(200, 25));
	  panArtefact.setBorder(BorderFactory.createTitledBorder("Artefact"));
	  artefactButton = new JButton("URL Artefact");
	  artefactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooserArtefact();    
			}
	    });
	  panArtefact.add(artefactButton);
	  panArtefact.add(urlArtefact);
	  
	//Le nom
	    JPanel panNom = new JPanel();
	    panNom.setBackground(Color.white);
	    panNom.setPreferredSize(new Dimension(350, 60));
	    nomArtefact = new JTextField();
	    nomArtefact.setPreferredSize(new Dimension(200, 25));
	    panNom.setBorder(BorderFactory.createTitledBorder("Nom Artefact"));
	    nomLabel = new JLabel("Saisir un nom :");
	    panNom.add(nomLabel);
	    panNom.add(nomArtefact);

      JPanel content = new JPanel();
      content.setBackground(Color.white);
      content.add(panArtefact);
      content.add(panNom);

      JPanel control = new JPanel();
      JButton okBouton = new JButton("OK");
    
      okBouton.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent arg0) {
			  if (nomArtefact.getText().trim().length() != 0 && urlArtefact.getText().trim().length() != 0) {	// Si les deux champs sont remplis
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
		JFileChooser chooser = new JFileChooser();

		if (chooser.showOpenDialog(null) == 0) {
			urlArtefact.setText(chooser.getSelectedFile().toString());
		}   
  }
  
  public void ajoutObjet() {
	  this.fenetre.ajouterObjet(nomArtefact.getText(), urlArtefact.getText());
  }

}