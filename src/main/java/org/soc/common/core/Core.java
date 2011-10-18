package org.soc.common.core;

import org.soc.common.game.*;
import org.soc.common.game.Random.ClientRandom;
import org.soc.common.game.Random.ServerRandom;
import org.soc.common.game.actions.*;
import org.soc.common.game.actions.ValidateResult.Valid;
import org.soc.common.server.entities.*;
import org.soc.common.server.entities.User.ServerUser;

import com.google.gwt.core.client.*;

/** Shares system-wide instances of entities 
 * 
 * blegh.
 * */
public class Core {
  private User serverUser = new ServerUser();
  private static Core instance = new Core();
  private Random random;
  public static ValidateResult valid = new Valid();

  private Core() {
    if (GWT.isClient()) {
      random = new ClientRandom();
    } else {
      random = new ServerRandom();
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
