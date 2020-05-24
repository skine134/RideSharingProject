package stage;

import java.io.IOException;
import java.util.ArrayList;

import arkanoid_object.ball;
import arkanoid_object.brick;
import arkanoid_object.character;
import event.ball_bigEvent;
import event.ball_doubleEvent;
import event.bar_shortEvent;
import event.bar_speedupEvent;
import event.character_hpup;

public class stage5 extends Stage {
	public stage5(character ch, ball b) throws IOException {
		super();
		stage_num = 5;
		int sizeofbricks=0;
		
		for(int i=0;i<6;i++) {
			for(int j=0;j<11;j++) {
				bricks.add(11*i+j, new brick());
				bricks.get(11*i+j).setX(20 + 50 * j);
				if(i>=3)
					bricks.get(11*i+j).setY(300+20*i);
				else
					bricks.get(11*i+j).setY(11+20*i);
				bricks.get(11*i+j).setCollision(b);
				bricks_th.add(11*i+j, new Thread(bricks.get(11*i+j).th));
				bricks.get(11*i+j).getPan().setSize(bricks.get(11*i+j).getSize_x(), bricks.get(11*i+j).getSize_y());
				bricks.get(11*i+j).getPan().setLocation(bricks.get(11*i+j).getX(), bricks.get(11*i+j).getY());
				bricks.get(11*i+j).setBrick_image("images\\Block\\Block_pupple.png");
				if(bricks.get(11*i+j).getY()==31&&(bricks.get(11*i+j).getX()==70||bricks.get(11*i+j).getX()==470))
					bricks.get(11*i+j).setItem(new character_hpup(ch, bricks.get(11*i+j), b));
				if(bricks.get(11*i+j).getX()==120&&bricks.get(11*i+j).getY()>=360)
					bricks.get(11*i+j).setItem(new ball_bigEvent(ch,bricks.get(11*i+j), b));
				for(int k=0;k<3;k++)
					if(bricks.get(11*i+j).getX()==270+50*k&&bricks.get(11*i+j).getY()==400-20*k) {
						bricks.get(11*i+j).setItem(new bar_shortEvent(ch,bricks.get(11*i+j), b));
						break;
					}
				if(bricks.get(11*i+j).getY()==400&&bricks.get(11*i+j).getX()==470)
					bricks.get(11*i+j).setItem(new ball_doubleEvent(ch, bricks.get(11*i+j), b));
				
			}
		}
		sizeofbricks=bricks.size();
		for(int i=0;i<4;i++) {
			for(int j=0;j<11;j++) {
				bricks.add(sizeofbricks+11*i+j, new brick());
				bricks.get(sizeofbricks+11*i+j).setX(20 + 50 * j);
				bricks.get(sizeofbricks+11*i+j).setY(90+40*i);
				bricks.get(sizeofbricks+11*i+j).setCollision(b);
				bricks_th.add(sizeofbricks+11*i+j, new Thread(bricks.get(sizeofbricks+11*i+j).th));
				bricks.get(sizeofbricks+11*i+j).getPan().setSize(bricks.get(sizeofbricks+11*i+j).getSize_x(), bricks.get(sizeofbricks+11*i+j).getSize_y());
				bricks.get(sizeofbricks+11*i+j).getPan().setLocation(bricks.get(sizeofbricks+11*i+j).getX(), bricks.get(sizeofbricks+11*i+j).getY());
				bricks.get(sizeofbricks+11*i+j).setBrick_image("images\\Block\\Block_gray.png");
				if(bricks.get(sizeofbricks+11*i+j).getX()==470&&bricks.get(sizeofbricks+11*i+j).getY()==170)
					bricks.get(sizeofbricks+11*i+j).setItem(new ball_doubleEvent(ch, bricks.get(sizeofbricks+11*i+j), b));
				else if(bricks.get(sizeofbricks+11*i+j).getX()==270&&bricks.get(sizeofbricks+11*i+j).getY()==210)
					bricks.get(sizeofbricks+11*i+j).setItem(new bar_speedupEvent(ch, bricks.get(sizeofbricks+11*i+j), b));
				else if(bricks.get(sizeofbricks+11*i+j).getY()==90) {
					if(bricks.get(sizeofbricks+11*i+j).getX()==170)
						bricks.get(sizeofbricks+11*i+j).setItem(new character_hpup(ch, bricks.get(sizeofbricks+11*i+j), b));
					
					else if(bricks.get(sizeofbricks+11*i+j).getX()==470)
						bricks.get(sizeofbricks+11*i+j).setItem(new ball_doubleEvent(ch, bricks.get(sizeofbricks+11*i+j), b));
					
				}
					
			}
		}
		sizeofbricks=bricks.size();
		for(int i=0;i<2;i++) {
			for(int j=0;j<10;j++) {
				bricks.add(sizeofbricks+10*i+j, new brick());
				if(j>=5)
					bricks.get(sizeofbricks+10*i+j).setX(70 + 50 * j);
				else
					bricks.get(sizeofbricks+10*i+j).setX(20 + 50 * j);
				bricks.get(sizeofbricks+10*i+j).setY(260+50*i);
				bricks.get(sizeofbricks+10*i+j).setCollision(b);
				bricks_th.add(sizeofbricks+10*i+j, new Thread(bricks.get(sizeofbricks+10*i+j).th));
				bricks.get(sizeofbricks+10*i+j).getPan().setSize(bricks.get(sizeofbricks+10*i+j).getSize_x(), bricks.get(sizeofbricks+10*i+j).getSize_y());
				bricks.get(sizeofbricks+10*i+j).getPan().setLocation(bricks.get(sizeofbricks+10*i+j).getX(), bricks.get(sizeofbricks+10*i+j).getY());
				bricks.get(sizeofbricks+10*i+j).setBrick_image("images\\Block\\Block_brown.png");

				if(i==1) {
					if(j==2)
						bricks.get(sizeofbricks+10*i+j).setItem(new bar_speedupEvent(ch, bricks.get(sizeofbricks+10*i+j), b));
					else if(j==8)
						bricks.get(sizeofbricks+10*i+j).setItem(new bar_speedupEvent(ch, bricks.get(sizeofbricks+10*i+j), b));
					
				}
			}
		}
	}

	@Override
	public String getH_wall() {
		return "images\\city\\city_pillar_cols.png";
	}
	@Override
	public String getW_wall() {
		return "images\\city\\city_pillar_rows.png";
	}
	@Override
	public String getball_image() {
		return "images\\city\\city_ball.png";
	}
	@Override
	public String getBackground_image() {
		return "images\\city\\city_back.png";
	}

	@Override
	public String getStage_Music() {
		return "sounds\\background_music\\city.mp3";
	}

}
