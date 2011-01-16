package soc.common.game.statuses;

public class Playing implements GameStatus
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
        return "Playing the game";
    }

}
