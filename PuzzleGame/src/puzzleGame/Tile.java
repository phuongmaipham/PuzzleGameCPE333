package puzzleGame;

import java.awt.Point;
import java.awt.Rectangle;

public class Tile {
	private int tileSize;
	private Point tile_position;
	private int ID;
	
	public boolean isEmpty = false;
	public boolean movePossible = false;
	
	//This method checks if a movement is valid.
	//That is, whether a tile is horizontally or vertically aligned with the target cell. 
	public Tile(Puzzle instance, int ID, int tileSize) {
		this.tileSize = tileSize;
		// x positions are 0,1,2 respectively 
		// y positions are 0,1,2 respectively
		if(ID <= 2) //if tiles id are 0,1,2 then the tile’s x position = tile’s id
			this.ID = ID;
		else if (ID <= 5) //if tiles id are 3,4,5 then the tile’s x position = tile’s id - 3
			this.ID = ID - 3;
		else //if tiles id are 6,7,8 then the tile’s x position = tile’s id - 6
			this.ID = ID - 6;
		//tile’s y position is the rounded value of tiles id/3 
		tile_position = new Point(this.ID, (int) Math.ceil((ID/3))); //tile position for each 3x3 piece 
	}
	
	//This method checks whether the point-on-click is contained in a specific cell. 
	//The rectangle is created by the tile’s size and position
	
	public boolean contain(Point p) {
		return new Rectangle(tile_position.x*tileSize, tile_position.y*tileSize, tileSize, tileSize).contains(p);
	}
	
	//This method checks if a movement is valid. 
	//That is, whether a tile is horizontally or vertically aligned with the target cell. 
	//movement is possible if the tile is lined up with the hole
	
	public boolean move(Point p) {
		//if the tile horizontally or vertically aligned with the empty tile
		if ((p.x == tile_position.x+1 && p.y == tile_position.y)||(p.x == tile_position.x-1 && p.y == tile_position.y)||(p.x == tile_position.x && p.y == tile_position.y+1)|| (p.x == tile_position.x && p.y == tile_position.y-1) )//if the tile horizontally aligned with the empty tile
			movePossible = true;
		
		if (movePossible) {
			tile_position = p; //the tile’s new position is the empty tile’s position
		}	
		return movePossible;
	}

	//This method returns the tile’s position
	public Point getPosition() {
		return this.tile_position;
	}
	
	// This method set a tile’s position to a new position 
	public void setPosition(Point p) {
		this.tile_position = p;
	}
}
