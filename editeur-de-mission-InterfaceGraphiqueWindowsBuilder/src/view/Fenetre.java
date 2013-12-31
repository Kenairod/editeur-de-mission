package view;

import javax.swing.JFrame;

/**
 * La fenêtre principale
 * @author Da Dream Team
 *
 */
public class Fenetre extends JFrame {
	private EditeurVue vue;
	private LeMenu menu;
	private mainPane contenu;
	
	public Fenetre(EditeurVue vue) {
		super();
		this.vue = vue;
		this.menu = new LeMenu();
		this.menu.setEtat(0);
		this.contenu = new mainPane();
		//this.vue = vue;
		setTitle("Bianji - Éditeur de jeu vidéo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		//contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPane.setLayout(new BorderLayout(0, 0));
		//setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		//MenuBar menuBar = new MenuBar(this);
		//this.setJMenuBar(menuBar.getMenuBar());
		this.setVisible(true);
		this.setContentPane(this.contenu);
		this.setJMenuBar(this.menu);
	}
}
