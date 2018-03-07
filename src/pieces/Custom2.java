package pieces;

import java.util.ArrayList;

import block.Block;
import board.Board;
import board.Position;
import player.Player;

/**
 * this piece can only move right up, right down, left up and left down for two possible blocks
 * @author thompsonteng
 *
 */
public class Custom2 extends Pieces {
	
	/**
	 * custom2 constructor
	 * @param player
	 * @param pos
	 * @param type
	 */
	public Custom2(Player player, Position pos, String type) {
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
		
		//right down direction
		for(int i = (currRow + 1), j = (currColumn + 1); i < (currRow+3) && j < (currColumn+3); i++, j++) { 
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
		for(int i = (currRow + 1), j = (currColumn - 1); i < (currRow+3) && j >= (currColumn-3); i++, j--) { 
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
		for(int i = (currRow - 1), j = (currColumn + 1); i >= (currRow-3) && j < (currColumn+3); i--, j++) { 
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
		for(int i = (currRow - 1), j = (currColumn - 1); i >= (currRow-3) && j >= (currColumn-3); i--, j--) { 
			if (inBound(board, new Position(i, j))) {
				if (!occupiedSamePlayer(board, new Position(i, j))) {
					potentialPos.add(currBoard[i][j]);
					if (currBoard[i][j].currPiece!=null&&currBoard[i][j].currOwner.pc==opponent) {
						break;
					}
				}else break;
			}
		}
		return potentialPos;
		}
}
