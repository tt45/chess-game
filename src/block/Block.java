package block;

import board.Position;
import pieces.Pieces;
import player.Player;

/**
 * block class used to compose the chessboard
 * @author thompsonteng
 *
 */
public class Block {
	public Position pos;
	public Player currOwner;
	public Pieces currPiece;
	
	/**
	 * block constructor
	 * @param pos
	 * @param currOwner
	 * @param piece
	 */
	public Block(Position pos, Player currOwner, Pieces piece) {
		this.pos = pos;
		this.currOwner = currOwner;
		this.currPiece = piece;
	}
	
	/**
	 * return the player of the block
	 * @return
	 */
	public Player getBlockOwner() {
		return currOwner;
	}
	
	/**
	 * return the piece of the block
	 * @return
	 */
	public Pieces getBlockPiece() {
		return currPiece;
	}
	
	/**
	 * update the block with the new player after a move is executed
	 * @param newPiece
	 * @param newOwner
	 */
	public void updateBlock(Pieces newPiece, Player newOwner) {
		currPiece = newPiece;
		currOwner = newOwner;
	}
	
}
