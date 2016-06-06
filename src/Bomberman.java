import java.io.File;

import processing.core.PApplet;
import processing.core.PImage;

public class Bomberman extends PApplet {

	boolean gameRunning = false;
	boolean gameEnded = false;
	
	String winner;

	// the dimensions of the playing field
	int mapSizeX = 15;
	int mapSizeY = 11;

	// the playing field
	MapItem[][] map;

	// the size in pixels of one tile of the playing field
	int tileSize = 40;

	// the players (duh!)
	Player player1;
	Player player2;

	public static void main(String args[]) {
		PApplet.main(new String[] { "Bomberman" });
	}

	@Override
	public void settings() {
		size(mapSizeX * tileSize, mapSizeY * tileSize);
	}

	@Override
	public void setup() {
		background(255, 255, 255);
		noStroke();

		// Initialize the map
		map = new MapItem[mapSizeX][];
		for (int i = 0; i < mapSizeX; i++) {
			map[i] = new MapItem[mapSizeY];
		}

		// add crates
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (Math.random() < 0.7) {
					map[i][j] = new Crate(this, map, i, j);
				}
			}
		}
		// make sure that there are no crates on or right next to the players
		// spawn point
		map[0][0] = null;
		map[0][1] = null;
		map[1][0] = null;
		map[mapSizeX - 1][mapSizeY - 1] = null;
		map[mapSizeX - 2][mapSizeY - 1] = null;
		map[mapSizeX - 1][mapSizeY - 2] = null;

		// add indestructible blocks
		for (int i = 1; i < map.length; i++) {
			for (int j = 1; j < map[i].length; j++) {
				if (j % 2 != 0 && i % 2 != 0) {
					map[i][j] = new IndestructibleBlock(this);
				}
			}
		}

		// initialize the players
		player1 = new Player(this, map, width - 35, height - 35, tileSize);
		player1.setImage(loadImage("src" + File.separator + "resources" + File.separator + "people-1.png"));
		player2 = new Player(this, map, 5, 5, tileSize);
		player2.setImage(loadImage("src" + File.separator + "resources" + File.separator + "people.png"));

	}

	@Override
	public void draw() {
		if (gameRunning) {
			gameScreen();
		} else if (gameEnded) {
			endScreen();
		} else {
			startScreen();
		}
	}

	private void startScreen() {
		background(255, 255, 255);
		fill(0, 0, 0);

		textSize(36);
		text("BOMBERMAN", 180, 50);

		textSize(12);
		// player1
		text("Player 1", 120, 120);
		PImage p1Image = loadImage("src" + File.separator + "resources" + File.separator + "people-1.png");
		image(p1Image, 130, 130);
		text("move: [Arrowkeys]", 120, 175);
		text("drop bomb: [SHIFT]", 120, 190);

		// player1
		text("Player 2", 390, 120);
		PImage p2Image = loadImage("src" + File.separator + "resources" + File.separator + "people.png");
		image(p2Image, 400, 130);
		text("move: [WASD]", 390, 175);
		text("drop bomb: [e]", 390, 190);

		text("press [ENTER] to start the game", 200, 300);

	}

	private void endScreen() {
		background(255, 255, 255);
		fill(0,0,0);
		
		textSize(18);
		text(winner + " has won!",210, 125);
		
		textSize(12);
		text("Press [ENTER] to play again or [ESC] to exit", 160, 310);
	}

	private void gameScreen() {
		// clear the screen
		background(255, 255, 255);

		// paint the players at their current position
		player1.paint();
		player2.paint();

		// draw the elements on the map
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] != null) {
					map[i][j].paint(i * tileSize, j * tileSize);
				}
			}
		}

		// detonates bomb -> adds explosions
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] instanceof Bomb) {
					Bomb bomb = (Bomb) map[i][j];
					if (bomb.getBombTimer() >= 0) {
						int oldTimer = bomb.getBombTimer();
						bomb.setBombTimer(oldTimer - 1);
					} else {
						bomb.detonateBomb(bomb.getOwner());
					}
				}
			}
		}

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] instanceof Explosion) {
					//check if the explosion hit a player
					if(player1.getXTile() == i && player1.getYTile() == j){
						winner = "Player 2";
						gameRunning = false;
						gameEnded = true;
					}
					
					if(player2.getXTile() == i && player2.getYTile() == j){
						winner = "Player 1";
						gameRunning = false;
						gameEnded = true;
					}
					
					Explosion explosion = (Explosion) map[i][j];
					if (explosion.getTimerExplosion() != 0) {
						int oldTimer = explosion.getTimerExplosion();
						explosion.setTimerExplosion(oldTimer - 1);
					} else {
						map[i][j] = null;
					}
				}
			}
		}
	}

	@Override
	public void keyPressed() {
		if (gameRunning) {
			// The value of the arrowkeys can't be read directly from the key
			// variable because it's coded
			if (key == CODED) {
				// Player 1
				switch (keyCode) {
				case UP:
					player1.setDirection(Direction.UP);
					break;
				case DOWN:
					player1.setDirection(Direction.DOWN);
					break;
				case LEFT:
					player1.setDirection(Direction.LEFT);
					break;
				case RIGHT:
					player1.setDirection(Direction.RIGHT);
					break;
				case SHIFT:
					player1.dropBomb();
				}
			} else {
				switch (key) {
				// Player 2
				case 'w':
					player2.setDirection(Direction.UP);
					break;
				case 'a':
					player2.setDirection(Direction.LEFT);
					break;
				case 's':
					player2.setDirection(Direction.DOWN);
					break;
				case 'd':
					player2.setDirection(Direction.RIGHT);
					break;
				case 'e':
					player2.dropBomb();
				}
			}
		} else {
			switch(key){
			case ENTER:
				gameRunning = true;
				gameEnded = false;
				winner = null;
				setup();
			}
		}
	}

	@Override
	public void keyReleased() {
		if (key == CODED) {
			// Player 1
			switch (keyCode) {
			case UP:
			case DOWN:
			case LEFT:
			case RIGHT:
				player1.setDirection(Direction.NOTHING);
			}
		} else {
			switch (key) {
			// Player 2
			case 'w':
			case 'a':
			case 's':
			case 'd':
				player2.setDirection(Direction.NOTHING);
			}
		}
	}
}