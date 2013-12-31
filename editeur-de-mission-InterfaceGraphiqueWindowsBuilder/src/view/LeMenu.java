package view;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class LeMenu extends JMenuBar{
	
	/**
	 * Permet de savoir si quels item on doit griser (une Enum pourrait être envisageable)
	 * Par exemple si on projet est ouvert ou fermer
	 */
	private int etat;
	
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

	public void setEtat(int etat) {
		this.etat = etat;
	}


	public LeMenu () {
		nouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		this.fichier.add(nouveau);
              
		ouvrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
		this.fichier.add(ouvrir);
       
		/*fermer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.CTRL_DOWN_MASK));
	 	this.fichier.add(fermer);
  	    fermer.setEnabled(false);*/
              
		this.fichier.addSeparator();//-------
              
		enregistrer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		this.fichier.add(enregistrer);
              
		this.fichier.addSeparator();//-------
              
		quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_DOWN_MASK));
		this.fichier.add(quitter);
              
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
              
              fichier.setMnemonic('F');
              this.add(fichier);
              edition.setMnemonic('E');
              this.add(edition);
              vue.setMnemonic('V');
              this.add(vue);
              aide.setMnemonic('H');
              this.add(aide);
	}
}
