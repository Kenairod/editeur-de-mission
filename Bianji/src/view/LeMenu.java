package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

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
	* L'item exit
	*/
	private JMenuItem quitter = new JMenuItem("Exit");
	
	//Les items d'édition
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
	
	//Les items de zoom
	/**
	* L'item zoom +
	*/
	private JMenuItem zoom = new JMenuItem("Zoom +");
	/**
	* L'item zoom -
	*/
	private JMenuItem dezoom = new JMenuItem("Zoom -");
	
	//Les items d'aide
	/**
	* L'item Help
	*/
	private JMenuItem aideItem = new JMenuItem("Help");
	/**
	* L'item à propos
	*/
	private JMenuItem a_propos = new JMenuItem("About Bianji...");
	
	ArrayList<LabelArtefact> listeLabelArtef = new ArrayList<LabelArtefact>();

	
	public LeMenu (Fenetre fenetre) {
		this.fenetre = fenetre;
		
		this.nouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		this.fichier.add(this.nouveau);
		this.nouveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newProject();    
			}
	    });
              
		this.ouvrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
		this.fichier.add(this.ouvrir);
		this.ouvrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openProject();    
			}
	    });
       
		this.fermer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.CTRL_DOWN_MASK));
	 	this.fichier.add(this.fermer);
	 	this.fermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				restartProject();    
			}
	    });
              
		this.fichier.addSeparator();//-------
              
		this.enregistrer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		this.fichier.add(this.enregistrer);
		this.enregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveProject(); 
			}
	    });	 
              
		this.fichier.addSeparator();//-------
              
		this.quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_DOWN_MASK));
		this.fichier.add(this.quitter);
		this.quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exitProject();    
			}
	    });
              
		//Menu edition
		this.precedent.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
		this.edition.add(this.precedent);
              
		this.suivant.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK));
		this.edition.add(this.suivant);
              
		this.edition.addSeparator();//-------
              
		this.couper.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
		this.edition.add(this.couper);
              
		this.copier.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
		this.edition.add(this.copier);
              
		this.coller.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
        this.edition.add(this.coller);
              
		//Menu vue
        this.zoom.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, KeyEvent.CTRL_DOWN_MASK));
		this.vue.add(this.zoom);
			  
		this.dezoom.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, KeyEvent.CTRL_DOWN_MASK));
		this.vue.add(this.dezoom);
			  
		//Menu help
		this.aideItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, KeyEvent.CTRL_DOWN_MASK));
		this.aide.add(this.aideItem);
			  
		this.aide.addSeparator(); //-------
			  
		//this.a_propos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, KeyEvent.CTRL_DOWN_MASK));
		this.aide.add(this.a_propos);
		this.a_propos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				aboutProject();    
			}
		});
			  
		this.fichier.setMnemonic('F');
		this.add(this.fichier);
		this.edition.setMnemonic('E');
		this.add(this.edition);
		this.vue.setMnemonic('V');
		this.add(this.vue);
		this.aide.setMnemonic('H');
		this.add(this.aide);
			  
		if (this.fenetre.getNomProjet().length()==0) {
			this.setAllFalse();
		} 
	}
	
	public void setAllFalse() {
		this.enregistrer.setEnabled(false);
		this.fermer.setEnabled(false);
		this.coller.setEnabled(false);
		this.copier.setEnabled(false);
		this.couper.setEnabled(false);
		this.suivant.setEnabled(false);
		this.precedent.setEnabled(false);
		this.zoom.setEnabled(false);
		this.dezoom.setEnabled(false);
		this.aideItem.setEnabled(false);
	}
	
	public void setEnregistrer(boolean b) {
		this.enregistrer.setEnabled(b);
	}
	
	public void setListeLabelArtef(ArrayList<LabelArtefact> la) {
		
	}
	
	public void exitProject () {
		int exit = JOptionPane.showConfirmDialog(this.fenetre, "Do you really want to quit ?", "Exit", JOptionPane.OK_CANCEL_OPTION);
		if(exit == 0) {
			if(this.fenetre.getNomProjet().length() != 0) {
				this.saveProject();
			}
			this.fenetre.dispose();
		}
	}

	public void openProject() {
		JFileChooser chooser = new JFileChooser(".");
		
        FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("XML Files (*.xml)", "xml");
        
        chooser.addChoosableFileFilter(xmlFilter);

        chooser.setFileFilter(xmlFilter);
        
        if (chooser.showOpenDialog(null) == 0) {
        	String[] str = chooser.getSelectedFile().getName().split(".xml");
        	this.fenetre.importProject(chooser.getSelectedFile().toString(),str[0]);
        	this.fenetre.enableContenu();
        	this.changeEnabled();
        	this.fenetre.resizeScene(this.fenetre.getLargeur(), this.fenetre.getHauteur());
        	
        	for(int i=0; i < listeLabelArtef.size(); i++) {
        		this.fenetre.getDraggysScene().add(this.listeLabelArtef.get(i));
        		this.fenetre.getScene().add(this.listeLabelArtef.get(i));
        		this.fenetre.getScene().validate();
				this.fenetre.getScene().repaint();
        	}
        }   
	}
	
	public void changeEnabled() {
		this.fermer.setEnabled(true);
		this.nouveau.setEnabled(false);
		this.ouvrir.setEnabled(false);
		this.enregistrer.setEnabled(true);
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
		if(this.fenetre.getStateChanged()) {
			this.fenetre.saveProject();
		}
		this.fenetre.setStateChanged(false);
		this.enregistrer.setEnabled(false);
	}

	public void newProject() {
		String name = JOptionPane.showInputDialog(null, "Project Name ?", "Project Name", JOptionPane.QUESTION_MESSAGE);
		if (name != null) {
		    if(name.length()!=0) {
		    	this.fenetre.setNomProjet(name);
				this.changeEnabled();
		    }
		    else {
		    	JOptionPane.showMessageDialog(null,
		    			"Nom Incorrect...", "Nom Incorrect", JOptionPane.ERROR_MESSAGE);
		    	this.newProject();
			}

		    this.setDimensions();
		}
	}
	
	public void setDimensions() {
		SetDimensionDialog aod = new SetDimensionDialog(this.fenetre, "Set Dimensions", true);
	}
	
	public void restartProject() {
		this.saveProject();
		this.fenetre.dispose();
		this.fenetre.restartProject();
	}
	
}
