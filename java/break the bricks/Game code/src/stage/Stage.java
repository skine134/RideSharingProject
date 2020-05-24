package stage;

import java.util.ArrayList;
import java.util.HashMap;

import arkanoid_object.Music;
import arkanoid_object.brick;

public abstract class Stage{

	public ArrayList<brick> bricks;
	public ArrayList<Thread> bricks_th;
	public int stage_num;
	public ArrayList<brick> Unbreak_bricks;
	Stage(){
		bricks = new ArrayList<brick>();
		bricks_th = new ArrayList<Thread>();
		Unbreak_bricks = new ArrayList<brick>();
	}
	public HashMap<Integer, ArrayList<Thread>> returnbricks_th() {
		HashMap<Integer, ArrayList<Thread>> map = new HashMap<Integer, ArrayList<Thread>>();
		map.put(stage_num, bricks_th);
		return map;
	}
	
	public HashMap<Integer, ArrayList<brick>> returnbricks() {
		HashMap<Integer, ArrayList<brick>> map = new HashMap<Integer, ArrayList<brick>>();
		map.put(stage_num, bricks);
		return map;
	}

	
	
	public ArrayList<brick> getUnbreak_bricks() {
		return Unbreak_bricks;
	}

	public ArrayList<brick> getBricks() {
		return bricks;
	}

	public ArrayList<Thread> getBricks_th() {
		return bricks_th;
	}

	public int getStage_num() {
		return stage_num;
	}


	public abstract String getH_wall() ;

	public abstract String getW_wall() ;

	public abstract String getball_image();

	public abstract String getBackground_image();
	
	public abstract String getStage_Music();
}
