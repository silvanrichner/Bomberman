
import java.io.File;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Indestructible Blocks are used to build the map.
 * They block the players movement and aren't affected by explosions.
 * 
 * @author Silvan
 *
 */
public class IndestructibleBlock implements MapItem {
	
	private PApplet applet;
	private PImage image;
	
	public IndestructibleBlock(PApplet applet) {
		this.applet = applet;
		this.image = applet.loadImage("src"+File.separator+"resources"+File.separator+"wall.png");
	}

	@Override
	public boolean isBlocking(Player player) {
		return true;
	}

	@Override
	public void paint(int x, int y) {
		applet.image(image, x,y);
	}

	@Override
	public boolean isDestructible() {
		return false;
	}

}
