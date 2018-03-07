package test;

import junit.framework.TestCase;

import block.Block;
import board.Board;
import board.Position;
import player.Player;
import pieces.*;
import java.util.ArrayList;

public class Custom1Test extends TestCase {
	public void testCustom1() {
		Block[][] block = null;
		Board board = new Board(block);
		
		Player player1 = board.player1;
		Custom1 black_cus_piece = new Custom1(player1, new Position(2,1), "black_custom1");
		board.chessBoard[2][1].updateBlock(black_cus_piece, player1);
		board.move(board, board.player1, new Position(2,1), new Position(4,1));
		ArrayList<Block> potentialPos = board.chessBoard[4][1].currPiece.potentialMoves(board);
		black_cus_piece.printValidMove(potentialPos);
		//board.move(board, board.player1, new Position(4,1), new Position(4,3));
		//board.move(board, board.player1, new Position(4,3), new Position(6,3));
	}
	
}
