package teamproject_Game;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;
import arkanoid_object.*;
public class Application extends JFrame{

	// 배경화면
	private static BackgroundImage backImage=new BackgroundImage();
	
	// 배경음악
		private static Music backMusic=new Music("sounds\\impactive sound\\city_brick2.mp3",true);
	
	// 컨테이너
	private static Container con;
	
	private static main_menu main;
	private static Game game;
	Application() throws IOException{
		//*************************************기본 셋팅
				
				// 이름 지정
				setTitle("break the brick");

				// 게임 종료시 콘솔도 종료
				setDefaultCloseOperation(EXIT_ON_CLOSE);

				// 메인 창 활성화
				setVisible(true);

				// 어플 크기 지정
				setSize(600, 1000);

				// 창 사이즈 조정 불가
				setResizable(false);

				// 컨테이너에 컨텐츠팬 삽입
				con = getContentPane();

				// 컨테이너의 배치타입(Layout)을 null로 지정(크기와 좌표로 각 객체를 배치함)
				con.setLayout(null);
		//*************************************스레드 객체 선언
				main=new main_menu();
				game=new Game();
		//*************************************main menu
				Thread intro=new Thread(backMusic.th);
				intro.start();
				main.start();
				try {
					main.join();
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
	}
	public static void main(String args[]) throws IOException {
		new Application();
	}
	public static BackgroundImage getBackImage() {
		return backImage;
	}
	public static void setBackImage(BackgroundImage backImage) {
		Application.backImage = backImage;
	}
	public static Music getBackMusic() {
		return backMusic;
	}
	public static void setBackMusic(Music backMusic) {
		Application.backMusic = backMusic;
	}
	public static Container getCon() {
		return con;
	}
	public static Thread mainmenuThread() {
		return main;
	}
	public static Thread GameThread() {
		return game;
	}

	public static void mainmenuThreadreset() {
		main=new main_menu();
	}
	public static void GameThreadreset() {
		try {
			game=new Game();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
