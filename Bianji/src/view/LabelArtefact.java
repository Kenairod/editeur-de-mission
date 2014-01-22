package view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import data.Objet;

public class LabelArtefact extends JLabel {
	
	private int x;
	private int y;
	private Objet objet;
	
	public LabelArtefact(ImageIcon icon, Objet o) {
		super(icon);
		this.objet = o;
		this.x = 0; this.y = 0;
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
