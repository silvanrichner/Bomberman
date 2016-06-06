
import java.io.File;

import processing.core.PApplet;
import processing.core.PImage;

public class Bomb implements MapItem {

	private PApplet applet;
	private PImage image;
	private MapItem[][] map;

	int tileSize = 40;
	// Owner of bomb
	private Player owner;

	// Position Bomb = Players Position when bomb dropped
	private int x;
	private int y;

	// The number of tiles that are affected by the bomb
	private int radius;

	// Amount of frames until the bomb detonates
	private int timerBomb = 180;

	public Bomb(PApplet applet, MapItem[][] map, Player owner, int x, int y) {
		this.applet = applet;
		this.map = map;

		this.owner = owner;
		this.radius = owner.getBombRadius();

		this.x = x;
		this.y = y;
		
		this.image = applet.loadImage("src"+File.separator+"resources"+File.separator+"danger.png");
	}

	@Override
	public boolean isBlocking(Player player) {
		if(owner.equals(player)){
			return timerBomb < 120;
		}
		
		return true;
	}

	@Override
	public boolean isDestructible() {
		return false;
	}

	@Override
	public void paint(int x, int y) {
		applet.image(image,x,y);
	}

	public void detonateBomb(Player owner) {
		map[x / tileSize][y / tileSize] = new Explosion(applet);

		// spread right
		int i = 1;
		while (!destroyTile(x / tileSize + i, y / tileSize) && i <= radius-1) {
			i++;
		}

		// spread down
		i = 1;
		while (!destroyTile(x / tileSize, y / tileSize + i) && i <= radius-1) {
			i++;
		}

		// spread left
		i = 1;
		while (!destroyTile(x / tileSize - i, y / tileSize) && i <= radius-1) {
			i++;
		}

		// spread up
		i = 1;
		while (!destroyTile(x / tileSize, y / tileSize - i) && i <= radius-1) {
			i++;
		}

		owner.restoreBomb();
	}

	private boolean destroyTile(int x, int y) {
		if (x < map.length && x >= 0 && y < map[x].length && y >= 0)
			if (map[x][y] != null) {
				if (map[x][y].isDestructible()) {

					if (map[x][y] instanceof Crate) {
						if(!((Crate) map[x][y]).destroy()){
							map[x][y] = new Explosion(applet);
						}
					}else{
						map[x][y] = new Explosion(applet);
					}
				}
				return true;
			} else {
				map[x][y] = new Explosion(applet);
			}
		return false;
	}

	// Getter and Setter
	public int getBombTimer() {
		return timerBomb;
	}

	public void setBombTimer(int t) {
		timerBomb = t;
	}

	public Player getOwner() {
		return owner;
	}

}
