package arkanoid_object;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import arkanoid_object.Item.ItemImagePanel;
import teamproject_Game.Game;

//*****************아이템들이 공통적으로 갖는 값들 정의(object패키지의 status와 동일한 역할)

public abstract class Item extends JComponent {

	// 컨테이너에 들어갈 패널
	private JPanel pan;

	// 각 오브젝트에 대한 정보 저장
	private character ch;
	private brick br;
	private ball b;
	private BufferedImage image;

	// 떨어지는 스레드
	private Thread drop_th;

	// 크기
	private int size_x;
	private int size_y;

	// 좌표
	private int x;
	private int y;

	public Item(character ch, brick br, ball b) throws IOException{
			setPan(new ItemImagePanel());
			setCh(ch);
			setBr(br);
			setB(b);
			setSize_x(40);
			setSize_y(20);
			setItem_image("images\\item.png");
			setDrop_th(new Thread(drop_event()));
	}
	
	// 다른 이벤트 마다 오버라이딩으로 다르게 구현하게 하기위해 비워둠.
	public void play_event() {
		
	}

	// 만약 블록이 사라졌을 경우 아이템이 떨어짐
	public Runnable drop_event() {
		Runnable drop_th = () -> {
			while (ch.getY() + ch.getSize_y() + 10 > this.getY()) {
				this.setY(this.getY() + 10);
				this.pan.setLocation(this.getX(), this.getY());
				try {
					Thread.sleep(1000 / 60);
				} catch (InterruptedException d) {
					d.getMessage();
				}
			}
			this.pan.setVisible(false);
		};
		return drop_th;
	}

	class ItemImagePanel extends JPanel {
		@Override
		public void paint(Graphics g) {
			g.drawImage(image, 0, 0, null);
		}

		@Override
		public Dimension getPreferredSize() {
			if (image == null) {
				return new Dimension(600, 1000);
			} else {
				return new Dimension(image.getWidth(), image.getHeight());
			}
		}
	}

	public void setItem_image(String str) throws IOException {
		image = ImageIO.read(new File(str));
	}

	public character getCh() {
		return ch;
	}

	public void setCh(character ch) {
		this.ch = ch;
	}

	public brick getBr() {
		return br;
	}

	public void setBr(brick br) {
		this.br = br;
	}

	public ball getB() {
		return b;
	}

	public void setB(ball b) {
		this.b = b;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public Thread getDrop_th() {
		return drop_th;
	}

	public void setDrop_th(Thread drop_th) {
		this.drop_th = drop_th;
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

	public JPanel getPan() {
		return pan;
	}

	public void setPan(JPanel pan) {
		this.pan = pan;
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

}
