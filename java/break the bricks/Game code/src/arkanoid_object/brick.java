package arkanoid_object;

import javax.imageio.ImageIO;

import javax.swing.*;
import teamproject_Game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class brick extends brickstatus {
	private JPanel pan;
	private Container con;
	public Runnable th;
	private BufferedImage image;
	Music brick_music=new Music("sounds\\impactive sound\\desert_brick2.mp3",false);
	Collision col = (JComponent e) -> {
		ball b = (ball) e;
		// 만약 벽돌과 충돌 했을 경우 true 충돌하지 않았을 경우 false 반환
		if ((b.getx() + b.getR()) >= this.getX() && (b.getx()) <= (this.getX() + this.getSize_x())
				&& (b.gety() + b.getR()) >= this.getY() && (b.gety()) <= (this.getY() + this.getSize_y()))
			return true;
		return false;

	};

	public brick() {
		// 벽돌을 담아낼 pan 객체 선언
		pan = new BrickImagePanel();
		// 초기 벽돌의 hp 구현
		this.setHp(1);
		this.con = Game.getCon();
	}

	class BrickImagePanel extends JPanel {
		@Override
		public void paint(Graphics g) {
			g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
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

	public void setBrick_image(String str) throws IOException {
		this.setColor_refer(str);
		image = ImageIO.read(new File(str));
	}

	public String getBrick_image() {
		return getColor_refer();
	}

	// 벽돌의 hp가 있는지 검사하는 함수
	public boolean isEmpty_Hp() {
		// hp가 0이하라면 true 리턴 hp가 1이상이라면 false 리턴
		if (this.getHp() <= 0)
			return true;
		return false;
	}

	// 벽돌을 담아내는 pan 반환
	public JPanel getPan() {
		return pan;
	}

	// 충돌에 대한 이벤트 함수
	public void setCollision(ball b) {
		// Runnable 클래스로 구현된 th의 객체 선언
		th = () -> {
			// 충돌 후 hp가 0인지 검사후 0이라면 pan을 투명하게 하고 만약 아이템을 갖고 있엇다면 아이템을 드롭한 후 스레드 종료
			if (isEmpty_Hp()) {
				Game.getCon().remove(this.getPan());
				pan.setVisible(false);
				Game.getCon().repaint();
				if (getItem() != null) {
					Game.items.add(this.getItem());
					con.add(this.getItem().getPan());
					this.getItem().getPan().setSize(this.getItem().getSize_x(), this.getItem().getSize_y());
					this.getItem().setX(this.getX());
					this.getItem().setY(this.getY());
					this.getItem().getPan().setLocation(this.getItem().getX(), this.getItem().getY());
					con.setComponentZOrder(this.getItem().getPan(), 0);
					this.getItem().getDrop_th().start();
				}
			}
			new Thread(brick_music.th).start();
		};
	}
}
