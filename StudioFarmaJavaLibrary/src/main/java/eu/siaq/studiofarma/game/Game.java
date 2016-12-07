/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.siaq.studiofarma.game;

import java.util.List;

/**
 *
 * @author Angelo
 */
public class Game {

  private List<Player> playerList;
  private int initialPizzas;
  private int minPizzasPerMove;
  private int maxPizzasPerMove;

  private int numAvailablePizzas;
  private Player lastPlayer;

  private Player lastEatingPlayer;
  private int lastNumPizzasEaten;

  protected Game(List<Player> playerList, int initialPizzas, int minPizzasPerMove, int maxPizzasPerMove) {
    this.playerList = playerList;
    this.initialPizzas = initialPizzas;
    this.numAvailablePizzas = this.initialPizzas;
    this.minPizzasPerMove = minPizzasPerMove;
    this.maxPizzasPerMove = maxPizzasPerMove;
  }

  public boolean play(Player player, int numPizzasToEat) {
    if (this.getNumAvailablePizzas() == 0) {
      throw new IllegalArgumentException("The game is over because " + lastEatingPlayer + " ate the last one and should be dead by now.");

    }
    if (player == null || !playerList.contains(player)) {
      throw new IllegalArgumentException("Player " + player + " is not in the list of players");
    }

    if (this.getLastPlayer() != null) {
      Player nextPlayer = this.peekNextPlayer();
      if (!player.equals(nextPlayer)) {
        throw new IllegalArgumentException("Player " + player + " cannot play right now, " + peekNextPlayer() + " has to.");
      }
    }

    if (numPizzasToEat < this.getMinPizzasPerMove() || numPizzasToEat > this.getMaxPizzasPerMove()) {
      throw new IllegalArgumentException(
              "Player " + player + " cannot eat " + numPizzasToEat + " pizzas"
              + " because the number of pizzas played in a single move must be between "
              + this.getMinPizzasPerMove()
              + " and "
              + this.getMaxPizzasPerMove()
      );
    }
    if (numPizzasToEat == this.lastNumPizzasEaten && (!player.equals(this.getLastEatingPlayer()))) {
      throw new IllegalArgumentException(
              "Player " + player + " cannot eat " + numPizzasToEat + " pizzas"
              + " because " + this.getLastEatingPlayer() + " has just eaten the same amount "
      );
    }
    this.setLastPlayer(player);
    if (numPizzasToEat > this.getNumAvailablePizzas()) {
      /*
              "Player " + player + " cannot eat " + numPizzasToEat + " pizzas"
              + " because there are only " + this.getNumAvailablePizzas() + " left "
       */
      return true;
    }
    this.setLastEatingPlayer(player);
    this.setNumAvailablePizzas(this.getNumAvailablePizzas() - numPizzasToEat);
    this.lastNumPizzasEaten = numPizzasToEat;
    return this.getNumAvailablePizzas() > 0;

  }

  public Player peekNextPlayer() {
    //TODO: It is not specified who plays first so no default can be applied.
    //This creates a potential null pointer exception if this is the first hand
    int idxNext = this.getPlayerList().indexOf(this.getLastPlayer()) + 1;
    idxNext = (idxNext == this.getPlayerList().size()) ? 0 : idxNext;
    return this.getPlayerList().get(idxNext);
  }

  public String gameStatus() {
    return "lastPlayer: " + nvl(this.getLastPlayer()) + "\n"
            + " - lastEatingPlayer: " + nvl(this.getLastEatingPlayer()) + "\n"
            + " - lastNumPizzasEaten: " + this.getLastNumPizzasEaten() + "\n"
            + " - numAvailablePizzas: " + this.getNumAvailablePizzas() + "\n"
            + (this.getNumAvailablePizzas() > 0
                    ? ""
                    : "GAME OVER, " + this.getLastEatingPlayer() + " should be dead by now");
  }

  public static String nvl(Object s) {
    return s == null ? "(none yet)" : s.toString();
  }

  /**
   * @return the numAvailablePizzas
   */
  public int getNumAvailablePizzas() {
    return numAvailablePizzas;
  }

  /**
   * @param numAvailablePizzas the numAvailablePizzas to set
   */
  public void setNumAvailablePizzas(int numAvailablePizzas) {
    this.numAvailablePizzas = numAvailablePizzas;
  }

  /**
   * @return the lastEatingPlayer
   */
  public Player getLastEatingPlayer() {
    return lastEatingPlayer;
  }

  /**
   * @param lastEatingPlayer the lastEatingPlayer to set
   */
  public void setLastEatingPlayer(Player lastEatingPlayer) {
    this.lastEatingPlayer = lastEatingPlayer;
  }

  /**
   * @return the lastNumPizzasEaten
   */
  public int getLastNumPizzasEaten() {
    return lastNumPizzasEaten;
  }

  /**
   * @param lastNumPizzasEaten the lastNumPizzasEaten to set
   */
  public void setLastNumPizzasEaten(int lastNumPizzasEaten) {
    this.lastNumPizzasEaten = lastNumPizzasEaten;
  }

  /**
   * @return the playerList
   */
  public List<Player> getPlayerList() {
    return playerList;
  }

  /**
   * @param playerList the playerList to set
   */
  public void setPlayerList(List<Player> playerList) {
    this.playerList = playerList;
  }

  /**
   * @return the initialPizzas
   */
  public int getInitialPizzas() {
    return initialPizzas;
  }

  /**
   * @param initialPizzas the initialPizzas to set
   */
  public void setInitialPizzas(int initialPizzas) {
    this.initialPizzas = initialPizzas;
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

  /**
   * @return the lastPlayer
   */
  public Player getLastPlayer() {
    return lastPlayer;
  }

  /**
   * @param lastPlayer the lastPlayer to set
   */
  public void setLastPlayer(Player lastPlayer) {
    this.lastPlayer = lastPlayer;
  }
}
