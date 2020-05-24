package arkanoid_object;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
public class BackgroundImage extends JPanel{
	private BufferedImage image;
	private JPanel pan;
	public BackgroundImage() {
		pan=new BackGroundImagePanel();
		pan.setSize(600,1000);
	}
	class BackGroundImagePanel extends JPanel{
		 @Override
	        public void paint(Graphics g) {
	            g.drawImage(image, 0, 0, null);
	        }
	       
	        @Override
	        public Dimension getPreferredSize() {
	            if (image == null) {
	                return new Dimension(600, 1000);
	            } else {
	                return new Dimension(600, 1000);
	            }
	        }
	}
	//공을 가지고 있는 패널을 반환해줌
	public JPanel getPan() {
		return pan;
	}
	public void setBackGround_image(String str) throws IOException {
		image=ImageIO.read(new File(str));
	}
	
}
