package view;

import java.util.ArrayList;

import javax.swing.JSplitPane;

/**
 * Représente le contenu principale de la fenêtre en dehors du menu (contient le panneau latéral et la scène)
 * @author Da Dream Team
 *
 */
public class MainPane extends JSplitPane {
	
	public MainPane(Fenetre fen) {
		super(JSplitPane.HORIZONTAL_SPLIT, new ScrollPanneauLateral(fen), new PanneauScene());
		this.setDividerLocation(200);
	}
}