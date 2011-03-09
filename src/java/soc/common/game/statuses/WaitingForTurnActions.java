package soc.common.game.statuses;

import java.util.List;

import soc.common.actions.gameAction.GameAction;

public class WaitingForTurnActions implements GameStatus
{
    private static final long serialVersionUID = 8620951881762820554L;
    private List<GameAction> blockingActions;

    /*
     * Players may perform turns
     * 
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

    public WaitingForTurnActions setBlockingActions(
                    List<GameAction> blockingActions)
    {
        this.blockingActions = blockingActions;
        return this;
    }

    @Override
    public String getDescription()
    {
        // TODO fix message
        return "Waiting for a player to finish turn actions";
    }

    @Override
    public boolean isWaitingForPlayers()
    {
        return false;
    }

    @Override
    public boolean isPlaying()
    {
        return false;
    }

}
