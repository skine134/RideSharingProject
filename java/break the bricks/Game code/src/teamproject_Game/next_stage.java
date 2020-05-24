package teamproject_Game;

import java.awt.*;

import java.awt.event.*;
import java.util.concurrent.Semaphore;
import javax.swing.*;

import arkanoid_object.*;

public class next_stage extends Thread {
	ImageIcon nextButtonEnterImage;
	ImageIcon nextButtonBasicImage;
	JButton next;
	private ImageIcon black_background;
	boolean stop;
	character ch;

	 next_stage(character ch) {
		this.ch = ch;
		nextButtonEnterImage = new ImageIcon("images//nextButton//next_buttonEnter.png");
		nextButtonBasicImage = new ImageIcon("images//nextButton//next_button.png");
		next = new JButton(nextButtonBasicImage);
		black_background = new ImageIcon("images//black_background.png");
		stop = false;

	}

	public void run() {
		if(ch.getPan().isFocusable()) {
			ch.getPan().setFocusable(false);
		}
			next.setBorderPainted(false);
			next.setContentAreaFilled(false);
			next.setFocusPainted(false);
			JLabel black_bg = new JLabel(black_background);
			black_bg.setBounds(0, 0, 600, 1000);
			black_bg.add(next);

			next.setLocation(60, 600);
			next.setSize(450, 200);
			next.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					next.setIcon(nextButtonEnterImage);
					next.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					next.setIcon(nextButtonBasicImage);
					next.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

				}

				@Override
				public void mousePressed(MouseEvent e) {
					Game.getCon().remove(black_bg);
					stop=true;
					ch.getPan().setFocusable(true);
					ch.getPan().requestFocus();
					Game.getCon().repaint();
				}
			});
			Game.getCon().add(black_bg);
			Game.getCon().setComponentZOrder(black_bg, 0);
			black_bg.setVisible(true);
			Game.getCon().repaint();
		while(!stop) {
			try {
				Thread.sleep(1000 / 60);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		
	}

}
