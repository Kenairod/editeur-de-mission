package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
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
	private JSplitPane contenu = new JSplitPane();
	private JPanel lateral = new JPanel();
	private JScrollPane scrollPane = new JScrollPane();
	private JTabbedPane onglets = new JTabbedPane();
	private ListPanneauLateral liste;
	private JPanelImageBg scene;
	private JPopupMenu jpm = new JPopupMenu();
	private JMenuItem object = new JMenuItem("Insert a new Object");
	private JMenuItem bg = new JMenuItem("Define a new Background");
	private JButton supprButton = new JButton("Delete Object");
	
	//private TabPanneauLateral onglet;

	
	public Fenetre(ArrayList<String> listeArtefacts, String urlBg, EditeurVue vue) {
		super();
		this.vue = vue;
		this.menu = new LeMenu(this);
		this.scene = new JPanelImageBg();
		this.scene.setImage(urlBg);
		
		this.supprButton.setEnabled(false);

		this.liste = new ListPanneauLateral(listeArtefacts,this);
		this.onglets = new JTabbedPane(JTabbedPane.TOP);
		this.onglets.addTab("Objects", this.liste);
		this.scrollPane = new JScrollPane(this.onglets);
		
		this.lateral.setLayout(new BorderLayout());
		this.lateral.add(this.scrollPane,BorderLayout.CENTER);
		this.lateral.add(this.supprButton, BorderLayout.SOUTH);
		
		this.contenu = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.lateral, this.scene); 
		//Représente le contenu principale de la fenêtre en dehors du menu (contient le panneau latéral et la scène)
		
		this.contenu.setDividerLocation(200);
		
		setTitle("Bianji - Éditeur de jeu vidéo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new AreYouSure());*/
		
		/*setLayout(new BorderLayout());
        pack();*/
        
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
	
	public void changeListeObjets(ArrayList<String> listeArtefacts) {
		this.liste.setListe(listeArtefacts);
	}
	
	public void changeFond(String urlBg) {
		this.scene.setImage(urlBg);
		this.scene.repaint();
	}
	
	public void enableContenu() {
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
	
	public JButton getSupprButton(){
		return this.supprButton;
	}
	
	public void supprObjet(String artefactPath) {
		this.vue.supprObjet(artefactPath);
	}
	
	public JPanelImageBg getScene() {
		return this.scene;
	}
	
	 private class AreYouSure extends WindowAdapter {  
	        public void windowClosing( WindowEvent e ) { 
	        	menu.exitProject();
	        }
	}
}
