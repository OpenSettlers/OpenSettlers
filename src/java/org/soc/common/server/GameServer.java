package org.soc.common.server;

import org.soc.common.actions.gameAction.GameAction;
import org.soc.common.game.Game;
import org.soc.common.server.gameActions.ServerActionFactory;
import org.soc.common.server.randomization.Random;

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

    public boolean hasQueuedBotActions();
}
