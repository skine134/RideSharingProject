package event;

import java.io.IOException;


import arkanoid_object.Item;
import arkanoid_object.ball;
import arkanoid_object.brick;
import arkanoid_object.character;
import arkanoid_object.Item;

public class ball_smallEvent extends Item {
	
		public ball_smallEvent(character ch,brick br,ball b) throws IOException {
			super(ch,br,b);
		
		}
		@Override
		public void play_event() {
			int check_min=(int)(0.9*getB().getR());
			if(check_min > ball.min_R) {
				getB().setR((int)(0.9*getB().getR()));
				getB().getPan().setSize(getB().getR(),getB().getR()); 
			}
			else {
				getB().setR(ball.min_R);
				getB().getPan().setSize(getB().getR(),getB().getR()); 
			}
		}

}
