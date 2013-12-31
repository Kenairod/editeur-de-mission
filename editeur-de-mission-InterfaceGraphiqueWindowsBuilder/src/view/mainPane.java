package view;

import javax.swing.JSplitPane;

/**
 * Représente le contenu principale de la fenêtre en dehors du menu (contient le panneau latéral et la scène)
 * @author Da Dream Team
 *
 */
public class mainPane extends JSplitPane {
	
	public mainPane() {
		super(JSplitPane.HORIZONTAL_SPLIT, new PanneauLateral(), new PanneauScene());
		this.setDividerLocation(200);
	}
}
