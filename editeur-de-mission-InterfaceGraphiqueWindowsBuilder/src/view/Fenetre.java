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
	private MainPane contenu;
	
	public Fenetre(EditeurVue vue) {
		super();
		this.vue = vue;
		this.menu = new LeMenu();
		this.menu.setEtat(0);
		this.contenu = new MainPane();
		
		setTitle("Bianji - Éditeur de jeu vidéo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		this.setContentPane(this.contenu);
		this.setJMenuBar(this.menu);
	}
}
