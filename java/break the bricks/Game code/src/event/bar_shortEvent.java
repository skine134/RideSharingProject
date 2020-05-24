package event;

import java.io.IOException;

import arkanoid_object.Item;
import arkanoid_object.ball;
import arkanoid_object.brick;
import arkanoid_object.character;
import javax.swing.*;


public class bar_shortEvent extends Item {
	
	public bar_shortEvent(character ch, brick br, ball b) throws IOException {
		super(ch,br,b);
	}
	
	@Override
	public void play_event() {
		int check_min=getCh().getSize_x()-20;
		if(check_min > character.min_size) {
			getCh().setSize_x(getCh().getSize_x()-20);
			getCh().getPan().setSize(getCh().getSize_x(),getCh().getSize_y());
			getCh().getPan().setLocation(getCh().getX()+10, getCh().getY());
		}
		else {
			getCh().setSize_x(character.min_size);
			getCh().getPan().setSize(getCh().getSize_x(),getCh().getSize_y());
			getCh().getPan().setLocation(getCh().getX(), getCh().getY());
		}
	}

}
