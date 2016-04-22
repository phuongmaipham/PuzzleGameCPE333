package puzzleGame;

import static org.junit.Assert.*;

import java.awt.Point;
import javax.swing.ImageIcon;
import org.junit.Before;
import org.junit.Test;

public class PuzzleTest {
	static Puzzle puzzle;
	Point[] myPoints = {
			  new Point(300, 200),
			  new Point(100,100),
			  new Point(200,200),
			  new Point(200,300),
			  new Point(300, 300),
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
		assertTrue("Game is not started",puzzle.started);
		assertTrue("Segment 8 is not empty",puzzle.segments[8].isEmpty);
		testPoint = puzzle.onClickPoint(myPoints[0]);
		assertEquals("Segment not moved",testPoint,puzzle.segments[8].getPosition()); //now segment 8 pos is at (1,2)
		//now we try to move a segment that is not lined up with the hole 
		testPoint = puzzle.onClickPoint(myPoints[1]);
		assertNotEquals("Segment not moved",testPoint,puzzle.segments[8].getPosition()); 
		testPoint = puzzle.onClickPoint(myPoints[2]);
		assertEquals("Segment not moved",testPoint,puzzle.segments[8].getPosition());
		testPoint = puzzle.onClickPoint(myPoints[3]);
		assertEquals("Segment not moved",testPoint,puzzle.segments[8].getPosition());
		testPoint = puzzle.onClickPoint(myPoints[4]);
		assertEquals("Segment not moved",testPoint,puzzle.segments[8].getPosition());
		
		//test if the game is ended correctly
		assertFalse("Game is not finished",puzzle.started);
	}
}
