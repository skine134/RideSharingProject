package arkanoid_object;

import java.awt.Point;
import java.util.concurrent.Semaphore;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class ballstatus extends JComponent {

	public static final int min_R = 10;
	public static final int max_R = 40;
	public static final int start_R = 20;

	public static final double start_D = Math.sqrt(250) / 2;
	public static final double min_D = 5d;
	public static final double max_D = Math.sqrt(125 - Math.pow(min_D, 2));

	public static final double max_speed = 2.1;
	public static final double min_speed = 0.2;

	private double speed;

	private double x;
	private double y;
	private Point center;
	
	private boolean ballclear;

	private int r;

	// 각 축에대한 공의 변화량
	private double dx;
	private double dy;

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public double getSpeed() {
		return speed;
	}

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;

	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;

	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getx() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double gety() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public boolean isBallclear() {
		return ballclear;
	}

	public void setBallclear(boolean ballclear) {
		this.ballclear = ballclear;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

}
