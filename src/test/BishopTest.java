package test;

import junit.framework.TestCase;

import block.Block;
import board.Board;
import board.Position;
import pieces.*;
import java.util.ArrayList;

public class BishopTest extends TestCase {
	public void testBishop() {
		Block[][] block = null;
		Board board = new Board(block);
		Pieces black_bishop = board.getPiece(new Position(0,2));
		System.out.println(black_bishop.type);
		Pieces black_queen = board.getPiece(new Position(0,3));
		System.out.println(black_queen.type);
		Pieces black_king = board.getPiece(new Position(0,4));
		System.out.println(black_king.type);
		
		ArrayList<Block> potentialPos = black_bishop.potentialMoves(board);
		black_bishop.printValidMove(potentialPos);
		board.move(board, board.player1, new Position(1,3), new Position(3,3));
		
		board.move(board, board.player1, new Position(0,2), new Position(3,5));
		potentialPos = black_bishop.potentialMoves(board);
		black_bishop.printValidMove(potentialPos);
		
		board.move(board, board.player1, new Position(3,5), new Position(5,3));
		potentialPos = black_bishop.potentialMoves(board);
		
		board.move(board, board.player2, new Position(6,3), new Position(4,3));
		board.move(board, board.player2, new Position(7,2), new Position(4,5));
		
		Pieces white_bishop = board.getPiece(new Position(4,5));
		potentialPos = white_bishop.potentialMoves(board);

		board.move(board, board.player2, new Position(4,5), new Position(2,3));
		potentialPos = white_bishop.potentialMoves(board);
		
	}
}
