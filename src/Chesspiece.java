import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;


public abstract class Chesspiece {
	public enum Direction{LEFT,RIGHT,UP,DOWN}
	
	
	public abstract void move(int numtimes,Direction d,Direction d2);
	public  boolean beenClickedon(MouseEvent e) {
		if(e.getX()==getxpos()&&e.getY()==getypos()) {
			return true;
		}
		return false;
		
	}
	public Direction d(String a) {
		if(a.equals("UP")) {
			return Direction.UP;
		}
		else if(a.equals("DOWN")) {
			return Direction.DOWN;
		}
		else if(a.equals("LEFT")) {
			return Direction.LEFT;
		}
		else if(a.equals("RIGHT")) {
			return Direction.RIGHT;
		}
		return null;
		
	}
	public abstract int getxpos();
	public abstract int getypos();
	
	public abstract void setpos (int x, int y);
	
	public abstract Color getColor();

}
