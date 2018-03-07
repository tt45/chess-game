package pieces;

import java.util.ArrayList;

import block.Block;
import board.Board;
import board.Position;
import player.Player;

/**
 * implements bishop piece including potential moves
 * @author thompsonteng
 *
 */
public class Bishop extends Pieces{
	
	/**
	 * bishop constructor
	 * @param player
	 * @param pos
	 * @param type
	 */
	public Bishop(Player player, Position pos, String type) {
		super(player, pos, type);
	}
	
	/**
	 * potential moves for bishop piece return list of possible moves
	 */
	public ArrayList<Block> potentialMoves(Board board) {
		ArrayList<Block> potentialPos = new ArrayList<Block>();
			
		Pieces currPiece = board.getPiece(pos);
		Block[][] currBoard = board.chessBoard;
		int currRow = currPiece.pos.row;
		int currColumn = currPiece.pos.column;
			
		int opponent = currPiece.player.getOpponent();
		
		bishopPotentialMove(board, potentialPos, currBoard, currRow, currColumn, opponent);
		return potentialPos;
	}

	protected void bishopPotentialMove(Board board, ArrayList<Block> potentialPos, Block[][] currBoard, int currRow,
			int currColumn, int opponent) {
		//right down direction
		for(int i = (currRow + 1), j = (currColumn + 1); i < 8 && j < 8; i++, j++) { 
			if (inBound(board, new Position(i, j))) {
				if (!occupiedSamePlayer(board, new Position(i, j))) {
					potentialPos.add(currBoard[i][j]);
					if (currBoard[i][j].currPiece!=null&&currBoard[i][j].currOwner.pc==opponent) {
						break;
					}
				}else break;
			}
		}
		
		//left down direction
		for(int i = (currRow + 1), j = (currColumn - 1); i < 8 && j >= 0; i++, j--) { 
			if (inBound(board, new Position(i, j))) {
				if (!occupiedSamePlayer(board, new Position(i, j))) {
					potentialPos.add(currBoard[i][j]);
					if (currBoard[i][j].currPiece!=null&&currBoard[i][j].currOwner.pc==opponent) {
						break;
					}
				}else break;
			}
		}
		
		//right up direction
		for(int i = (currRow - 1), j = (currColumn + 1); i >= 0 && j < 8; i--, j++) { 
			if (inBound(board, new Position(i, j))) {
				if (!occupiedSamePlayer(board, new Position(i, j))) {
					potentialPos.add(currBoard[i][j]);
					if (currBoard[i][j].currPiece!=null&&currBoard[i][j].currOwner.pc==opponent) {
						break;
					}
				}else break;
			}
		}
		
		
		//left up direction
		for(int i = (currRow - 1), j = (currColumn - 1); i >= 0 && j >= 0; i--, j--) { 
			if (inBound(board, new Position(i, j))) {
				if (!occupiedSamePlayer(board, new Position(i, j))) {
					potentialPos.add(currBoard[i][j]);
					if (currBoard[i][j].currPiece!=null&&currBoard[i][j].currOwner.pc==opponent) {
						break;
					}
				}else break;
			}
		}
	}
}
