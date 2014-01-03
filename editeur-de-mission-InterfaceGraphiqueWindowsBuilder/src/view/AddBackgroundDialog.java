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

	public class AddBackgroundDialog extends JDialog {
		private Fenetre fenetre;
		private JButton bgButton;
		private JTextField urlBg;
		private JLabel nomLabel;	
	
	public AddBackgroundDialog(Fenetre parent, String title, boolean modal){
		super(parent,title,modal);
		this.fenetre = parent;
		this.setSize(400, 150);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.initComponent();
		setVisible(true);
	}
	
	private void initComponent(){
	
		JPanel panBg = new JPanel();
		panBg.setBackground(Color.white);
		panBg.setPreferredSize(new Dimension(350, 60));
		urlBg = new JTextField();
		urlBg.setPreferredSize(new Dimension(200, 25));
		panBg.setBorder(BorderFactory.createTitledBorder("Background"));
		bgButton = new JButton("URL Background");
		bgButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			JFileChooserBg();    
		}
		});
		panBg.add(bgButton);
		panBg.add(urlBg);
		
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panBg);
		
		JPanel control = new JPanel();
		JButton okBouton = new JButton("OK");
	
		okBouton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if (urlBg.getText().trim().length() != 0) {
				ajoutBg();
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
	  
		public void JFileChooserBg() {
			
			FileNameExtensionFilter imagesFilter = new FileNameExtensionFilter("Images", "bmp", "gif", "jpg", "jpeg", "png");
			
			JFileChooser chooser = new JFileChooser();

	        chooser.addChoosableFileFilter(imagesFilter);

	        chooser.setFileFilter(imagesFilter);
	
			if (chooser.showOpenDialog(null) == 0) {
				urlBg.setText(chooser.getSelectedFile().toString());
			}   
		} 
	  
		public void ajoutBg() {
			this.fenetre.ajouterBg(urlBg.getText());
		}

}