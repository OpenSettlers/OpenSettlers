package soc.common.server;

import soc.common.actions.gameAction.GameAction;
import soc.common.game.Game;
import soc.common.server.actions.ServerActionFactory;
import soc.common.server.randomization.Random;

public interface GameServer extends Server
{
    public JoinResult join(UserCredentials credentials);

    public void sendAction(GameAction action);

    public void leave();

    public Random getRandom();

    public Game getGame();

    public ServerActionFactory createActionFactory();

    public void startGame(Game game);

    public void setBotTurnHandled(boolean handled);
}
