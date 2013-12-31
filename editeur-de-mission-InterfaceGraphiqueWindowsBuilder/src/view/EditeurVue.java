package view;

import java.util.Observable;
import java.util.Observer;

import controler.EditeurControler;

/**
 * La vue
 * @author Da Dream Team
 *
 */
public class EditeurVue implements Observer {
	private Fenetre fenetre;
	private EditeurControler controler;

	@Override
	public void update(Observable arg0, Object arg1) {
		this.fenetre.repaint();
	}
	
	public EditeurVue(EditeurControler controler) {
		this.fenetre = new Fenetre(this);
	}
}
