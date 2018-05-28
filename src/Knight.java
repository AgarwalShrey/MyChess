import java.awt.Color;
import java.awt.event.MouseEvent;


public class Knight extends Chesspiece {
	private int xpos;
	private int ypos;
	private Color color;

	public Knight(int x, int y,Color g){
	   xpos=x;
	   ypos=y;
	   color = g; 
	}


	@Override
	public boolean beenClickedon(MouseEvent e) {
		if(e.getX()==getxpos()&&e.getY()==getypos()){
			return true;
			
		}
		return false;

	}

	@Override
	public void move(int numtimes,Direction d,Direction d2) {
		if(numtimes==3){
			if(d.equals(Direction.UP)&&d2.equals(Direction.LEFT)){
               xpos=xpos-1;
               ypos=ypos+numtimes;
			}
			if(d.equals(Direction.UP)&&d2.equals(Direction.RIGHT)){
				xpos=xpos+1;
				ypos=ypos+numtimes;

			}
			if(d.equals(Direction.DOWN)&&d2.equals(Direction.LEFT)){
				xpos=xpos-1;
				ypos=ypos-numtimes;

			}
			if(d.equals(Direction.DOWN)&&d2.equals(Direction.RIGHT)){
				xpos=xpos-1;
                ypos=ypos-numtimes;
			}
			if(d.equals(Direction.LEFT)&&d2.equals(Direction.UP)){
                 xpos=xpos-numtimes;
                 ypos=ypos+1;
			}
			if(d.equals(Direction.LEFT)&&d2.equals(Direction.DOWN)){
                xpos=xpos-numtimes;
                ypos=ypos-1;
			}
			if(d.equals(Direction.RIGHT)&&d2.equals(Direction.UP)){
                xpos=xpos+numtimes;
                ypos=ypos+1;
			}
			if(d.equals(Direction.RIGHT)&&d2.equals(Direction.DOWN)){
                xpos=xpos+numtimes;
                ypos=ypos-1;
			} 
		}

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
