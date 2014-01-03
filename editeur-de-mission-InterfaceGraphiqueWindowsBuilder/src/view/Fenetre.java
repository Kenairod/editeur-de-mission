package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

/**
 * La fenêtre principale
 * @author Da Dream Team
 *
 */
public class Fenetre extends JFrame {
	private EditeurVue vue;
	private LeMenu menu;
	private JSplitPane contenu;
	private JScrollPane scrollPane;
	//private TabPanneauLateral onglet;
	private JTabbedPane onglets;
	private ListPanneauLateral liste;
	private JPanelImageBg scene;
	private JPopupMenu jpm = new JPopupMenu();
	private JMenuItem object = new JMenuItem("Insert a new Object");
	private JMenuItem bg = new JMenuItem("Define a new Background");

	
	public Fenetre(String [] listeArtefacts, String urlBg, EditeurVue vue) {
		super();
		this.vue = vue;
		this.menu = new LeMenu(this);
		this.menu.setEtat(0);
		this.scene = new JPanelImageBg();
		this.scene.setImage(urlBg);
		this.liste = new ListPanneauLateral(listeArtefacts);
		this.liste.setListe(listeArtefacts);
		this.onglets = new JTabbedPane(JTabbedPane.TOP);
		this.onglets.addTab("Objects", this.liste);
		//this.onglet.repaint();
		this.scrollPane = new JScrollPane(this.onglets);
		this.contenu = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.scrollPane, this.scene); //Représente le contenu principale de la fenêtre en dehors du menu (contient le panneau latéral et la scène)
		this.contenu.setDividerLocation(200);
		
		setTitle("Bianji - Éditeur de jeu vidéo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		this.setContentPane(this.contenu);
		this.getContentPane().setVisible(false);
		this.setJMenuBar(this.menu);
	}
	
	public void openObjectDialog() {
		AddObjectDialog aod = new AddObjectDialog(this, "Insert New Artefact", true);
	}
	
	public void openBgDialog() {
		AddBackgroundDialog obd = new AddBackgroundDialog(this, "Define a New Background", true);
	}
	
	public void showMenu() {
		object.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openObjectDialog();
			}
	    });
		bg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openBgDialog();
			}
	    });
		this.onglets.getComponent(0).addMouseListener(new MouseAdapter() {
		public void mouseReleased(MouseEvent event) {
			 if(event.isPopupTrigger()){       
				 jpm.add(bg);
				 jpm.add(object);
		         jpm.show(contenu, event.getX(), event.getY());	//La méthode qui va afficher le menu
		          
		     }
		}
	});
	}
	
	public void changeListeObjets(String [] listeArtefacts) {
		this.liste.setListe(listeArtefacts);
	}
	
	public void changeFond(String urlBg) {
		this.scene.setImage(urlBg);
		this.scene.repaint();
	}
	
	public void newProject () {
		this.getContentPane().setVisible(true);
		showMenu();
	}
	
	public void oldProject () {
		this.getContentPane().setVisible(true);
		showMenu();
	}
	
	public void saveProject() {
		this.vue.saveProject();
	}
	
	public void importProject(String path, String nom) {
		this.vue.importProject(path,nom);
	}
	
	public void setNomProjet(String name) {
		this.vue.setNomProjet(name);
	}
	
	public String getNomProjet() {
		return this.vue.getNomProjet();
	}
	
	public void ajouterObjet(String idArtefact, String urlRelativeArtefact, String scriptAgent) {
		this.vue.ajouterObjet(idArtefact, urlRelativeArtefact, scriptAgent);
	}
	
	public void restartProject() {
		this.vue.restartProject();
	}
	
	public void ajouterBg(String urlBg) {
		this.vue.setFond(urlBg);
		this.changeFond(urlBg);
	}
}
