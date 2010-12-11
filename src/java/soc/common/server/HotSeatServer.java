package soc.common.server;

import soc.common.game.Game;
import soc.common.server.actions.ServerActionFactory;

public class HotSeatServer extends AbstractGameServer
{
    public HotSeatServer(IGameServerCallback callback)
    {
        this.callback=callback;
        game = new Game();
        serverActionFactory = new ServerActionFactory();
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
}
