package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class Fenetre extends JFrame{
	
		private JPanel contentPane;
		
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
		private JList liste;
		private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		private EditeurVue vue;
		
		 private JPopupMenu jpm = new JPopupMenu();
		  
		 private JMenuItem insertion = new JMenuItem("Insert a new Object");
		  
		/**
		 * Constructeur par défaut
		 * Create the frame.
		 */
		public Fenetre(EditeurVue vue) {
			super();
			this.vue = vue;
			setTitle("Bianji - Éditeur de jeu vidéo");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 800, 600);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);
			this.setLocationRelativeTo(null);
			MenuBar menuBar = new MenuBar(this);
		    this.setJMenuBar(menuBar.getMenuBar());
			this.setVisible(true);
		}
		
		/**
		 * Permet de créer l'arbre du paneau latéral
		 */
		public void initList(ArrayList<String> artefacts) {
			//ArrayList<String> artefacts = this.getListeNoms();
			this.liste = new JList(artefacts.toArray());
			this.getContentPane().add(this.tabbedPane, BorderLayout.CENTER);
			
			this.revalidate();
		}
		
		/**
		 * Permet de créer le scroll du panneau latéral
		 */
		public void initScroll() {
			this.scroll = new JScrollPane(this.tabbedPane);
			insertion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					openZDialog();
				}
		    });
			scroll.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent event) {
					 if(event.isPopupTrigger()){       
				          jpm.add(insertion);
				          jpm.show(contentPane, event.getX(), event.getY());	//La méthode qui va afficher le menu
				          
				     }
				}
			});
			//this.scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			this.getContentPane().add(this.scroll, BorderLayout.CENTER);
			
			scroll.setColumnHeaderView(tabbedPane);
			
			this.tabbedPane.addTab("Object", this.liste);
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
	
		public void openZDialog() {
			ZDialog zd = new ZDialog(this, "Insert New Artefact", true);
		}
		
		public void newProject () {
			this.initScroll();
			this.initSplit();
		}
		
		public void oldProject () {
			this.initList(this.vue.getListeNoms());
			this.initScroll();
			this.initSplit();
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
		
		/*public Project getProject() {
			return this.project;
		}
		
		public void setProject(Project project) {
			this.project = project;
		}*/
}
