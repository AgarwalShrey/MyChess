/***** WRITTEN BY ABHINAV PRASANNA ******/

import java.awt.Color;
import java.awt.event.MouseEvent;



public class King extends Chesspiece {
	private int xpos;
	private int ypos;
	private Color color;

	public King(int x, int y,Color g){
	   xpos=x;
	   ypos=y;
	   color = g; 
	}

	@Override
	public int getxpos() {
		// TODO Auto-generated method stub
		return xpos;
	}
	@Override
	public int getypos() {
		// TODO Auto-generated method stub
		return ypos;
	}
	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
	}
	
	
	@Override
	public void setpos(int x, int y) {
		// TODO Auto-generated method stub
		xpos = x;
		ypos = y;
	}
}
