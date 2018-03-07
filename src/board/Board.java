package board;

import pieces.Pieces;
import pieces.*;

import java.util.ArrayList;

import javax.swing.JOptionPane;

//import java.util.ArrayList;

import block.Block;
import player.Player;

/**
 * black chess at the top
 * white chess at the bottom
 * @author thompsonteng
 *
 */
public class Board{
	public final int board_width = 8;
	public final int board_height = 8;
	public Player player1;
	public Player player2;
	public Block[][] chessBoard;
	
	/**
	 * board constructor
	 * @param chessBoard
	 */
	public Board(Block[][] chessBoard){
		chessBoard = new Block[board_width][board_height];
		this.chessBoard = chessBoard;
		initialize_player();
		initialize_board(chessBoard);
		
	}
	
	
	/**
	 * initialize two player for the chess game
	 */
	private void initialize_player() {
		player1 = new Player(0, null);
		player2 = new Player(1, null);
	}
	
	/**
	 * initialize the chessboard with player and pieces
	 * @param chessBoard
	 */
	public void initialize_board(Block[][] chessBoard) {
		for (int i=0; i<board_height; i++) {
			for (int j=0; j<board_width; j++) {
				chessBoard[i][j] = new Block(new Position(i, j), null, null);
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
			}
		}
	}
	
	/**
	 * return the chessboard
	 * @return
	 */
	public Block[][] getBoard() {
		return chessBoard;
	}
	
	/**
	 * get block given position
	 * @param pos
	 * @return
	 */
	public Block getBlock(Position pos) {
		return chessBoard[pos.row][pos.column];
	}
	
	/**
	 * get piece given position
	 * @param pos
	 * @return
	 */
	public Pieces getPiece(Position pos) {
		return chessBoard[pos.row][pos.column].currPiece;
	}
	
	/**
	 * move function to move the given piece at start_pos for currPlayer. return true if the 
	 * move is possible return false otherwise
	 * @param board
	 * @param currPlayer
	 * @param start_pos
	 * @param end_pos
	 * @return
	 */
	public boolean move(Board board, Player currPlayer, Position start_pos, Position end_pos) {
		Pieces currPiece = board.chessBoard[start_pos.row][start_pos.column].currPiece;
		
		/*if (currPiece == null) {
	        throw new NullPointerException("the current moving piece is not valid");
	    }*/
		
		boolean isValidStep = currPiece.isValidMove(board, currPlayer, start_pos, end_pos);
		if (isValidStep==false) return false;
		
		chessBoard[end_pos.row][end_pos.column].updateBlock(currPiece, currPlayer);
		chessBoard[start_pos.row][start_pos.column].updateBlock(null, null);
		currPiece.updatePos(end_pos);
		
		return true;
	}

}