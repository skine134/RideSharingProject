package stage;

import java.awt.Color;
import java.io.IOException;
import java.util.*;
import arkanoid_object.*;
import event.*;

public class stage2 extends Stage{

	public stage2(character ch,ball b) throws IOException {
		super();
		stage_num = 2;
		for(int i=0;i<8;i++) {
			for(int j=0;j<20;j++) {
				bricks.add(19*i+j,new brick());
				bricks.get(19*i+j).setX(11+50*i);
				bricks.get(19*i+j).setY(11+20*j);
				bricks.get(19*i+j).setCollision(b);
				bricks_th.add(19*i+j,new Thread(bricks.get(19*i+j).th));
				bricks.get(19*i+j).getPan().setSize(bricks.get(19*i+j).getSize_x(),bricks.get(19*i+j).getSize_y());
				bricks.get(19*i+j).getPan().setLocation(bricks.get(19*i+j).getX(),bricks.get(19*i+j).getY());
				if(j==19)
					bricks.get(19*i+j).setBrick_image("images\\Block\\Block_orange.png");
				else
					bricks.get(19*i+j).setBrick_image("images\\Block\\Block_yellow.png");
				if(bricks.get(19*i+j).getBrick_image().equals("images\\Block\\Block_orange.png")) {
					bricks.get(19*i+j).setHp(9999);
					bricks.get(19*i+j).setBreak(false);
					Unbreak_bricks.add(bricks.get(19*i+j));
				}
				if(bricks.get(19*i+j).getX()==11&&bricks.get(19*i+j).getY()==11)
					bricks.get(19*i+j).setItem(new bar_reverse(ch, bricks.get(19*i+j), b));
				if(bricks.get(19*i+j).getX()==11&&bricks.get(19*i+j).getY()==371)
					bricks.get(19*i+j).setItem(new ball_TransparentEvent(ch, bricks.get(19*i+j), b));
				if(bricks.get(19*i+j).getX()==361&&bricks.get(19*i+j).getY()==371)
					bricks.get(19*i+j).setItem(new speed_upEvent(ch, bricks.get(19*i+j), b));
				if(bricks.get(19*i+j).getX()==361&&(31<=bricks.get(19*i+j).getY()&&bricks.get(19*i+j).getY()<=71))
					bricks.get(19*i+j).setItem(new ball_smallEvent(ch, bricks.get(19*i+j), b));
				if((211<=bricks.get(19*i+j).getX()&&bricks.get(19*i+j).getX()<=311)&&bricks.get(19*i+j).getY()==11)
					bricks.get(19*i+j).setItem(new bar_speedupEvent(ch, bricks.get(19*i+j), b));
				if(bricks.get(19*i+j).getX()==211&&(151<=bricks.get(19*i+j).getY()&&bricks.get(19*i+j).getY()<=211))
					bricks.get(19*i+j).setItem(new bar_longEvent(ch, bricks.get(19*i+j), b));
				if(bricks.get(19*i+j).getX()==311&&(31<=bricks.get(19*i+j).getY()&&bricks.get(19*i+j).getY()<=51))
					bricks.get(19*i+j).setItem(new character_hpup(ch, bricks.get(19*i+j), b));
				if(bricks.get(19*i+j).getX()==311&&(51==bricks.get(19*i+j).getY()||bricks.get(19*i+j).getY()==71))
					bricks.get(19*i+j).setItem(new character_hpup(ch, bricks.get(19*i+j), b));
				for(int k=0;k<3;k++) 
					if(bricks.get(19*i+j).getX()==211+k*50&&bricks.get(19*i+j).getY()==51+k*80) {
						bricks.get(19*i+j).setItem(new ball_doubleEvent(ch, bricks.get(19*i+j), b));
						break;
					}
				if(bricks.get(19*i+j).getX()==111&&(171<=bricks.get(19*i+j).getY()&&bricks.get(19*i+j).getY()<=231))
					bricks.get(19*i+j).setItem(new character_hpup(ch, bricks.get(19*i+j), b));
				if(bricks.get(19*i+j).getX()==311&&31==bricks.get(19*i+j).getY())
					bricks.get(19*i+j).setItem(new character_hpup(ch, bricks.get(19*i+j), b));
				
			}
		}
		for(int i=0;i<16;i++) {

			bricks.add(new brick());
			bricks.get(160+i).setX(411);
			bricks.get(160+i).setY(391-20*i);
			bricks.get(160+i).setCollision(b);
			bricks_th.add(160+i,new Thread(bricks.get(160+i).th));
			bricks.get(160+i).getPan().setSize(bricks.get(160+i).getSize_x(),bricks.get(160+i).getSize_y());
			bricks.get(160+i).getPan().setLocation(bricks.get(160+i).getX(),bricks.get(160+i).getY());
			bricks.get(160+i).setBrick_image("images\\Block\\Block_orange.png");
			bricks.get(160+i).setHp(999);
			bricks.get(160+i).setBreak(false);
			if(!bricks.get(160+i).isBreak()) {
				Unbreak_bricks.add(bricks.get(160+i));
			}
		}
	}

	@Override	
	public String getH_wall(){
		return "images\\desert\\desert_pillar_cols.png";
	}
	@Override
	public String getW_wall(){
		return "images\\desert\\desert_pillar_rows.png";
	}
	@Override
	public String getball_image() {
		return "images\\desert\\desert_ball.png";
	}
	@Override
	public String getBackground_image() {
		return "images\\desert\\desert_back.png";
	}

	@Override
	public String getStage_Music() {
		return "sounds\\background_music\\desert.mp3";
	}
}
