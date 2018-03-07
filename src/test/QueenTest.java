package test;

import java.util.ArrayList;

import block.Block;
import board.Board;
import board.Position;
import junit.framework.TestCase;
import pieces.Pieces;

public class QueenTest extends TestCase {
	public void testQueen() {
		Block[][] block = null;
		Board board = new Board(block);
		Pieces black_queen = board.getPiece(new Position(0,3));
		System.out.println(black_queen.type);
		ArrayList<Block> potentialPos = black_queen.potentialMoves(board);
		//black_queen.printValidMove(potentialPos);
		
		board.move(board, board.player1, new Position(1,2), new Position(3,2));
		System.out.println(board.move(board, board.player1, new Position(0,3), new Position(3,0)));
		potentialPos = black_queen.potentialMoves(board);
		//black_queen.printValidMove(potentialPos);
		
		
		
		Pieces white_queen = board.getPiece(new Position(7,3));
		board.move(board, board.player1, new Position(6,2), new Position(4,2));
		potentialPos = white_queen.potentialMoves(board);
		//white_queen.printValidMove(potentialPos);
		
		board.move(board, board.player1, new Position(7,3), new Position(5,1));
		potentialPos = white_queen.potentialMoves(board);
		white_queen.printValidMove(potentialPos);
		
	}
}
