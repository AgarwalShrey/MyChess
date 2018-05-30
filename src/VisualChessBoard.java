import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.*;
import javax.swing.border.BevelBorder;


public class VisualChessBoard extends JLayeredPane {
	private static final int PANEL_SIZE = 640;	// same height and width
	private static final int NUMROWS = 8;
	private static final int NUMCOLS = 8;
	
	private static Dimension LAYERED_PANE_SIZE = new Dimension (PANEL_SIZE, PANEL_SIZE);
	private GridLayout gridLayout = new GridLayout(NUMROWS, NUMCOLS);
	
	private JPanel chessPanel = new JPanel (gridLayout);
	private JPanel [][] squares = new JPanel [NUMROWS][NUMCOLS];
	
	private static JPanel statusPanel = new JPanel ();
	private static JLabel statusLabel = new JLabel ();
	
	private static boolean whiteTurn = true;
	private static boolean blackTurn = false;
	
	private Board logicalBoard = null;
	
	public VisualChessBoard() {
		// Create the logical chess board
		logicalBoard = new Board(8, 8);
		logicalBoard.initialize ();
		
		chessPanel.setSize(LAYERED_PANE_SIZE);
		chessPanel.setBackground(Color.BLACK);
		chessPanel.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
		
		createBoard ();
		draw (logicalBoard);
		setPreferredSize (LAYERED_PANE_SIZE);
		add(chessPanel, JLayeredPane.DEFAULT_LAYER);
		
		MoveManager moveMgr = new MoveManager ();
		addMouseListener(moveMgr);
		addMouseMotionListener (moveMgr);
	}
		
	private class MoveManager extends MouseAdapter {
		private JLabel draggedLabel = null;
		private JPanel clickedPanel = null;
		private int halfDraggedLabelWidth;
		private int halfDraggedLabelHeight;

		private int origGridX = -1;
		private int origGridY = -1;
			
		@Override
		public void mousePressed(MouseEvent e) {
			Point p = e.getPoint ();
			// find grid location
			origGridX = (int)((p.getX ())/(PANEL_SIZE/8));	
			origGridY = (int)((p.getY ())/(PANEL_SIZE/8));	

			System.out.println("Pressed (" + origGridX + ", " + origGridY + ")");
		
			
			// Dragging the pieces
			
			clickedPanel = (JPanel) chessPanel.getComponentAt(e.getPoint());
			Component [] components = clickedPanel.getComponents();
			if (components.length == 0) {
				return;	// nothing here to do
			}
			// This panel does have a label
			if (components[0] instanceof JLabel) {
				// Let us first check if this person has the turn
				if (whiteTurn == true && logicalBoard.chessboard[origGridX][origGridY].getColor()==Color.BLACK) {
					// not the black turn
					statusLabel.setText ("Not black turn");
					return;
				} else if (blackTurn == true && logicalBoard.chessboard[origGridX][origGridY].getColor()==Color.WHITE ) {
					// not the white turn
					statusLabel.setText ("Not white turn");
					return;
				}
				
				// remove label from the panel
				draggedLabel = (JLabel) components[0];
				clickedPanel.remove(draggedLabel);
				clickedPanel.revalidate();
				clickedPanel.repaint();
				
				halfDraggedLabelWidth = draggedLabel.getWidth()/2;
				halfDraggedLabelHeight = draggedLabel.getHeight()/2;
				
				int x = p.x - halfDraggedLabelWidth;
				int y = p.y - halfDraggedLabelHeight;
				draggedLabel.setLocation(x, y);
				add(draggedLabel, JLayeredPane.DRAG_LAYER);
				repaint ();
			}
		}
		
		@Override
		public void mouseDragged (MouseEvent e) {
			if (draggedLabel == null) {
				return;
			}
			
			int x = e.getPoint().x - halfDraggedLabelWidth;
			int y = e.getPoint().y- halfDraggedLabelHeight;
			draggedLabel.setLocation(x, y);
			repaint ();
		}
			
		@Override
		public void mouseReleased(MouseEvent e) {
			Point p = e.getPoint ();
			int gridX = (int)((p.getX ())/(PANEL_SIZE/8));	
			int gridY = (int)((p.getY ())/(PANEL_SIZE/8));	
			
			// Check for point within board; if outside, force grid to be -1, -1
			if (p.getX() < 0 || p.getX() > PANEL_SIZE || p.getY() < 0 || p.getY() > PANEL_SIZE) {
				gridX = -1;
				gridY = -1;
			}
			
			JPanel droppedPanel = (JPanel) chessPanel.getComponentAt(e.getPoint());

			System.out.println("Released (" + gridX + ", " + gridY + ")");
		
			//remove the label from JLayeredPane if one exists
			if (draggedLabel == null)
				return;
			
			// check for legal moves and other information
			// Return val: -4	piece was moved outside the board; not a move
			// Return val: -3	piece was returned to same location, not a move
			// Return val: -2	causes a check on your king, not a legal move
			// Return val: -1	illegal move; doesn't follow rules of game
			// Return val:  0	legal move; let us play
			// Return val:  1	legal move; check on the opponent's king
			// Return val:  2	legal move; check mate
			// Return val:  3	legal move; stale mate
			
			int retVal = logicalBoard.isLegalMove(origGridX, origGridY, gridX, gridY);
			if (retVal < 0) {
				// illegal moves
				if (retVal == -4) {
					// returned the piece to original location
					statusLabel.setText ("Stay on the board");
				} else if (retVal == -3) {
					// returned the piece to original location
					statusLabel.setText ("");
				} else if (retVal == -2) {
					statusLabel.setText ("Illegal move: Check");
				} else if (retVal == -1) {
					statusLabel.setText ("Illegal move");
				}
					
				if (droppedPanel != null) {
					Component [] components = droppedPanel.getComponents();
					if (components.length != 0) {
						if (components[0] instanceof JLabel) {
							// remove label from the panel
							JLabel droppedLabel = (JLabel) components[0];
							//droppedPanel.remove(droppedLabel);
							droppedPanel.revalidate();
						}
					}
				}
				remove (draggedLabel);
				//find where it is dropped; go back to orig if nowhere
				
				clickedPanel.add(draggedLabel);
				clickedPanel.revalidate();	
			} else if (retVal >= 0) {
				if (retVal == 0) {
					statusLabel.setText ("");	// all good
				} else if (retVal == 1) {
					statusLabel.setText ("Check!");	// check on other side
				} else if (retVal == 2) {
					statusLabel.setText ("Check mate!");
					// game over, nobody moves anymore
					blackTurn = false;
					whiteTurn = false;
				} else if (retVal == 3 ) {
					// game over, nobody moves anymore
					blackTurn = false;
					whiteTurn = false;
					statusLabel.setText ("Stale mate!");
				}
				
				// turn changes to the other side
				whiteTurn = !whiteTurn;	// change the turn, so nobody gets 2 turns
				blackTurn = !blackTurn;
				if (droppedPanel != null) {
					Component [] components = droppedPanel.getComponents();
					if (components.length != 0) {
						if (components[0] instanceof JLabel) {
							// remove label from the panel
							JLabel droppedLabel = (JLabel) components[0];
							droppedPanel.remove(droppedLabel);
							droppedPanel.revalidate();
						}
					}
				}
				droppedPanel.add(draggedLabel);
				droppedPanel.revalidate();		
			}
			repaint ();
			draggedLabel = null;
		}
			
		@Override
		public void mouseEntered(MouseEvent e) {
				// no action
		}
			
		@Override
		public void mouseExited(MouseEvent e) {
			// no action
		}
			
		@Override
		public void mouseClicked(MouseEvent e) {
			// no action
		}
			
	}
		

	public void createBoard() {
		boolean white = true;
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				squares[x][y] = new JPanel (new GridBagLayout());
				if (white) {
					squares[x][y].setBackground(Color.WHITE);
				}
				else {
					squares[x][y].setBackground(Color.GRAY);	
				}
				white = !white;
				chessPanel.add(squares[x][y]);
			}
			white = !white;
		}
	}

	public void draw (Board cBoard) {
		Chesspiece [][] logicalBoard = cBoard.getBoard();
		JLabel label = null;
		try {
			for (int y = 0; y < 8; y++) {
				for (int x = 0; x < 8; x++) {
					if (logicalBoard[x][y] != null) {
						label = new JLabel();
						if (logicalBoard[x][y].getClass().getName ().equals("Pawn")) {
							if (logicalBoard[x][y].getColor () == Color.BLACK)
								label = new JLabel(new ImageIcon(ImageIO.read(new File("resources/PA_B.png"))));
							else
								label = new JLabel(new ImageIcon(ImageIO.read(new File("resources/PA_W.png"))));
						} else if (logicalBoard[x][y].getClass().getName ().equals("King")) {
							if (logicalBoard[x][y].getColor () == Color.BLACK)
								label = new JLabel(new ImageIcon(ImageIO.read(new File("resources/KI_B.png"))));
							else
								label = new JLabel(new ImageIcon(ImageIO.read(new File("resources/KI_W.png"))));
						} else if (logicalBoard[x][y].getClass().getName ().equals("Queen")) {
							if (logicalBoard[x][y].getColor () == Color.BLACK)
								label = new JLabel(new ImageIcon(ImageIO.read(new File("resources/QU_B.png"))));
							else
								label = new JLabel(new ImageIcon(ImageIO.read(new File("resources/QU_W.png"))));
						} else if (logicalBoard[x][y].getClass().getName ().equals("Knight")) {
							if (logicalBoard[x][y].getColor () == Color.BLACK)
								label = new JLabel(new ImageIcon(ImageIO.read(new File("resources/KN_B.png"))));
							else
								label = new JLabel(new ImageIcon(ImageIO.read(new File("resources/KN_W.png"))));
						} else if (logicalBoard[x][y].getClass().getName ().equals("Bishop")) {
							if (logicalBoard[x][y].getColor () == Color.BLACK)
								label = new JLabel(new ImageIcon(ImageIO.read(new File("resources/BI_B.png"))));
							else
								label = new JLabel(new ImageIcon(ImageIO.read(new File("resources/BI_W.png"))));
						} else if (logicalBoard[x][y].getClass().getName ().equals("Castle")) {
							if (logicalBoard[x][y].getColor () == Color.BLACK)
								label = new JLabel(new ImageIcon(ImageIO.read(new File("resources/RO_B.png"))));
							else
								label = new JLabel(new ImageIcon(ImageIO.read(new File("resources/RO_W.png"))));
						}
						
						squares[x][y].add(label);
					}
				}
			}      
		}
		catch (IOException e) {
			System.out.println(e);
		}
	}
	
	
	public static void CreateAndShow () {
		JFrame chessFrame = new JFrame ("2-Player Chess");
        chessFrame.getContentPane().add(new VisualChessBoard());
        
        // add status bar at the bottom
        statusPanel.setBackground(Color.LIGHT_GRAY);
        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        chessFrame.add(statusPanel, BorderLayout.SOUTH);
        statusPanel.setPreferredSize(new Dimension(chessFrame.getWidth(), (PANEL_SIZE/8)));
        statusPanel.add(statusLabel);
        statusLabel.setFont(new Font("Serif", Font.PLAIN, 64));
        
        
        chessFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chessFrame.pack();
        chessFrame.setLocationRelativeTo(null);
        chessFrame.setVisible(true);
 	}


	public static void main(String[] args) {
				
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CreateAndShow();
            }
        });
	}
}


