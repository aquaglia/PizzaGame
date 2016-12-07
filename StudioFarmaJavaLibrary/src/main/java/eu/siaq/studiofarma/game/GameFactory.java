/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.siaq.studiofarma.game;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Angelo
 */
public class GameFactory {

  public static final int DEFAULT_NUM_PLAYERS = 2;
  public static final int DEFAULT_MIN_PIZZAS = 10;
  public static final int DEFAULT_MAX_PIZZAS = Integer.MAX_VALUE;
  public static final int DEFAULT_MIN_PIZZAS_PER_MOVE = 1;
  public static final int DEFAULT_MAX_PIZZAS_PER_MOVE = 3;

  private int numPlayers;
  private int minPizzas;
  private int maxPizzas;
  private int minPizzasPerMove;
  private int maxPizzasPerMove;

  public GameFactory() {
    this(
            DEFAULT_NUM_PLAYERS,
            DEFAULT_MIN_PIZZAS,
            DEFAULT_MAX_PIZZAS,
            DEFAULT_MIN_PIZZAS_PER_MOVE,
            DEFAULT_MAX_PIZZAS_PER_MOVE
    );
  }

  public GameFactory(
          int numPlayers,
          int minPizzas,
          int maxPizzas,
          int minPizzasPerMove,
          int maxPizzasPerMove
  ) {
    this.numPlayers = numPlayers;
    this.minPizzas = minPizzas;
    this.maxPizzas = maxPizzas;
    this.minPizzasPerMove = minPizzasPerMove;
    this.maxPizzasPerMove = maxPizzasPerMove;
  }

  public Game newGame(List<Player> playerList) {
    int numPizzas = ThreadLocalRandom.current().nextInt(this.getMinPizzas(), this.getMaxPizzas());
    return newGame(
            playerList,
            numPizzas,
            DEFAULT_MIN_PIZZAS_PER_MOVE,
            DEFAULT_MAX_PIZZAS_PER_MOVE
    );
  }

  public Game newGame(
          List<Player> playerList,
          int numPizzas,
          int minPizzasPerMove,
          int maxPizzasPerMove
  ) throws IllegalArgumentException {

    int playerListSize
            = (playerList == null || playerList.isEmpty())
                    ? 0
                    : playerList.size();

    if ((playerListSize != this.getNumPlayers())
            || (playerList == null /*only to avoid the null pointer hint in NetBeans*/)) {
      throw new IllegalArgumentException("The number of players is "
              + playerListSize
              + " while " + this.getNumPlayers() + " were expected.");
    }

    if (new HashSet<>(playerList).size() < playerList.size()) {
      throw new IllegalArgumentException("Please specify a different name for each player."
      );
    }

    if (numPizzas <= this.getMinPizzas()) {
      throw new IllegalArgumentException("The number of pizzas is "
              + numPizzas
              + " while at lieast " + this.getMinPizzas() + " were expected.");
    }

    return new Game(
            playerList,
            numPizzas,
            minPizzasPerMove,
            maxPizzasPerMove
    );
  }

  /**
   * @return the numPlayers
   */
  public int getNumPlayers() {
    return numPlayers;
  }

  /**
   * @param numPlayers the numPlayers to set
   */
  public void setNumPlayers(int numPlayers) {
    this.numPlayers = numPlayers;
  }

  /**
   * @return the minPizzas
   */
  public int getMinPizzas() {
    return minPizzas;
  }

  /**
   * @param minPizzas the minPizzas to set
   */
  public void setMinPizzas(int minPizzas) {
    this.minPizzas = minPizzas;
  }

  /**
   * @return the maxPizzas
   */
  public int getMaxPizzas() {
    return maxPizzas;
  }

  /**
   * @param maxPizzas the maxPizzas to set
   */
  public void setMaxPizzas(int maxPizzas) {
    this.maxPizzas = maxPizzas;
  }

  /**
   * @return the minPizzasPerMove
   */
  public int getMinPizzasPerMove() {
    return minPizzasPerMove;
  }

  /**
   * @param minPizzasPerMove the minPizzasPerMove to set
   */
  public void setMinPizzasPerMove(int minPizzasPerMove) {
    this.minPizzasPerMove = minPizzasPerMove;
  }

  /**
   * @return the maxPizzasPerMove
   */
  public int getMaxPizzasPerMove() {
    return maxPizzasPerMove;
  }

  /**
   * @param maxPizzasPerMove the maxPizzasPerMove to set
   */
  public void setMaxPizzasPerMove(int maxPizzasPerMove) {
    this.maxPizzasPerMove = maxPizzasPerMove;
  }
}
