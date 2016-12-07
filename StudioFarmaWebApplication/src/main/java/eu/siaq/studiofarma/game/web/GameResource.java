/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.siaq.studiofarma.game.web;

import eu.siaq.studiofarma.game.Game;
import eu.siaq.studiofarma.game.GameFactory;
import eu.siaq.studiofarma.game.Player;
import java.util.ArrayList;
import java.util.Arrays;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author Angelo
 */
@Path("Game")
public class GameResource {

  @Context
  private UriInfo context;
  private static Game currentGame;

  /**
   * Creates a new instance of GameResource
   */
  public GameResource() {
  }

  /**
   * Retrieves representation of an instance of
   * eu.siaq.studiofarma.game.web.GameResource
   *
   * @return an instance of eu.siaq.studiofarma.game.Game
   */
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Response getXml() {
    if (currentGame == null) {
      throw new WebApplicationException(Response.status(Status.BAD_REQUEST)// Or another Status
              .entity("Please enter the name of the two players and click on 'Start game'").build());
    }
    return Response.status(Status.OK)
            .entity(currentGame.gameStatus()).build();
  }

  /**
   * POST method for updating or creating an instance of GameResource
   *
   * @param player1
   * @param player2
   */
  @POST
  @Consumes("application/x-www-form-urlencoded")
  public void post(
          @FormParam("player1") String player1,
          @FormParam("player2") String player2
  ) {
    GameFactory instance = new GameFactory();

    try {
      currentGame
              = instance.newGame(
                      new ArrayList<>(
                              Arrays.asList(
                                      new Player[]{
                                        new Player(player1),
                                        new Player(player2)
                                      })));
    } catch (IllegalArgumentException ex) {
      throw new WebApplicationException(Response.status(Status.BAD_REQUEST)// Or another Status
              .entity(ex.getMessage()).build());
    }

  }

  /**
   * PUT method for updating or creating an instance of GameResource
   *
   * @param player
   * @param numPizzasToEat
   */
  @PUT
  @Consumes("application/x-www-form-urlencoded")
  public void put(
          @FormParam("player") String player,
          @FormParam("numPizzasToEat") int numPizzasToEat
  ) {
    if (currentGame == null) {
      throw new WebApplicationException(Response.status(Status.BAD_REQUEST)// Or another Status
              .entity("Please enter the name of the two players and click on 'Start game'").build());
    }
    try {
      currentGame.play(new Player(player), numPizzasToEat);
    } catch (IllegalArgumentException ex) {
      throw new WebApplicationException(Response.status(Status.BAD_REQUEST)// Or another Status
              .entity(ex.getMessage()).build());
    }

  }
}
