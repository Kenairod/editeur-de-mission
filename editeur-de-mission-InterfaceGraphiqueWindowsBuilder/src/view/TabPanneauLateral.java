package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;

public class TabPanneauLateral extends JTabbedPane {
	
	private JPopupMenu jpm = new JPopupMenu();
	private JMenuItem insertion = new JMenuItem("Insert a new Object");
	private Fenetre fenetre;
	
	public TabPanneauLateral(Fenetre fen) {
		super(JTabbedPane.TOP);
		this.addTab("Objects", new ListPanneauLateral());
		
		this.fenetre = fen;
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
