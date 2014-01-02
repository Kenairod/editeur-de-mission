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
	private JMenuItem object = new JMenuItem("Insert a new Object");
	private JMenuItem bg = new JMenuItem("Define a new Background");
	private Fenetre fenetre;
	private JList<String> liste = new JList<String>();
	
	public void setListe(ArrayList<String> list) {
		System.out.println("LALIIIISTE :"+list+" De taille : "+list.size());
		//if (list != null) {
			//String[] tab = new String[list.size()];
			String [] tab = {"Obj 1","Obj 2"};
			for (int i = 0; i < list.size(); i++) {
				tab[i] = list.get(i);
				System.out.println("Element : "+i+"Valeur : "+tab[i]);
			}
			this.liste = new JList<String>(tab);
		//}
		this.liste.repaint();
		this.repaint();
	}
	
	public TabPanneauLateral(ArrayList<String> list, Fenetre fenetre) {
		super(JTabbedPane.TOP);
		this.fenetre = fenetre;
		this.setListe(list);
		this.addTab("Objects", this.liste);
		this.showMenu();
	}
	
	public void openObjectDialog() {
		AddObjectDialog aod = new AddObjectDialog(fenetre, "Insert New Artefact", true);
	}
	
	public void openBgDialog() {
		AddBackgroundDialog obd = new AddBackgroundDialog(fenetre, "Define a New Background", true);
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
		this.getComponent(0).addMouseListener(new MouseAdapter() {
		public void mouseReleased(MouseEvent event) {
			 if(event.isPopupTrigger()){       
				 jpm.add(bg);
				 jpm.add(object);
		         jpm.show(fenetre.getContentPane(), event.getX(), event.getY());	//La méthode qui va afficher le menu
		          
		     }
		}
	});
	
	}
	
}
