package com.ca.training.bad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.ca.training.bad.Game.GameType.CHECKERS;
import static com.ca.training.bad.Piece.PieceType.CHECKER;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {
  private Game game;

  @BeforeEach
  public void setup() {
    game = new Game(CHECKERS);
  }

  @Test
  public void test_move() {
    game.move(0, 2, 1, 3);
    Piece p = game.board[1][3];
    assertNotEquals(null, p);
    assertNull(game.board[0][2]);
    assertEquals(CHECKER, p.type);
  }

  @Test()
  public void test_oob() {
    assertThrows(
        PieceNotFound.class,
        () -> game.move(4, 5, 5, 6));
  }

  @Test()
  public void test_backwards() {
    assertThrows(
        IllegalMove.class,
        () -> game.move(0, 2, 1, 1));
  }
}
