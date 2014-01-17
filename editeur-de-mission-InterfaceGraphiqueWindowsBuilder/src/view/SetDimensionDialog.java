package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

	public class SetDimensionDialog extends JDialog {
		private Fenetre fenetre;
		private JTextField hauteur, largeur;
		private JLabel hauteurLabel, largeurLabel;	
	
	public SetDimensionDialog(Fenetre parent, String title, boolean modal){
		super(parent,title,modal);
		this.fenetre = parent;
		this.setSize(450, 220);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
		this.setVisible(true);
	}
	
	private void initComponent(){
		
		JPanel panHauteur = new JPanel();
		panHauteur.setBackground(Color.white);
		panHauteur.setPreferredSize(new Dimension(380, 60));
		this.hauteur = new JTextField();
		this.hauteur.setPreferredSize(new Dimension(200, 25));
		panHauteur.setBorder(BorderFactory.createTitledBorder("Height"));
		this.hauteurLabel = new JLabel("Enter the Height :");
		panHauteur.add(this.hauteurLabel);
		panHauteur.add(this.hauteur);
		
		JPanel panLargeur = new JPanel();
		panLargeur.setBackground(Color.white);
		panLargeur.setPreferredSize(new Dimension(380, 60));
		this.largeur = new JTextField();
		this.largeur.setPreferredSize(new Dimension(200, 25));
		panLargeur.setBorder(BorderFactory.createTitledBorder("Width"));
		this.largeurLabel = new JLabel("Enter the Width :");
		panLargeur.add(this.largeurLabel);
		panLargeur.add(this.largeur);
		
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panHauteur);
		content.add(panLargeur);
		
		JPanel control = new JPanel();
		JButton okBouton = new JButton("OK");
	
		okBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (hauteur.getText().trim().length() != 0 && largeur.getText().trim().length() != 0) {
					if (Integer.parseInt(hauteur.getText()) > 0 && 
							Integer.parseInt(largeur.getText()) > 0) {
						setDimensions();
						setVisible(false);
					}
				}  
			}
		}); 
		    
		control.add(okBouton);
		    
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);		
		}  
	
	
	public void setDimensions() {
		this.fenetre.resizeScene(Integer.parseInt(largeur.getText()), Integer.parseInt(hauteur.getText()));
		this.fenetre.setHauteur(Integer.parseInt(hauteur.getText()));
		this.fenetre.setLargeur(Integer.parseInt(largeur.getText()));
		this.fenetre.enableContenu();
	}

}