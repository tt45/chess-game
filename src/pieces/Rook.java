package pieces;

import java.util.ArrayList;

import block.Block;
import board.Board;
import board.Position;
import player.Player;


/**
 * implements rook piece including potential moves
 * @author thompsonteng
 *
 */
public class Rook extends Pieces {
	
	/**
	 * rook constructor
	 * @param player
	 * @param pos
	 * @param type
	 */
	public Rook(Player player, Position pos, String type) {
		super(player, pos, type);
	}
	
	/**
	 * potential moves for rook piece return list of possible moves
	 */
	public ArrayList<Block> potentialMoves(Board board) {
		ArrayList<Block> potentialPos = new ArrayList<Block>();
		
		
		Pieces currPiece = board.getPiece(pos);
		Block[][] currBoard = board.chessBoard;
		int currRow = currPiece.pos.row;
		int currColumn = currPiece.pos.column;
		
		
		rookPotentialMove(board, potentialPos, currBoard, currRow, currColumn);
		
		return potentialPos;
	}

	protected void rookPotentialMove(Board board, ArrayList<Block> potentialPos, Block[][] currBoard, int currRow,
			int currColumn) {
		//left
		 for (int i=1; (currColumn-i)>=0; i++) {
			 if(inBound(board, new Position(currRow, currColumn-i))&&!occupiedSamePlayer(board, new Position(currRow, currColumn-i))) {
				 potentialPos.add(currBoard[currRow][currColumn-i]);
				 if (currBoard[currRow][currColumn-i].currOwner!=null) {
					 break;
				 }
			 }else break;
		 }
		
		//right
		 for (int i=1; (currColumn+i)<8; i++) {
			 if(inBound(board, new Position(currRow, currColumn+i))&&!occupiedSamePlayer(board, new Position(currRow, currColumn+i))) {
				 potentialPos.add(currBoard[currRow][currColumn+i]);
				 if (currBoard[currRow][currColumn+i].currOwner!=null) {
					 break;
				 }
			 }else break;
		 }
		 
		//down
		 for (int i=1; (currRow+i)<8; i++) {
			 if(inBound(board, new Position(currRow+i, currColumn))&&!occupiedSamePlayer(board, new Position(currRow+i, currColumn))) {
				 potentialPos.add(currBoard[currRow+i][currColumn]);
				 if (currBoard[currRow+i][currColumn].currOwner!=null) {
					 break;
				 }
			 }else break;
		 }
		
		//up
		 for (int i=1; (currRow-i)>=0; i++) {
			 if(inBound(board, new Position(currRow-i, currColumn))&&!occupiedSamePlayer(board, new Position(currRow-i, currColumn))) {
				 potentialPos.add(currBoard[currRow-i][currColumn]);
				 if (currBoard[currRow-i][currColumn].currOwner!=null) {
					 break;
				 }
			 }else break;
		 }
	}
}
