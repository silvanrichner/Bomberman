import java.io.File;

import processing.core.PApplet;

public class BombPowerup extends Powerup {

	public BombPowerup(PApplet applet, MapItem[][] map, int x, int y) {
		super(applet, map, x, y);
		image = applet.loadImage("src"+File.separator+"resources"+File.separator+"danger.png");
	}
	
	@Override 
	public void applyPowerup(Player player) {
		player.increaseBombCount();
		
		map [x][y] = null; 
	}
}
