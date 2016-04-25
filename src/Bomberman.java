

import processing.core.PApplet;

public class Bomberman extends PApplet {
	
	//the dimensions of the playing field
	int mapSizeX = 29;
	int mapSizeY = 19;
	
	//the playing field
	MapItem[][] map;
	
	//the size in pixels of one tile of the playing field
	int tileSize = 20;
	
	//the players (duh!)
	Player player1;
	Player player2;
	
	public static void main(String args[]) {
		PApplet.main(new String[] { "--present", "Bomberman" }); 
	}
	
	@Override
	public void settings(){
		size(mapSizeX * tileSize, mapSizeY * tileSize);
		
	}
	
	@Override
	public void setup() {
		background(0,0,0);
		noStroke();
		
		//Initialize the map
		map = new MapItem[mapSizeX][];
		for(int i=0; i<mapSizeX; i++){
			map[i] = new MapItem[mapSizeY];
		}
		
		//add indestructible crates
		for(int i=1; i<map.length; i++){
			for(int j=1; j<map[i].length; j++){
				if(j % 2 != 0 && i % 2 != 0 ){
					map[i][j] = new IndestructibleBlock(this);
				}
			}
		}
		
		//initialize the players
		player1 = new Player(this, map, 5, 5, tileSize);
		player2 = new Player(this, map, width-15, height-15, tileSize);
	}
	
	@Override
	public void draw() {
		//clear the screen
		background(0,0,0);
		
		//paint the players at their current position
		player1.paint();
		player2.paint();
		
		//draw the elements on the map
		for(int i=0; i<map.length; i++){
			for(int j=0; j<map[i].length; j++){
				if(map[i][j] != null){
					map[i][j].paint(i * 20, j * 20);
				}
			}
		}
	}
	
	@Override
	public void keyPressed(){
		//The value of the arrowkeys can't be read directly from the key variable because it's coded
		if(key == CODED){
			//Player 1
			switch(keyCode){
			case UP:
				player1.move(Direction.UP);
				break;				
			case DOWN:
				player1.move(Direction.DOWN);
				break;
			case LEFT:
				player1.move(Direction.LEFT);
				break;
			case RIGHT:
				player1.move(Direction.RIGHT);
				break;
			case SHIFT:
				player1.dropBomb();
			}
		}else{
			switch(key){
			//Player 2
			case 'w':
				player2.move(Direction.UP);
				break;
			case 'a':
				player2.move(Direction.LEFT);
				break;
			case 's':
				player2.move(Direction.DOWN);
				break;
			case 'd':
				player2.move(Direction.RIGHT);
				break;
			case 'e':
				player2.dropBomb();
			}
		}
	}
}