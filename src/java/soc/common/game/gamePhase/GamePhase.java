package soc.common.game.gamePhase;

import soc.common.actions.gameAction.GameAction;
import soc.common.game.Game;
import soc.common.game.Turn;

public interface GamePhase
{
    public void performAction(GameAction action, Game game);

    public void start(Game game);

    public GamePhase next(Game game);

    public Turn nextTurn(Game game);

    public boolean isAllowed(GameAction action);

    public String getName();

    public String getMessage();

}