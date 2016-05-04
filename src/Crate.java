import java.util.Random;

import processing.core.PApplet;

public class Crate implements MapItem {
	
	private PApplet applet;
	private MapItem[][] map;
	
	private boolean containsPowerup;
	
	private int x;
	private int y;

	public Crate(PApplet applet, MapItem[][] map, int x, int y) {
		this.applet = applet;
		this.map = map;
		
		this.x = x;
		this.y = y;
		
		this.containsPowerup = Math.random() < 0.3;
	}

	@Override
	public boolean isBlocking() {
		return true;
	}

	@Override
	public boolean isDestructible() {
		return true;
	}

	@Override
	public void paint(int x, int y) {
		applet.fill(0,0,255);
		applet.rect(x, y, 40, 40);

	}
	
	public void destroy(){
		//remove the crate from the map
		map[x][y] = null;
		
		//drop a powerup
		if(containsPowerup){
			Powerup powerup = null;
			int random = new Random().nextInt(3);
			switch(random){
			case 0:
				//Speed Powerup
				powerup = new SpeedPowerup(applet, map, x, y);
			case 1:
				//additional bomb Powerup
				powerup = new BombPowerup(applet, map, x, y);
			case 2:
				//Bombradius Powerup
				powerup = new BombradiusPowerup(applet, map, x, y);
			}
			
			map[x][y] = powerup;
		}
	}

}
