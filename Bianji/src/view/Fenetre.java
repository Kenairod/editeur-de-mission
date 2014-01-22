package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import data.Objet;

/**
 * La fenêtre principale
 * @author Da Dream Team
 *
 */
public class Fenetre extends JFrame {
	
	private EditeurVue vue;
	private LeMenu menu;
	private JSplitPane contenu = new JSplitPane();
	private JPanel lateral = new JPanel();
	private JScrollPane scrollPane = new JScrollPane();
	private JTabbedPane onglets = new JTabbedPane();
	private ListPanneauLateral listeObjets;
	private ListSecondPanneauLateral listeMissions;
	private JPanelImageBg scene;
	private JPopupMenu jpm;
	private JMenuItem object = new JMenuItem("Insert a new Object");
	private JMenuItem bg = new JMenuItem("Define a new Background");
	private JMenuItem mission = new JMenuItem("Insert a new Mission");
	private JButton supprButtonImages = new JButton("Delete Object");
	private JButton supprButtonMissions = new JButton("Delete Mission");
	
	public Fenetre(List<String> listeMissions, List<Objet> listeObjets, String urlBg, EditeurVue vue) {
		super();
		this.vue = vue;
		this.menu = new LeMenu(this);
		this.scene = new JPanelImageBg(this);
		this.scene.setImage(urlBg);
		
		this.supprButtonImages.setEnabled(false);
		this.supprButtonMissions.setEnabled(false);

		JPanel panelObjets = new JPanel();
		panelObjets.setLayout(new BorderLayout());
		panelObjets.add(this.supprButtonImages, BorderLayout.SOUTH);
		
		JPanel panelMissions = new JPanel();
		panelMissions.setLayout(new BorderLayout());
		panelMissions.add(this.supprButtonMissions, BorderLayout.SOUTH);
		
		this.listeObjets = new ListPanneauLateral(listeObjets, this);
		panelObjets.add(this.listeObjets,BorderLayout.CENTER);
		
		this.listeMissions = new ListSecondPanneauLateral(listeMissions, this);
		panelMissions.add(this.listeMissions,BorderLayout.CENTER);
		
		this.onglets = new JTabbedPane(JTabbedPane.TOP);
		this.onglets.addTab("Objects", panelObjets);
		this.onglets.addTab("Missions", panelMissions);
		this.scrollPane = new JScrollPane(this.onglets);
		
		this.lateral.setLayout(new BorderLayout());
		this.lateral.add(this.scrollPane,BorderLayout.CENTER);
		
		this.contenu = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.lateral, this.scene);
		//Représente le contenu principale de la fenêtre en dehors du menu (contient le panneau latéral et la scène)
		
		this.contenu.setDividerLocation(200);
		this.contenu.setEnabled(false);
		
		this.setTitle("Bianji - Éditeur de jeu vidéo");

		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new AreYouSure());
		
		this.setBounds(0, 0, this.vue.getLargeur(), this.vue.getHauteur());
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(this.vue.getRedimensionnable());
		
		this.setContentPane(this.contenu);
		this.getContentPane().setVisible(false);
		this.setJMenuBar(this.menu);
	}
	
	public void openObjectDialog() {
		new AddObjectDialog(this, "Insert New Object", true);
	}
	
	public void openBgDialog() {
		new AddBackgroundDialog(this, "Define a New Background", true);
	}
	
	public void openMissionDialog() {
		new AddMissionDialog(this, "Insert New Mission", true);
	}
	
	public void showMenu() {
		this.object.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openObjectDialog();
			}
	    });
		
		this.bg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openBgDialog();
			}
	    });
		
		this.mission.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openMissionDialog();
			}
	    });
		
		this.listeObjets.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				if (SwingUtilities.isRightMouseButton(event)) {
					jpm = new JPopupMenu();
					jpm.add(bg);
					jpm.add(object);
					jpm.show(contenu, event.getX(), event.getY());
				}
			}
		});
		
		this.listeMissions.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				if (SwingUtilities.isRightMouseButton(event)) {
					jpm = new JPopupMenu();
					jpm.add(mission);
					jpm.show(contenu, event.getX(), event.getY());
				}
			}
		});
	}
	
	public void changeMissions(List<String> listeMissions) {
		this.listeMissions.setListe(listeMissions);
	}
	
	public void changeListeObjets(List<Objet> listeObjets) {
		this.listeObjets.setListe(listeObjets);
	}
	
	public List<Objet> getObjets() {
		return this.vue.getObjets();
	}
	
	public void enableContenu() {
		this.getContentPane().setVisible(true);
		this.showMenu();
	}
	
	public void saveProject() {
		this.vue.saveProject(this.listeObjets.getDraggysScene());
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
	
	public LeMenu getMenu() {
		return this.menu;
	}
	
	public void ajouterObjet(Objet o) {
		this.vue.ajouterObjet(o);
	}
	
	public void restartProject() {
		this.vue.restartProject();
	}
	
	public void ajouterBg(String urlBg) {
		this.vue.setFond(urlBg);
	}
	
	public void changeFond(String urlBg) {
		this.scene.setImage(urlBg);
		this.scene.repaint();
	}
	
	public JButton getSupprButtonImages(){
		return this.supprButtonImages;
	}
	
	public JButton getSupprButtonMissions(){
		return this.supprButtonMissions;
	}
	
	public void supprObjet(String idObjet) {
		this.vue.supprObjet(idObjet);
	}
	
	public JPanelImageBg getScene() {
		return this.scene;
	}
	
	private class AreYouSure extends WindowAdapter {  
		public void windowClosing(WindowEvent e) { 
			menu.exitProject();
		}
	}
	 
	 public int getLargeur() {
		return this.vue.getLargeur();
	 }
		
	public int getHauteur() {
		return this.vue.getHauteur();
	}
		
	public void setLargeur(int x) {
		this.vue.setLargeur(x);
	}
		
	public void setHauteur(int x) {
		this.vue.setHauteur(x);
	}
	
	public void resizeScene(int x, int y) {
		int hauteur = this.getHeight() - this.scene.getHeight();
		int largeur = this.getWidth() - this.scene.getWidth();
		this.setSize(largeur + x, hauteur + y);
		if (System.getProperty("os.name").equals("Linux")) {
			this.pack();
		}
		this.setLocationRelativeTo(null);
	}
	
	public ArrayList<LabelArtefact> getDraggysScene() {
		return this.listeObjets.getDraggysScene();
	}
	
	public void updateListeLabelArtef(ArrayList<LabelArtefact> elems) {
		this.menu.setListeLabelArtef(elems);
	}
	
	public int getLastIdObjet() {
		return this.vue.getLastIdObjet();
	}
	
	public void ajoutMission(String idMission) {
		this.vue.ajoutMission(idMission);
	}
	
	public void retireMission(String idMission) {
		this.vue.retireMission(idMission);
	}
	
	public List<String> getMissions() {
		return this.vue.getMissions();
	}
	
}
