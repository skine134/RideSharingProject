package teamproject_Game;

import java.awt.*;

import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import arkanoid_object.*;
import arkanoid_object.ball.ballImagePanel;
import stage.*;

public class Game extends Thread {

	// 배경화면
	BackgroundImage background;

	// 배경음악
	Music backMusic;
	Thread backMusic_th;
	// 컨테이너
	private static Container con;

	// 벽
	Wall[] wall;

	// 공
	ball b;

	// 캐릭터(유저)
	character ch;

	private static int stage_num = 1;

	// 각 스테이지 벽돌의 모음집
	public static HashMap<Integer, Stage> stages = new HashMap<Integer, Stage>();

	// 아이템 관리 모음집
	public static ArrayList<Item> items = new ArrayList<Item>();;

	// 각 객채에 대한 스레드
	Thread ball_th;
	Thread ch_ball_th;
	Thread move_th;
	Thread ch_items_th;

	Music start_ball;
	public static boolean flag;

	public Game(int stage_num) throws IOException {
		this.stage_num = stage_num;
		// 컨테이너에 컨텐츠팬 삽입
		con = Application.getCon();

//*************************************각 객체의 선언

		ch = new character();
		wall = new Wall[4];
		b = new ball(ch);
		background = Application.getBackImage();
		backMusic = Application.getBackMusic();
		ch.setCollision(b);
		ch.setB(b);
		ch_ball_th = new Thread(ch.balls_th.get(ch.ball_count - 1));
		ch.setCollision(items);
		ch_items_th = new Thread(ch.items_th);
		ball_th = new Thread(b.th);
		start_ball = new Music("sounds\\impactive sound\\start_ball.mp3", false);

		// 각 스테이지에 대한 객체 선언
		stages.put(1, new stage1(ch, b));
		stages.put(2, new stage3(ch, b));
		stages.put(3, new stage4(ch, b));
		stages.put(4, new stage5(ch, b));
		stages.put(5, new stage2(ch, b));
		// move_th의 선언(move는 Runable 클래스로 선언 되었으므로 Thread에 포함 시켜주어야함).
		move_th = new Thread(ch.move());

		// 컨테이너에 키보드 리스너 이벤트를 부여함
		ch.getPan().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					if (ch.right)
						ch.right = false;
					if (ch.reverseflag) {
						if (ch.getDx() == 0)
							ch.setDx(ch.getMove_speed());
						else if (ch.getDx() < 0)
							ch.setDx(-ch.getDx());
					} else {
						if (ch.getDx() == 0)
							ch.setDx(-ch.getMove_speed());
						else if (ch.getDx() > 0)
							ch.setDx(-ch.getDx());
					}
					ch.left = true;

				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if (ch.left)
						ch.left = false;

					if (ch.reverseflag) {
						if (ch.getDx() == 0)
							ch.setDx(-ch.getMove_speed());
						// 만약 캐릭터가 이전에 오른쪽으로 움직이고 있었다면 x축 벡터방향을 반대로 변경
						else if (ch.getDx() > 0)
							ch.setDx(-ch.getDx());
					} else {
						// 만약 캐릭터의 움직이지 않고 정지 상태라면(초기 상태에서 움직이지 않음) x축 벡터를 +5로 설정
						if (ch.getDx() == 0)
							ch.setDx(ch.getMove_speed());
						// 만약 캐릭터가 이전에 왼쪽으로 움직이고 있었다면 x축 벡터방향을 반대로 변경
						else if (ch.getDx() < 0)
							ch.setDx(-ch.getDx());
					}
					ch.right = true;

				}

				// 만약에 키가 눌려있는데 그 키가 스페이스 방향키이고 공의 y축 백터가 0이라면(정지상태라면) 아래 명령어 수행
				if (e.getKeyCode() == KeyEvent.VK_SPACE && ch.getB().getDy() == 0) {
					new Thread(start_ball.th).start();

					// 캐릭터가 움직인 방향으로 공을 발사(정지해 있다면 수직으로 발사)
					if (ch.getDx() != 0) {
						if (ch.getDx() < 0)
							ch.getB().setDx(-Math.sqrt(ch.getB().getSpeed()) * ball.start_D);
						else
							b.setDx(Math.sqrt(b.getSpeed()) * ball.start_D);
						ch.getB().setDy(-ball.start_D * Math.sqrt(ch.getB().getSpeed()));
					} else
						ch.getB().setDy(-ball.start_D * Math.sqrt(ch.getB().getSpeed() * 2));
				}

				// esc 버튼 클릭시 게임 종료
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(1);
				}
			}

			@Override // 누른 키가 떨어졌을때 이벤트
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					ch.right = false;
					if (ch.left == true)
						return;
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					ch.left = false;
					if (ch.right == true)
						return;
				}
				ch.setDx(0);
			}

		});
	}

	public Game() throws IOException {
		this(1);
	}

	public void run() {
		flag = false;
//*************************************배치
		// 벽에 대한 배치
		for (int i = 0; i < 4; i++) {

			// 각 벽에대한 객체 선언
			wall[i] = new Wall();

			// 만약 i가 0,1이면 10,953 로 막대 크기를 지정(세로 막대)
			if (i < 2)
				wall[i].getPan().setSize(10, 963);

			// 만약 i가 2,3이면 582,10 로 막대 크기를 지정(가로 막대)
			else
				wall[i].getPan().setSize(592, 10);
		}

		// 각 벽의 컨테이너에 배치 및 위치 지정
		con.add(wall[0].getPan());
		wall[0].getPan().setLocation(0, 0);
		con.add(wall[1].getPan());
		wall[1].getPan().setLocation(582, 0);
		con.add(wall[2].getPan());
		wall[2].getPan().setLocation(0, 0);
		con.add(wall[3].getPan());
		wall[3].getPan().setLocation(0, 953);

		// 현제 hp를 보여주는 이미지를 오른쪽 상단에 표시
		for (int i = 0; i < ch.getHp_pans().size(); i++) {
			con.add(ch.getHp_pans().get(i));
			ch.getHp_pans().get(i).setSize(50, 50);
			ch.getHp_pans().get(i).setLocation(500 - i * 50, 15);
		}

		// 캐릭터의 컨테이너에 배치 및 위치 지정
		con.add(ch.getPan());
		ch.getPan().setLocation(ch.getX(), ch.getY());

		// 공의 컨테이너에 배치 및 위치 지정
		con.add(b.getPan());
		b.getPan().setLocation(b.getX(), b.getY());

		con.add(background.getPan());

//*************************************각 스레드 동작

		ball_th.start();
		ch_ball_th.start();
		ch_items_th.start();
		move_th.start();

		// 키 이벤트를 받을 수 있도록 컨테이너 설정
		ch.getPan().setFocusable(true);
		ch.getPan().requestFocus();

//*************************************스테이지별 게임 작동
		for (int i = 5; i <= stages.size(); i++) {
			stage_num = i;
			ch.ball_count=1;
			// 컨테이너에 n스테이지에 포함된 벽돌을 추가 및 배치
			for (int j = 0; j < stages.get(i).getBricks().size(); j++) {
				con.add(stages.get(i).getBricks().get(j).getPan());
			}
			// 각 벽에 대한 이미지 지정
			try {
				for (int j = 0; j < wall.length; j++) {
					if (j < 2)
						wall[j].setWall_image(stages.get(i).getH_wall());

					else
						wall[j].setWall_image(stages.get(i).getW_wall());

				}

				// 공의 이미지 지정
				ch.getB().setball_image(stages.get(i).getball_image());

				// 배경화면 이미지 지정
				background.setBackGround_image(stages.get(i).getBackground_image());
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 배경화면과 공을 맨 뒤로 보냄( 다른 객체들이 보이게 하기 위함)
			con.setComponentZOrder(background.getPan(), con.getComponentCount() - 1);
			con.setComponentZOrder(ch.getB().getPan(), con.getComponentCount() - 2);

			// 한 스테이지가 지날 때마다 컨테이너를 동기화(이미지 최신화)시킴
			con.repaint();
			backMusic.setMusic(stages.get(i).getStage_Music());
//			backMusic_th=new Thread(backMusic.th);
//			backMusic_th.start();
			// 블록이 모두 사라질 때 까지 무한 반복(한 스테이지에 머무르게 하기 위함)

			while (stages.get(i).getBricks().size() > (stages.get(i)).getUnbreak_bricks().size()) {
				if (flag)
					return;
				for (int j = 0; j < stages.get(i).getBricks().size(); j++) {
					if (!stages.get(i).getBricks().get(j).getPan().isVisible()) {
						stages.get(i).getBricks().remove(j);
					}
				}
			}
			for (int j = 0; j < stages.get(i).getUnbreak_bricks().size(); j++)
				con.remove(stages.get(i).getUnbreak_bricks().get(j));
			// 공과 캐릭터의 위치와 벡터값과 크기를 초기값으로 변경
			ch.reset();
			ch.getB().reset(ch);
			for(int j=0;j<Game.getCon().getComponentCount();j++)
				if(con.getComponent(j)instanceof ballImagePanel&&con.getComponent(j)!=ch.getB().getPan()) {
					ballImagePanel ballimage=(ballImagePanel)con.getComponent(j);
					ball setball=ballimage.getball();
					setball.setDx(0);
					setball.setDy(0);
					setball.setX(1000d);
					setball.setY(1000d);
					setball.getPan().setLocation((int)setball.getx(),(int)setball.gety());
					con.remove(j);
				}
			if (i != 5) {
				next_stage next = new next_stage(ch);
				next.start();
				try {
					next.join();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}

		new finish_stage(ch).start();
	}

	public static Container getCon() {
		return con;
	}

	public static HashMap<Integer, Stage> getStages() {
		return stages;
	}

	public static int getstageNum() {
		return stage_num;
	}
}
