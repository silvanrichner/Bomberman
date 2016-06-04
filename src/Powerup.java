import java.io.File;

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
	public boolean isBlocking() {
		return false;
	}

	@Override
	public boolean isDestructible() {
		return false;
	}

	@Override
	public void paint(int x, int y) {
	  PImage image = applet.loadImage(".."+File.separator+"res"+File.separator+"bomb.png");	// TODO Auto-generated method stub
		applet.image(image,x,y);
	}

}
