package pieces;

import java.util.ArrayList;

import board.Board;
import board.Position;
import block.Block;
import player.Player;

/**
 * implements pawn piece including potential moves
 * @author thompsonteng
 *
 */
public class Pawn extends Pieces{
	
	/**
	 * pawn constructor
	 * @param player
	 * @param pos
	 * @param type
	 */
	public Pawn(Player player, Position pos, String type) {
		super(player, pos, type);
	}
	
	/**
	 * potential moves for pawn piece return list of possible moves
	 */
	public ArrayList<Block> potentialMoves(Board board){
		ArrayList<Block> potentialPos = new ArrayList<Block>();
		
		Pieces currPiece = board.getPiece(pos);
		Block[][] currBoard = board.chessBoard;
		int board_height = board.board_height;
		boolean isWhite = currPiece.player.pc == 1 ? true : false;
		
		if(isWhite) {
			if (pos.row == board_height-2) {
				if (!occupiedSamePlayer(board, new Position(pos.row-2, pos.column)))
					potentialPos.add(currBoard[pos.row-2][pos.column]);
			}
			//if (!occupiedSamePlayer(board, new Position(pos.row-1, pos.column))) 
			if(inBound(board, new Position(pos.row-1, pos.column))&&board.getPiece(new Position(pos.row-1, pos.column))==null)
				potentialPos.add(currBoard[pos.row-1][pos.column]);
			
			
			Position tempPos = new Position(pos.row-1, pos.column-1);
			if (inBound(board, tempPos) && board.getPiece(tempPos)!=null&&board.getPiece(tempPos).player.pc == 0) {
				potentialPos.add(currBoard[pos.row-1][pos.column-1]);
			}
			
			tempPos = new Position(pos.row-1, pos.column+1);
			if (inBound(board, tempPos)&&board.getPiece(tempPos)!=null&&board.getPiece(tempPos).player.pc == 0) {					
				potentialPos.add(currBoard[pos.row-1][pos.column+1]);
			}
			
		}else {
			if (pos.row == 1) {
				if (!occupiedSamePlayer(board, new Position(3, pos.column)))
					potentialPos.add(currBoard[3][pos.column]);
			}
			//if (!occupiedSamePlayer(board, new Position(pos.row+1, pos.column)))
			if(inBound(board, new Position(pos.row+1, pos.column))&&board.getPiece(new Position(pos.row+1, pos.column))==null)
				potentialPos.add(currBoard[pos.row+1][pos.column]);
			
			Position tempPos = new Position(pos.row+1, pos.column-1);
			if (inBound(board, tempPos)&&board.getPiece(tempPos) != null && board.getPiece(tempPos).player.pc==1) {
					potentialPos.add(currBoard[pos.row+1][pos.column-1]);
			}
			
			tempPos = new Position(pos.row+1, pos.column+1);
			if (inBound(board, tempPos)&&board.getPiece(tempPos) != null&&board.getPiece(tempPos).player.pc==1) {
					potentialPos.add(currBoard[pos.row+1][pos.column+1]);
			}
		}
		return potentialPos;
	}
}
