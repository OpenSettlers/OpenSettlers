package soc.common.board.settings;

/*
 * When set, the game has a different InitialPlacementGamePhase. Instead of building
 * two towns and two roads, one town, road, city, road is built. The first
 * player starts building a third road consecutively until the last player built a 
 * third road.
 */
public class TournamentStart implements BoardSetting
{
    private boolean tournamentStart;

    @Override
    public void set(BoardSettings boardSettings)
    {
        if (boardSettings.getTournamentStart() == null)
            boardSettings.setTournamentStart(this);

        boardSettings.getTournamentStart().setTournamentStart(tournamentStart);
    }

    /**
     * @return the tournamentStart
     */
    public boolean isTournamentStart()
    {
        return tournamentStart;
    }

    /**
     * @param tournamentStart
     *            the tournamentStart to set
     */
    public TournamentStart setTournamentStart(boolean tournamentStart)
    {
        this.tournamentStart = tournamentStart;
        return this;
    }

}
