
import processing.core.PApplet;

public class Player {
	
	PApplet applet;
	
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
		this.bombRadius = 1;
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
		}
	}
	
	private boolean canMove(int newX, int newY){
		
		//check if the new coordinates are outside the map
		if(newX < 0 || newX + playerSize > applet.width || newY < 0 || newY + playerSize > applet.height){
			return false;
		}
		
		//Indices of the four corners of the player model
		int minXIndex = newX / tileSize;
		int maxXIndex = (newX + playerSize) / tileSize;
		int minYIndex = newY / tileSize;
		int maxYIndex = (newY + playerSize) / tileSize;
		
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
		//TODO method stub
	}
	
	public void paint(){
		applet.fill(255,255,255);
		applet.rect(x, y, playerSize, playerSize);
	}

}
