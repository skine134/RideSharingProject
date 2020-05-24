package teamproject_Game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import arkanoid_object.character;

public class Fail_stage extends Thread {

	ImagePanel fail;
	ImageIcon retryButtonEnterImage;
	ImageIcon retryButtonBasicImage;
	
	ImageIcon ExitEnter;
	ImageIcon ExitBasic;
	
	ImageIcon menuButtonEnterImage;
	ImageIcon menuButtonBasicImage;
	
	JButton menu;
	JButton retry;
	JButton Exit;
	boolean flag;
	character ch;
	public Fail_stage(character ch){
		this.ch=ch;
		if(ch.getPan().isFocusable()) {
			ch.getPan().setFocusable(false);
		}
		//background
		fail = new ImagePanel("images//Fail_background.png");
		fail.setSize(600, 1000);
		fail.setLayout(null);
		//retrybutton
		retryButtonEnterImage = new ImageIcon("images//RestartButton//retryButton_Enter.png");
		retryButtonBasicImage = new ImageIcon("images//RestartButton//retryButton_Basic.png");
		retry = new JButton(retryButtonBasicImage);
		retry.setLocation(0, 500);
		retry.setSize(200, 152);
		retry.setBorderPainted(false);
		retry.setContentAreaFilled(false);
		retry.setFocusPainted(false);
		
		//exitbutton
		ExitEnter = new ImageIcon("images//ExitButton//ExitButton_Enter.png");
		ExitBasic = new ImageIcon("images//ExitButton//ExitButton_Basic.png");
		Exit = new JButton(ExitBasic);
		Exit.setLocation(150, 500);
		Exit.setSize(100, 123);
		Exit.setBorderPainted(false);
		Exit.setContentAreaFilled(false);
		Exit.setFocusPainted(false);
		
		//menubutton
		menuButtonEnterImage = new ImageIcon("images//mainmenuButton//mainmenu_Enter.png");
		menuButtonBasicImage = new ImageIcon("images//mainmenuButton//mainmenu_Basic.png");
		menu = new JButton(menuButtonBasicImage);
		menu.setLocation(350, 500);
		menu.setSize(150, 150);
		menu.setBorderPainted(false);
		menu.setContentAreaFilled(false);
		menu.setFocusPainted(false);
		
		
		//flag
		flag = false;
		
	}

	public void run() {
		Game.getCon().add(fail);
		fail.add(menu);
//		fail.add(retry);
		fail.add(Exit);
		Game.getCon().setComponentZOrder(fail, 0);
		Game.getCon().repaint();
//		retry.addMouseListener(new MouseAdapter() {
//
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				retry.setIcon(retryButtonEnterImage);
//				retry.setCursor(new Cursor(Cursor.HAND_CURSOR));
//			}
//
//			@Override
//			public void mouseExited(MouseEvent e) {
//				retry.setIcon(retryButtonBasicImage);
//				retry.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
//			}
//
//			@Override
//			public void mousePressed(MouseEvent e) {
//				Game.getCon().remove(fail);
//				Game.getCon().remove(retry);
//				flag = true;
//				int stagenum=Game.getstageNum();
//				
//				Game.getCon().repaint();
//				
//			}
//		});
		
		menu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				menu.setIcon(menuButtonEnterImage);
				menu.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menu.setIcon(menuButtonBasicImage);
				menu.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				flag = true;
				Game.getCon().removeAll();
				Application.GameThread().interrupt();
				Game.getCon().repaint();
				Game.flag=true;
				character.flag=true;
				Application.mainmenuThreadreset();
				Application.GameThreadreset();
				Application.mainmenuThread().start();
			}
		});
		
		Exit.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				Exit.setIcon(ExitEnter);
				Exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Exit.setIcon(ExitBasic);
				Exit.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(1);
			}
		});
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