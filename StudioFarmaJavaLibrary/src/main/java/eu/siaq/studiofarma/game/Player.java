/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.siaq.studiofarma.game;

import java.util.Objects;

/**
 *
 * @author Angelo
 */
public class Player {

  private String name;

  public Player(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return this.name;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (!Player.class.isAssignableFrom(obj.getClass())) {
      return false;
    }
    final Player other = (Player) obj;
    return !((this.name == null) ? (other.name != null) : !this.name.equals(other.name));
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 83 * hash + Objects.hashCode(this.name);
    return hash;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }
}
