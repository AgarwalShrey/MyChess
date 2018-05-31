/***** WRITTEN BY ABHINAV PRASANNA ******/

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;



public class Bishop extends Chesspiece {
	private int xpos;
	private int ypos;
	private Color color;

	public Bishop(int x, int y,Color g){
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
	public void setpos(int x, int y) {
		// TODO Auto-generated method stub
		xpos = x;
		ypos = y;
	}
	


	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
	}
}
