package puzzleGame;

import java.awt.*;
import javax.swing.*;

public class Puzzle extends JPanel {
	Segment[] segments;
	Image img;
	public Point segmentPosition;
	
	public boolean started = false;
	public boolean done = true;
	
	public Puzzle(Image img) {
		this.img = img;
		segments = new Segment[9];
		int segmentSize = img.getWidth(null)/3;
		for (int i = 0; i != segments.length; i++)// segment length = 9 
		{
			segments[i] = new Segment(this, i, segmentSize);
		}
	}
	
	public void start() {
		started = true;
		segments[8].isEmpty = true;
	}
	
	public Point onClickPoint(Point e) {
		for (Segment s : segments) {
			if (s.hitten(e)) // if segment s contains the hit point 
			{
				segmentPosition = s.getPosition();
				if (s.move(segments[8].getPosition())) // get position of the empty tile 
				{
					segments[8].setPosition(segmentPosition); //swap segment 8 with s 	
					for (int i = 0; i != 9; i++) 
					{
						if (segments[i].getPosition().x != ((i <= 2)? i:(i <= 5)? (i-3):(i-6)) && segments[i].getPosition().y != (int) Math.ceil((i/3))) 
						{
							done = false;
						} 
					}
					
					if (done) {
						started = false;
						segments[8].isEmpty = false;
					}
				}
			}
		}
		return segmentPosition;
	}
	
}