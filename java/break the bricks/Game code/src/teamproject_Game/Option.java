package teamproject_Game;
import java.awt.Cursor;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import teamproject_Game.Game;
import arkanoid_object.Music;

public class Option extends Thread {
	ImagePanel option_title;
	
	ImageIcon ExitButtonBasicImage;
	ImageIcon ExitButtonEnterImage;
	
	JButton Exit;
	boolean flag;

//************************************����ȭ��
	public Option() {
		option_title = new ImagePanel("images//option_background.png");
		option_title.setSize(600, 1000);
		ExitButtonEnterImage=new ImageIcon("images//back//back_Enter.png");
		ExitButtonBasicImage = new ImageIcon("images//back//back_Basic.png");
		Exit=new JButton();
		Exit = new JButton(ExitButtonBasicImage);
		Exit.setLocation(50, 800);
		Exit.setSize(100, 100);
		Exit.setBorderPainted(false);
		Exit.setContentAreaFilled(false);
		Exit.setFocusPainted(false);
		flag = false;
	}

	public void run() {
		
		//Game.getmove_th().suspend();
		Game.getCon().add(option_title);
		Music introMusic = new Music("sounds\\background_music\\title.mp3", true);
		Game.getCon().setComponentZOrder(option_title, 0);
		Game.getCon().repaint();
		//new Thread(introMusic.th).start();
		Exit.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				Exit.setIcon(ExitButtonEnterImage);
				Exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Exit.setIcon(ExitButtonBasicImage);
				Exit.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				flag=true;
				Game.getCon().remove(option_title);
				Game.getCon().remove(Exit);
				//Game.getmove_th().resume();
				Game.getCon().repaint();
				introMusic.close();
				return;
			}
		});
		option_title.add(Exit);
		while (true) {
			if (flag == true)
				break;
			try {
				Thread.sleep(1000 / 60);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	class ImagePanel extends JPanel {
		Image image;

		public ImagePanel(String filename) {
			image = new ImageIcon(filename).getImage();
		}

		@Override
		public void paint(Graphics g) {
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			setOpaque(false);
			super.paint(g);
		}

	}
}