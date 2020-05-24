package event;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Timer;

import arkanoid_object.Item;
import arkanoid_object.ball;
import arkanoid_object.brick;
import arkanoid_object.character;

public class ball_TransparentEvent extends Item {
	
	public ball_TransparentEvent(character ch, brick br, ball b) throws IOException {
		super(ch,br,b);
	}
	@Override
	public void play_event() {
		getB().setBallclear(true);
		getB().getPan().setVisible(!getB().isBallclear());
		Timer t = new Timer(3000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getB().setBallclear(false);
				getB().getPan().setVisible(!getB().isBallclear());
			}
		});
		t.start();
	
	}
	
}
