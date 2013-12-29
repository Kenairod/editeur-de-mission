package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MenuBar {

	private Fenetre fenetre;
	
	/**
	 * La barre de menu principal
	 */
	private JMenuBar menuBar = new JMenuBar();
	/**
	 * Le menu File
	 */
	private JMenu fichier = new JMenu("File");
	/**
	 * Le menu Edit
	 */
	private JMenu edition = new JMenu("Edit");
	/**
	 * Le menu View
	 */
	private JMenu vue = new JMenu("View");
	/**
	 * Le menu Help
	 */
	private JMenu aide = new JMenu("Help");
	
	//Les items de fichier
	/**
	 * L'item Nouveau
	 */
	private JMenuItem nouveau = new JMenuItem("New Document");
	/**
	 * L'item ouvrir
	 */
	private JMenuItem ouvrir = new JMenuItem("Open Document");
	/**
	 * L'item fermer
	 */
	//private JMenuItem fermer = new JMenuItem("Close Document");
	/**
	 * L'item enregistrer
	 */
	private JMenuItem enregistrer = new JMenuItem("Save");
	/**
	 * L'item enregistrer sous
	 */
	//private JMenuItem enregistrer_sous = new JMenuItem("Save As");
	/**
	 * L'item print
	 */
	//private JMenuItem imprimer = new JMenuItem("Print");
	/**
	 * L'item preview
	 */
	//private JMenuItem apercu = new JMenuItem("Print Preview");
	/**
	 * L'item exit
	 */
	private JMenuItem quitter = new JMenuItem("Exit");
	//Les items de edition
	/**
	 * L'item undo
	 */
	private JMenuItem precedent = new JMenuItem("Undo");
	/**
	 * L'item redo
	 */
	private JMenuItem suivant = new JMenuItem("Redo");
	/**
	 * L'item cut
	 */
	private JMenuItem couper = new JMenuItem("Cut");
	/**
	 * L'item copy
	 */
	private JMenuItem copier = new JMenuItem("Copy");
	/**
	 * L'item paste
	 */
	private JMenuItem coller = new JMenuItem("Paste");
	//Les items de edition
	/**
	 * L'item zoom +
	 */
	private JMenuItem zoom = new JMenuItem("Zoom +");
	/**
	 * L'item zoom -
	 */
	private JMenuItem dezoom = new JMenuItem("Zoom -");
	//Les items de aide
	/**
	 * L'item Help
	 */
	private JMenuItem aideItem = new JMenuItem("Help");
	/**
	 * L'item à propos
	 */
	private JMenuItem a_propos = new JMenuItem("About Bianji...");
	
	//private String nomFichier;
	
	public MenuBar (Fenetre fenetre) {
		this.fenetre = fenetre;
		
		nouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		this.fichier.add(nouveau);
		nouveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newProject();    
			}
	    });
		
		ouvrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
		this.fichier.add(ouvrir);
		ouvrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openProject();    
			}
	    });
		
		/*fermer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.CTRL_DOWN_MASK));
		this.fichier.add(fermer);
		fermer.setEnabled(false);*/
		
		this.fichier.addSeparator();//-------
		
		enregistrer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		this.fichier.add(enregistrer);
		//enregistrer.setEnabled(false);
		enregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveProject(); 
			}
	    });	 
		
		/*enregistrer_sous.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
		this.fichier.add(enregistrer_sous);
		enregistrer_sous.setEnabled(false);*/
		
		/*this.fichier.addSeparator();//-------
		 
		imprimer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
		this.fichier.add(imprimer);
		imprimer.setEnabled(false);
		apercu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
		this.fichier.add(apercu);
		apercu.setEnabled(false);*/
		
		this.fichier.addSeparator();//-------
		
		quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_DOWN_MASK));
		this.fichier.add(quitter);
		quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exitProject();    
			}
	    });	// Fermeture de la fenêtre en appuyant sur 'Exit'
		
		//Menu edition
		precedent.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
		this.edition.add(precedent);
		
		suivant.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK));
		this.edition.add(suivant);
		
		this.edition.addSeparator();//-------
		
		couper.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
		this.edition.add(couper);
		
		copier.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
		this.edition.add(copier);
		
		coller.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
		this.edition.add(coller);
		
		//Menu vue
		zoom.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, KeyEvent.CTRL_DOWN_MASK));
		this.vue.add(zoom);
		
		dezoom.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, KeyEvent.CTRL_DOWN_MASK));
		this.vue.add(dezoom);
		
		//Menu help
		aideItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, KeyEvent.CTRL_DOWN_MASK));
		this.aide.add(aideItem);
		aideItem.setEnabled(false);
		
		this.aide.addSeparator();//-------
		
		//a_propos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, KeyEvent.CTRL_DOWN_MASK));
		this.aide.add(a_propos);
		a_propos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				aboutProject();    
			}
	    });
		
		fichier.setMnemonic('F');
		this.menuBar.add(fichier);
		edition.setMnemonic('E');
		this.menuBar.add(edition);
		vue.setMnemonic('V');
		this.menuBar.add(vue);
		aide.setMnemonic('H');
		this.menuBar.add(aide);
		
		this.fenetre.setJMenuBar(menuBar);
		this.fenetre.revalidate();
		
		/* if (fenetre.getProject() == null) {
		    	enregistrer.setEnabled(false);
		    	coller.setEnabled(false);
		    	copier.setEnabled(false);
		    	couper.setEnabled(false);
		    	suivant.setEnabled(false);
		    	precedent.setEnabled(false);
		    	zoom.setEnabled(false);
		    	dezoom.setEnabled(false);
		    }*/
	}
	
	public JMenuBar getMenuBar() {
		return menuBar;
	}
	
	public void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}
	
	public void exitProject () {
			int exit = JOptionPane.showConfirmDialog(this.fenetre, "Voulez-vous vraiment quitter ?", "Exit", JOptionPane.OK_CANCEL_OPTION);
			if(exit == 0) {
				this.fenetre.dispose();
			}
		}
	
	public void openProject() {
			JFileChooser chooser = new JFileChooser();
	        // create filter
	        FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("XML Files (*.xml)", "xml");
	        // add filter
	        chooser.addChoosableFileFilter(xmlFilter);
	        // set selected filter
	        chooser.setFileFilter(xmlFilter);
	        chooser.showOpenDialog(null);
	        System.out.println("Fichier choisi : " + chooser.getSelectedFile()); // récupération du fichier sélectionné
	}
	
	public void aboutProject () {
			JFrame about = new JFrame("About");
			JLabel aboutLabel = new JLabel("Bianji est un logiciel libre développé par Aurélien CANO, Steven KIEFFER, Dorian KURZAJ et Benjamin TEISSEYRE dans le cadre du projet à l'IUT de Montpellier.", JLabel.CENTER);
			about.setSize(1000, 100);
			about.add(aboutLabel);
			about.setLocationRelativeTo(null);
			about.setVisible(true);
	}
	
	public void saveProject(){
		this.fenetre.saveProject(this.fenetre.getTitre());
		JOptionPane.showMessageDialog(null,"Projet Sauvegardé", "Save",JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void newProject(){
		String name = JOptionPane.showInputDialog(null, "Nom du Projet ?", "Nom du Projet", JOptionPane.QUESTION_MESSAGE);
		if (name != null) {
		    if(name.length()!=0) {
		    	this.fenetre.setTitre(name);
				this.fenetre.newProject();
				nouveau.setEnabled(false);
				enregistrer.setEnabled(true);
		    	coller.setEnabled(true);
		    	copier.setEnabled(true);
		    	couper.setEnabled(true);
		    	suivant.setEnabled(true);
		    	precedent.setEnabled(true);
		    	zoom.setEnabled(true);
		    	dezoom.setEnabled(true);
		    }
		    else {
		    	JOptionPane.showMessageDialog(null,
		    			"Nom Incorrect...", "Nom Incorrect", JOptionPane.ERROR_MESSAGE);
		    	newProject();
			}
		}
	}

}
