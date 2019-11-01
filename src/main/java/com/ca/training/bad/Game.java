package com.ca.training.bad;

class Game {
  private final GameType type;
  Piece[][] board;

  Game(GameType type) {
    this.type = type;
    this.board = new Piece[8][8];
    switch (type) {
      case CHECKERS:
        setupCheckers();
        break;
      case CHESS:
        setupChess();
        break;
    }
  }

  private void setupCheckers() {
    for (int y = 0; y < 8; y++) {
      if (y > 2 && y < 6) continue;
      int start = y % 2 == 0 ? 0 : 1;
      for (int x = start; x < 8; x += 2) {
        board[x][y] = new Piece(x, y, Piece.PieceType.CHECKER, y < 4);
      }
    }
  }

  private void setupChess() {
    for (int x = 0; x < 8; x++) {
      board[x][1] = new Piece(x, 1, Piece.PieceType.PAWN, true);
      board[x][6] = new Piece(x, 6, Piece.PieceType.PAWN, false);
    }
    board[0][0] = new Piece(0, 0, Piece.PieceType.ROOK, true);
    board[7][0] = new Piece(7, 0, Piece.PieceType.ROOK, true);
    board[0][7] = new Piece(0, 7, Piece.PieceType.ROOK, false);
    board[7][7] = new Piece(7, 7, Piece.PieceType.ROOK, false);
    // plus many more
  }

  void move(int x1, int y1, int x2, int y2) {
    Piece p = board[x1][y1];
    if (p == null) throw new PieceNotFound();
    if (!validMove(p, x2, y2)) throw new IllegalMove();
    board[x2][y2] = p;
    board[x1][y1] = null;
  }

  private boolean validMove(Piece p, int x2, int y2) {
    if (x2 < 0 || x2 > 7 || y2 < 0 || y2 > 7) return false;

    switch (p.type) {
      case CHECKER:
        if (Math.abs(p.x - x2) != 1) return false;
        if (p.isBlack && (p.y - y2) != -1) return false;
        if (!p.isBlack && (p.y - y2) != 1) return false;
        break;
      case CHECKER_KING:
        if (Math.abs(p.x - x2) != 1) return false;
        if (Math.abs(p.y - y2) != 1) return false;
        break;
      case QUEEN:
        // More logic
        break;
        // More cases
    }
    return true;
  }

  enum GameType {
    CHECKERS,
    CHESS
  }
}
