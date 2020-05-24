package arkanoid_object;

import javax.swing.JComponent;

public class character_status extends JComponent{
	
	public static final int min_size=20;
	public static final int max_size=200;
	
	public static final int max_move_speed=10;
	public static final int min_move_speed=3;
	public static final int start_move_speed=6;

	private int hp;
	

	private int x;
	private int y=850;
	

	private int dx=0;
	

	private int move_speed;
	

	private int size_x;
	private int size_y=20;
	
	
	
	public int getMove_speed() {
		return move_speed;
	}
	public void setMove_speed(int move_speed) {
		this.move_speed = move_speed;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getSize_y() {
		return size_y;
	}
	public int getDx() {
		return dx;
	}
	public void setDx(int dx) {
		this.dx=dx;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	public int getSize_x() {
		return size_x;
	}
	public void setSize_x(int size_x) {
		this.size_x = size_x;	
	}
	
	

}
