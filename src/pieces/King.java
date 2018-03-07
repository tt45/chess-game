package pieces;

import java.util.ArrayList;

import block.Block;
import board.Board;
import board.Position;
import player.Player;
/**
 * implements king piece including potential moves
 * @author thompsonteng
 *
 */
public class King extends Pieces {
	
	/**
	 * king constructor
	 * @param player
	 * @param pos
	 * @param type
	 */
	public King(Player player, Position pos, String type) {
		super(player, pos, type);
	}
	
	/**
	 * potential moves for king piece return list of possible moves
	 */
	public ArrayList<Block> potentialMoves(Board board) {
		ArrayList<Block> potentialPos = new ArrayList<Block>();
		
		Pieces currPiece = board.getPiece(pos);
		Block[][] currBoard = board.chessBoard;
		int currRow = currPiece.pos.row;
		int currColumn = currPiece.pos.column;
		
		if (inBound(board, new Position(currRow-1, currColumn))&&!occupiedSamePlayer(board, new Position(currRow-1, currColumn))) { //up
			potentialPos.add(currBoard[currRow-1][currColumn]);
		}
		
		if (inBound(board, new Position(currRow+1, currColumn))&&!occupiedSamePlayer(board, new Position(currRow+1, currColumn))) { //down
			potentialPos.add(currBoard[currRow+1][currColumn]);
		}
		
		if (inBound(board, new Position(currRow, currColumn-1))&&!occupiedSamePlayer(board, new Position(currRow, currColumn-1))) { //left
			potentialPos.add(currBoard[currRow][currColumn-1]);
		}
		
		if (inBound(board, new Position(currRow, currColumn+1))&&!occupiedSamePlayer(board, new Position(currRow, currColumn+1))) { //right
			potentialPos.add(currBoard[currRow][currColumn+1]);
		}
		
		if (inBound(board, new Position(currRow+1, currColumn-1))&&!occupiedSamePlayer(board, new Position(currRow+1, currColumn-1))) { //left down
			potentialPos.add(currBoard[currRow+1][currColumn-1]);
		}
		
		if (inBound(board, new Position(currRow+1, currColumn+1))&&!occupiedSamePlayer(board, new Position(currRow+1, currColumn+1))) { //right down
			potentialPos.add(currBoard[currRow+1][currColumn+1]);
		}
		
		if (inBound(board, new Position(currRow-1, currColumn-1))&&!occupiedSamePlayer(board, new Position(currRow-1, currColumn-1))) { //left up
			potentialPos.add(currBoard[currRow-1][currColumn-1]);
		}
		
		if (inBound(board, new Position(currRow-1, currColumn+1))&&!occupiedSamePlayer(board, new Position(currRow-1, currColumn+1))) { //right up
			potentialPos.add(currBoard[currRow-1][currColumn+1]);
		}
	
        return potentialPos;
	}
	
	/**
	 * boolean function to determine if the king is checked. it uses the testPos as the position
	 * of the king and detect if there's any enemy piece that could danger the king
	 * @param board
	 * @param testPos
	 * @return
	 */
	public boolean isKingChecked(Board board, Position testPos) {
		//Position king_pos = this.pos;
		int currRow = testPos.row;
		int currColumn = testPos.column;
		Block[][] currBoard = board.chessBoard;
		String opponent = this.player.pc==0 ? "white" : "black";
		return allPositionCheck(board, currRow, currColumn, currBoard, opponent);
	}

	private Boolean allPositionCheck(Board board, int currRow, int currColumn, Block[][] currBoard, String opponent) {
		if(opponent=="white") {
			if (inBound(board, new Position(currRow+1, currColumn+1))&&currBoard[currRow+1][currColumn+1].currPiece!=null&&currBoard[currRow+1][currColumn+1].currPiece.type.equals(opponent+"_pawn")) {
				System.out.println("not here????");
				return true;
			}
			
			if (inBound(board, new Position(currRow+1, currColumn-1))&&currBoard[currRow+1][currColumn-1].currPiece!=null&&currBoard[currRow+1][currColumn-1].currPiece.type.equals(opponent+"_pawn")) {
				return true;
			}
		}else {
			if (inBound(board, new Position(currRow-1, currColumn+1))&&currBoard[currRow-1][currColumn+1].currPiece!=null&&currBoard[currRow-1][currColumn+1].currPiece.type.equals(opponent+"_pawn")) {
				return true;
			}
			
			if (inBound(board, new Position(currRow-1, currColumn-1))&&currBoard[currRow-1][currColumn-1].currPiece!=null&&currBoard[currRow-1][currColumn-1].currPiece.type.equals(opponent+"_pawn")) {
				return true;
			}
		}
		for (int i=1; (currRow-i)>=0; i++) {//up
			 if(inBound(board, new Position(currRow-i, currColumn))&&!occupiedSamePlayer(board, new Position(currRow-i, currColumn))) {
				 if(currBoard[currRow-i][currColumn].currPiece!=null) {
					 if (currBoard[currRow-i][currColumn].currPiece.type.equals(opponent+"_rook")||currBoard[currRow-i][currColumn].currPiece.type.equals(opponent+"_queen")) {
						 return true;
					 }else break;
				 }
			 }else break;
		}
		
		for (int i=1; (currRow+i)<8; i++) {//down
			 if(inBound(board, new Position(currRow+i, currColumn))&&!occupiedSamePlayer(board, new Position(currRow+i, currColumn))) {
				 if(currBoard[currRow+i][currColumn].currPiece!=null) {
					 if (currBoard[currRow+i][currColumn].currPiece.type.equals(opponent+"_rook")||currBoard[currRow+i][currColumn].currPiece.type.equals(opponent+"_queen")) {
						 return true;
					 }else break;
				 } 
			 }else break;
		}
		
		for (int i=1; (currColumn+i)<8; i++) {//right
			 if(inBound(board, new Position(currRow, currColumn+i))&&!occupiedSamePlayer(board, new Position(currRow, currColumn+i))) {
				 if(currBoard[currRow][currColumn+i].currPiece!=null) {
					 if (currBoard[currRow][currColumn+i].currPiece.type.equals(opponent+"_rook")||currBoard[currRow][currColumn+i].currPiece.type.equals(opponent+"_queen")) {
						 return true;
					 }else break;
				 }
			 }else break;
		}
		
		for (int i=1; (currColumn-i)>=0; i++) {//left
			 if(inBound(board, new Position(currRow, currColumn-i))&&!occupiedSamePlayer(board, new Position(currRow, currColumn-i))) {
				 if(currBoard[currRow][currColumn-i].currPiece!=null) {
					 if (currBoard[currRow][currColumn-i].currPiece.type.equals(opponent+"_rook")||currBoard[currRow][currColumn-i].currPiece.type.equals(opponent+"_queen")) {
						 return true;
					 }else break;
				 }
			 }else break;
		}
		
		//right down direction
		for(int i = (currRow + 1), j = (currColumn + 1); i < 8 && j < 8; i++, j++) { 
			if (inBound(board, new Position(i, j))&&!occupiedSamePlayer(board, new Position(i, j))) {
				if(currBoard[i][j].currPiece!=null) {
					if (currBoard[i][j].currPiece.type.equals(opponent+"_bishop")||currBoard[i][j].currPiece.type.equals(opponent+"_queen")) {
						return true;
					}else break;	 
				 }
			}else break;
		}
		
		//left down direction
		for(int i = (currRow + 1), j = (currColumn - 1); i < 8 && j >= 0; i++, j--) { 
			if (inBound(board, new Position(i, j))&&!occupiedSamePlayer(board, new Position(i, j))) {
				if(currBoard[i][j].currPiece!=null) {
					if (currBoard[i][j].currPiece.type.equals(opponent+"_bishop")||currBoard[i][j].currPiece.type.equals(opponent+"_queen")) {
						return true;
					}else break;	 
				}
			}else break;
		}
		
		//right up direction
		for(int i = (currRow - 1), j = (currColumn + 1); i >= 0 && j < 8; i--, j++) { 
			if (inBound(board, new Position(i, j))&&!occupiedSamePlayer(board, new Position(i, j))) {
				if(currBoard[i][j].currPiece!=null) {
					if (currBoard[i][j].currPiece.type.equals(opponent+"_bishop")||currBoard[i][j].currPiece.type.equals(opponent+"_queen")) {
						return true;
					}else break;	 
				 }
			}else break;
		}
		
		//left up direction
		for(int i = (currRow - 1), j = (currColumn - 1); i >= 0 && j >= 0; i--, j--) { 
			if (inBound(board, new Position(i, j))&&!occupiedSamePlayer(board, new Position(i, j))) {
				if(currBoard[i][j].currPiece!=null) {
					if (currBoard[i][j].currPiece.type.equals(opponent+"_bishop")||currBoard[i][j].currPiece.type.equals(opponent+"_queen")) {
						return true;
					}else break;	 
				 }
			}else break;
		}
		
		//knight check
		if (inBound(board, new Position(currRow+2, currColumn+1))&&!occupiedSamePlayer(board, new Position(currRow+2, currColumn+1))) {
			if(currBoard[currRow+2][currColumn+1].currPiece!=null&&currBoard[currRow+2][currColumn+1].currPiece.type.equals(opponent+"_knight")) {
				return true;
			}
		}
		if (inBound(board, new Position(currRow+2, currColumn-1))&&!occupiedSamePlayer(board, new Position(currRow+2, currColumn-1))) {	
			if(currBoard[currRow+2][currColumn-1].currPiece!=null&&currBoard[currRow+2][currColumn-1].currPiece.type.equals(opponent+"_knight")) {
				return true;
			}
		}
		if (inBound(board, new Position(currRow+1, currColumn+2))&&!occupiedSamePlayer(board, new Position(currRow+1, currColumn+2))) {
			if(currBoard[currRow+1][currColumn+2].currPiece!=null&&currBoard[currRow+1][currColumn+2].currPiece.type.equals(opponent+"_knight")) {
				return true;
			}
		}
		if (inBound(board, new Position(currRow+1, currColumn-2))&&!occupiedSamePlayer(board, new Position(currRow+1, currColumn-2))) {
			if(currBoard[currRow+1][currColumn-2].currPiece!=null&&currBoard[currRow+1][currColumn-2].currPiece.type.equals(opponent+"_knight")) {
				return true;
			}
		}
		
		if (inBound(board, new Position(currRow-2, currColumn+1))&&!occupiedSamePlayer(board, new Position(currRow-2, currColumn+1))) {
			if(currBoard[currRow-2][currColumn+1].currPiece!=null&&currBoard[currRow-2][currColumn+1].currPiece.type.equals(opponent+"_knight")) {
				return true;
			}
		}
		if (inBound(board, new Position(currRow-2, currColumn-1))&&!occupiedSamePlayer(board, new Position(currRow-2, currColumn-1))) {
			if(currBoard[currRow-2][currColumn-1].currPiece!=null&&currBoard[currRow-2][currColumn-1].currPiece.type.equals(opponent+"_knight")) {
				return true;
			}
		}
		if (inBound(board, new Position(currRow-1, currColumn-2))&&!occupiedSamePlayer(board, new Position(currRow-1, currColumn-2))) {
			if(currBoard[currRow-1][currColumn-2].currPiece!=null&&currBoard[currRow-1][currColumn-2].currPiece.type.equals(opponent+"_knight")) {
				return true;
			}
		}
		if (inBound(board, new Position(currRow-1, currColumn+2))&&!occupiedSamePlayer(board, new Position(currRow-1, currColumn+2))) {
			if(currBoard[currRow-1][currColumn+2].currPiece!=null&&currBoard[currRow-1][currColumn+2].currPiece.type.equals(opponent+"_knight")) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * boolean function to determine if the king is checkmate. It uses valid moves for
	 * king and determine if there's possible way to get rid of check status
	 * @param potentialPos
	 * @param board
	 * @return
	 */
	public boolean isCheckMate(ArrayList<Block> potentialPos, Board board) {
		int row = this.pos.row;
		int column = this.pos.column;
		if (!isKingChecked(board, new Position(row, column))) {
			return false;
		}
		for (Block tempBlock: potentialPos) {
			row = tempBlock.pos.row;
			column = tempBlock.pos.column;
			if(!isKingChecked(board, new Position(row, column))) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isStaleMate(ArrayList<Block> potentialPos, Board board) {
		int row = this.pos.row;
		int column = this.pos.column;
		if (isKingChecked(board, new Position(row, column))) {
			return false;
		}
		for (Block tempBlock: potentialPos) {
			row = tempBlock.pos.row;
			column = tempBlock.pos.column;
			if(!isKingChecked(board, new Position(row, column))) {
				return false;
			}
		}
		return true;
	} 
	
}
