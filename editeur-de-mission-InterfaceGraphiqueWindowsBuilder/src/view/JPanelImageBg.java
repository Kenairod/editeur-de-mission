package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class JPanelImageBg extends JPanel {
	
	    Image image;
	    
	    public JPanelImageBg(Image image) {
	        this.image = image;
	    }
	    public JPanelImageBg() {
	    	this.image = null;
			this.setBackground(Color.RED);
	    }
	    public void setImage(Image image){
	        this.image = image;
	    }
	    public Image getImage(Image image){
	        return image;
	    }
	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        if (image != null) {
	            int height = this.getSize().height;
	            int width = this.getSize().width;        
	            g.drawImage(image,0,0, width, height, this);
	        }
	    }
}
