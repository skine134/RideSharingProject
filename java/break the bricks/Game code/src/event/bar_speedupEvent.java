package event;


import java.awt.Color;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.*;
import arkanoid_object.*;

//*****************바가 길어지는 이벤트
public class  bar_speedupEvent extends Item{
	
	//아래 생성자의 양식은 어떤 이벤트든 동일 해야함 (이벤트에 상호작용 하는 오브젝트는 벽돌,캐릭터,공 이기 때문)
	public bar_speedupEvent(character ch,brick br,ball b) throws IOException{
		super(ch,br,b);
	}
	
	
	//아래 함수에서 일어나야할 이벤트 정의
	@Override
	public void play_event() {
		int check_max=(int)(getCh().getMove_speed()*1.2);
		if(check_max<character.max_move_speed) {
			getCh().setMove_speed((int)(getCh().getMove_speed()*1.2));
		}
		else {
			getCh().setMove_speed(character.max_move_speed);
		}
	}
}
