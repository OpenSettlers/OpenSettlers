package soc.common.game.statuses;

/*
 * Status where the game has been ended. 
 */
public class Ended implements GameStatus
{
    private static final long serialVersionUID = -6698546004041080852L;

    @Override
    public boolean isGameBlocking()
    {
        return false;
    }

    @Override
    public String getDescription()
    {
        // TODO fix message
        return "This game has ended.";
    }
}
