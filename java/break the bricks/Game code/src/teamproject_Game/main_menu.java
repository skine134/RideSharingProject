package teamproject_Game;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.concurrent.Semaphore;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import arkanoid_object.BackgroundImage;
import arkanoid_object.character;

public class main_menu extends Thread {
	BackgroundImage back;
	ImageIcon startButtonEnterImage;
	ImageIcon startButtonBasicImage;
	JButton start;
	boolean flag;

//************************************메인화면
	main_menu() {
		back=Application.getBackImage();
		try {
			back.setBackGround_image("images//startTitle.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		startButtonEnterImage = new ImageIcon("images//startButton//startbuttonExited.png");
		startButtonBasicImage = new ImageIcon("images//startButton//startbutton.png");
		start = new JButton(startButtonBasicImage);
		start.setLocation(100, 500);
		start.setSize(400, 200);
		start.setBorderPainted(false);
		start.setContentAreaFilled(false);
		start.setFocusPainted(false);
		flag = false;
	}

	public void run() {
		Application.getCon().add(back.getPan());
		Application.getBackMusic().setMusic("sounds\\background_music\\title.mp3");
		Application.getCon().repaint();
		start.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				start.setIcon(startButtonEnterImage);
				start.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				start.setIcon(startButtonBasicImage);
				start.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Application.getCon().remove(start);
				flag = true;
				Application.getCon().repaint();
				Application.GameThread().start();
			}
		});
		back.getPan().add(start);
		Application.getCon().setComponentZOrder(start, 0);
		while (true) {
			if (flag)
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
