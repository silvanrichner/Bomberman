import java.io.File;

import processing.core.PApplet;

public class BombradiusPowerup extends Powerup {

	public BombradiusPowerup(PApplet applet, MapItem[][] map, int x, int y) {
		super(applet, map, x, y);
		image = applet.loadImage(".."+File.separator+"resources"+File.separator+"explosion.png");
	}
	public void applyPowerup(Player player) {
		player.increaseRadius();
		
		map [x][y] = null; 
			
	}
	
	

	

}
