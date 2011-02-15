package soc.common.server;

import soc.common.bots.BotPrincipalImpl;
import soc.common.game.Game;
import soc.common.server.actions.ServerActionFactory;
import soc.common.server.randomization.ClientRandom;

public class HotSeatServer extends AbstractGameServer
{
    public HotSeatServer(GameServerCallback callback)
    {
        super();
        this.callback = callback;

        game = new Game();
        random = new ClientRandom();
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
    public soc.common.server.randomization.Random getRandom()
    {
        return random;
    }

    @Override
    public ServerActionFactory createActionFactory()
    {
        return new ServerActionFactory();
    }

    @Override
    public void startGame(Game game)
    {
        this.game = game;
        botPrincipal = new BotPrincipalImpl(this);
    }

    @Override
    public void setBotTurnHandled(boolean handled)
    {
        botTurnHandled = handled;
    }
}
