package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * Là où sont listés tous les objets du projet
 * @author Da Dream Team
 *
 */
public class ListPanneauLateral extends JList {
	
	private JPopupMenu jpm = new JPopupMenu();
	private JMenuItem object = new JMenuItem("Insert a new Object");
	private JMenuItem bg = new JMenuItem("Define a new Background");

	private String [] listeTab;
	private Fenetre fenetre;
	
	public void setListe(String[] list) {
		for (int i = 0; i < list.length; i++) {
			this.listeTab[i] = list[i];
		}
	}
	
	public ListPanneauLateral(String [] listeTab, Fenetre fenetre) {
		super(listeTab);
		this.listeTab = listeTab;
		this.fenetre = fenetre;
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
