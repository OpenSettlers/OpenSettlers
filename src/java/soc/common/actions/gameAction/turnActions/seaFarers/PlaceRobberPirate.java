package soc.common.actions.gameAction.turnActions.seaFarers;

import soc.common.actions.gameAction.turnActions.AbstractTurnAction;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.internationalization.I18n;

public class PlaceRobberPirate extends AbstractTurnAction
{
    private static final long serialVersionUID = 6371235501183992737L;

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String getToDoMessage()
    {
        return I18n.get().actions().placeRobberToDo(player.getUser().getName());
    }

}
