package com.ca.training.bad;

public class Piece {
  final PieceType type;
  final boolean isBlack;
  int x;
  int y;

  public Piece(int x, int y, PieceType type, boolean isBlack) {
    this.x = x;
    this.y = y;
    this.type = type;
    this.isBlack = isBlack;
  }

  public enum PieceType {
    CHECKER,
    CHECKER_KING,
    PAWN,
    ROOK,
    KNIGHT,
    BISHOP,
    KING,
    QUEEN
  }
}
