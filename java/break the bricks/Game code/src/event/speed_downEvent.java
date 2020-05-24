package event;

import java.io.IOException;


import arkanoid_object.Item;
import arkanoid_object.ball;
import arkanoid_object.brick;
import arkanoid_object.character;
import arkanoid_object.Item;

public class speed_downEvent extends Item {

	public speed_downEvent(character ch, brick br, ball b) throws IOException {
		super(ch,br,b);
	}
	
	@Override
	public void play_event() {
		double check_min=(Math.pow(getB().getDx(),2)+Math.pow(getB().getDy(),2))*0.8;
		if((Math.pow(getB().getDx(),2)+Math.pow(getB().getDy(),2))*ball.min_speed<check_min) {
			getB().setDx(getB().getDx()*Math.sqrt(getB().getSpeed()));
			getB().setDy(getB().getDy()*Math.sqrt(getB().getSpeed()));
		}
	}
	
}
