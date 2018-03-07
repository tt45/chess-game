package test;

import junit.framework.TestCase;

import block.Block;
import board.Board;
import board.Position;
import pieces.*;
import java.util.ArrayList;

public class KnightTest extends TestCase {
	public void testKnight() {
		Block[][] block = null;
		Board board = new Board(block);
		Pieces black_knight = board.getPiece(new Position(0,1));
		System.out.println(black_knight.type);
		ArrayList<Block> potentialPos = black_knight.potentialMoves(board);
		board.move(board, board.player1, new Position(0,1), new Position(2,2));
		black_knight.printValidMove(potentialPos);
		potentialPos = black_knight.potentialMoves(board);
		board.move(board, board.player1, new Position(2,2), new Position(4,3));
		
		Pieces white_knight = board.getPiece(new Position(7,1));
		board.move(board, board.player2, new Position(7,1), new Position(5,2));
		potentialPos = white_knight.potentialMoves(board);
		board.move(board, board.player2, new Position(5,2), new Position(3,3));
	}
}
