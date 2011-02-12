package soc.common.board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import soc.common.game.variants.Variant;

public class BoardSettings implements Serializable
{
    private static final long serialVersionUID = 439520618383077352L;

    // Minimum amount of players expected
    private int minPlayers = 3;

    // Maximum amount of players for this board
    private int maxPlayers = 4;

    // max allowed cards in hand when a 7 rolls
    private int maximumCardsInHandWhenSeven = 7;

    // Amount of vp to win on this board
    private int vpToWin = 10;

    // Whether or not players can earn traderoute points to connect territories
    private boolean isUseTradeRoutes = false;

    private boolean useTradeRoutes = false;
    private boolean assignPortsBeforePlacement = false;

    // Are players forced to build a ship after their first town placement?
    private boolean requiresInitialShipsFirstTown = false;

    // Are players forced to build a ship after their secondary town placement
    private boolean requiresInitialShipsSecondTown = false;

    // Name of the designer of the board
    private String designer = "Unknown player";

    // Global unique identifier for this board (UUID/GUID)
    private String id;

    // data fields
    private String name = "New Board";

    private List<Class<? extends Variant>> supportedVariants = new ArrayList<Class<? extends Variant>>();

    public static BoardSettings standard()
    {
        BoardSettings settings = new BoardSettings();

        // default settings are good (for now?)

        return settings;
    }

    /**
     * @return the id
     */
    public String getId()
    {
        return id;
    }

    /**
     * @return the minPlayers
     */
    public int getMinPlayers()
    {
        return minPlayers;
    }

    /**
     * @param minPlayers
     *            the minPlayers to set
     */
    public BoardSettings setMinPlayers(int minPlayers)
    {
        this.minPlayers = minPlayers;
        return this;
    }

    /**
     * @return the maxPlayers
     */
    public int getMaxPlayers()
    {
        return maxPlayers;
    }

    /**
     * @param maxPlayers
     *            the maxPlayers to set
     */
    public BoardSettings setMaxPlayers(int maxPlayers)
    {
        this.maxPlayers = maxPlayers;
        return this;
    }

    /**
     * @return the maximumCardsInHandWhenSeven
     */
    public int getMaximumCardsInHandWhenSeven()
    {
        return maximumCardsInHandWhenSeven;
    }

    /**
     * @param maximumCardsInHandWhenSeven
     *            the maximumCardsInHandWhenSeven to set
     */
    public BoardSettings setMaximumCardsInHandWhenSeven(
            int maximumCardsInHandWhenSeven)
    {
        this.maximumCardsInHandWhenSeven = maximumCardsInHandWhenSeven;

        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }

    /**
     * @return the vpToWin
     */
    public int getVpToWin()
    {
        return vpToWin;
    }

    /**
     * @param vpToWin
     *            the vpToWin to set
     */
    public BoardSettings setVpToWin(int vpToWin)
    {
        this.vpToWin = vpToWin;
        return this;
    }

    public BoardSettings setName(String name)
    {
        this.name = name;
        return this;
    }

    public String getName()
    {
        return name;
    }

    /**
     * @return the designer
     */
    public String getDesigner()
    {
        return designer;
    }

    /**
     * @param designer
     *            the designer to set
     */
    public BoardSettings setDesigner(String designer)
    {
        this.designer = designer;
        return this;
    }

    /**
     * @param id
     *            the id to set
     */
    public BoardSettings setId(String id)
    {
        this.id = id;
        return this;
    }

    public List<Class<? extends Variant>> getSupportedVariants()
    {
        return supportedVariants;
    }

}
