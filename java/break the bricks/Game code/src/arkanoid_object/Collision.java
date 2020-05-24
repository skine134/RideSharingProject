package arkanoid_object;

import javax.swing.JComponent;


//각 객체마다 충돌처리를 인터페이스 구현
interface Collision {
	
	//만약 객체가 공과 충돌하면 true, 충돌하지 않았다면 false를 반환하는 함수.
	boolean collision(JComponent e);
}
