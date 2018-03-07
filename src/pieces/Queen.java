package pieces;

import java.util.ArrayList;

import block.Block;
import board.Board;
import board.Position;
import player.Player;

/**
 * implements queen piece including potential moves
 * @author thompsonteng
 *
 */
public class Queen extends Pieces {
	
	/**
	 * queen constructor
	 * @param player
	 * @param pos
	 * @param type
	 */
	public Queen(Player player, Position pos, String type) {
		super(player, pos, type);
	}
	
	/**
	 * potential moves for queen piece return list of possible moves
	 */
	public ArrayList<Block> potentialMoves(Board board) {
		ArrayList<Block> potentialPos = new ArrayList<Block>();
		
		
		Pieces currPiece = board.getPiece(pos);
		Block[][] currBoard = board.chessBoard; 
		int currRow = currPiece.pos.row;
		int currColumn = currPiece.pos.column;
		
		int opponent = currPiece.player.getOpponent();
		
		Bishop bishop = new Bishop(this.player, this.pos, this.type);
		bishop.bishopPotentialMove(board, potentialPos, currBoard, currRow, currColumn, opponent);
		
		Rook rook = new Rook(this.player, this.pos, this.type);
		rook.rookPotentialMove(board, potentialPos, currBoard, currRow, currColumn);
		
		return potentialPos;
	}
}
