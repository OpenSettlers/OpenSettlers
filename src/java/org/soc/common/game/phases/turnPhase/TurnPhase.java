package org.soc.common.game.phases.turnPhase;

import java.io.Serializable;

import org.soc.common.actions.gameAction.GameAction;
import org.soc.common.game.Game;
import org.soc.common.views.meta.HasMeta;


public interface TurnPhase extends Serializable, HasMeta
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