package view;

import javax.swing.JTabbedPane;

public class TabPanneauLateral extends JTabbedPane {
	public TabPanneauLateral() {
		super(JTabbedPane.TOP);
		this.addTab("Objects", new ListPanneauLateral());
	}
}
