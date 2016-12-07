/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.siaq.studiofarma.game;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Angelo
 */
public class PlayerTest {

  public PlayerTest() {
  }

  /**
   * Test of toString method, of class Player.
   */
  @Test
  public void testToString() {
    System.out.println("toString");
    String expResult = "Angelo";
    Player instance = new Player("Angelo");
    String result = instance.toString();
    assertEquals(expResult, result);
  }

  /**
   * Test of equals method, of class Player.
   */
  @Test
  public void testEquals() {
    System.out.println("equals");
    Object obj = new Player("Angelo");;
    Player instance = new Player("Angelo");
    boolean expResult = true;
    boolean result = instance.equals(obj);
    assertEquals(expResult, result);

    obj = new Player("Sonia");
    expResult = false;
    result = instance.equals(obj);
    assertEquals(expResult, result);

  }

}
