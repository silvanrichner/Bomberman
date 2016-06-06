import processing.core.PApplet;
import processing.core.PImage;

public abstract class Powerup implements MapItem {
	
	protected PApplet applet;
	protected MapItem[][] map;
	protected int x;
	protected int y;
	protected PImage image; 

	public Powerup(PApplet applet, MapItem[][] map, int x, int y) {
		this.applet = applet;
		this.map = map;
		this.x = x;
		this.y = y;
	}
	
	public abstract void applyPowerup(Player player);
	
	@Override
	public boolean isBlocking(Player player) {
		return false;
	}

	@Override
	public boolean isDestructible() {
		return false;
	}

	@Override
	public void paint(int x, int y) {
		applet.image(image,x,y);
	}

}
