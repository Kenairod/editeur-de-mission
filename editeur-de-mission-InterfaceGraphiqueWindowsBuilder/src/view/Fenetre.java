package view;

import javax.swing.JFrame;

public class Fenetre extends JFrame {
	private LeMenu menu;
	
	public Fenetre(EditeurVue vue) {
		super();
		this.menu = new LeMenu();
		this.menu.setEtat();
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
	}
}
