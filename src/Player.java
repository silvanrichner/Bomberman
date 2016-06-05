
import processing.core.PApplet;
import processing.core.PImage;

public class Player {
	
	PApplet applet;
	
	PImage image;
	
	MapItem[][] map;
	int mapWidth;
	int mapHeight;
	int tileSize;
	
	//How many pixels the players moves with each keypress
	int speed;
	//How many bombs the player can hold
	int maxBombs;
	//How many bombs the player currently has at his disposal
	int curBombs;
	//The number of tiles that are affected by the players bomb
	int bombRadius;
	
	int playerSize = 25;
	
	//position of the player
	int x;
	int y;
	
	private Direction direction = Direction.NOTHING;

	public Player(PApplet applet,MapItem[][] map, int xPos, int yPos, int tileSize){
		this.applet = applet;
		this.x = xPos;
		this.y = yPos;
		this.map = map;
		this.mapHeight = map[0].length;
		this.mapWidth = map.length;
		this.tileSize = tileSize;
		
		this.speed = 3;
		this.maxBombs = 1;
		this.curBombs = 1;
		this.bombRadius = 1;
	}
	
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public void move(Direction direction){
		switch(direction){
		case UP:
			if(canMove(x, y - speed)){
				y -= speed;				
			}
			break;
		case DOWN:
			if(canMove(x, y + speed)){
				y += speed;
			}
			break;
		case LEFT:
			if(canMove(x - speed, y)){
				x -= speed;
			}
			break;
		case RIGHT:
			if(canMove(x + speed, y)){
				x += speed;
			}
			break;
		case NOTHING:
			break;
		}

		if (map[x/tileSize][y/tileSize] instanceof Powerup){
			((Powerup) map[x/tileSize][y/tileSize]).applyPowerup(this);
			
		}
	}
	
	private boolean canMove(int newX, int newY){
		
		//check if the new coordinates are outside the map
		if(newX < 0 || newX + playerSize > map.length * tileSize || newY < 0 || newY + playerSize > map[0].length * tileSize){
			return false;
		}
		
		//Indices of the four corners of the player model
		int minXIndex = newX / tileSize;
		int maxXIndex = (newX + playerSize -1) / tileSize;
		int minYIndex = newY / tileSize;
		int maxYIndex = (newY + playerSize -1) / tileSize;
		
		//check if there's no item on the new coordinates
		if((map[minXIndex][minYIndex] != null  && map[minXIndex][minYIndex].isBlocking()) ||
				(map[maxXIndex][maxYIndex] != null && map[maxXIndex][maxYIndex].isBlocking()) ||
				(map[maxXIndex][minYIndex] != null  && map[maxXIndex][minYIndex].isBlocking()) ||
				(map[minXIndex][maxYIndex] != null && map[minXIndex][maxYIndex].isBlocking())){
		
			return false;
		}
		
		return true;
	}
	
	public void dropBomb(){
		if(curBombs > 0){
			Bomb bomb = new Bomb(applet, map, this, x, y);
			map[x/tileSize][y/tileSize] = bomb;
			curBombs--;
		}
	}
	
	public void paint(){
		move(direction);
		applet.image(image,x,y);
	}

	public void increaseRadius(){
		bombRadius++;
	}
	
	public void increaseSpeed(){
		speed+=2;
	}
	
	public void increaseBombCount(){
		maxBombs++;
		curBombs++;
	}
	
	public int getSpeed() {
		return speed;
	}

	public int getMaxBombs() {
		return maxBombs;
	}

	public int getCurBombs() {
		return curBombs;
	}
	
	public void restoreCurBombs(){
		this.curBombs++;
	}

	public int getBombRadius() {
		return bombRadius;
	}
	
	public void setImage(PImage image){
		this.image = image;
	}
	
	public int getXTile(){
		return x/tileSize;
	}
	
	public int getYTile(){
		return y/tileSize;
	}
}
