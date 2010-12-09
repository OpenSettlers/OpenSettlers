package soc.common.server;

import com.google.gwt.user.client.Random;

import soc.common.actions.gameAction.GameAction;
import soc.common.game.Game;
import soc.common.server.actions.IServerAction;

public interface IGameServerActionFactory
{
    /*
     * Returns associated server action for given GameAction. Returns null if no
     * assocated serveraction exists.
     */
    public IServerAction getServerAction(GameAction action, Game game, Random random);
}
