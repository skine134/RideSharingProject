package arkanoid_object;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import teamproject_Game.Fail_stage;
import teamproject_Game.Game;
import teamproject_Game.finish_stage;

public class character extends character_status {
	public static boolean ballremoveflag=false;
	public static boolean flag;
	public boolean reverseflag = false;
	// 충돌 처리를 위한 함수 람다식으로 구현
	private Collision ball_col = (JComponent e) -> {

		ball b = (ball) e;
		// 만약 캐릭터와 충돌 했을때 true 반환 충돌하지 않았다면 false 반환
		if ((b.getx() + b.getR()) >= this.getX() && (b.gety() + b.getR()) >= this.getY()
				&& (b.getx()) <= (this.getX() + this.getSize_x()) && (b.gety()) <= (this.getY() + this.getSize_y())) {
			return true;
		} else
			return false;
	};

	// 아이템 충돌 처리를 위한 함수 람다식으로 구현
	private Collision items_col = (JComponent e) -> {

		Item i = (Item) e;
		// 만약 캐릭터와 충돌 했을때 true 반환 충돌하지 않았다면 false 반환
		if ((i.getX() + i.getSize_x()) >= this.getX() && (i.getY() + i.getSize_y()) >= this.getY()
				&& (i.getX()) <= (this.getX() + this.getSize_x()) && (i.getY()) <= (this.getY() + this.getSize_y())) {
			return true;
		} else
			return false;
	};
	// character에대한 스레드 선언
	public ArrayList<Runnable> balls_th;
	public Runnable items_th;
	public Runnable move;
	public int ball_count;
	private ball b;
	// 이동을 위한 논리식
	public boolean left;
	public boolean right;
	
	// character을 담아낼 pan 선언
	private JPanel pan;
	private BufferedImage image;
	private ArrayList<JLabel> hp_pans;
	private Music bound_ball;

	public character() throws IOException {
		// character의 x사이즈 설정(※y사이즈는 변동될 일이 없으므로 선언 전에 설정 됌)
		setSize_x(100);

		// character의 x좌표 선언(※y좌표는 변동될 일이 없으므로 선언 전에 설정 됌)
		setX(246);

		// character의 hp 구현(3칸)
		setHp(3);

		// character의 초기 x벡터선언(※y벡터는 변동될 일이 없으므로 구현되지 않음)
		setDx(0);

		setMove_speed(character.start_move_speed);
		// character을 담아낼 pan 객체 선언 및 이미지 지정
		pan = new CharacterImagePanel();

		// character을 담아낼 pan의 사이즈 지정
		pan.setSize(getSize_x(), getSize_y());
		setCharacter_image("images\\character.png");
		
		ball_count=0;
		
		// 움직임 제어를 위한 로직
		left = false;
		right = false;
		balls_th=new ArrayList<Runnable>();
		hp_pans = new ArrayList<JLabel>(3);
		// hp의 이미지 추가
		for (int i = 0; i < 3; i++)
			hp_pans.add(i, new JLabel(new ImageIcon("images\\heart.png")));
		bound_ball = new Music("sounds\\impactive sound\\city_brick2.mp3", false);
	}

	class CharacterImagePanel extends JPanel {
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

	public void setCharacter_image(String str) throws IOException {
		image = ImageIO.read(new File(str));
	}

	public ArrayList<JLabel> getHp_pans() {
		return hp_pans;
	}

	public void setHp_pan(ArrayList<JLabel> hp_pans) {
		this.hp_pans = hp_pans;
	}

//hp를 증가시킴
	public void Hpadd() {
		if (this.getHp() < 10) {
			this.setHp(this.getHp() + 1);
			this.getHp_pans().add(this.getHp_pans().size(), new JLabel(new ImageIcon("images\\heart.png")));
			this.getHp_pans().get(this.getHp_pans().size() - 1).setSize(50, 50);
			this.getHp_pans().get(this.getHp_pans().size() - 1).setLocation(500 - (this.getHp_pans().size() - 1) * 50,
					15);
			Game.getCon().add(this.getHp_pans().get(this.getHp_pans().size() - 1));
			Game.getCon().setComponentZOrder(this.getHp_pans().get(this.getHp_pans().size() - 1),
					Game.getCon().getComponentZOrder(this.getHp_pans().get(this.getHp_pans().size() - 2)) + 1);
			Game.getCon().repaint();
		}
	}

//hp를 감소시킴
	public void Hpremove() {
		this.setHp(this.getHp() - 1);
		Game.getCon().remove(this.getHp_pans().get(this.getHp()));
		this.getHp_pans().remove(this.getHp());
		Game.getCon().repaint();
		if (this.getHp() <= 0) {
			new Fail_stage(this).start();
		}
//		new Game_Over().start();
	}

	// character를 담은pan 반환
	public JPanel getPan() {
		return pan;
	}

	// 충돌 시 일어나는 이벤트 스레드구현
	public void setCollision(ball b) {
		Runnable ball_th = () -> {
			while (true) {
				if(ballremoveflag) {
					return;
				}
//				//충돌이 일어났고 공의 y벡터가 0이 아니였을경우
//				//(ball.collision(b)&&ball.getDy()!=0){
//				//delta==변화량(캐릭터 중심으로 부터 떨어진 거리/캐릭터의 크기의 1/2)
//				//-1<delta<0이면 dx값 감소, dy값 증가{
//					dy>ball.maxspeed
//					ball.dy=ball.maxspeed
//					ball.dx=ball.minspeed
//				}
//				
//				//-1==deLta 이면 dx값 반전,dy값 반전
//				// 0==delta 이면 dx값 통과,dy값 반전
//				//0<delta<=1이면 dx깂 증가.dy깂 감소 및 반전{
//					dy<ball.minspeed이거나 NaN일 경우
//					ball.dy=ball.minspeed
//					ball.dx=ball.maxspeed
//				}
//				//-1>delta이거나 delta>1 이면 dx값 유지,dy 반전<----------
//				}

				Double pre_speed = Math.pow(b.getDx(), 2) + Math.pow(b.getDy(), 2);
				Double delta;
				// 만약 충돌이 일어났다면
				if (ball_col.collision(b) && b.getDy() != 0) {
					new Thread(bound_ball.th).start();
					// x 벡터 값 설정
					// delta==변화량(캐릭터 중심으로 부터 떨어진 거리/캐릭터의 크기의 1/2)
					if (b.getDx() > 0) {
						delta = (((b.getx() + (b.getR() / 2)) - (this.getX() + (this.getSize_x() / 2))))
								/ (this.getSize_x() / 2);
					}

					else if (b.getDx() < 0) {
						delta = (((this.getX() + (this.getSize_x() / 2))) - ((b.getx() + (b.getR() / 2))))
								/ (this.getSize_x() / 2);
					} else {
						if (this.getDx() != 0)
							b.setDx(this.getDx());
						delta = 0d;
					}
					b.setDx(2 * b.getDx() * delta + b.getDx());
					double check_Dx = Math.sqrt(Math.pow(b.getDx(), 2));
					if ((check_Dx < (ball.min_D * Math.sqrt(b.getSpeed())) && check_Dx > 0) || Double.isNaN(check_Dx)) {
//							System.out.println("범위 값 초과");
						if (b.getDx() > 0)
							b.setDx(ball.min_D * Math.sqrt(b.getSpeed()));
						else
							b.setDx(-ball.min_D * Math.sqrt(b.getSpeed()));
					} else if (check_Dx > (ball.max_D * Math.sqrt(b.getSpeed()))) {
						if (b.getDx() > 0)
							b.setDx(ball.max_D * Math.sqrt(b.getSpeed()));
						else
							b.setDx(-ball.max_D * Math.sqrt(b.getSpeed()));
					}
					Double immediate_dy = Math.sqrt(pre_speed - (Math.pow(b.getDx(), 2)));

//					//-1==deLta 이면 dx값 반전,dy값 반전
//					// 0==delta 이면 dx값 통과,dy값 반전
					if (delta == -1 || delta == 0) {
						if (delta == -1)
							b.setDx(-b.getDx());
						b.setDy(-immediate_dy);
					}
//					//-1<delta<0이면 dx값 감소, dy값 증가{
//					dy>ball.maxspeed
//					ball.dy=ball.maxspeed
//					ball.dx=ball.minspeed
//				}
					else if (-1 < delta && delta < 0) {
						b.setDy(-immediate_dy);
					}

//					//0<delta<=1이면 dx깂 증가.dy깂 감소 및 반전

					else if (0 < delta && delta <= 1) {
						b.setDy(-immediate_dy);
					}

//					// 1<delta 이면 dx값 유지,dy 반전
					else
						b.setDy(-immediate_dy);
					if ((immediate_dy < (ball.min_D * Math.sqrt(b.getSpeed())) && immediate_dy > 0)
							|| Double.isNaN(immediate_dy)) {
						b.setDy(-ball.min_D * Math.sqrt(b.getSpeed()));
						if (b.getDx() > 0)
							b.setDx(ball.max_D * Math.sqrt(b.getSpeed()));
						else if (b.getDx() < 0)
							b.setDx(-ball.max_D * Math.sqrt(b.getSpeed()));
					} else if (immediate_dy > (ball.max_D * Math.sqrt(b.getSpeed()))) {
						b.setDy(-ball.max_D * Math.sqrt(b.getSpeed()));
						if (b.getDx() > 0)
							b.setDx(ball.min_D * Math.sqrt(b.getSpeed()));
						else if (b.getDx() < 0)
							b.setDx(-ball.min_D * Math.sqrt(b.getSpeed()));
					} else if (immediate_dy == 0 && b.getDx() != 0) {
						b.setDy(-ball.min_D * Math.sqrt(b.getSpeed()));
						if (b.getDx() > 0)
							b.setDx(ball.max_D * Math.sqrt(b.getSpeed()));
						else if (b.getDx() < 0)
							b.setDx(-ball.max_D * Math.sqrt(b.getSpeed()));
					}
					try {
						// 스레드가 연속해서 일어나는오류를 방지하기위해 충돌 후 0.5초간 상기된 이벤트 정지
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.getMessage();
					}
				}
				try {

					// 충돌에 대한 감시를 1초당60번 수행
					Thread.sleep(1000 / 60);
				} catch (InterruptedException e) {
					e.getMessage();
				}
			}
		};
		ball_count+=1;
		balls_th.add(ball_count-1,ball_th);
		b.setball_num(ball_count);
	}

	// 아이템 충돌 시 일어나는 이벤트 스레드구현
	public void setCollision(ArrayList<Item> items) {

		items_th = () -> {
			flag = false;
			while (true) {
				if (flag)
					return;
				// 아이템을 갖고 있다면 하위의 명령어 실행
				if (items != null)
					for (int i = 0; i < items.size(); i++) {

						// 만약 충돌이 일어났다면
						if (items_col.collision(items.get(i))) {

							// 아이템의 이벤트 실헹
							items.get(i).play_event();
							items.get(i).getPan().setVisible(false);
							items.remove(i);
						}
					}
				try {
					// 위의 검사를 1초에 60번 수행(＊부러운 움직임을 위함)
					Thread.sleep(1000 / 60);
				} catch (InterruptedException e) {
					e.getMessage();
				}
			}
		};

	}

	public Runnable move() {
		move = () -> {
			while (true) {
				// 만약 right==true이면 아래의 명령어 수행
				if (right) {
					if (reverseflag) {
						if (this.getX() >= 10) {
							this.setX(this.getX() + this.getDx());
							this.getPan().setLocation(this.getX(), this.getY());
						}
					} else {
						// 만약 캐릭터가 화면의 오른쪽 끝에 닿지 않았다면 현제 캐릭터의 속도만큼 캐릭터를 오른쪽으로 이동
						if (this.getX() + this.getSize_x() <= 582) {
							this.setX(this.getX() + this.getDx());
							this.getPan().setLocation(this.getX(), this.getY());
						}
					}

					// 만약 right==true이면 아래의 명령어 수행
				} else if (left) {

					// 만약 캐릭터가 화면의 왼쪽 끝에 닿지 않았다면 현제 캐릭터의 속도만큼 캐릭터를 왼쪽으로 이동
					if (reverseflag) {
						if (this.getX() + this.getSize_x() <= 582) {
							this.setX(this.getX() + this.getDx());
							this.getPan().setLocation(this.getX(), this.getY());
						}
					} else {
						if (this.getX() >= 10) {
							this.setX(this.getX() + this.getDx());
							this.getPan().setLocation(this.getX(), this.getY());
						}
					}
				}

				try {
					// 위의 검사를 1초에 60번 수행(＊부러운 움직임을 위함)
					Thread.sleep(1000 / 60);
				} catch (InterruptedException e) {
					e.getMessage();
				}
			}

		};
		return move;
	}public ball getB() {
		return b;
	}
	public void setB(ball b) {
		this.b=b;
	}
	public void reset() {
		reverseflag=false;
		setSize_x(100);
		setMove_speed(6);
		pan.setSize(getSize_x(), getSize_y());
		pan.setLocation(246,this.getY());
	}
}
