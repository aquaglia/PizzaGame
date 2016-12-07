/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.siaq.studiofarma.game;

import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Angelo
 */
public class GameFactoryTest {

  public GameFactoryTest() {
  }

  /**
   * Test of GameFactory method, of class GameFactory.
   */
  @Test
  public void testGameFactory() {
    System.out.println("GameFactory");
    Player a = new Player("A");
    Player b = new Player("B");
    ArrayList<Player> players = new ArrayList<>(Arrays.asList(new Player[]{a, b}));
    int minPizzas = 10;
    int minPizzasPerMove = 1;
    int maxPizzasPerMove = 3;

    GameFactory instance = new GameFactory();
    boolean exceptionRaised = false;
    try {
      instance.newGame(
              players,
              minPizzas,
              minPizzasPerMove,
              maxPizzasPerMove
      );
    } catch (IllegalArgumentException ex) {
      exceptionRaised = true;
    }
    assertTrue(exceptionRaised);

  }

  /**
   * Test of newGame method, of class GameFactory.
   */
  @Test
  public void testNewGame() {
    System.out.println("newGame");
    Player a = new Player("A");
    Player b = new Player("B");
    ArrayList<Player> players = new ArrayList<>(Arrays.asList(new Player[]{a, b}));
    int numPlayers = 2;
    //int numPizzas = 11;
    int minPizzasPerMove = 1;
    int maxPizzasPerMove = 3;
    int minPizzas = 11;
    int maxPizzas = 100;
    GameFactory instance = new GameFactory(numPlayers, minPizzas, maxPizzas, minPizzasPerMove, maxPizzasPerMove);
    Game expResult = null;
//    Game result = instance.newGame(players, numPizzas, minPizzasPerMove, maxPizzasPerMove);
    Game result = instance.newGame(players);
    for (int i = 1; i <= maxPizzasPerMove; i = (i++ == maxPizzasPerMove) ? 1 : Math.min(i, result.getNumAvailablePizzas())) {
      System.out.println(result.gameStatus());
      try {
        boolean play = result.play(result.getLastEatingPlayer() == null ? players.get(0) : result.peekNextPlayer(), i);
        if (!play) {
          System.out.println("Game over");
          break;
        }
      } catch (IllegalArgumentException ex) {
        System.out.println(ex.getMessage());
      }
    }
    System.out.println(result.gameStatus());

    //assertEquals(expResult, result);
  }

  @Test
  public void testReference() {
    System.out.println("testReference");
    Player a = new Player("A");
    Player b = new Player("B");
    ArrayList<Player> players = new ArrayList<>(Arrays.asList(new Player[]{a, b}));

    int numPlayers = 2;
    int numPizzas = 12;
    int minPizzasPerMove = 1;
    int maxPizzasPerMove = 3;
    int minPizzas = 11;
    int maxPizzas = 100;
    GameFactory instance = new GameFactory(numPlayers, minPizzas, maxPizzas, minPizzasPerMove, maxPizzasPerMove);
    Game expResult = null;
    Game result = instance.newGame(players, numPizzas, minPizzasPerMove, maxPizzasPerMove);
    try {
      System.out.println(result.gameStatus());
      result.play(a, 1);
      System.out.println(result.gameStatus());
      result.play(b, 3);
      System.out.println(result.gameStatus());
      result.play(a, 2);
      System.out.println(result.gameStatus());
      result.play(b, 1);
      System.out.println(result.gameStatus());
      result.play(a, 3);
      System.out.println(result.gameStatus());
      assertFalse(result.play(b, 2));
      System.out.println(result.gameStatus());
    } catch (IllegalArgumentException ex) {
      System.out.println(ex.getMessage());
    }

    //assertEquals(expResult, result);
  }

}
