package puzzleGame;

import java.awt.*;

public class Segment {
	private int segmentSize;
	private Point pos;
	
	public boolean isEmpty = false;
	public boolean movementPossible = false;
	
	public Segment(Puzzle instance, int ID, int segmentSize) {
		this.segmentSize = segmentSize;
		pos = new Point(((ID <= 2)? ID:(ID <= 5)? (ID-3):(ID-6)), (int) Math.ceil((ID/3))); //position for each piece 
	}
	
	public boolean hitten(Point p) {
		return new Rectangle(pos.x*segmentSize, pos.y*segmentSize, segmentSize, segmentSize).contains(p);// check if the rectangle contains this 
	}
	
	public boolean move(Point p) {
		if (p.x == pos.x+1 && p.y == pos.y) movementPossible = true;
		if (p.x == pos.x-1 && p.y == pos.y) movementPossible = true;
		if (p.x == pos.x && p.y == pos.y+1) movementPossible = true;
		if (p.x == pos.x && p.y == pos.y-1) movementPossible = true;
		
		if (movementPossible) {
			pos = p;
		}	
		return movementPossible;
	}

	public Point getPosition() {
		return this.pos;
	}
	
	public void setPosition(Point p) {
		this.pos = p;
	}
}
