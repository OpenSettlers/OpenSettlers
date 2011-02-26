package soc.common.views.behaviour.gameWidget.received;

import soc.common.actions.gameAction.turns.RolledSame;
import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.main.GameWidget;

public class RolledSameBehaviour implements ReceiveGameBehaviour
{
    private GameWidget gameWidget;
    private RolledSame rolledSame;

    public RolledSameBehaviour(GameWidget gameWidget, RolledSame rolledSame)
    {
        super();
        this.gameWidget = gameWidget;
        this.rolledSame = rolledSame;
    }

    @Override
    public boolean endsManually()
    {
        return false;
    }

    @Override
    public void finish()
    {

    }

    @Override
    public void start(GameWidget gameWidget)
    {
        for (int playerID : rolledSame.getSameRolledPlayers())
        {
            GamePlayer player = gameWidget.getGame().getPlayerByID(playerID);
        }
    }

}
