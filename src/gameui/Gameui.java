package gameui;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.util.ArrayList;

import board.*;
import block.*;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Pieces;
import pieces.Queen;
import pieces.Rook;
import player.Player;

import java.awt.*;

 
 /**
  * game user interface for chess
  * @author thompsonteng
  *
  */
public final class Gameui{
	
	/**
	 * Gameui constructor
	 */
	
	Block[][] chessboard = null;
	Board board = null;
	JButton selectedPiece = null;
	JButton chessButtons[][] = null;
	int currPlayerPc = 0;
	int black_score = 0;
	int white_score = 0;
	String black_name = "Black";
	String white_name = "White";
	
	
	ArrayList<Pieces> currPieces = new ArrayList<Pieces>();
	ArrayList<Pieces> prevPieces = new ArrayList<Pieces>();
	ArrayList<Position> currStartPoss = new ArrayList<Position>();
	ArrayList<Position> currEndPoss = new ArrayList<Position>();
	
	private JFrame window = null;
	private JPanel myPanel = null;
	private JPanel playerPanel = null;
	private JLabel blackPlayerLabel = null;
	private JLabel whitePlayerLabel = null;
	
	/*
	 * Gameui constructor to set up the game frame
	 */
    public Gameui(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            //silently ignore
        }
        window = new JFrame("Chess Game");
        window.setSize(650, 500);
        
        
        board = new Board(chessboard);
        chessboard = board.chessBoard;
        chessButtons = new JButton[board.board_height][board.board_width];
        myPanel = initializePanel(board);
        playerPanel = initializePlayerPanel();
        
        initializePlayerPanel();
        
        setUpMenu(window);
        //window.setContentPane(myPanel); 
        window.getContentPane().add(myPanel, BorderLayout.WEST);
        window.getContentPane().add(playerPanel, BorderLayout.EAST);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JOptionPane.showMessageDialog(null, "Welcome to Chess Game! Black Player moves first", null, JOptionPane.INFORMATION_MESSAGE);
    }
 
    /**
     * initialize the panel for chess board
     * @param board
     * @return
     */
    private JPanel initializePanel(Board board) {
        JPanel myPanel = new JPanel();
        myPanel.setPreferredSize(new Dimension(500,500));
        myPanel.setLayout(new GridLayout(8,8));
        for(int row = 0; row < 8; row++){
			for(int col = 0; col < 8; col++) {
				Block block = board.getBlock(new Position(row, col));
				if(block != null) {
					JButton tempButton = createSquareButton(block);
					chessButtons[row][col] = tempButton;
					myPanel.add(tempButton);
				}
					
			}
		}
        return myPanel; 
    }
    
    /*
     * initialize the player panel on the right
     */
    private JPanel initializePlayerPanel() {
    		playerPanel = new JPanel();
    		
    		playerPanel.setLayout(new GridLayout(2,1));
    		blackPlayerLabel = new JLabel();
    		blackPlayerLabel.setText("Black Team score:  "+black_score);
    		whitePlayerLabel = new JLabel();
    		whitePlayerLabel.setText("White team score:  "+white_score);
    		
    		playerPanel.add(blackPlayerLabel, BorderLayout.NORTH);
    		playerPanel.add(whitePlayerLabel, BorderLayout.SOUTH);
    		
    		return playerPanel;
    }
    
    /**
     * create buttons for the chessboard
     * @param block
     * @return
     */
    private JButton createSquareButton(Block block) {
		ImageIcon myImage = null;
		if(block.currPiece != null){
			Pieces p = block.getBlockPiece();
			myImage = new ImageIcon("images/"+p.type+".png");
		}
		JButton squareButton = new JButton(myImage);
		if ((block.pos.row+block.pos.column)%2==0) {
			squareButton.setBackground(new Color(255, 206, 158));
		}else {
			
			squareButton.setBackground(new Color(209, 139, 71));
		}
		
		squareButton.putClientProperty("Position", block.pos);
		squareButton.setBorderPainted(false);
		squareButton.setOpaque(true);
		squareButton.addActionListener(new selectSquareAction());
		return squareButton;
	}

    
    
    /**
     * set up the game menu for chess board
     * @param window
     */
    private void setUpMenu(JFrame window) { //restart, undo, forfeit
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("Game");
        
        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			int flag = JOptionPane.showConfirmDialog(null, "Are you sure to start New Game? ", null, JOptionPane.YES_NO_OPTION);
        			if (flag == JOptionPane.YES_OPTION) {			
        				startNewGame();
        			} else {
        				//JOptionPane.showMessageDialog(null, "Your opponent declines your restart request.");
        			}
            }
        });
        
        JMenuItem restart = new JMenuItem("Restart");
        restart.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			String opponent = (currPlayerPc!=0) ? black_name : white_name;
        			int flag = JOptionPane.showConfirmDialog(null, "Press yes if "+opponent+" team agree to restart", null, JOptionPane.YES_NO_OPTION);
        			if (flag == JOptionPane.YES_OPTION) {
        				resetGame();
        			} else {
        				JOptionPane.showMessageDialog(null, "Your opponent declines your restart request.");
        			}
            }
        });
        
        JMenuItem forfeit = new JMenuItem("Forfeit");
        forfeit.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			String player = (currPlayerPc==0) ? black_name : "white_name";
        			int flag = JOptionPane.showConfirmDialog(null, "Are you sure to forfeit? "+player+" Team", null, JOptionPane.YES_NO_OPTION);
        			if (flag == JOptionPane.YES_OPTION) {
        				int opponent = (currPlayerPc-1)*(-1);
        				int opponentScore = currPlayerPc!=0 ? black_score : white_score;
        				increaseScore(opponent, opponentScore);
        				resetGame();
        			} else {
        				//JOptionPane.showMessageDialog(null, "Your opponent declines your restart request.");
        			}
            }
        });
        
        file.add(newGame);
        file.add(restart);
        file.add(forfeit);
        
        JMenu player = new JMenu("Player");
        JMenuItem undoMove = new JMenuItem("Undo");
        undoMove.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				undoMove();
    			}
        });
        
        JMenuItem changeName = new JMenuItem("Change Name");
        changeName.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			String currPlayerName = (currPlayerPc==0) ? black_name : white_name;
        			String player_name = JOptionPane.showInputDialog("Please enter the name of player "+currPlayerName+" Team");
        			if(currPlayerPc==0) {
        				black_name = player_name;
        				blackPlayerLabel.setText(black_name+" team score:  "+black_score);
        			}else {
        				white_name = player_name;
        				whitePlayerLabel.setText(white_name+" team score:  "+white_score);
        			}
        		}
        });
        
        player.add(undoMove);
        player.add(changeName);
        menubar.add(file);
        menubar.add(player);
        window.setJMenuBar(menubar);
    }
    
    /*
     * action listener when user click on the chessboard and move the chess piece
     */
    private class selectSquareAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			JButton selectedSquare = (JButton) e.getSource();
			Position curr_pos = (Position) selectedSquare.getClientProperty("Position");
			
			Pieces piece = board.getPiece(curr_pos);
			if (piece != null && piece.player.pc == currPlayerPc) { //confirm player to be added
				selectPiece(selectedSquare);
				return;
			}
			
			if (selectedPiece == null) {
				if(chessboard[curr_pos.row][curr_pos.column].currPiece==null||chessboard[curr_pos.row][curr_pos.column].currPiece.player.pc!=currPlayerPc) {
					String currPlayer = (currPlayerPc==0) ? "Black" : "White";
					JOptionPane.showMessageDialog(null, currPlayer+" Team Turn!", null, JOptionPane.ERROR_MESSAGE);
				}
			}
			
			if (selectedPiece!=null) {
				Player currPlayer = currPlayerPc==0 ? board.player1 : board.player2;
				moveToSquare(selectedSquare);
				if (currPlayer.king.isKingChecked(board, currPlayer.king.pos)) {
					JOptionPane.showMessageDialog(null, " Illegal Move", null, JOptionPane.ERROR_MESSAGE);
					undoMove();
				}
			}
			
			kingCheckMate();
			kingChecked();
			checkGameEnded();
			
		}
		
		/*
		 * first step of movement
		 */
		private void selectPiece(JButton selectedSquare) {
			selectedPiece = selectedSquare;
			resetBackGround();
			showPossibleMoves(selectedSquare);
			
		}
		
		/*
		 * second step of movement
		 */
		private void moveToSquare(JButton intendSquare) {
			Position intend_pos = (Position) intendSquare.getClientProperty("Position");
			Position select_pos = (Position) selectedPiece.getClientProperty("Position");
			Pieces piece = board.getPiece(select_pos);
			
			if (piece.isValidMove(board, piece.player, select_pos, intend_pos)) {
				Pieces capturedPiece = board.getPiece(intend_pos);
				currPlayerPc = (currPlayerPc-1)*(-1);
				board.move(board, piece.player, select_pos, intend_pos);
				selectedPiece.setIcon(null);
				selectedPiece = null;
				intendSquare.setIcon(new ImageIcon("images/"+piece.type+".png"));
				recordGameState(piece, capturedPiece ,select_pos, intend_pos);
			}else {
				selectedPiece = null;
				JOptionPane.showMessageDialog(null, "Invalid move.", null, JOptionPane.ERROR_MESSAGE);

			}
			resetBackGround();	
		}
	}
    
    /*
     * make current selection of piece's possible moves visible to user
     */
    private void showPossibleMoves(JButton selectedSquare) {
    		Position curr_pos = (Position) selectedSquare.getClientProperty("Position");
		Pieces piece = board.getPiece(curr_pos);
		ArrayList<Block> potentialPos = piece.potentialMoves(board);
    		for (Block block: potentialPos) {
    			JButton tempButton = chessButtons[block.pos.row][block.pos.column];
    			tempButton.setBackground(Color.white);
    		}
    }
    
    /*
     * reset the color of the background
     */
    private void resetBackGround() {
	    	for (int i=0; i<board.board_height; i++) {
			for (int j=0; j<board.board_width; j++) {
				if ((i+j)%2==0) {
					chessButtons[i][j].setBackground(new Color(255, 206, 158));
				}else {
					
					chessButtons[i][j].setBackground(new Color(209,139,71));
				}
			}
	    	}
    }
    
    /*
     * record the game state so the user will be able to undo the movement
     */
    private void recordGameState(Pieces currExecPiece, Pieces capturedPiece, Position start_pos, Position end_pos) {
    		currPieces.add(currExecPiece);
    		prevPieces.add(capturedPiece);
    		currStartPoss.add(start_pos);
    		currEndPoss.add(end_pos);
    		
    }
    
    /*
     * undo movement. Undo is functional until the no more moves in the stack
     */
    private void undoMove() {
    		if (currPieces.size()!=0) {
    			Pieces currPiece = currPieces.remove(currPieces.size()-1);
        		Pieces prevPiece = prevPieces.remove(prevPieces.size()-1);
        		Position start_pos = currStartPoss.remove(currStartPoss.size()-1);
        		Position end_pos = currEndPoss.remove(currEndPoss.size()-1);
        		
        		Player prevPlayer = (prevPiece != null) ? prevPiece.player : null;
        		chessboard[end_pos.row][end_pos.column].updateBlock(prevPiece, prevPlayer);
        		chessboard[start_pos.row][start_pos.column].updateBlock(currPiece, currPiece.player);
        		if(currPiece!=null) currPiece.updatePos(start_pos);
        		if (prevPiece!=null)prevPiece.updatePos(end_pos);
        		
        		if(prevPiece != null)
        			chessButtons[end_pos.row][end_pos.column].setIcon(new ImageIcon("images/"+prevPiece.type+".png"));
        		else
        			chessButtons[end_pos.row][end_pos.column].setIcon(null);
        		chessButtons[start_pos.row][start_pos.column].setIcon(new ImageIcon("images/"+currPiece.type+".png"));
        		
        		resetBackGround();
        		currPlayerPc = (currPlayerPc-1)*(-1);
    		}else {
    			JOptionPane.showMessageDialog(null, "No move to undo.", null, JOptionPane.ERROR_MESSAGE);
    		}
    		
    }
    
    /*
     * reset the chess game
     */
    private void resetGame() {
	    	int board_height = board.board_height;
	    	int board_width = board.board_width;
	    	Block[][] chessBoard = board.chessBoard;
	    	Player player1 = board.player1;
	    	Player player2 = board.player2;
	    	for (int i=0; i<board_height; i++) {
			for (int j=0; j<board_width; j++) {
				
				if (i == 1) {
					Pawn black_pawn = new Pawn(player1, new Position(i, j), "black_pawn");
					chessBoard[i][j].updateBlock(black_pawn, player1);
				}
				if ((i==0&&j==0)||(i==0&&j==7)) {
					Rook black_rook = new Rook(player1, new Position(i, j), "black_rook");
					chessBoard[i][j].updateBlock(black_rook, player1);
				}
				if ((i==0&&j==1)||(i==0&&j==6)) {
					Knight black_knight = new Knight(player1, new Position(i, j), "black_knight");
					chessBoard[i][j].updateBlock(black_knight, player1);
				}
				if ((i==0&&j==2)||(i==0&&j==5)) {
					Bishop black_bishop = new Bishop(player1, new Position(i, j), "black_bishop");
					chessBoard[i][j].updateBlock(black_bishop, player1);
				}
				if (i==0&&j==3) {
					Queen black_queen = new Queen(player1, new Position(i, j), "black_queen");
					chessBoard[i][j].updateBlock(black_queen, player1);
				}
				if (i==0&&j==4) {
					King black_king = new King(player1, new Position(i, j), "black_king");
					chessBoard[i][j].updateBlock(black_king, player1);
					player1.king = black_king;
				}
				// white pieces
				if (i == 6) {
					Pawn white_pawn = new Pawn(player2, new Position(i, j), "white_pawn");
					chessBoard[i][j].updateBlock(white_pawn, player2);
				}
				if ((i==7&&j==0)||(i==7&&j==7)) {
					Rook white_rook = new Rook(player2, new Position(i, j), "white_rook");
					chessBoard[i][j].updateBlock(white_rook, player2);
				}
				if ((i==7&&j==1)||(i==7&&j==6)) {
					Knight white_knight = new Knight(player2, new Position(i, j), "white_knight");
					chessBoard[i][j].updateBlock(white_knight, player2);
				}
				if ((i==7&&j==2)||(i==7&&j==5)) {
					Bishop white_bishop = new Bishop(player2, new Position(i, j), "white_bishop");
					chessBoard[i][j].updateBlock(white_bishop, player2);
				}
				if (i==7&&j==3) {
					Queen white_queen = new Queen(player2, new Position(i, j), "white_queen");
					chessBoard[i][j].updateBlock(white_queen, player2);
				}
				if (i==7&&j==4) {
					King white_king = new King(player2, new Position(i, j), "white_king");
					chessBoard[i][j].updateBlock(white_king, player2);
					player2.king = white_king;
				}
				Pieces piece = board.getPiece(new Position(i,j));
				if (i<=1||i>=6) {
					chessButtons[i][j].setIcon(new ImageIcon("images/"+piece.type+".png"));
				}else {
					chessButtons[i][j].setIcon(null);
					chessBoard[i][j].updateBlock(null, null);
				}
				
			}
		}
	    	resetBackGround();
	    	currPieces.clear();
	    	prevPieces.clear();
	    	currStartPoss.clear();
	    	currEndPoss.clear();
	    	currPlayerPc = 0;
    }
    
    /*
     * check if game has ended by making sure there's no more king
     */
    private void checkGameEnded() {
    		int whiteKingAliveFlag = 0;
    		int blackKingAliveFlag = 0;
    		for (int i=0; i<board.board_height; i++) {
    			for (int j=0; j<board.board_width; j++) {
    				if (chessboard[i][j].currPiece!=null) {
    					if (chessboard[i][j].currPiece.type=="white_king") {
    						whiteKingAliveFlag = 1;
    					}
    					if (chessboard[i][j].currPiece.type=="black_king") {
    						blackKingAliveFlag = 1;
    					}
    				}
    			}
    		}
    		
    		if (whiteKingAliveFlag==0) {
    			JOptionPane.showMessageDialog(null, "black team wins", null, JOptionPane.INFORMATION_MESSAGE);
    			increaseScore(0, black_score);
    			resetGame();
    		}
    		
    		if (blackKingAliveFlag==0) {
    			JOptionPane.showMessageDialog(null, "white team wins", null, JOptionPane.INFORMATION_MESSAGE);
    			increaseScore(1, white_score);
    			resetGame();
    		}
    		
    }
    
    /*
     * check if anyside's king is checked
     */
    private void kingChecked() {
		Position king_pos = board.player2.king.pos;
		if(board.player2.king.isKingChecked(board, king_pos)) {
			System.out.println("player2 is checked");
			JOptionPane.showMessageDialog(null, "white king is checked", null, JOptionPane.INFORMATION_MESSAGE);
		}
		king_pos = board.player1.king.pos;
		if(board.player1.king.isKingChecked(board, king_pos)) {
			System.out.println("player1 is checked");
			JOptionPane.showMessageDialog(null, "black king is checked", null, JOptionPane.INFORMATION_MESSAGE);
		}
		
		
    }
    
    /*
     * check if anyside's king is stalemate
     */
    private void kingStaleMate() {
    		ArrayList<Block> potentialMoves;
		potentialMoves = board.player2.king.potentialMoves(board);
		if(board.player2.king.isStaleMate(potentialMoves, board)) {
			JOptionPane.showMessageDialog(null, "white king is staleMated", null, JOptionPane.INFORMATION_MESSAGE);
			resetGame();
		}
		potentialMoves = board.player1.king.potentialMoves(board);
		if(board.player1.king.isStaleMate(potentialMoves, board)) {
			JOptionPane.showMessageDialog(null, "black king is staleMated", null, JOptionPane.INFORMATION_MESSAGE);
			resetGame();
		}
    }
    
    /*
     * check if anyside's king is checkmated
     */
    private void kingCheckMate() {
    		ArrayList<Block> potentialMoves;
		potentialMoves = board.player2.king.potentialMoves(board);
		if(board.player2.king.isCheckMate(potentialMoves, board)) {
			JOptionPane.showMessageDialog(null, "white king is checkMated", null, JOptionPane.INFORMATION_MESSAGE);
			increaseScore(0, black_score);
			resetGame();
		}
		potentialMoves = board.player1.king.potentialMoves(board);
		if(board.player1.king.isCheckMate(potentialMoves, board)) {
			JOptionPane.showMessageDialog(null, "black king is checkMated", null, JOptionPane.INFORMATION_MESSAGE);
			increaseScore(1, white_score);
			resetGame();
		}
    }
    
    /*
     * increase the score of player
     */
    private void increaseScore(int side, int score) {
    		if (side==0) {
    			black_score++;
    			blackPlayerLabel.setText(black_name+" team score:  "+black_score);
    		}else {
    			white_score++;
    			whitePlayerLabel.setText(white_name+" team score:  "+white_score);
    		}
    }
    
    /*
     * start new game
     */
    private void startNewGame() {
    		resetGame();
    		black_score = 0;
    		white_score = 0;
    		blackPlayerLabel.setText("Black team score:  "+black_score);
    		whitePlayerLabel.setText("White team score:  "+white_score);
    }
    
    /**
     * main function to call the game ui
     * @param args
     */
    public static void main(String[] args) {
        new Gameui();
    }
}

