package soc.common.game.gamePhase;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.actions.gameAction.GameAction;
import soc.common.game.Game;

public interface GamePhase
{
    public void performAction(AbstractGameAction action, Game game);

    public void start(Game game);

    public GamePhase next(Game game);

    public boolean isAllowed(GameAction action);

    public String getName();

}