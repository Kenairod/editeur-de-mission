package view;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
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
	private JPanelImageBg scene;

	
	public Fenetre(ArrayList<String> listeArtefacts, EditeurVue vue) {
		super();
		this.vue = vue;
		this.menu = new LeMenu(this);
		this.menu.setEtat(0);
		this.scene = new JPanelImageBg();
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
		this.onglet.repaint();
	}
	
	
	public void newProject () {
		this.getContentPane().setVisible(true);
	}
	
	public void oldProject () {
		this.getContentPane().setVisible(true);
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
		try {
			BufferedImage image = ImageIO.read(new File(urlBg));
			this.scene.setImage(image);
			this.scene.repaint();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
