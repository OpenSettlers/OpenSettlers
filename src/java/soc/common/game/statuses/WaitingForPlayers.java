package soc.common.game.statuses;

/*
 * When the game does not have enough players to continue with playing
 */
public class WaitingForPlayers implements GameStatus
{
    private static final long serialVersionUID = 6687601384463272579L;

    /*
     * Returns true; when waiting for players, the game can't continue
     * 
     * @see soc.common.game.statuses.IGameStatus#isGameBlocking()
     */
    @Override
    public boolean isGameBlocking()
    {
        return true;
    }

    @Override
    public String getDescription()
    {
        // TODO fix message
        return "Waiting for players";
    }

}
