package teamproject_Game;

import java.awt.Cursor;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import teamproject_Game.Game;
import arkanoid_object.*;

public class finish_stage extends Thread {
	
	ImageIcon HomeEnter;
	ImageIcon HomeBasic;
	ImageIcon ExitEnter;
	ImageIcon ExitBasic;
	JButton Home;
	JButton Exit;
	boolean flag;
	Music clearMusic;
	
	main_menu title=new main_menu();
	public finish_stage(character ch) {
		
		if(ch.getPan().isFocusable()) {
			ch.getPan().setFocusable(false);
		}
		clearMusic=Application.getBackMusic();
		try {
			Application.getBackImage().setBackGround_image("images//end.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HomeEnter = new ImageIcon("images//mainmenuButton//mainmenu_Enter.png");
		HomeBasic = new ImageIcon("images//mainmenuButton//mainmenu_Basic.png");
		ExitEnter = new ImageIcon("images//ExitButton//ExitButton_Enter.png");
		ExitBasic = new ImageIcon("images//ExitButton//ExitButton_Basic.png");
		
		Home = new JButton(HomeBasic);
		Home.setLocation(0, 400);
		Home.setSize(400, 200);
		Home.setBorderPainted(false);
		Home.setContentAreaFilled(false);
		Home.setFocusPainted(false);
		
		Exit = new JButton(ExitBasic);
		Exit.setLocation(250, 400);
		Exit.setSize(400, 200);
		Exit.setBorderPainted(false);
		Exit.setContentAreaFilled(false);
		Exit.setFocusPainted(false);
		
		flag = false;
		
	}

	public void run() {
		Game.getCon().removeAll();
		Game.getCon().add(Application.getBackImage().getPan());
		Game.getCon().add(Home);
		Game.getCon().add(Exit);
		Game.getCon().setComponentZOrder(Application.getBackImage().getPan(), Game.getCon().getComponentCount()-1);
		Game.getCon().repaint();
		clearMusic.setMusic("sounds\\background_music\\clear.mp3");
		Game.getCon().repaint();
		Home.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				Home.setIcon(HomeEnter);
				Home.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Home.setIcon(HomeBasic);
				Home.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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
}