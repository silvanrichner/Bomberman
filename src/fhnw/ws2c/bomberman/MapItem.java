package fhnw.ws2c.bomberman;


/**
 * All elements that can be placed on the map are MapItems (Blocks, crates, bombs, etc.)
 * @author Silvan
 *
 */
public interface MapItem {
	
	/**
	 * 
	 * @return true when the item is blocking the players movement, false when not.
	 */
	public boolean isBlocking();
	
	/**
	 * 
	 * @return true when the item can be destroyed by an explosion, false when not.
	 */
	public boolean isDestructible();
	
	/**
	 * Draws the visual representation of the item on the screen. A valid PApplet must be set (through the constructor).
	 * @param x x-coordinate of the element on the screen (top left corner)
	 * @param y y-coordinate of the element on the screen (top left corner)
	 */
	public void paint(int x, int y);

}
