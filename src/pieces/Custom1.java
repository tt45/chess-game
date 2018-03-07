package pieces;

import java.util.ArrayList;

import block.Block;
import board.Board;
import board.Position;
import player.Player;

/**
 * this piece can only move up, down, right and left for two possible blocks
 * @author thompsonteng
 *
 */
public class Custom1 extends Pieces{
	
	/**
	 * custom1 constructor
	 * @param player
	 * @param pos
	 * @param type
	 */
	public Custom1(Player player, Position pos, String type) {
		super(player, pos, type);
	}
	
	/**
	 * potential moves for bishop piece return list of possible moves
	 */
	public ArrayList<Block> potentialMoves(Board board) {
		ArrayList<Block> potentialPos = new ArrayList<>(); 
		
		Pieces currPiece = board.getPiece(pos);
		Block[][] currBoard = board.chessBoard;
		int currRow = currPiece.pos.row;
		int currColumn = currPiece.pos.column;
		
		//left
		 for (int i=1; i<3; i++) {
			 if(inBound(board, new Position(currRow, currColumn-i))&&!occupiedSamePlayer(board, new Position(currRow, currColumn-i))) {
				 potentialPos.add(currBoard[currRow][currColumn-i]);
				 if (currBoard[currRow][currColumn-i].currOwner!=null) {
					 break;
				 }
			 }else break;
		 }
		
		//right
		 for (int i=1; i<3; i++) {
			 if(inBound(board, new Position(currRow, currColumn+i))&&!occupiedSamePlayer(board, new Position(currRow, currColumn+i))) {
				 potentialPos.add(currBoard[currRow][currColumn+i]);
				 if (currBoard[currRow][currColumn+i].currOwner!=null) {
					 break;
				 }
			 }else break;
		 }
		 
		//down
		 for (int i=1; i<3; i++) {
			 if(inBound(board, new Position(currRow+i, currColumn))&&!occupiedSamePlayer(board, new Position(currRow+i, currColumn))) {
				 potentialPos.add(currBoard[currRow+i][currColumn]);
				 if (currBoard[currRow+i][currColumn].currOwner!=null) {
					 break;
				 }
			 }else break;
		 }
		
		//up
		 for (int i=1; i<3; i++) {
			 if(inBound(board, new Position(currRow-i, currColumn))&&!occupiedSamePlayer(board, new Position(currRow-i, currColumn))) {
				 potentialPos.add(currBoard[currRow-i][currColumn]);
				 if (currBoard[currRow-i][currColumn].currOwner!=null) {
					 break;
				 }
			 }else break;
		 }
		
		return potentialPos;
	}
}
