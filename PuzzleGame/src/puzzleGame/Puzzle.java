package puzzleGame;

import java.awt.*;
import javax.swing.*;

public class Puzzle extends JPanel {
	Tile[] tiles;
	Image img;
	public Point tilePosition;
	
	public boolean started = false;
	public boolean done = true;
	
	//This method has an interface with the class tile from which it inherits attributes and functions
	//This method will first get an image from the database and the tile size 
	// Tile size is one third of the image width 
	// Then it will start to initialize 3x3 tiles with this image

	public Puzzle(Image img) {
		this.img = img;
		tiles = new Tile[9];
		int tileSize = img.getWidth(null)/3;
		for (int i = 0; i != tiles.length; i++)// tile length = 9 
		{
			//tile ID are 0,1,2,3,4,5,6,7,8 respectively 
			tiles[i] = new Tile(this, i, tileSize);
		}
	}
	
	//This method will set start to true and remove the last cell to create an empty cell. 
	public void start() {
		started = true;
		tiles[8].isEmpty = true;
	}
	
	// This method checks the point-on-click is contained in which tile. Then it gets this tile's position 
	// After the tile is identified, it will then check if the movement is possible.
	// If yes it performs the movement 	
	// The method also keeps track of the correct position of the tiles from the original image constantly as the tiles are moved 
	// Finally, when the user has completed the game correctly, the method will return. 
	
	public Point onClickPoint(Point e) {
		for (Tile s : tiles) {
			if (s.contain(e)) // if segment s contains the hit point 
			{
				tilePosition = s.getPosition();
				if (s.move(tiles[8].getPosition())) // get position of the empty tile 
				{
					tiles[8].setPosition(tilePosition); //swap segment 8 with s 	
					for (int i = 0; i != 9; i++) 
					{
						//if this tile's position is equal to tile's id, it means the tile is at the correct position to form the image 
						if (tiles[i].getPosition().x != ((i <= 2)? i:(i <= 5)? (i-3):(i-6)) && tiles[i].getPosition().y != (int) Math.ceil((i/3))) 
							done = false;
					}
					
					if (done) {
						started = false;
						tiles[8].isEmpty = false; //complete the image 
					}
				}
			}
		}
		return tilePosition;
	}
	
}