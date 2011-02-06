package soc.common.server;

import soc.common.bots.BotPrincipal;
import soc.common.bots.BotPrincipalImpl;
import soc.common.game.Game;
import soc.common.server.actions.ServerActionFactory;
import soc.common.server.random.ClientRandom;

public class HotSeatServer extends AbstractGameServer
{
    public HotSeatServer(GameServerCallback callback)
    {
        super();
        this.callback = callback;

        game = new Game();
        random = new ClientRandom();
        botPrincipal = createBotPrincipal();
    }

    @Override
    public JoinResult join(UserCredentials credentials)
    {
        return null;
    }

    @Override
    public void leave()
    {
    }

    @Override
    public soc.common.server.random.Random getRandom()
    {
        return random;
    }

    @Override
    public ServerActionFactory createActionFactory()
    {
        return new ServerActionFactory();
    }

    @Override
    public BotPrincipal createBotPrincipal()
    {
        return new BotPrincipalImpl(this);
    }
}
