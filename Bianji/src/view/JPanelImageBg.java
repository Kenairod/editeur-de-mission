package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class JPanelImageBg extends JPanel {
	
	    private TexturePaint texture; 
		private BufferedImage bufferedImage;
		private Fenetre fenetre;
		
		public JPanelImageBg(Fenetre fenetre) {
			super(new FlowLayout(FlowLayout.LEFT));
			this.fenetre = fenetre;
			MoveMeMouseHandler handler = new MoveMeMouseHandler();
			this.addMouseListener(handler);
			this.addMouseMotionListener(handler);
	    }
		
		public void setImage (String fileName) {
			File f = new File(fileName);
			if (f.exists()) {
				this.bufferedImage = this.toBufferedImage(Toolkit.getDefaultToolkit().getImage(fileName));
				this.texture = new TexturePaint(bufferedImage,new Rectangle(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight()));
			}
		} 

		public void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D)g; 
			g2d.setPaint(texture);
			g2d.fillRect(0, 0, getWidth(), getHeight());	
		}


		public BufferedImage toBufferedImage(Image image) {	
			
			image = new ImageIcon(image).getImage(); 

			BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB); 
			Graphics g = bufferedImage.createGraphics(); 

			g.setColor(Color.white); 
			g.fillRect(0, 0, image.getWidth(null), image.getHeight(null)); 
			g.drawImage(image, 0, 0, null); 
			g.dispose(); 
			return bufferedImage; 
		}
		
		private class MoveMeMouseHandler extends MouseAdapter {

	        private int xOffset;
	        private int yOffset;
	        private LabelArtefact draggy;

	        public void mousePressed(MouseEvent me) {
	            JComponent comp = (JComponent) me.getComponent();
	            Component child = comp.findComponentAt(me.getPoint());
	            if (child instanceof JLabel) {
	                xOffset = me.getX() - child.getX();
	                yOffset = me.getY() - child.getY();
	                draggy = (LabelArtefact) child;
	                if (SwingUtilities.isRightMouseButton(me)) {
						Container parent = draggy.getParent();
						parent.remove(draggy);
						parent.validate();
						parent.repaint();
						fenetre.getDraggysScene().remove(draggy);
					}
	                fenetre.setStateChanged(true);
	        		fenetre.getMenu().setEnregistrer(true);
	            }
	        }

	        public void mouseDragged(MouseEvent me) {
	            if (draggy != null) {
	            	
	            	draggy.setPosition(me.getX() - xOffset, me.getY() - yOffset);
	            	
	            	fenetre.setStateChanged(true);
	        		fenetre.getMenu().setEnregistrer(true);
	            	
	            	if (me.getX() - xOffset <= 0)
	            		draggy.setPosition(0, me.getY() - yOffset);
	            	
	            	if (me.getY() - yOffset <= 0)
	            		draggy.setPosition(me.getX() - xOffset, 0);
	            	
	            	if (me.getX() - xOffset <= 0 && me.getY() - yOffset <= 0)
	            		draggy.setPosition(0, 0);
	            	
	            	if (me.getX() - xOffset + draggy.getWidth() >= getWidth())
	            		draggy.setPosition(getWidth() - draggy.getWidth(), me.getY() - yOffset);
	            	
	            	if (me.getY() - yOffset + draggy.getHeight() >= getHeight())
	            		draggy.setPosition(me.getX() - xOffset, getHeight() - draggy.getHeight());
	            	
	            	if (me.getX() - xOffset + draggy.getWidth() >= getWidth() && me.getY() - yOffset + draggy.getHeight() >= getHeight())
	            		draggy.setPosition(getWidth() - draggy.getWidth(), getHeight() - draggy.getHeight());
	            	
	            	if (me.getX() - xOffset + draggy.getWidth() >= getWidth() && me.getY() - yOffset <= 0)
	            		draggy.setPosition(getWidth() - draggy.getWidth(), 0);
	            	
	            	if (me.getY() - yOffset + draggy.getHeight() >= getHeight() && me.getX() - xOffset <= 0)
	            		draggy.setPosition(0, getHeight() - draggy.getHeight());
	            	
	            }
	        }
	    }

}
