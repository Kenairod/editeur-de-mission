package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AddBackgroundDialog extends JDialog {
	
	private Fenetre fenetre;
	private JButton bgButton;
	private JTextField urlBg;
	
	public AddBackgroundDialog(Fenetre parent, String title, boolean modal) {
		super(parent,title,modal);
		this.fenetre = parent;
		this.setSize(440, 135);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.initComponent();
		this.setVisible(true);
	}
	
	private void initComponent() {
	
		JPanel panBg = new JPanel();
		panBg.setBackground(Color.white);
		panBg.setPreferredSize(new Dimension(430, 60));
		this.urlBg = new JTextField();
		this.urlBg.setPreferredSize(new Dimension(200, 25));
		panBg.setBorder(BorderFactory.createTitledBorder("Background"));
		this.bgButton = new JButton("URL Background Image");
		this.bgButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooserBg();    
			}
		});
		panBg.add(this.bgButton);
		panBg.add(this.urlBg);
		
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panBg);
		
		JPanel control = new JPanel();
		JButton okBouton = new JButton("OK");
	
		okBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (urlBg.getText().trim().length() != 0) {
					File f = new File(urlBg.getText().trim());
					if (f.exists()) {
						ajoutBg();
						setVisible(false);
					}
					else {
				    	JOptionPane.showMessageDialog(null,
				    			"Wrong Path...", "Failure", JOptionPane.ERROR_MESSAGE);
					}
				}  
			}
		}); 
	   
		JButton cancelBouton = new JButton("Cancel");
		cancelBouton.addActionListener(new ActionListener() {
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
			
			FileNameExtensionFilter imagesFilter = new FileNameExtensionFilter("Images", "bmp", "gif ", "jpg", "jpeg", "png");
			
			JFileChooser chooser = new JFileChooser(".");

	        chooser.addChoosableFileFilter(imagesFilter);

	        chooser.setFileFilter(imagesFilter);
	
			if (chooser.showOpenDialog(null) == 0) {
				this.urlBg.setText(this.relativePath(chooser.getSelectedFile().toString()));
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
	  
		public void ajoutBg() {
			this.fenetre.ajouterBg(urlBg.getText().trim());
		}

}
