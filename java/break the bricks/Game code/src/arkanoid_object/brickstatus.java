package arkanoid_object;

import java.awt.*;
import javax.swing.*;
import event.*;

public class brickstatus extends JComponent{
	
	private boolean Break;
	
	private int hp;
	

	private String Color_refer;
	

	private int x;
	private int y;
	

	private int size_x=50;
	private int size_y=20;
	
	private Item Item;
	
	public Item getItem() {
		return Item;
	}
	public void setItem(Item item) {
		Item = item;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	
	
	public String getColor_refer() {
		return Color_refer;
	}
	public void setColor_refer(String color_refer) {
		Color_refer = color_refer;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getSize_x() {
		return size_x;
	}
	public void setSize_x(int size_x) {
		this.size_x = size_x;
	}
	public int getSize_y() {
		return size_y;
	}
	public void setSize_y(int size_y) {
		this.size_y = size_y;
	}
	public boolean isBreak() {
		return Break;
	}
	public void setBreak(boolean Break) {
		this.Break = Break;
	}
	
	
}
