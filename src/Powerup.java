import processing.core.PApplet;

public class Powerup implements MapItem {
	
	private PApplet applet;
	private MapItem[][] map;
	private int x;
	private int y;

	public Powerup(PApplet applet, MapItem[][] map, int x, int y) {
		this.applet = applet;
		this.map = map;
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean isBlocking() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDestructible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void paint(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
