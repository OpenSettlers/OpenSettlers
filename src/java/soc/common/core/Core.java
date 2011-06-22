package soc.common.core;

import soc.common.actions.Valid;
import soc.common.actions.ValidateResult;
import soc.common.server.entities.ServerUser;
import soc.common.server.entities.User;
import soc.common.server.randomization.ClientRandom;
import soc.common.server.randomization.Random;

import com.google.gwt.core.client.GWT;

/*
 * Shares system-wide instances of entities
 */
public class Core
{
    private User serverUser = new ServerUser();
    private static Core instance = new Core();
    private Random random;
    public static ValidateResult valid = new Valid();

    private Core()
    {
        if (GWT.isClient())
        {
            random = new ClientRandom();
        } else
        {
            // TODO: implement for server side
        }
    }

    public static Core get()
    {
        return instance;
    }

    /** @return the user representing the server. Mainly used for referee */
    public User getServerUser()
    {
        return serverUser;
    }

    /** @return a random instance */
    public Random getRandom()
    {
        return random;
    }
}
