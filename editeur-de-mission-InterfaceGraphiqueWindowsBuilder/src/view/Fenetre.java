package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
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
			/*this.initTree();
			this.initScroll();
			this.initSplit();*/
		}

		/**
		 * Permet de créer l'arbre du paneau latéral
		 */
		public void initTree() {
			String[] objets = new String[3];
			objets[0]  = "obj 1";
			objets[1]  = "obj 2";
			objets[2]  = "obj 3";
			this.liste = new JList(objets);
			this.getContentPane().add(this.tabbedPane, BorderLayout.CENTER);
			this.revalidate();
		}
		
		/**
		 * Permet de créer le scroll du panneau latéral
		 */
		public void initScroll() {
			this.scroll = new JScrollPane(this.tabbedPane);
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
		
		public void newProject () {
			this.initTree();
			this.initScroll();
			this.initSplit();
		}
		
		public void saveProject() {
			this.vue.saveProject();
		}
		
		public void importProject(String path) {
			this.vue.importProject(path);
		}
		
		public void setNomProjet(String name) {
			this.vue.setNomProjet(name);
		}
		
		public String getNomProjet() {
			return this.vue.getNomProjet();
		}
		/*public Project getProject() {
			return this.project;
		}
		
		public void setProject(Project project) {
			this.project = project;
		}*/
}
