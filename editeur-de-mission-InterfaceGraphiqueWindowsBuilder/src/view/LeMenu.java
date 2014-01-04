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

/**
 * La barre de menu
 * @author Da Dream Team
 *
 */
public class LeMenu extends JMenuBar {
	
	private Fenetre fenetre;
		
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
	private JMenuItem fermer = new JMenuItem("Close Document");
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


	public LeMenu (Fenetre fenetre) {
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
       
		fermer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.CTRL_DOWN_MASK));
	 	this.fichier.add(fermer);
  	    fermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				restartProject();    
			}
	    });
              
		this.fichier.addSeparator();//-------
              
		enregistrer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		this.fichier.add(enregistrer);
		enregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveProject(); 
			}
	    });	 
              
		this.fichier.addSeparator();//-------
              
		quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_DOWN_MASK));
		this.fichier.add(quitter);
		quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exitProject();    
			}
	    });
              
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
			this.add(fichier);
			edition.setMnemonic('E');
			this.add(edition);
			vue.setMnemonic('V');
			this.add(vue);
			aide.setMnemonic('H');
			this.add(aide);
			  
			if (this.fenetre.getNomProjet().length()==0) {
				this.setAllFalse();
			} 
	}
	
	public void setAllFalse() {
			enregistrer.setEnabled(false);
	  	    fermer.setEnabled(false);
	    	coller.setEnabled(false);
	    	copier.setEnabled(false);
	    	couper.setEnabled(false);
	    	suivant.setEnabled(false);
	    	precedent.setEnabled(false);
	    	zoom.setEnabled(false);
	    	dezoom.setEnabled(false);
	}
	
	public void exitProject () {
		int exit = JOptionPane.showConfirmDialog(this.fenetre, "Do you really want to quit ?", "Exit", JOptionPane.OK_CANCEL_OPTION);
		if(exit == 0) {
			this.fenetre.dispose();
		}
	}

	public void openProject() {
		JFileChooser chooser = new JFileChooser();
        // création du filtre
        FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("XML Files (*.xml)", "xml");
        // ajout du filtre
        chooser.addChoosableFileFilter(xmlFilter);
        // set filtre selectionné
        chooser.setFileFilter(xmlFilter);
        //System.out.println("Fichier choisi : " + chooser.getSelectedFile()); // récupération du fichier sélectionné
        if (chooser.showOpenDialog(null) == 0) {
        	String[] str = chooser.getSelectedFile().getName().split(".xml");
        	this.fenetre.importProject(chooser.getSelectedFile().toString(),str[0]);
        	this.fenetre.enableContenu();
        	this.changeEnabled();
        }   
	}
	
	public void changeEnabled() {
  	    fermer.setEnabled(true);
		nouveau.setEnabled(false);
		ouvrir.setEnabled(false);
		enregistrer.setEnabled(true);
	}

	public void aboutProject () {
		JFrame about = new JFrame("About");
		JLabel aboutLabel = new JLabel("Bianji est un logiciel libre développé par Aurélien CANO, Steven KIEFFER, Dorian KURZAJ et Benjamin TEISSEYRE dans le cadre du projet à l'IUT de Montpellier.", JLabel.CENTER);
		about.setSize(1000, 100);
		about.add(aboutLabel);
		about.setLocationRelativeTo(null);
		about.setVisible(true);
	}

	public void saveProject() {
		this.fenetre.saveProject();
	}

	public void newProject() {
		String name = JOptionPane.showInputDialog(null, "Project Name ?", "Project Name", JOptionPane.QUESTION_MESSAGE);
		if (name != null) {
		    if(name.length()!=0) {
		    	this.fenetre.setNomProjet(name);
				this.fenetre.enableContenu();
				this.changeEnabled();
		    }
		    else {
		    	JOptionPane.showMessageDialog(null,
		    			"Nom Incorrect...", "Nom Incorrect", JOptionPane.ERROR_MESSAGE);
		    	newProject();
			}
		}
	}
	
	public void restartProject() {
		this.saveProject();
		this.fenetre.dispose();
		this.fenetre.restartProject();
	}
}
