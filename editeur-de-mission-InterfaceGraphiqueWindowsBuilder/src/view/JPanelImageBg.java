package view;

import java.awt.Color;
import java.awt.Component;
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


public class JPanelImageBg extends JPanel {
	
	    private TexturePaint texture; 
		private BufferedImage bufferedImage; 
		
		public JPanelImageBg() {
			super();
			MoveMeMouseHandler handler = new MoveMeMouseHandler();
            addMouseListener(handler);
            addMouseMotionListener(handler);
	    }
		
		public void setImage (String fileName) {
			File f = new File(fileName);
			if(f.exists()) {
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

			BufferedImage bufferedImage = new BufferedImage( image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB); 
			Graphics g = bufferedImage.createGraphics(); 

			g.setColor(Color.white); 
			g.fillRect(0, 0, image.getWidth(null), 
			image.getHeight(null)); 
			g.drawImage(image, 0, 0, null); 
			g.dispose(); 
			return bufferedImage; 
		}
		
		public class MoveMeMouseHandler extends MouseAdapter {

	        private int xOffset;
	        private int yOffset;
	        private JLabel draggy;

	        public void mousePressed(MouseEvent me) {
	            JComponent comp = (JComponent) me.getComponent();
	            Component child = comp.findComponentAt(me.getPoint());
	            if (child instanceof JLabel) {
	                xOffset = me.getX() - child.getX();
	                yOffset = me.getY() - child.getY();
	                draggy = (JLabel) child;
	            }
	        }

	        public void mouseDragged(MouseEvent me) {
	            if (draggy != null) {
	                draggy.setLocation(me.getX() - xOffset, me.getY() - yOffset);
	            }
	        }
	    }

	}
