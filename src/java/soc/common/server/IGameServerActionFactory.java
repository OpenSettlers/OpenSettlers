package soc.common.server;

import com.google.gwt.user.client.Random;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.game.Game;
import soc.common.server.actions.ServerAction;

public interface IGameServerActionFactory
{
    /*
     * Returns associated server action for given GameAction. Returns null if no
     * assocated serveraction exists.
     */
    public ServerAction getServerAction(AbstractGameAction action, Game game, Random random);
}
