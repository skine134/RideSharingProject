package stage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import arkanoid_object.*;
import event.*;

public class stage1 extends Stage {

	public stage1(character ch, ball b) throws IOException {
		super();
		stage_num = 1;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 21; j++) {
				bricks.add(21 * i + j, new brick());
				bricks.get(21 * i + j).setX(11 + 150 * i);
				bricks.get(21 * i + j).setY(11 + 20 * j);
				bricks.get(21 * i + j).setCollision(b);
				bricks_th.add(21 * i + j, new Thread(bricks.get(21 * i + j).th));
				bricks.get(21 * i + j).getPan().setSize(bricks.get(21 * i + j).getSize_x(),bricks.get(21 * i + j).getSize_y());
				bricks.get(21 * i + j).getPan().setLocation(bricks.get(21 * i + j).getX(),bricks.get(21 * i + j).getY());
				if(bricks.get(21 * i + j).getY()<250)
				bricks.get(21 * i + j).setBrick_image("images\\Block\\Block_green.png");
				else
					bricks.get(21 * i + j).setBrick_image("images\\Block\\Block_brown.png");
			if(bricks.get(21 * i + j).getY()==311&&(bricks.get(21*i+j).getX()==11||bricks.get(21*i+j).getX()==161))
				bricks.get(21 * i + j).setItem(new ball_doubleEvent(ch, bricks.get(21 * i + j), b));
			if(bricks.get(21 * i + j).getY()==211)
				bricks.get(21 * i + j).setItem(new bar_longEvent(ch,bricks.get(21 * i + j), b));
			if(bricks.get(21 * i + j).getX()==461&&(61<=bricks.get(21 * i + j).getY()&&bricks.get(21 * i + j).getY()<=81))
				bricks.get(21 * i + j).setItem(new bar_reverse(ch,bricks.get(21 * i + j), b));

			}
		}
	}

	@Override
	public String getH_wall() {
		return "images\\forest\\forest_pillar_cols.png";
	}

	@Override
	public String getW_wall() {
		return "images\\forest\\forest_pillar_rows.png";
	}

	@Override
	public String getball_image() {
		return "images\\forest\\forest_ball.png";
	}

	@Override
	public String getBackground_image() {
		return "images\\forest\\forest_back.png";
	}

	@Override
	public String getStage_Music() {
		return "sounds\\background_music\\forest.mp3";
	}
}