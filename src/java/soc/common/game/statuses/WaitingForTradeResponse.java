package soc.common.game.statuses;


public class WaitingForTradeResponse implements GameStatus
{

    @Override
    public boolean isGameBlocking()
    {
        return false;
    }

    @Override
    public String getDescription()
    {
        // TODO fix message
        return "Waiting for players to respond on a trade offer";
    }

}
