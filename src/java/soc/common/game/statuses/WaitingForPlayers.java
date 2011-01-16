package soc.common.game.statuses;

/*
 * When the game does not have enough players to continue with playing
 */
public class WaitingForPlayers implements GameStatus
{

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
