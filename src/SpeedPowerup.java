import java.io.File;

import processing.core.PApplet;

public class SpeedPowerup extends Powerup {

	public SpeedPowerup(PApplet applet, MapItem[][] map, int x, int y) {
		super(applet, map, x, y);
		image = applet.loadImage("src"+File.separator+"resources"+File.separator+"urgency.png");
	}

	@Override
	public void applyPowerup(Player player) {
		player.increaseSpeed();
		
		map [x][y] = null; 
			
	}

}
