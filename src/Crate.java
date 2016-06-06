import java.io.File;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

public class Crate implements MapItem {
	
	private PApplet applet;
	private PImage image;
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
		
		this.image = applet.loadImage("src"+File.separator+"resources"+File.separator+"crate.png");
	}

	@Override
	public boolean isBlocking(Player player) {
		return true;
	}

	@Override
	public boolean isDestructible() {
		return true;
	}

	@Override
	public void paint(int x, int y) {
		applet.image(image, x, y);
	}
	
	/**
	 * 
	 * @return true when there was a powerup created
	 */
	public boolean destroy(){
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
				break;
			case 1:
				//additional bomb Powerup
				powerup = new BombPowerup(applet, map, x, y);
				break;
			case 2:
				//Bombradius Powerup
				powerup = new BombradiusPowerup(applet, map, x, y);
			}
			map[x][y] = powerup;
			return true;
		}
		return false;
	}
}
