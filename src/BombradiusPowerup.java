import processing.core.PApplet;

public class BombradiusPowerup extends Powerup {

	public BombradiusPowerup(PApplet applet, MapItem[][] map, int x, int y) {
		super(applet, map, x, y);
		// TODO Auto-generated constructor stub
	}
	public void applyPowerup(Player player) {
		player.increaseRadius();
		
		map [x][y] = null; 
			
	}
	
	

	

}
