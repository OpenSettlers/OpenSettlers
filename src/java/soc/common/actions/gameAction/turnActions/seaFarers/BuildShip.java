package soc.common.actions.gameAction.turnActions.seaFarers;

import soc.common.actions.gameAction.turnActions.AbstractTurnAction;
import soc.common.annotations.SeaFarers;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.internationalization.I18n;

@SeaFarers
public class BuildShip extends AbstractTurnAction
{
    private static final long serialVersionUID = 2879449324222432769L;

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
        return I18n.get().actions().buildShipToDo(player.getUser().getName());
    }

}
