import java.awt.Color;
import java.awt.event.MouseEvent;



public class Board {
	public Chesspiece[][] chessboard;
	private int rows;
	private int columns;
	private Color black = new Color(0,0,0);
	private Color white = new Color(255,255,255);
	private int Blacklost;
	private int Whitelost;
	public Board(int row, int col){
		rows = row;
		columns = col;
		chessboard = new Chesspiece[rows][columns];
	}
	
	public Chesspiece[][] getBoard(){
		return chessboard;
	}

	public void create(){
		for(int a =0; a<rows;a++){
			for(int k =0; k<columns;k++){
				if(a==columns-2||a==2 ){
					if(a==columns-2) {
						chessboard[a][k]= new Pawn(k,a,black);
					}
					else if (a==2) {
						chessboard[a][k]= new Pawn(k,a,white);
					}
				}
				else if(a==columns-1||a==0){
					if(k==0||k==rows-1){
						if(a==columns-1) {
							chessboard[a][k]=new Castle(k,a,black);
						}
						else if (a==0) {
							chessboard[a][k]=new Castle(k,a,white); 
						}
					}
					else if(k==1||k==rows-2){
						if(a==columns-1) {
							chessboard[a][k]=new Knight(k,a,black);
						}
						else if(a==0) {
							chessboard[a][k]=new Knight(k,a,white); 
						}
					}
				}
				else if(k==2||k==rows-3){
					if(a==columns-1) {
						chessboard[a][k]=new Bishop(k,a,black);
					}
					else if(a==0) {
						chessboard[a][k]=new Bishop(k,a,white); 
					}
				}
				else if(k==3){
					if(a==columns-1) {
						chessboard[a][k]=new Queen(k,a,black);
					}
					else if(a==0){
						chessboard[a][k]=new Queen(k,a,white);
					}
				}
				else if(a==4){
					if(a==columns-1) {
						chessboard[a][k]=new King(k,a,black);
					}
					else if(a==0) {
						chessboard[a][k]=new King(k,a,white);
					}
				}
			}
		}
	}
	
	public void initialize () {
		for(int x = 0; x < columns; x++){
			for(int y = 0; y < rows; y++){
				if (y == 1)
					chessboard[x][y] = new Pawn (x, y, Color.BLACK);
				else if (y == 6)
					chessboard[x][y] = new Pawn (x, y, Color.WHITE);
				else {
					if (x== 0 || x == 7) {
						if (y == 0) 
							chessboard[x][y] = new Castle (x, y, Color.BLACK);
						else if (y == 7)
							chessboard[x][y] = new Castle (x, y, Color.WHITE);
					} else if (x == 1 || x == 6) {
						if (y == 0) 
							chessboard[x][y] = new Knight (x, y, Color.BLACK);
						else if (y == 7)
							chessboard[x][y] = new Knight (x, y, Color.WHITE);
					} else if (x == 2 || x == 5) {
						if (y == 0) 
							chessboard[x][y] = new Bishop (x, y, Color.BLACK);
						else if (y == 7)
							chessboard[x][y] = new Bishop (x, y, Color.WHITE);
					} else if (x == 3) {
						if (y == 0) 
							chessboard[x][y] = new King (x, y, Color.BLACK);
						else if (y == 7)
							chessboard[x][y] = new King (x, y, Color.WHITE);
					} else if (x == 4) {
						if (y == 0) 
							chessboard[x][y] = new Queen (x, y, Color.BLACK);
						else if (y == 7)
							chessboard[x][y] = new Queen (x, y, Color.WHITE);
					}
				}
			}
		}
	}
	
	public void redraw(MouseEvent e, MouseEvent e2) {
		for(int a = 0; a<rows;a++) {
			for(int k=0;k<rows;k++){
				Chesspiece b = chessboard[a][k];
				if(b.beenClickedon(e)) {
					if(e2.getY()>b.getypos()&&e2.getX()==b.getxpos()) {
						b.move(e.getY()-b.getypos(), b.d("UP"), b.d("UP"));
						if(chessboard[b.getypos()][b.getxpos()]!=null) {
							if(chessboard[b.getypos()][b.getxpos()].getColor()==black) {
								Blacklost=Blacklost+1;
							}
							else {
								Whitelost=Whitelost+1;
							}
						}
						chessboard[b.getypos()][b.getxpos()]=b;
					}
					else if(e2.getY()<b.getypos()&&e2.getX()==b.getypos()) {
						b.move(b.getypos()-e2.getY(), b.d("DOWN"), b.d("DOWN"));
						if(chessboard[b.getypos()][b.getxpos()]!=null) {
							if(chessboard[b.getypos()][b.getxpos()].getColor()==black) {
								Blacklost=Blacklost+1;
							}
							else {
								Whitelost=Whitelost+1;
							}
						}
						chessboard[b.getypos()][b.getxpos()]=b;
					}
					else if(e2.getY()==b.getypos()&&e2.getX()>b.getxpos()) {
						b.move(e2.getX()-b.getxpos(), b.d("RIGHT"), b.d("RIGHT"));
						if(chessboard[b.getypos()][b.getxpos()]!=null) {
							if(chessboard[b.getypos()][b.getxpos()].getColor()==black) {
								Blacklost=Blacklost+1;
							}
							else {
								Whitelost=Whitelost+1;
							}
						}
						chessboard[b.getypos()][b.getxpos()]=b;
					}
					else if(e2.getY()==b.getypos()&&e2.getX()<b.getxpos()) {
						b.move(b.getxpos()-e2.getX(), b.d("LEFT"), b.d("LEFT"));
						if(chessboard[b.getypos()][b.getxpos()]!=null) {
							if(chessboard[b.getypos()][b.getxpos()].getColor()==black) {
								Blacklost=Blacklost+1;
							}
							else {
								Whitelost=Whitelost+1;
							}
						}
						chessboard[b.getypos()][b.getxpos()]=b;
					}
					else if(e2.getY()>b.getypos()&&e2.getX()<b.getxpos()) {
						b.move(e.getY()-b.getypos(), b.d("LEFT"), b.d("UP"));
						if(chessboard[b.getypos()][b.getxpos()]!=null) {
							if(chessboard[b.getypos()][b.getxpos()].getColor()==black) {
								Blacklost=Blacklost+1;
							}
							else {
								Whitelost=Whitelost+1;
							}
						}
						chessboard[b.getypos()][b.getxpos()]=b;
					}
					else if(e2.getY()>b.getypos()&&e2.getX()>b.getxpos()) {
						b.move(e.getY()-b.getypos(), b.d("RIGHT"), b.d("UP"));
						if(chessboard[b.getypos()][b.getxpos()]!=null) {
							if(chessboard[b.getypos()][b.getxpos()].getColor()==black) {
								Blacklost=Blacklost+1;
							}
							else {
								Whitelost=Whitelost+1;
							}
						}
						chessboard[b.getypos()][b.getxpos()]=b;
					}
					else if(e2.getY()<b.getypos()&&e2.getX()>b.getxpos()) {
						b.move(b.getypos()-e2.getY(), b.d("RIGHT"), b.d("DOWN"));
						if(chessboard[b.getypos()][b.getxpos()]!=null) {
							if(chessboard[b.getypos()][b.getxpos()].getColor()==black) {
								Blacklost=Blacklost+1;
							}
							else {
								Whitelost=Whitelost+1;
							}
						}
						chessboard[b.getypos()][b.getxpos()]=b;
					}
					else if(e2.getY()<b.getypos()&&e2.getX()<b.getxpos()) {
						b.move(b.getypos()-e2.getY(), b.d("LEFT"), b.d("DOWN"));
						if(chessboard[b.getypos()][b.getxpos()]!=null) {
							if(chessboard[b.getypos()][b.getxpos()].getColor()==black) {
								Blacklost=Blacklost+1;
							}
							else {
								Whitelost=Whitelost+1;
							}
						}
						chessboard[b.getypos()][b.getxpos()]=b;
					}

				}
			}
		}
	}
	
	public int isLegalMove (int origX, int origY, int newX, int newY) {
		// check to see if the piece at the original position
		// can legally move to the new position 
		
		// if legal, change the location of the piece on the chessboard
		System.out.println("ON THE BOARD: OX="+origX+", OY="+origY+", NX="+newX+", NY="+newY);
		
		// check for legal moves and other information
		// Return val: -4	piece was moved outside the board
		// Return val: -3	piece was returned to same location, not a move
		// Return val: -2	causes a check on your king, not a legal move
		// Return val: -1	illegal move; doesn't follow rules of game
		// Return val:  0	legal move; let us play
		// Return val:  1	legal move; check on the opponent's king
		// Return val:  2	legal move; check mate
		// Return val:  3	legal move; stale mate
		
		// Check if the move is outside the board
		if (newX < 0 || newX > 7 || newY < 0 || newY > 7)
			return -4; // no move really
		
		// if the old and new locations are the same, just return true
		if (newX == origX && newY == origY)
			return -3;	// no move really
		
		int moveRetVal = -9;
		Color movingPieceColor = chessboard[origX][origY].getColor();
		
		if (chessboard[origX][origY].getClass().getName().equals("Pawn")) {
			moveRetVal = checkPawnMove (origX, origY, newX, newY);
			if (moveRetVal < 0)
				return moveRetVal;
		}
		
		if (chessboard[origX][origY].getClass().getName().equals("Bishop")) {
			moveRetVal = checkBishopMove (origX, origY, newX, newY);
			if (moveRetVal < 0)
				return moveRetVal;
		}
		
		if (chessboard[origX][origY].getClass().getName().equals("Castle")) {
			moveRetVal = checkRookMove (origX, origY, newX, newY);
			if (moveRetVal < 0)
				return moveRetVal;
		}

		if (chessboard[origX][origY].getClass().getName().equals("Knight")) {
			moveRetVal = checkKnightMove (origX, origY, newX, newY);
			if (moveRetVal < 0)
				return moveRetVal;
		}
		
		if (chessboard[origX][origY].getClass().getName().equals("Queen")) {
			moveRetVal = checkQueenMove (origX, origY, newX, newY);
			if (moveRetVal < 0)
				return moveRetVal;
		}
		
		if (chessboard[origX][origY].getClass().getName().equals("King")) {
			moveRetVal = checkKingMove (origX, origY, newX, newY);
			if (moveRetVal < 0)
				return moveRetVal;
		}
		
		// It looks legal. Let us make the change, but test for a couple of things
		// Set the pieces like they would be after move and see
		// Find where the two kings are, and use their location as new location to check for check
		// 1. after the move, is own king under check. If so, illegal move; change board back to original, and return -2
		// 2. If 1 is ok, then legal move, see if there is check on opponent's king
		
		// setting the board like the move was legal
		Chesspiece temp = chessboard[newX][newY];			// in case we have to switch later
		chessboard[newX][newY] = chessboard[origX][origY];
		chessboard[newX][newY].setpos(newX, newY);
		chessboard[origX][origY] = null;
		
		int kingsFound = 0;
		int whiteKingX = -1;
		int whiteKingY = -1;
		int blackKingX = -1;
		int blackKingY = -1;
		
		// where are the black and white kings
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (chessboard[x][y] != null && chessboard[x][y].getClass().getName().equals("King")) {
					if (chessboard[x][y].getColor() == Color.WHITE) {
						whiteKingX = x;
						whiteKingY = y;
						kingsFound++;
					} else if (chessboard[x][y].getColor() == Color.BLACK) {
						blackKingX = x;
						blackKingY = y;
						kingsFound++;
					}
					
					if (kingsFound == 2)	// both kings found
						break;
				}
			}
		}
		
		// Now check if our own king may be under check
		boolean blackKingInCheck = false;
		boolean whiteKingInCheck = false;
		
		// check if black king under check after move; if so it is an invalid move and we need to go back to original
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				// see if one of them leads to check
				if (chessboard[x][y] != null && chessboard[x][y].getColor()==Color.WHITE && chessboard[x][y].getClass().getName().equals("Pawn")) {
					if (checkPawnMove (x, y, blackKingX, blackKingY) == 0) {
						blackKingInCheck = true;
						break;
					}
				} else if (chessboard[x][y] != null && chessboard[x][y].getColor()==Color.WHITE && chessboard[x][y].getClass().getName().equals("Bishop")) {
					if (checkBishopMove (x, y, blackKingX, blackKingY) == 0) {
						blackKingInCheck = true;
						break;
					}
				} else if (chessboard[x][y] != null && chessboard[x][y].getColor()==Color.WHITE && chessboard[x][y].getClass().getName().equals("Rook")) {
					if (checkRookMove (x, y, blackKingX, blackKingY) == 0) {
						blackKingInCheck = true;
						break;
					}
				}  else if (chessboard[x][y] != null && chessboard[x][y].getColor()==Color.WHITE && chessboard[x][y].getClass().getName().equals("Knight")) {
					if (checkKnightMove (x, y, blackKingX, blackKingY) == 0) {
						blackKingInCheck = true;
						break;
					}
				}  else if (chessboard[x][y] != null && chessboard[x][y].getColor()==Color.WHITE && chessboard[x][y].getClass().getName().equals("Queen")) {
					if (checkQueenMove (x, y, blackKingX, blackKingY) == 0) {
						blackKingInCheck = true;
						break;
					}
				}  else if (chessboard[x][y] != null && chessboard[x][y].getColor()==Color.WHITE && chessboard[x][y].getClass().getName().equals("King")) {
					if (checkKingMove (x, y, blackKingX, blackKingY) == 0) {
						blackKingInCheck = true;
						break;
					}
				}
			}
		}
		// check if white king under check after move; if so it is an invalid move and we need to go back to original
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				// see if one of them leads to check
				if (chessboard[x][y] != null && chessboard[x][y].getColor()==Color.BLACK && chessboard[x][y].getClass().getName().equals("Pawn")) {
					if (checkPawnMove (x, y, whiteKingX, whiteKingY) == 0) {
						whiteKingInCheck = true;
						break;
					}
				} else if (chessboard[x][y] != null && chessboard[x][y].getColor()==Color.BLACK && chessboard[x][y].getClass().getName().equals("Bishop")) {
					if (checkBishopMove (x, y, whiteKingX, whiteKingY) == 0) {
						whiteKingInCheck = true;
						break;
					}
				} else if (chessboard[x][y] != null && chessboard[x][y].getColor()==Color.BLACK && chessboard[x][y].getClass().getName().equals("Rook")) {
					if (checkRookMove (x, y, whiteKingX, whiteKingY) == 0) {
						whiteKingInCheck = true;
						break;
					}
				}  else if (chessboard[x][y] != null && chessboard[x][y].getColor()==Color.BLACK && chessboard[x][y].getClass().getName().equals("Knight")) {
					if (checkKnightMove (x, y, whiteKingX, whiteKingY) == 0) {
						whiteKingInCheck = true;
						break;
					}
				}  else if (chessboard[x][y] != null && chessboard[x][y].getColor()==Color.BLACK && chessboard[x][y].getClass().getName().equals("Queen")) {
					if (checkQueenMove (x, y, whiteKingX, whiteKingY) == 0) {
						whiteKingInCheck = true;
						break;
					}
				}  else if (chessboard[x][y] != null && chessboard[x][y].getColor()==Color.BLACK && chessboard[x][y].getClass().getName().equals("King")) {
					if (checkKingMove (x, y, whiteKingX, whiteKingY) == 0) {
						whiteKingInCheck = true;
						break;
					}
				}
			}
		}	
		

		
		// if black move causes black king under check, it is an illegal move; need to restore to original and return -2, or
		// if white move causes white king under check, it is an illegal move; need to restore to original and return -2
		if ((movingPieceColor == Color.BLACK && blackKingInCheck) || (movingPieceColor == Color.WHITE && whiteKingInCheck)) {
			// illegal move; let us reset the board
			// setting the board like the move was legal
			chessboard[origX][origY] = chessboard[newX][newY];
			chessboard[origX][origY].setpos(origX, origY);
			chessboard[newX][newY] = temp;
			if (chessboard[newX][newY] != null)
				chessboard[newX][newY].setpos(newX, newY);
			
			return -2;
		}
		
		// if it was black move, and white king under check, it is all valid, return 1, or
		// if it was white move, and black king under check, it is all valid, return 1
		if ((movingPieceColor == Color.BLACK && whiteKingInCheck) || (movingPieceColor == Color.WHITE && blackKingInCheck))
			return 1;
		
		
		// return what you got
		return moveRetVal;
	}
	
	private int checkKingMove (int origX, int origY, int newX, int newY) {
		// King moves one step in any direction (not doing castling for the minute)
		// Let us check if this is a legal position
		
		if ((Math.abs(newX-origX) == 1 && (Math.abs(newY-origY)==1 || Math.abs(newY-origY)==0)) ||
				(Math.abs(newY-origY) == 1 && (Math.abs(newX-origX)==1 || Math.abs(newX-origX)==0))) {
			// let us check we are not clobbering our own piece
			if (chessboard[newX][newY] != null && chessboard[newX][newY].getColor() == chessboard[origX][origY].getColor())
				return -1;	// can't clobber own piece
			
			return 0;
		}
		
		return -1;	// illegal move
	}
	
	
	private int checkQueenMove (int origX, int origY, int newX, int newY) {
		// Before we check for obstructions, let us see if this is valid
		// Queen can move diagonally or in straight line 
		// This is like Bishop and Rook together
		
		int retVal = checkBishopMove (origX, origY, newX, newY);
		
		if (retVal == 0)	// legal move like a bishop
			return retVal;
		
		// perhaps it will work like a rook
		retVal = checkRookMove (origX, origY, newX, newY);
		if (retVal == 0)	// legal move like a rook
			return retVal;
		
		return -1;	//illegal move
	}
	
	
	private int checkKnightMove (int origX, int origY, int newX, int newY) {
		// Before we check for obstructions, let us see if this is valid. 
		// Knight moves 2 in X and 1 in Y directions, or 2 in Y and 1 in X directions
		// No obstruction problems
		
		if ((Math.abs(newX-origX)==2 && Math.abs(newY-origY)==1) || 
				(Math.abs(newY-origY)==2 && Math.abs(newX-origX)==1)) {
			// legal position. Need to make sure we don't clobber our piece
			if (chessboard[newX][newY] != null && chessboard[newX][newY].getColor() == chessboard[origX][origY].getColor())
				return -1;	// can't clobber own piece
			
			return 0;	// legal move
		}
		
		return -1;	// illegal move
	}
	
	private int checkRookMove (int origX, int origY, int newX, int newY) {
		// Before we check for obstructions, let us see if this is valid. 
		// Rook only has straight line moves
		
		if (Math.abs(newX - origX) == 0 || Math.abs(newY - origY) == 0) {
			// Legal position. Let us check for obstructions, and if we there is our own piece at destination
			if (chessboard[newX][newY] != null && chessboard[newX][newY].getColor() == chessboard[origX][origY].getColor())
				return -1;	// can't clobber your own piece
			
			int dirX, dirY;
			
			if (newX > origX)
				dirX = 1;
			else if (newX < origX)
				dirX = -1;
			else 
				dirX = 0;
			
			if (newY > origY)
				dirY = 1;
			else if (newY < origY)
				dirY = -1;
			else
				dirY = 0;
			
			if (dirX == 0) {
				// going vertically
				for (int i=1; i < Math.abs(newY - origY); ++i) {
				    if (chessboard [origX][origY + i * dirY] != null) {
				      return -1;
				    }
				}
				return 0;	// legal
			} else {
				// dirY == 0
				for (int i=1; i < Math.abs(newX - origX); ++i) {
				    if (chessboard [origX + i * dirX][origY] != null) {
				      return -1;
				    }
				}
				return 0;	// legal
			}
		}
		
		return -1;	// illegal move
			
	}
	
	private int checkBishopMove (int origX, int origY, int newX, int newY) {
		// Before we check for obstructions, let us see if this is valid. 
		// Bishop only has diagonal moves
		
		if (Math.abs(newX - origX) == Math.abs(newY - origY)) {
			// legal position, but we need to check for obstructions and if the final position is own piece
			
			if (chessboard[newX][newY] != null && chessboard[newX][newY].getColor() == chessboard[origX][origY].getColor())
				return -1;	// can't clobber your own piece
			
			
			int dirX, dirY;
			
			if (newX > origX)
				dirX = 1;
			else
				dirX = -1;
			
			if (newY > origY)
				dirY = 1;
			else
				dirY = -1;
			
			for (int i=1; i < Math.abs(newX - origX); ++i) {
			    if (chessboard [origX + i * dirX][origY + i * dirY] != null) {
			      return -1;
			    }
			}
			      
			return 0;
		}
		
		return -1;
	}
	
	private int checkPawnMove (int origX, int origY, int newX, int newY) {
		// if black pawn, it can move one higher (2 higher in row 1) in Y-direction if there are no block
		// if white pawn, it can move one lower (2 lower in row 6) in Y-direction if there are no block
		// if black pawn, can go one diagonally higher in Y-direction if white piece there
		// if white pawn, can go one diagonally lower in Y-direction if black piece there
		// otherwise illegal
		
		Color pawnColor = chessboard[origX][origY].getColor();
		if (pawnColor == Color.BLACK) {
			if (origX == newX) {
				// moving down
				if (newY == origY+1 && chessboard[newX][newY] == null)
					// move one step - legal
					return 0;
				if (newY == origY+2 && origY==1 && chessboard[newX][newY] == null && chessboard[newX][origY+1] == null)
					// move two steps - legal
					return 0;
			} else if (newY == origY+1 && (newX == origX+1 || newX == origX-1)) {
				// other team's piece
				if (chessboard[newX][newY] !=null && chessboard[newX][newY].getColor()==Color.WHITE)
					return 0;
			}
		} else if (pawnColor == Color.WHITE) {
			if (origX == newX) {
				// moving up
				if (newY == origY-1 && chessboard[newX][newY] == null)
					// move one step - legal
					return 0;
				if (newY == origY-2 && origY==6 && chessboard[newX][newY] == null && chessboard[newX][origY-1] == null)
					// move two steps - legal
					return 0;
			} else if (newY == origY-1 && (newX == origX+1 || newX == origX-1)) {
				// other team's piece
				if (chessboard[newX][newY] !=null && chessboard[newX][newY].getColor()==Color.BLACK)
					return 0;
			}		
		}
		
		return -1;	// illegal move
	}
	
	
	
	public int getWhiteLost() {
		return Whitelost;
	}
	
	public int getBlackLost() {
		return Blacklost;
	}
	
	public int getRows(){
		return rows;
	}
	
	public int getColumns(){
		return columns;
	}
	
	

}

