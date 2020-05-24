package arkanoid_object;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.Semaphore;

import javax.imageio.ImageIO;
import javax.swing.*;

import arkanoid_object.ball.ballImagePanel;
import arkanoid_object.brick.BrickImagePanel;
import teamproject_Game.Game;

public class ball extends ballstatus {
	Music wall_music;
	Music die_music;
	public Runnable th;
	private boolean flag;
	private int ball_num;
	private BufferedImage image;
	private Collision brick_col = (JComponent e) -> {
		brick br = (brick) e;
		
		if (((int)this.getx() + this.getR()) >= br.getX() && ((int)this.getx()) <= (br.getX() + br.getSize_x())
				&& ((int)this.gety() + this.getR()) >= br.getY() && ((int)this.gety()) <= (br.getY() + br.getSize_y())) {
			return true;
		} else
			return false;
	};
	private JPanel pan = new ballImagePanel();
	private character ch;
	public ball(character ch) {
		this.ch=ch;
		// 공의 투명성 여부 설정
		setBallclear(false);
		// 공의 지름크기 설정
		setR(start_R);
		flag = true;
		// 공의 초기 x값,y값 설정(캐릭터의 중앙 위에 위치하도록 설정)
		setX(ch.getX() + (int) (ch.getSize_x() / 2) - getR() / 2);
		setY(ch.getY() - getR());
		// 공의 초기 스피드 설정
		setSpeed(1d);
		// 공을 표시할 패널의 사이즈 설정
		pan.setSize(getR(), getR());
		pan.setVisible(!this.isBallclear());
		wall_music=new Music("sounds\\impactive sound\\wall.mp3",false);
		die_music=new Music("sounds\\impactive sound\\die.mp3",false);
		// 공에 대한 스레드 람다식으로 설정
		th = () -> {
			while (true) {
				// *********************벽 충돌 *********************\\

				// 만약 공이 움직이지 않았을 경우(게임 시작 전)
				if (this.getDy() == 0) {

					this.reset(ch);

				}
				// 만약 공이 움직이고 있었을 경우
				else {

					// 공이 위아래 벽에 부딛혔을 경우 y벡터값 반전
					if (this.gety() < 11 && this.getDy() < 0) {
						this.setDy(-this.getDy());
						new Thread(wall_music.th).start();
					}
					if (this.gety() + this.getR() > 1000) {
						if(ch.ball_count<=1) {
							ch.setB(this);
							this.reset(ch);
							ch.Hpremove();
							new Thread(die_music.th).start();
						}
						else {
							synchronized(this) {
								ch.ballremoveflag=true;
								ch.ball_count=ch.ball_count-1;
									for(int j=0;j<Game.getCon().getComponentCount();j++)
										if(Game.getCon().getComponent(j)instanceof ballImagePanel&&Game.getCon().getComponent(j)!=this.pan) {
											ballImagePanel setball=(ballImagePanel)Game.getCon().getComponent(j);
											setball.setmainball();
										}
							ch.ballremoveflag=false;
							}
							Game.getCon().remove(this.pan);
							new Thread(die_music.th).start();
							return;
						}
					}

					// 공이 좌우 벽에 부딛혔을 경우 x벡터값 반전
					if (this.getx() < 11 || this.getx() + this.getR() > 582) {
						if (this.getx() < 11) {
							if (this.getDx() < 0)
								this.setDx(-this.getDx());

						} else {
							if (this.getDx() > 0)
								this.setDx(-this.getDx());

						}
						new Thread(wall_music.th).start();
					}

					// ********************* 블록 충돌*********************\\

					for (int j = 0; j < Game.getStages().get(Game.getstageNum()).getBricks().size(); j++) {
						if (brick_col.collision(Game.getStages().get(Game.getstageNum()).getBricks().get(j))) {
							brick br = Game.getStages().get(Game.getstageNum()).getBricks().get(j);
							br.setHp(br.getHp() - 1);
							new Thread(Game.getStages().get(Game.getstageNum()).getBricks().get(j).th).start();
							if (flag) {
								flag = false;
								if (this.getDx() == 0) {
									this.setDy(-this.getDy());
								}

								else {
									// 왼쪽 상단에서 공이 날아 올때
									if (this.getDx() > 0 && this.getDy() > 0) {

										System.out.print("("+br.getX()+","+br.getY()+")"+"왼쪽 위  ");
										int x = br.getX();
										int y = (int) ((this.getDy() / this.getDx()) * (x - (this.getx() + this.getR()))
												+ this.gety());
										//공이 왼쪽 윗면에서 들어온 경우
										if (y < br.getY() - this.getR()) {
											System.out.print("윗면 ");
											//만약 위쪽에 벽돌이 있다면 x벡터를, 만약 왼쪽에 벽돌이 있다면 y벡터를 반전
											if (Game.getCon().getComponentAt(br.getX() + br.getSize_x() / 2,br.getY() - 3) instanceof BrickImagePanel) {
												this.setDx(-this.getDx());
												System.out.println("윗쪽에 벽돌이 있어 왼쪽 튕김");
											}
											else {
												this.setDy(-this.getDy());
												System.out.println("윗쪽 튕김");
											}
											//공이 왼쪽 측면에서 들어온 경우
										} else if (y > br.getY() - this.getR()) {

											System.out.print("측면 ");
											//만약 왼쪽에 벽돌이 있다면 y벡터를, 위쪽에 벽돌이 있다면 x벡터를 반전
											if (Game.getCon().getComponentAt(br.getX() - 3,br.getY() + br.getSize_y() / 2) instanceof BrickImagePanel) {
												this.setDy(-this.getDy());
												System.out.println("왼쪽에 벽돌이 있어 위쪽 튕김");
											}
												else {
												this.setDx(-this.getDx());
												System.out.println("왼쪽 튕김");
												}
											//공이 왼쪽 상단 모서리를 맞은 경우
										} else if (y == br.getY() - this.getR()) {
											System.out.print("모서리 ");
											//만약 위쪽에 벽돌이 없다면 y벡터를 왼쪽에 벽돌이 x벡터를 둘다 있다면 x,y벡터 모두 반전
											if (!(Game.getCon().getComponentAt(br.getX() - 3,br.getY() + br.getSize_y() / 2) instanceof BrickImagePanel)) {
												this.setDx(-this.getDx());
												System.out.println("위쪽에 벽돌이 없어 위쪽 튕김");
										}else if (!(Game.getCon().getComponentAt(br.getX() + br.getSize_x() / 2,br.getY() - 3) instanceof BrickImagePanel)) {
											System.out.println("왼쪽에 벽돌이 없어 왼쪽 튕김");
												this.setDy(-this.getDy());
										}else {
											System.out.println("위쪽 왼쪽 모두 벽돌이 있어 반환시킴");
												this.setDy(-this.getDy());
												this.setDx(-this.getDx());
											}
										} else {
											System.out.println("error");
										}
									}
									// 오른쪽 상단에서 공이 날아 올때
									else if (this.getDx() < 0 && this.getDy() > 0) { 

										System.out.print("("+br.getX()+","+br.getY()+")"+"오른쪽 위 ");
										int x = br.getX() + br.getSize_x();
										int y = (int) ((this.getDy() / this.getDx()) * (x - this.getx()) + this.gety());
										//오른쪽 윗면에서 부딛혔을 경우
										if (y < br.getY() - this.getR()) {
											System.out.print("윗면 ");
											//윗면에 벽돌이 있는경우 검사하고 있으면 x벡터를 없으면 y벡터를 반전
											if (Game.getCon().getComponentAt(br.getX() + br.getSize_x() / 2,br.getY() - 3) instanceof BrickImagePanel) {
												this.setDx(-this.getDx());
												System.out.println("위쪽에 벽돌이 있어 오른쪽 튕김");
										}else {
												this.setDy(-this.getDy());
												System.out.println("위쪽 튕김");
										}
											//오른쪽 옆면에 부딛혔을 경우
										} else if (y > br.getY() - this.getR()) {

											System.out.print("측면 ");
											//옆면에 벽돌이 있는경우  y벡터를 없다면 x벡터를 반전
											if (Game.getCon().getComponentAt(br.getX() + br.getSize_x() + 3,br.getY() + br.getSize_y() / 2) instanceof BrickImagePanel) {
												this.setDy(-this.getDy());
											System.out.println("오른쪽에 벽돌이 있어 위쪽 튕김");
										}
										else {
												this.setDx(-this.getDx());
												System.out.println("오른쪽 튕김");
										}//오른쪽 상단 모서리에 부딛혔을 경우
											
										} else if (y == br.getY() - this.getR()) {

											System.out.print("모서리 ");
											//모서리에 부딛혔을 경우 오른쪽에 벽돌이 없다면 x벡터를 위쪽에 벽돌이 없다면 y벡터를 만약 둘다 있다면 x,y벡터를 둘다 반전 
											if (!(Game.getCon().getComponentAt(br.getX() + br.getSize_x() + 3,br.getY() + br.getSize_y() / 2) instanceof BrickImagePanel)) {
												this.setDx(-this.getDx());
												System.out.println("오른쪽에 벽돌이 없어 오른쪽 튕김");
										}else if (!(Game.getCon().getComponentAt(br.getX() + br.getSize_x() / 2,br.getY() - 3) instanceof BrickImagePanel)) {
												this.setDy(-this.getDy());
												System.out.println("위쪽에 벽돌이 없어 위쪽 튕김");
										}
											else {
												System.out.println("위쪽 오른쪽 모두 벽돌이 있어 반환시킴");
												this.setDy(-this.getDy());
												this.setDx(-this.getDx());
											}
										} else {
											System.out.println("error");
										}
									}
									// 왼쪽 하단에서 공이 날아 올때
									else if (this.getDx() > 0 && this.getDy() < 0) {
										
										System.out.print("("+br.getX()+","+br.getY()+")"+"왼쪽 밑 ");
										int x = br.getX();
										int y = (int) ((this.getDy() / this.getDx()) * (x - (this.getx() + this.getR()))
												+ this.gety());
										//공이 왼쪽 밑면에서 충돌했을 경우
										if (y > br.getY() + br.getSize_y()) {
											System.out.print("밑면 ");
											//아래쪽이 벽돌이 있을경우 x축을, 왼쪽에 벽돌이 있을경우 y축을 벡터 반전
											if (Game.getCon().getComponentAt(br.getX() + br.getSize_x() / 2,br.getY() + br.getSize_y() + 3) instanceof BrickImagePanel) {
												this.setDx(-this.getDx());
												System.out.println("아래쪽에 벽돌이 있어 왼쪽 튕김");
										}
											else {
												this.setDy(-this.getDy());
												System.out.println("아래쪽 튕김");
										}
											//공이 왼쪽 옆면을 맞았을 경우
										} else if (y < br.getY() + br.getSize_y()) {
											System.out.print("측면 ");
											// 왼쪽에 벽돌이 있을경우 y축을 벡터 반전,아래쪽이 벽돌이 있을경우 x축을
											if (Game.getCon().getComponentAt(br.getX() - 3,br.getY() + br.getSize_y() / 2) instanceof BrickImagePanel) {
												this.setDy(-this.getDy());
												System.out.println("왼쪽에 벽돌이 있어 아래쪽 튕김");
										}else {
												this.setDx(-this.getDx());
												System.out.println("왼쪽 튕김");
										}//공이 왼쪽 하단 모서리에 맞았을 경우
										} else if (y == br.getY() + br.getSize_y()) {
											System.out.print("모서리 ");
											if (!(Game.getCon().getComponentAt(br.getX() - 3,br.getY() + br.getSize_y() / 2) instanceof BrickImagePanel)) {
												
												this.setDx(-this.getDx());
												System.out.println("왼쪽에 벽돌이 없어 왼쪽 튕김");
										}else if (!(Game.getCon().getComponentAt(br.getX() + br.getSize_x() / 2,br.getY() + br.getSize_y() + 3) instanceof BrickImagePanel)) {
												this.setDy(-this.getDy());
												System.out.println("아래쪽에 벽돌이 없어 아래쪽 튕김");
										}
											else {
												System.out.println("아래쪽과 왼쪽 모두 벽돌이 있어 반환시킴");
												this.setDy(-this.getDy());
												this.setDx(-this.getDx());
											}
										} else {
											System.out.println("error");
										}

									}
									// 오른쪽 하단에서 공이 날아 올때
									else if (this.getDx() < 0 && this.getDy() < 0) {

										System.out.print("("+br.getX()+","+br.getY()+")"+"오른쪽 밑 ");
										int x = br.getX() + br.getSize_x();
										int y = (int) ((this.getDy() / this.getDx()) * (x - this.getx()) + this.gety());
										//공이 오른쪽 하단 밑면에 맞았을 경우
										if (y > br.getY() + br.getSize_y()) {
											System.out.print("밑면 ");
											if (Game.getCon().getComponentAt(br.getX() + br.getSize_x() / 2,br.getY() + br.getSize_y() + 3) instanceof BrickImagePanel
													) {
												this.setDx(-this.getDx());
												System.out.println("아래쪽에 벽돌이 있어 오른쪽 튕김");
										}else {
												this.setDy(-this.getDy());
												System.out.println("아래쪽 튕김");
											}
											//공이 오른쪽 하단 옆면에 맞았을 경우
										} else if (y < br.getY() + br.getSize_y()) {
											System.out.print("측면 ");
											if (Game.getCon().getComponentAt(br.getX() + br.getSize_x() + 3,br.getY() + br.getSize_y() / 2) instanceof BrickImagePanel
													) {
												this.setDy(-this.getDy());
												System.out.println("오른쪽에 벽돌이 있어 아래쪽 튕김");
										}else {
												this.setDx(-this.getDx());
												System.out.println("오른쪽 튕김");
										}//공이 오른쪽 하단 모서리에 맞았을 경우
										} else if (y == br.getY() + br.getSize_y()) {
											System.out.print("모서리 ");
											if (!(Game.getCon().getComponentAt(br.getX() + br.getSize_x() / 2,br.getY() + br.getSize_y() + 3) instanceof BrickImagePanel)) {
												this.setDy(-this.getDy());
												System.out.println("아래쪽에 벽돌이 없어 아래쪽 튕김");
											}else if (!(Game.getCon().getComponentAt(br.getX() + br.getSize_x() + 3,br.getY() + br.getSize_y() / 2) instanceof BrickImagePanel)) {
												this.setDx(-this.getDx());
												System.out.println("오른쪽에 벽돌이 없어 오른쪽에 튕김");
											}else {
												System.out.println("아래쪽과 오른쪽 모두 벽돌이 있어 반환시킴");
												this.setDy(-this.getDy());
												this.setDx(-this.getDx());
											}
										} else {
											System.out.println("error");
										}
									}
									// this.getDy()는 0이 될 수 없음
									else {
										System.out.println("error");
									}
								} // dx!=0
								
							} // flag
							if(br.isEmpty_Hp()) {
								flag=true;
							}
							break;
						} // 벽돌충돌

						// 마지막 까지 충돌 안일어 났다면 flag를 true로
						if (j == Game.getStages().get(Game.getstageNum()).getBricks().size() - 1) {
							flag = true;
						}
					}
				}

				// ********************* 이동 *********************\\

				// 현제 x,y값을 변경
				this.setX((this.getx() + this.getDx()));
				this.setY((this.gety() + this.getDy()));
				// 공을 표시하는 팬의 위치를 현제 x,y값에 맞춰 변경
				this.pan.setLocation((int) this.getx(), (int) this.gety());
				this.setCenter( new Point((int)(this.getx()+this.getR()/2),(int)(this.gety()+this.getR()/2)));
				try {

					// 1초에 60번 스레드 동작
					Thread.sleep(1000 / 60);
				} catch (InterruptedException e) {
					e.getMessage();
				}
			}

		};
	}

	public class ballImagePanel extends JPanel {
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
		public void reset() {
			ch.setB(ball.this);
			ball.this.reset(ch);
		}
		public void setmainball() {
			ch.setB(ball.this);
		}
		public ball getball() {
			return ball.this;
		}
	}

	// 공을 가지고 있는 패널을 반환해줌
	public JPanel getPan() {
		return pan;
	}

	public void reset(character ch) {
		setR(start_R);
		setDy(0);
		setDx(0);
		setSpeed(1d);
		setX(ch.getX() + (int) (ch.getSize_x() / 2) - getR() / 2);
		setY(ch.getY() - getR());
	}

	public void setball_image(String str) throws IOException {
		image = ImageIO.read(new File(str));
	}
	public BufferedImage getball_image() {
		return image;
	}
	public void setball_num(int ball_num) {
		this.ball_num=ball_num;
	}
	public int getball_num() {
		return this.ball_num;
	}
}
