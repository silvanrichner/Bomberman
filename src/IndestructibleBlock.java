
import processing.core.PApplet;

/**
 * Indestructible Blocks are used to build the map.
 * They block the players movement and aren't affected by explosions.
 * 
 * @author Silvan
 *
 */
public class IndestructibleBlock implements MapItem {
	
	PApplet applet;
	
	public IndestructibleBlock(PApplet applet) {
		this.applet = applet;
	}

	@Override
	public boolean isBlocking() {
		return true;
	}

	@Override
	public void paint(int x, int y) {
		applet.fill(255,0,0);
		applet.rect(x, y, 40, 40);
	}

	@Override
	public boolean isDestructible() {
		return false;
	}

}
