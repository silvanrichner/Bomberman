import processing.core.PApplet;

public class SpeedPowerup extends Powerup {

	public SpeedPowerup(PApplet applet, MapItem[][] map, int x, int y) {
		super(applet, map, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void applyPowerup(Player player) {
		player.increaseSpeed();
		
		map [x][y] = null; 
			
	}

}
