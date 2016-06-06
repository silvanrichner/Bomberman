import java.io.File;

import processing.core.PApplet;
import processing.core.PImage;

public class Explosion implements MapItem{
	
	private PApplet applet;
	private PImage image;
	
	//Amount of time explosion is shown
	private int timerExplosion = 60;
	
	public Explosion(PApplet applet) {
		this.applet = applet;
		this.image = applet.loadImage("src"+File.separator+"resources"+File.separator+"explosion.png");
	}
	
	public int getTimerExplosion() {
		return timerExplosion;
	}

	public void setTimerExplosion(int timerExplosion) {
		this.timerExplosion = timerExplosion;
	}
	@Override
	public void paint(int x, int y){
		applet.image(image, x, y);
	}

	@Override
	public boolean isBlocking(Player player) {
		return false;
	}

	@Override
	public boolean isDestructible() {
		return false;
	}
	
}
