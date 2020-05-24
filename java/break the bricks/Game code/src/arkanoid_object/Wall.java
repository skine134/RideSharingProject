package arkanoid_object;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import arkanoid_object.*;

public class Wall extends JPanel{

	private BufferedImage image;
	private JPanel pan;
	public Wall() {
		pan=new WallImagePanel();
	}
	class WallImagePanel extends JPanel{
		 @Override
	        public void paint(Graphics g) {
				g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	        }
	       
	        @Override
	        public Dimension getPreferredSize() {
	            if (image == null) {
	                return new Dimension(600, 1000);
	            } else {
	                return new Dimension(image.getWidth(), image.getHeight());
	            }
	        }
	}
	public void setWall_image(String str) throws IOException {
		image=ImageIO.read(new File(str));
	}
	public JPanel getPan() {
		return pan;
	}
	
}
