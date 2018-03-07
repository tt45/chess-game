package test;

import java.util.ArrayList;
import player.Player;

import block.Block;
import board.Board;
import board.Position;
import pieces.Pieces;
import junit.framework.TestCase;

public class PawnTest extends TestCase {
	Block[][] block = null;
	Board board = new Board(block);
	Block this_block = board.getBlock(new Position(4, 5));
	Block[][] chessBoard = board.getBoard();
	int player2Pc = board.player1.getOpponent();
	public void testBoard() throws Exception {
		//System.out.println(board.chessBoard[1][1].currPiece.type);
		assertEquals(board.chessBoard[1][1].currPiece.type, "black_pawn");
		Player player = chessBoard[1][1].getBlockOwner();
		Pieces p = chessBoard[1][1].getBlockPiece();
		
	}
	
	public void testPawn() throws Exception {
		ArrayList<Block> potentialMoves = board.chessBoard[1][7].currPiece.potentialMoves(board);
		for (Block tempBlock: potentialMoves) {
			System.out.print(tempBlock.pos.row);
			System.out.println(tempBlock.pos.column);
		}
		System.out.println(board.player1.pc);
		System.out.println(board.chessBoard[1][1]);
		board.move(board, board.player1, new Position(1,0), new Position(3,0));
		board.move(board, board.player2, new Position(6,0), new Position(4,0));
		
		board.move(board, board.player1, new Position(1,1), new Position(3,1));
		potentialMoves = board.chessBoard[3][1].currPiece.potentialMoves(board);
		for (Block tempBlock: potentialMoves) {
			System.out.print(tempBlock.pos.row);
			System.out.println(tempBlock.pos.column);
		}
		board.move(board, board.player2, new Position(6,1), new Position(4,1));
		
		
		//test capture 
		board.move(board, board.player1, new Position(1,2), new Position(2,2));
		board.move(board, board.player1, new Position(2,2), new Position(3,2));
		
		board.move(board, board.player1, new Position(1,4), new Position(2,4));
		board.move(board, board.player1, new Position(2,4), new Position(3,4));
		
		board.move(board, board.player2, new Position(6,3), new Position(5,3));
		board.move(board, board.player2, new Position(5,3), new Position(4,3));
		System.out.println(board.move(board, board.player2, new Position(4,3), new Position(3,4)));
		
		board.move(board, board.player2, new Position(6,6), new Position(5,6));
		board.move(board, board.player2, new Position(5,6), new Position(4,6));
		
		board.move(board, board.player2, new Position(6,4), new Position(5,4));
		board.move(board, board.player2, new Position(5,4), new Position(4,4));
		
		board.move(board, board.player1, new Position(1,5), new Position(2,5));
		board.move(board, board.player1, new Position(2,5), new Position(3,5));
		
		board.move(board, board.player1, new Position(3,5), new Position(4,4));
		board.move(board, board.player1, new Position(4,4), new Position(4,4));
	}
}
