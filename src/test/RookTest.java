package test;

import java.util.ArrayList;

import block.Block;
import board.Board;
import board.Position;
import junit.framework.TestCase;
import pieces.Pieces;

public class RookTest extends TestCase {
	public void testRook() {
		Block[][] block = null;
		Board board = new Board(block);
		Pieces black_rook = board.getPiece(new Position(0,0));
		System.out.println(black_rook.type);
		ArrayList<Block> potentialPos = black_rook.potentialMoves(board);
		
		board.move(board, board.player1, new Position(1,0), new Position(3,0));
		
		board.move(board, board.player1, new Position(0,0), new Position(2,0));
		board.move(board, board.player1, new Position(2,0), new Position(2,5));
		potentialPos = black_rook.potentialMoves(board);
		black_rook.printValidMove(potentialPos);
	}
}
