package soc.common.game;

import java.io.Serializable;

import soc.common.annotations.Sea3D;
import soc.common.board.Board;
import soc.common.board.BoardSettings;

public class GameSettings implements Serializable
{
    private static final long serialVersionUID = -258750633772212293L;

    // Game boardsettings may be overridden by the user
    // This implies not having a ladder game (where settings should equal
    // original boardsettings)
    private BoardSettings boardSettings = BoardSettings.standard();

    // Whether or not deserts will be replaced by jungles
    @Sea3D
    private boolean replaceDesertWithJungles = false;

    // Whether or not deserts will b replaces by volcanos
    @Sea3D
    private boolean replaceDesertWithVolcanos = false;

    // If selected, the build phase's second town will be a city,
    // and a third road is added
    private boolean tournamentStart = false;

    // Whether or not first round has sevens
    private int noSevensFirstRound = 0;

    // Whether or not robbing from players with 2vp is allowed
    private boolean no2VPPlayersRobbing = false;

    // Whether or not players can trade after they entered the build phase
    private boolean tradingAfterBuilding = false;

    // Players do not see the chitnumbers before initial placement
    // TODO:implement
    private boolean showChitsAfterPlacing = false;

    private int maximumTradesPerTurn = 2;
    private String name = "New Game";
    private int host;

    private String boardGuid;
    private boolean isLadder = true;
    private Board board;

    /**
     * @return the boardSettings
     */
    public BoardSettings getBoardSettings()
    {
        return boardSettings;
    }

    /**
     * @param boardSettings
     *            the boardSettings to set
     */
    public void setBoardSettings(BoardSettings boardSettings)
    {
        this.boardSettings = boardSettings;
    }

    @Sea3D
    public boolean isReplaceDesertWithJungles()
    {
        return replaceDesertWithJungles;
    }

    @Sea3D
    public void setReplaceDesertWithJungles(boolean replaceDesertWithJungles)
    {
        this.replaceDesertWithJungles = replaceDesertWithJungles;
    }

    @Sea3D
    public boolean isReplaceDesertWithVolcanos()
    {
        return replaceDesertWithVolcanos;
    }

    @Sea3D
    public void setReplaceDesertWithVolcanos(boolean replaceDesertWithVolcanos)
    {
        this.replaceDesertWithVolcanos = replaceDesertWithVolcanos;
    }

    public boolean isTournamentStart()
    {
        return tournamentStart;
    }

    public void setTournamentStart(boolean tournamentStart)
    {
        this.tournamentStart = tournamentStart;
    }

    public int getNoSevensFirstRound()
    {
        return noSevensFirstRound;
    }

    public void setNoSevensFirstRound(int noSevensFirstRound)
    {
        this.noSevensFirstRound = noSevensFirstRound;
    }

    public boolean isNo2VPPlayersRobbing()
    {
        return no2VPPlayersRobbing;
    }

    public void setNo2VPPlayersRobbing(boolean no2vpPlayersRobbing)
    {
        no2VPPlayersRobbing = no2vpPlayersRobbing;
    }

    public boolean isTradingAfterBuilding()
    {
        return tradingAfterBuilding;
    }

    public void setTradingAfterBuilding(boolean tradingAfterBuilding)
    {
        this.tradingAfterBuilding = tradingAfterBuilding;
    }

    public boolean isShowChitsAfterPlacing()
    {
        return showChitsAfterPlacing;
    }

    public void setShowChitsAfterPlacing(boolean showChitsAfterPlacing)
    {
        this.showChitsAfterPlacing = showChitsAfterPlacing;
    }

    public int getMaximumTradesPerTurn()
    {
        return maximumTradesPerTurn;
    }

    public void setMaximumTradesPerTurn(int maximumTradesPerTurn)
    {
        this.maximumTradesPerTurn = maximumTradesPerTurn;
    }

    public int getHost()
    {
        return host;
    }

    public void setHost(int host)
    {
        this.host = host;
    }

    public String getBoardGuid()
    {
        return boardGuid;
    }

    public void setBoardGuid(String boardGuid)
    {
        this.boardGuid = boardGuid;
    }

    public boolean isLadder()
    {
        return isLadder;
    }

    public void setLadder(boolean isLadder)
    {
        this.isLadder = isLadder;
    }

    public Board getBoard()
    {
        return board;
    }

    public void setBoard(Board board)
    {
        this.board = board;
    }

    public String getName()
    {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public GameSettings setName(String name)
    {
        this.name = name;

        return this;
    }

}
