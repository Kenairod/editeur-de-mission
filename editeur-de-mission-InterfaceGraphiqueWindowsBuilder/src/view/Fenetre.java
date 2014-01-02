package view;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

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
			private TabPanneauLateral onglet;
		private JPanel scene;
	/*private JPopupMenu jpm = new JPopupMenu();
	private JMenuItem insertion = new JMenuItem("Insert a new Object");*/
	
	public Fenetre(ArrayList<String> listeArtefacts, EditeurVue vue) {
		super();
		this.vue = vue;
		this.menu = new LeMenu(this);
		this.menu.setEtat(0);
		this.scene = new JPanel();
		this.scene.setBackground(Color.RED);
		this.onglet = new TabPanneauLateral(listeArtefacts, this);
		this.onglet.repaint();
		this.scrollPane = new JScrollPane(this.onglet);
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
	
	public void changeListeObjets(ArrayList<String> listeArtefacts) {
		this.onglet.setListe(listeArtefacts);
	}
	
	/*public void openZDialog() {
		ZDialog zd = new ZDialog(this, "Insert New Artefact", true);
	}
	
	public void showMenu() {
		insertion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openZDialog();
			}
	    });
		contenu.addMouseListener(new MouseAdapter() {	// Sur le rouge et pas la liste (à régler)
			public void mouseReleased(MouseEvent event) {
				 if(event.isPopupTrigger()){       
			          jpm.add(insertion);
			          jpm.show(contenu, event.getX(), event.getY());	//La méthode qui va afficher le menu
			          
			     }
			}
		});
		
	}*/
	
	public void newProject () {
		this.getContentPane().setVisible(true);
		//showMenu();
	}
	
	public void oldProject () {
		this.getContentPane().setVisible(true);
		//showMenu();
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
}
