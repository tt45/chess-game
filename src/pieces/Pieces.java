package pieces;

import board.Position;
import board.Board;
import block.Block;
import player.Player;
import java.util.ArrayList;

/**
 * implements parent piece class
 * @author thompsonteng
 *
 */
public abstract class Pieces {
	public Player player; //black is 0 and white is 1
	public Position pos;
	public String type;
	
	/**
	 * piece constructor 
	 * @param player
	 * @param pos
	 * @param type
	 */
	public Pieces(Player player, Position pos, String type) {
		this.player = player;
		this.pos = pos;
		this.type = type;
	}
	
	/**
	 * for a piece given position update its new position
	 * @param newPos
	 */
	public void updatePos(Position newPos) {
		this.pos = newPos;
	}
	
	/**
	 * determine it the position provided is in the bound of the chess board
	 * @param board
	 * @param pos
	 * @return
	 */
	public boolean inBound(Board board, Position pos) {
		int row = board.chessBoard.length;
		int column = board.chessBoard[0].length;
		if ((pos.row>=0 && pos.row<row) && (pos.column>=0 && pos.column<column)) {
			return true;
		}
		return false;
	}
	
	/**
	 * determine if the position is occupied by the same piece, return true if it is occupied
	 * by the same piece, return false if the block is null or opponent occupies it
	 * @param board
	 * @param intend_pos
	 * @return
	 */
	public boolean occupiedSamePlayer(Board board, Position intend_pos) {
		int currPlayerPc = player.pc;
		if(board.chessBoard[intend_pos.row][intend_pos.column].currPiece!=null&&board.chessBoard[intend_pos.row][intend_pos.column].currOwner.pc==currPlayerPc) {
			return true;
		}
		return false;
	}
	
	/**
	 * determine if the intended move for the relative piece is valid
	 * @param board
	 * @param player
	 * @param start_pos
	 * @param end_pos
	 * @return
	 */
	public boolean isValidMove(Board board, Player player, Position start_pos, Position end_pos) {
		if (start_pos.row==end_pos.row && start_pos.column==end_pos.column) {
			return false;
		}
		//Pieces currPiece = board.chessBoard[end_pos.row][end_pos.column].currPiece;
		ArrayList<Block> potentialBlock = potentialMoves(board);
		for (Block tempBlock: potentialBlock) {
			if((tempBlock.pos.row == end_pos.row)&&(tempBlock.pos.column == end_pos.column)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * print all the valid moves for the current piece
	 * @param potentialPos
	 */
	public void printValidMove(ArrayList<Block> potentialPos) {
		for (Block tempBlock: potentialPos) {
			System.out.println("("+ tempBlock.pos.row+ ","+ tempBlock.pos.column+ ")");
		}
	}
	
	/**
	 * abstract method potential moves
	 * @param board
	 * @return
	 */
	public abstract ArrayList<Block> potentialMoves(Board board);
	
}
