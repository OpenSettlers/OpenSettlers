package soc.common.game.statuses;

import soc.common.actions.Action;

public class WaitingForTradeResponse implements GameStatus
{

    @Override
    public boolean isGameBlocking()
    {
        return false;
    }

}
