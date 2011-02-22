package soc.common.game.gamePhase.turnPhase;

import soc.common.actions.gameAction.GameAction;
import soc.common.game.Game;

public interface TurnPhase
{

    public TurnPhase next();

    public TurnPhase processAction(GameAction action, Game game);

    public boolean isAllowed(GameAction action);

    public String getName();

    public boolean isBeforeDiceRoll();

    public boolean isDiceRoll();

    public boolean isTrading();

    public boolean isBuilding();

    public String getMessage();

}