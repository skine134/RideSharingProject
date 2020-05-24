package event;

import java.io.IOException;


import arkanoid_object.Item;
import arkanoid_object.ball;
import arkanoid_object.brick;
import arkanoid_object.character;
import arkanoid_object.Item;

public class bar_speed_downEvent extends Item {
	// 아래 생성자의 양식은 어떤 이벤트든 동일 해야함 (이벤트에 상호작용 하는 오브젝트는 벽돌,캐릭터,공 이기 때문)
	public bar_speed_downEvent(character ch, brick br, ball b) throws IOException {
		super(ch,br,b);
	}

	// 아래 함수에서 일어나야할 이벤트 정의
	@Override
	public void play_event() {
		int check_min=(int)(getCh().getMove_speed()*0.8);
		if (check_min> character.min_move_speed)
			getCh().setMove_speed((int)(getCh().getMove_speed()*0.8));
		else
			getCh().setMove_speed(character.min_move_speed);
	}
}
