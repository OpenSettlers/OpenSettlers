package org.soc.common.core;

import org.soc.common.game.Random;
import org.soc.common.game.Random.ClientRandom;
import org.soc.common.game.actions.ValidateResult;
import org.soc.common.game.actions.ValidateResult.Valid;
import org.soc.common.server.entities.User;
import org.soc.common.server.entities.User.ServerUser;

import com.google.gwt.core.client.GWT;

/** Shares system-wide instances of entities */
public class Core {
  private User serverUser = new ServerUser();
  private static Core instance = new Core();
  private Random random;
  public static ValidateResult valid = new Valid();

  private Core() {
    if (GWT.isClient()) {
      random = new ClientRandom();
    } else
    {
      // TODO: implement for server side
    }
  }
  public static Core get() {
    return instance;
  }
  /** @return the user representing the server. Mainly used for referee */
  public User getServerUser() {
    return serverUser;
  }
  /** @return a random instance */
  public Random random() {
    return random;
  }
}
