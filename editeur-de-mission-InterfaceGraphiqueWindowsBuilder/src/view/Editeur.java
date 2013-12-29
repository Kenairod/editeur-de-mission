package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;


public class Editeur extends JFrame {

	private JPanel contentPane;

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
	private JMenuItem fermer = new JMenuItem("Close Document");
	/**
	 * L'item enregistrer
	 */
	private JMenuItem enregistrer = new JMenuItem("Save");
	/**
	 * L'item enregistrer sous
	 */
	private JMenuItem enregistrer_sous = new JMenuItem("Save As");
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
	
	/**
	 * La pane permettant de splitter la scène et le panneau latéral
	 */
	private JSplitPane split;
	/**
	 * La pane qui permet de scroller dans le panneau latéral
	 */
	private JScrollPane scroll;
	/**
	 * La pane où se trouve les objets
	 */
	private JTree arbre;
	
	
	/**
	 * Constructeur par défaut
	 * Create the frame.
	 */
	public Editeur() {
		setTitle("Bianji - Éditeur de jeu vidéo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.initMenu();
		this.initTree();
		this.initScroll();
		this.initSplit();
	}

	/**
	 * Permet de créer l'arbre du paneau latéral
	 */
	public void initTree() {
		DefaultMutableTreeNode racine = new DefaultMutableTreeNode("Objets");
		for(int j = 1; j < 5; j++){
			DefaultMutableTreeNode rep2 = new DefaultMutableTreeNode("Objet n°" + j);
			racine.add(rep2);
		}
		this.arbre = new JTree(racine);
		this.getContentPane().add(this.arbre, BorderLayout.CENTER);
		this.revalidate();
	}
	
	/**
	 * Permet de créer le scroll du panneau latéral
	 */
	public void initScroll() {
		this.scroll = new JScrollPane(this.arbre);
		//this.scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.getContentPane().add(this.scroll, BorderLayout.CENTER);
		this.revalidate();
	}
	
	/**
	 * Permet de créer le split séparant la sène et le panneau latéral
	 */
	public void initSplit() {
		MainPanel principal = new MainPanel();
		this.split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.scroll, principal);
		this.getContentPane().add(this.split, BorderLayout.CENTER);
		this.split.setOneTouchExpandable(true);
		this.split.setDividerLocation(200);
		this.revalidate();
	}
	
	/**
	 * Permet d'initialiser la barre de menu
	 */
	public void initMenu() {
		//Menu fichier
		nouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		this.fichier.add(nouveau);
		ouvrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
		this.fichier.add(ouvrir);
		fermer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.CTRL_DOWN_MASK));
		this.fichier.add(fermer);
		fermer.setEnabled(false);
		this.fichier.addSeparator();//-------
		enregistrer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		this.fichier.add(enregistrer);
		enregistrer.setEnabled(false);
		enregistrer_sous.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
		this.fichier.add(enregistrer_sous);
		enregistrer_sous.setEnabled(false);
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
		// Fermeture de la fenêtre en appuyant sur 'Exit'
		quitter.addActionListener(new QuitListener());
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
		//Menu edition
		zoom.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, KeyEvent.CTRL_DOWN_MASK));
		this.vue.add(zoom);
		dezoom.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, KeyEvent.CTRL_DOWN_MASK));
		this.vue.add(dezoom);
		//Menu edition
		aideItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, KeyEvent.CTRL_DOWN_MASK));
		this.aide.add(aideItem);
		this.aide.addSeparator();//-------
		//a_propos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, KeyEvent.CTRL_DOWN_MASK));
		this.aide.add(a_propos);
		
		fichier.setMnemonic('F');
		this.menuBar.add(fichier);
		edition.setMnemonic('E');
		this.menuBar.add(edition);
		vue.setMnemonic('V');
		this.menuBar.add(vue);
		aide.setMnemonic('H');
		this.menuBar.add(aide);
		
		this.setJMenuBar(menuBar);
		this.revalidate();
	}

	private class QuitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	} 

}