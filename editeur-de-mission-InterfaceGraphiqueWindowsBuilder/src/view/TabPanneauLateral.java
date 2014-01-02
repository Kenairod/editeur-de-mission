package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;

public class TabPanneauLateral extends JTabbedPane {
	
	private JPopupMenu jpm = new JPopupMenu();
	private JMenuItem insertion = new JMenuItem("Insert a new Object");
	private Fenetre fenetre;
	private JList<String> liste = new JList<String>();
	
	public void setListe(ArrayList<String> list) {
		if (list != null) {
			String[] tab = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				tab[i] = list.get(i);
			}
			this.liste = new JList<String>(tab);
		}
		System.out.println("LALIIIISTE :"+list);
		String [] tab = {"Obj 1", "objet 2"};
		this.liste = new JList<String>(tab);
	}
	
	public TabPanneauLateral(ArrayList<String> list) {
		super(JTabbedPane.TOP);
		this.setListe(list);
		this.addTab("Objects", this.liste);
		this.showMenu();
	}
	
	public void openZDialog() {
		ZDialog zd = new ZDialog(fenetre, "Insert New Artefact", true);
	}
	
	public void showMenu() {
		insertion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openZDialog();
			}
	    });
		this.getComponent(0).addMouseListener(new MouseAdapter() {	// Sur le rouge et pas la liste (à régler)
		public void mouseReleased(MouseEvent event) {
			 if(event.isPopupTrigger()){       
		          jpm.add(insertion);
		          jpm.show(fenetre.getContentPane(), event.getX(), event.getY());	//La méthode qui va afficher le menu
		          
		     }
		}
	});
	
	}
	
}
