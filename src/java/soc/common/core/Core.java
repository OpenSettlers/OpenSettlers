package soc.common.core;

import soc.common.server.entities.ServerUser;
import soc.common.server.entities.User;
import soc.common.server.randomization.ClientRandom;
import soc.common.server.randomization.Random;
import soc.common.ui.ClientFactory;
import soc.common.ui.DefaultClientFactory;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

import com.google.gwt.core.client.GWT;

/*
 * Shares system-wide instances of entities
 */
public class Core
{
    private User serverUser = new ServerUser();
    private static Core instance = new Core();
    private Random random;
    private ClientFactory clientFactory;
    private GameWidget gameWidget;

    private Core()
    {
        if (GWT.isClient())
        {
            random = new ClientRandom();
        }
        else
        {
        }
    }

    public static Core get()
    {
        return instance;
    }

    public void setGameWidget(GameWidget gameWidget)
    {
        this.gameWidget = gameWidget;
        clientFactory = new DefaultClientFactory(gameWidget);
    }

    /**
     * @return the clientFactory
     */
    public ClientFactory getClientFactory()
    {
        return clientFactory;
    }

    /**
     * @return the user representing the server. Mainly used for referee
     */
    public User getServerUser()
    {
        return serverUser;
    }

    /**
     * @return a random instance
     */
    public Random getRandom()
    {
        return random;
    }
}
