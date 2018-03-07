package pieces;

import java.util.ArrayList;

import block.Block;
import board.Board;
import board.Position;
import player.Player;

/**
 * implements knight piece including potential moves
 * @author thompsonteng
 *
 */
public class Knight extends Pieces{
	
	/**
	 * knight constructor
	 * @param player
	 * @param pos
	 * @param type
	 */
	public Knight(Player player, Position pos, String type) {
		super(player, pos, type);
	}

	/**
	 * potential moves for knight piece return list of possible moves
	 */
	public ArrayList<Block> potentialMoves(Board board) {
		ArrayList<Block> potentialPos = new ArrayList<Block>();
		
		if (inBound(board, new Position(pos.row+2, pos.column+1))&&!occupiedSamePlayer(board, new Position(pos.row+2, pos.column+1))) {	
			potentialPos.add(board.chessBoard[pos.row+2][pos.column+1]);
		}
		if (inBound(board, new Position(pos.row+2, pos.column-1))&&!occupiedSamePlayer(board, new Position(pos.row+2, pos.column-1))) {	
			potentialPos.add(board.chessBoard[pos.row+2][pos.column-1]);
		}
		if (inBound(board, new Position(pos.row+1, pos.column+2))&&!occupiedSamePlayer(board, new Position(pos.row+1, pos.column+2))) {
			potentialPos.add(board.chessBoard[pos.row+1][pos.column+2]);
		}
		if (inBound(board, new Position(pos.row+1, pos.column-2))&&!occupiedSamePlayer(board, new Position(pos.row+1, pos.column-2))) {
			potentialPos.add(board.chessBoard[pos.row+1][pos.column-2]);
		}
		
		if (inBound(board, new Position(pos.row-2, pos.column+1))&&!occupiedSamePlayer(board, new Position(pos.row-2, pos.column+1))) {
			potentialPos.add(board.chessBoard[pos.row-2][pos.column+1]);
		}
		if (inBound(board, new Position(pos.row-2, pos.column-1))&&!occupiedSamePlayer(board, new Position(pos.row-2, pos.column-1))) {
			potentialPos.add(board.chessBoard[pos.row-2][pos.column-1]);
		}
		if (inBound(board, new Position(pos.row-1, pos.column-2))&&!occupiedSamePlayer(board, new Position(pos.row-1, pos.column-2))) {
			potentialPos.add(board.chessBoard[pos.row-1][pos.column-2]);
		}
		if (inBound(board, new Position(pos.row-1, pos.column+2))&&!occupiedSamePlayer(board, new Position(pos.row-1, pos.column+2))) {
			potentialPos.add(board.chessBoard[pos.row-1][pos.column+2]);
		}
		return potentialPos;
	}
	
}
