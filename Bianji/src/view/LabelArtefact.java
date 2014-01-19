package view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import data.Objet;

public class LabelArtefact extends JLabel {
	private int x = 0;
	private int y = 0;
	private Objet objet;
	
	public LabelArtefact(ImageIcon icon, Objet o) {
		super(icon);
		this.objet = o;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		this.setLocation(this.x, this.y);
	}
	
	public Objet getObjet() {
		return this.objet;
	}
	
	@Override
	public void validate() {
		super.validate();
		this.setLocation(this.x, this.y);
	}
	
}
