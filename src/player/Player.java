package player;

import pieces.King;

/**
 * player class
 * @author thompsonteng
 *
 */
public class Player {
	
	public int pc; //black is 0 and white is 1
	public King king;
	
	/**
	 * player constructor
	 * @param pc
	 * @param king
	 */
	public Player(int pc, King king) {
		this.pc = pc;
		this.king = king;
	}
	
	/**
	 * return pc of opponent given requesting player
	 * @return
	 */
	public int getOpponent() {
		return (this.pc-1)*(-1);
	}
	
}
