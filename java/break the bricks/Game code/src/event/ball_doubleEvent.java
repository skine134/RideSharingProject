package event;

import java.awt.Color;


import java.io.IOException;

import javax.swing.JPanel;
import arkanoid_object.*;
import teamproject_Game.Game;

public class ball_doubleEvent extends Item {

	public ball_doubleEvent(character ch, brick br, ball b) throws IOException {
		super(ch, br, b);
	}

	@Override
	public void play_event() {
		setB(getCh().getB());
		ball b2 = new ball(getCh());
		try {
			b2.setball_image(Game.getStages().get(Game.getstageNum()).getball_image());
		} catch (IOException e) {
			e.printStackTrace();
		}
		b2.setSize(getB().getR(),getB().getR());
		b2.setX(getB().getx());
		b2.setY(getB().gety());
		b2.getPan().setLocation((int)b2.getx(),(int)b2.gety());
		double immediatly=Math.pow(getB().getDx(),2)+Math.pow(getB().getDy(), 2);
		if(getB().getDx()==0) {
			getB().setDx(getB().getDy()*3/5);
			getB().setDy(getB().getDy()*4/5);
			b2.setDy(getB().getDy());
			b2.setDx(-getB().getDx());
		}
		else {
			double immediatly_Dx=getB().getDx();
			getB().setDx(immediatly_Dx*4/5);
			if(getB().getDy()<0)
				getB().setDy(-Math.sqrt(immediatly-Math.pow(getB().getDx(), 2)));
			else
				getB().setDy(Math.sqrt(immediatly-Math.pow(getB().getDx(), 2)));
			b2.setDx(immediatly_Dx*6/5);
			if(getB().getDy()<0)
				b2.setDy(-Math.sqrt(immediatly-Math.pow(b2.getDx(), 2)));
			else
				b2.setDy(Math.sqrt(immediatly-Math.pow(b2.getDx(), 2)));
			if(Double.isNaN(b2.getDy())) {
				b2.setDy(ball.min_D);
				b2.setDx(ball.max_D);
			}
		}
		Game.getCon().add(b2.getPan());
		getCh().setCollision(b2);
		Game.getCon().repaint();
		Game.getCon().setComponentZOrder(b2.getPan(), Game.getCon().getComponentCount()-2);
		new Thread(getCh().balls_th.get(b2.getball_num()-1)).start();
		new Thread(b2.th).start();
	}

}