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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ZDialog extends JDialog {
	  private JButton artefactButton, agentButton;
	  private JTextField artefact;
	  private String nomArtefact;

  public ZDialog(JFrame parent, String title, boolean modal){
      super(parent, title, modal);
      this.setSize(400, 150);
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
	  artefact = new JTextField();
	  artefact.setPreferredSize(new Dimension(200, 25));
	  panArtefact.setBorder(BorderFactory.createTitledBorder("Artefact"));
	  artefactButton = new JButton("URL Artefact");
	  artefactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooserArtefact();    
			}
	    });
	  panArtefact.add(artefactButton);
	  panArtefact.add(artefact);

      JPanel content = new JPanel();
      content.setBackground(Color.white);
      content.add(panArtefact);

      JPanel control = new JPanel();
      JButton okBouton = new JButton("OK");
    
      okBouton.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent arg0) {
			  //ajoutObjet();
			  setVisible(false);
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
			artefact.setText(chooser.getSelectedFile().toString());
			nomArtefact = chooser.getName(chooser.getSelectedFile());
		}   
  }

}