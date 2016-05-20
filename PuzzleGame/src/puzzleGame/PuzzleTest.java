package puzzleGame;

import static org.junit.Assert.*;

import java.awt.Point;
import javax.swing.ImageIcon;
import org.junit.Before;
import org.junit.Test;

public class PuzzleTest {
	static Puzzle puzzle;
	
	//random points that simulate the point-on-click 
	Point[] myPoints = {
			  new Point(300, 200), //this point is contained in tile with id 5 
			  new Point(100, 100), //this point is contained in tile with id 0 
			  new Point(200, 200), //this point is contained in tile with id 4 
			  new Point(200, 300), //this point is contained in tile with id 7 
			  new Point(300, 300), //this point is contained in tile with id 8 
			}; 
	Point testPoint;

	@Before
	public void setUp() {
		// initialize image. This image size is 327x327
		/*0,0			0,1		0,2 	  
			 * |------------------------|
			 * |   		|		|		|
			 * | 109x109|		|		|
			 * |  		|		|		|
		 1,0 * |------------------------|
			 * |		|		|		|
			 * |		|		|		|
			 * |		|		|		|
		 2,0 * |------------------------|
			 * |		|		|		|
			 * |		|		| seg8	|
			 * |		|		|isEmpty|
		 3,0 * |------------------------|
		 */
		puzzle = new Puzzle(new ImageIcon(PuzzleTest.class.getResource("/puzzleGame/Image.png")).getImage());
		if (!puzzle.started) puzzle.start();
	}
	
	@Test
	public void test() {
		// We test the value of argument started in class puzzle
		// if game has started, puzzle.started is true, otherwise it's false
		// We can see that puzzle.started value is TRUE 
		//assertFalse("puzzle.started is TRUE",puzzle.started);
	
		// puzzle.started is TRUE, meaning game has started. Therefore we can proceed the game 
		assertTrue("Game is not started",puzzle.started);
		
		// If the tile with ID 8 is empty (puzzle.tiles[8].isEmpty is TRUE) , we can proceed the game 
		assertTrue("Segment 8 is not empty",puzzle.tiles[8].isEmpty);
		
		// CLick at point [0] with coordinate = (300,200)
		// Because this image size is 327x327 and we divided it into 9 3x3 slots, Point (300,200) will belong to 
		//the tile with id 5 
		// If this segment is moved, we can proceed our game 
		// The empty segment is now at position (2,1)  
		testPoint = puzzle.onClickPoint(myPoints[0]);
		assertEquals("Segment not moved",testPoint,puzzle.tiles[8].getPosition()); //now segment 8 pos is at (2,1)
		
		//now we try to move a segment that is not lined up with the hole 
		testPoint = puzzle.onClickPoint(myPoints[1]);
		assertNotEquals("Segment not moved",testPoint,puzzle.tiles[8].getPosition()); // tile cannot move 
		
		//empty tile position (tile[8] position) is not the same as the position of the tile that we has just clicked
		//in other words, position of tile[8] and position returned from testPoint has not been swapped 
		//tile[8] remains at the same position
		/*testPoint = puzzle.onClickPoint(myPoints[1]);
		assertEquals("Segment not moved",testPoint,puzzle.tiles[8].getPosition());*/
		
		//We try to re-make the original image 
		testPoint = puzzle.onClickPoint(myPoints[2]);
		assertEquals("Segment not moved",testPoint,puzzle.tiles[8].getPosition()); //now segment 8 pos is at (1,1)
		testPoint = puzzle.onClickPoint(myPoints[3]);
		assertEquals("Segment not moved",testPoint,puzzle.tiles[8].getPosition()); //now segment 8 pos is at (1,2)
		testPoint = puzzle.onClickPoint(myPoints[4]);
		assertEquals("Segment not moved",testPoint,puzzle.tiles[8].getPosition()); //now segment 8 pos is at (2,2) - game completed
		
		//test if the game is ended correctly
		assertFalse("Game is not finished",puzzle.started);
	}
}
