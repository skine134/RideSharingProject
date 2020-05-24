package event;

import java.io.IOException;

import arkanoid_object.*;

public class bar_reverse extends Item {

	public bar_reverse(character ch, brick br, ball b) throws IOException {
		super(ch, br, b);
	}

	@Override
	public void play_event() {
		if(getCh().reverseflag==false)
			getCh().reverseflag = true;
		else
			getCh().reverseflag=false;
	}
}
