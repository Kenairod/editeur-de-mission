package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ZDialog extends JDialog {
	  private JButton artefactButton, agentButton;
	  private JTextField artefact, agent;
	  private String nomArtefact, nomAgent;

  public ZDialog(JFrame parent, String title, boolean modal){
      super(parent, title, modal);
      this.setSize(400, 220);
      this.setLocationRelativeTo(null);
      this.setResizable(false);
      this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
      this.initComponent();
      setVisible(true);
  }

  private void initComponent(){

    //L'agent
	  JPanel panAgent = new JPanel();
	  panAgent.setBackground(Color.white);
	  panAgent.setPreferredSize(new Dimension(350, 60));
	  agent = new JTextField();
	  agent.setPreferredSize(new Dimension(200, 25));
	  panAgent.setBorder(BorderFactory.createTitledBorder("Agent"));
	  agentButton = new JButton("URL Agent");
	  agentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooserAgent();    
			}
	    });
	  panAgent.add(agentButton);
	  panAgent.add(agent);

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
      content.add(panAgent);

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
  
  public void JFileChooserAgent() {
	   JFileChooser chooser = new JFileChooser();
		
	   if (chooser.showOpenDialog(null) == 0) {
		   agent.setText(chooser.getSelectedFile().toString());
		   nomAgent = chooser.getName(chooser.getSelectedFile());
	   }   
  }
  
  public void JFileChooserArtefact() {
		JFileChooser chooser = new JFileChooser();

		if (chooser.showOpenDialog(null) == 0) {
			artefact.setText(chooser.getSelectedFile().toString());
			nomArtefact = chooser.getName(chooser.getSelectedFile());
		}   
  }

}