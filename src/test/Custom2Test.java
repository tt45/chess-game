package test;

import java.util.ArrayList;

import block.Block;
import board.Board;
import board.Position;
import junit.framework.TestCase;
import pieces.*;
import player.Player;

public class Custom2Test extends TestCase {
	public void testCustom2() {
		Block[][] block = null;
		Board board = new Board(block);
		
		Player player1 = board.player1;
		Player player2 = board.player2;
		Custom2 black_cus_piece = new Custom2(player1, new Position(2,1), "black_custom2");
		board.chessBoard[2][1].updateBlock(black_cus_piece, player1);
		board.move(board, board.player1, new Position(2,1), new Position(4,3));
		ArrayList<Block> potentialPos = board.chessBoard[4][3].currPiece.potentialMoves(board);
		black_cus_piece.printValidMove(potentialPos);
		board.chessBoard[6][1].updateBlock(null, null);
		board.chessBoard[6][5].updateBlock(null, null);
		
		Pawn white_pawn = new Pawn(player2, new Position(2, 1), "white_pawn");
		board.chessBoard[2][1].updateBlock(white_pawn, player2);
		board.chessBoard[4][3].currPiece.potentialMoves(board);
		board.chessBoard[2][1].updateBlock(null, null);
		
		Pawn white_pawn_2 = new Pawn(player2, new Position(2, 5), "white_pawn");
		board.chessBoard[2][5].updateBlock(white_pawn_2, player2);
		board.chessBoard[4][3].currPiece.potentialMoves(board);
		board.chessBoard[2][5].updateBlock(null, null);
		
		
		
		/*
		
		Custom2 white_cus_piece = new Custom2(player2, new Position(6,1), "black_custom2");
		board.chessBoard[6][1].updateBlock(black_cus_piece, player1);
		board.move(board, board.player1, new Position(6,1), new Position(4,3));
		potentialPos = board.chessBoard[4][3].currPiece.potentialMoves(board);
		black_cus_piece.printValidMove(potentialPos);
		*/
	}
}
