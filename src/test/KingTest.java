package test;

import java.util.ArrayList;

import block.Block;
import board.Board;
import board.Position;
import player.Player;
import pieces.King;
import pieces.Knight;
import pieces.Pieces;
import pieces.Queen;
import junit.framework.TestCase;

public class KingTest extends TestCase {
	public void testKing() {
		//new board test
		Block[][] chessboard = null;
		Board new_board = new Board(chessboard);
		chessboard = new_board.chessBoard;
		Player player1 = new_board.player1;
		Player player2 = new_board.player2;
		
		
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				chessboard[i][j].updateBlock(null, null);
			}
		}
		
		King black_king = new King(player1, new Position(3, 3), "black_king");
		Position king_pos = black_king.pos;
		chessboard[3][3].updateBlock(black_king, player1);
		player1.king = black_king;
		
		Queen white_queen = new Queen(player2, new Position(1, 1), "white_queen");
		chessboard[1][1].updateBlock(white_queen, player2);
		System.out.println(player1.king.isKingChecked(new_board, king_pos));
		chessboard[1][1].updateBlock(null, null);
		
		Queen white_queen_2 = new Queen(player2, new Position(3, 1), "white_queen");
		chessboard[3][1].updateBlock(white_queen_2, player2);
		System.out.println(player1.king.isKingChecked(new_board, king_pos));
		chessboard[3][1].updateBlock(null, null);
		
		Queen white_queen_3 = new Queen(player2, new Position(5, 1), "white_queen");
		chessboard[5][1].updateBlock(white_queen_3, player2);
		System.out.println(player1.king.isKingChecked(new_board, king_pos));
		chessboard[5][1].updateBlock(null, null);
		
		Queen white_queen_4 = new Queen(player2, new Position(5, 3), "white_queen");
		chessboard[5][3].updateBlock(white_queen_4, player2);
		System.out.println(player1.king.isKingChecked(new_board, king_pos));
		chessboard[5][3].updateBlock(null, null);
		
		Queen white_queen_5 = new Queen(player2, new Position(5, 5), "white_queen");
		chessboard[5][5].updateBlock(white_queen_5, player2);
		System.out.println(player1.king.isKingChecked(new_board, king_pos));
		chessboard[5][5].updateBlock(null, null);
		
		Queen white_queen_6 = new Queen(player2, new Position(3, 5), "white_queen");
		chessboard[3][5].updateBlock(white_queen_6, player2);
		System.out.println(player1.king.isKingChecked(new_board, king_pos));
		chessboard[3][5].updateBlock(null, null);
		
		Queen white_queen_7 = new Queen(player2, new Position(1, 5), "white_queen");
		chessboard[1][5].updateBlock(white_queen_7, player2);
		System.out.println(player1.king.isKingChecked(new_board, king_pos));
		chessboard[1][5].updateBlock(null, null);
		
		
		Knight white_knight = new Knight(player2, new Position(1, 2), "white_knight");
		chessboard[1][2].updateBlock(white_knight, player2);
		System.out.println(player1.king.isKingChecked(new_board, king_pos));
		chessboard[1][2].updateBlock(null, null);
		
		Knight white_knight_2 = new Knight(player2, new Position(2, 1), "white_knight");
		chessboard[2][1].updateBlock(white_knight_2, player2);
		System.out.println(player1.king.isKingChecked(new_board, king_pos));
		chessboard[2][1].updateBlock(null, null);
		
		Knight white_knight_3 = new Knight(player2, new Position(5, 2), "white_knight");
		chessboard[5][2].updateBlock(white_knight_3, player2);
		System.out.println(player1.king.isKingChecked(new_board, king_pos));
		chessboard[5][2].updateBlock(null, null);
		
		///////////////////////////
		ArrayList<Block> potentialPos = player1.king.potentialMoves(new_board);
		System.out.println(player1.king.isCheckMate(potentialPos, new_board));
		
		Knight white_knight_4 = new Knight(player2, new Position(4, 1), "white_knight");
		chessboard[4][1].updateBlock(white_knight_4, player2);
		System.out.println(player1.king.isKingChecked(new_board, king_pos));
		potentialPos = player1.king.potentialMoves(new_board);
		System.out.println(player1.king.isCheckMate(potentialPos, new_board));
		System.out.println(player1.king.isStaleMate(potentialPos, new_board));
		chessboard[4][1].updateBlock(null, null);
		System.out.println(player1.king.isStaleMate(potentialPos, new_board));
		
		Knight white_knight_5 = new Knight(player2, new Position(5, 4), "white_knight");
		chessboard[5][4].updateBlock(white_knight_5, player2);
		System.out.println(player1.king.isKingChecked(new_board, king_pos));
		chessboard[5][4].updateBlock(null, null);
		
		Knight white_knight_6 = new Knight(player2, new Position(4, 5), "white_knight");
		chessboard[4][5].updateBlock(white_knight_6, player2);
		System.out.println(player1.king.isKingChecked(new_board, king_pos));
		chessboard[4][5].updateBlock(null, null);
		
		Knight white_knight_7 = new Knight(player2, new Position(2, 5), "white_knight");
		chessboard[2][5].updateBlock(white_knight_7, player2);
		System.out.println(player1.king.isKingChecked(new_board, king_pos));
		chessboard[2][5].updateBlock(null, null);
		
		Knight white_knight_8 = new Knight(player2, new Position(1, 4), "white_knight");
		chessboard[1][4].updateBlock(white_knight_8, player2);
		System.out.println(player1.king.isKingChecked(new_board, king_pos));
		chessboard[1][4].updateBlock(null, null);
		
		chessboard[3][3].updateBlock(null, null);
		
		//white king
		King white_king = new King(player2, new Position(3, 3), "white_king");
		king_pos = black_king.pos;
		chessboard[3][3].updateBlock(white_king, player2);
		player2.king = white_king;
		
		Queen black_queen = new Queen(player1, new Position(1, 1), "black_queen");
		chessboard[1][1].updateBlock(black_queen, player1);
		System.out.println(player2.king.isKingChecked(new_board, king_pos));
		chessboard[1][1].updateBlock(null, null);
		
		Queen black_queen_2 = new Queen(player1, new Position(3, 1), "black_queen");
		chessboard[3][1].updateBlock(black_queen_2, player1);
		System.out.println(player2.king.isKingChecked(new_board, king_pos));
		chessboard[3][1].updateBlock(null, null);
		
		Queen black_queen_3 = new Queen(player1, new Position(5, 1), "black_queen");
		chessboard[5][1].updateBlock(black_queen_3, player1);
		System.out.println(player2.king.isKingChecked(new_board, king_pos));
		chessboard[5][1].updateBlock(null, null);
		
		Queen black_queen_4 = new Queen(player1, new Position(5, 3), "black_queen");
		chessboard[5][3].updateBlock(black_queen_4, player1);
		System.out.println(player2.king.isKingChecked(new_board, king_pos));
		chessboard[5][3].updateBlock(null, null);
		
		Queen black_queen_5 = new Queen(player1, new Position(5, 5), "black_queen");
		chessboard[5][5].updateBlock(black_queen_5, player1);
		System.out.println(player2.king.isKingChecked(new_board, king_pos));
		chessboard[5][5].updateBlock(null, null);
		
		Queen black_queen_6 = new Queen(player1, new Position(3, 5), "black_queen");
		chessboard[3][5].updateBlock(black_queen_6, player1);
		System.out.println(player2.king.isKingChecked(new_board, king_pos));
		chessboard[3][5].updateBlock(null, null);
		
		Queen black_queen_7 = new Queen(player1, new Position(1, 5), "black_queen");
		chessboard[1][5].updateBlock(black_queen_7, player1);
		System.out.println(player2.king.isKingChecked(new_board, king_pos));
		chessboard[1][5].updateBlock(null, null);
		
		
		Knight black_knight = new Knight(player1, new Position(1, 2), "black_knight");
		chessboard[1][2].updateBlock(black_knight, player1);
		System.out.println(player2.king.isKingChecked(new_board, king_pos));
		chessboard[1][2].updateBlock(null, null);
		
		Knight black_knight_2 = new Knight(player1, new Position(2, 1), "black_knight");
		chessboard[2][1].updateBlock(black_knight_2, player1);
		System.out.println(player2.king.isKingChecked(new_board, king_pos));
		chessboard[2][1].updateBlock(null, null);
		
		Knight black_knight_3 = new Knight(player1, new Position(5, 2), "black_knight");
		chessboard[5][2].updateBlock(black_knight_3, player1);
		System.out.println(player2.king.isKingChecked(new_board, king_pos));
		chessboard[5][2].updateBlock(null, null);
		
		Knight black_knight_4 = new Knight(player1, new Position(4, 1), "black_knight");
		chessboard[4][1].updateBlock(black_knight_4, player1);
		System.out.println(player2.king.isKingChecked(new_board, king_pos));
		chessboard[4][1].updateBlock(null, null);
		
		Knight black_knight_5 = new Knight(player1, new Position(5, 4), "black_knight");
		chessboard[5][4].updateBlock(black_knight_5, player1);
		System.out.println(player2.king.isKingChecked(new_board, king_pos));
		chessboard[5][4].updateBlock(null, null);
		
		Knight black_knight_6 = new Knight(player1, new Position(4, 5), "black_knight");
		chessboard[4][5].updateBlock(black_knight_6, player1);
		System.out.println(player2.king.isKingChecked(new_board, king_pos));
		chessboard[4][5].updateBlock(null, null);
		
		Knight black_knight_7 = new Knight(player1, new Position(2, 5), "black_knight");
		chessboard[2][5].updateBlock(black_knight_7, player1);
		System.out.println(player2.king.isKingChecked(new_board, king_pos));
		chessboard[2][5].updateBlock(null, null);
		
		Knight black_knight_8 = new Knight(player1, new Position(1, 4), "black_knight");
		chessboard[1][4].updateBlock(black_knight_8, player1);
		System.out.println(player2.king.isKingChecked(new_board, king_pos));
		potentialPos = chessboard[3][3].currPiece.potentialMoves(new_board);
		System.out.println(player2.king.isStaleMate(potentialPos, new_board));
		white_king.printValidMove(potentialPos);
		chessboard[1][4].updateBlock(null, null);
		
		chessboard[3][3].updateBlock(null, null);
	}
}
