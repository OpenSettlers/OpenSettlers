package soc.common.game.statuses;

import java.util.List;

import soc.common.actions.gameAction.GameAction;

public class WaitingForTurnActions implements IGameStatus
{
    private List<GameAction> blockingActions;
    
    /*
     * Players may perform turns
     * @see soc.common.game.statuses.IGameStatus#isGameBlocking()
     */
    @Override
    public boolean isGameBlocking()
    {
        return false;
    }
    
    public List<GameAction> getBlockingActions()
    {
        return blockingActions;
    }
    public WaitingForTurnActions setBlockingActions(List<GameAction> blockingActions)
    {
        this.blockingActions=blockingActions;
        return this;
    }

}
