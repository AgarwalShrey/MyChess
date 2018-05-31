/***** WRITTEN BY ABHINAV PRASANNA ******/

import java.awt.Color;
import java.awt.event.MouseEvent;


public abstract class Chesspiece {
	
	public abstract int getxpos();
	public abstract int getypos();
	
	public abstract void setpos (int x, int y);
	
	public abstract Color getColor();

}
