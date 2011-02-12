package soc.common.board.pieces.pieceLists;

import java.io.Serializable;

/*
 * Represents a stock of a player.
 */
public class Stock implements Serializable
{
    private static final long serialVersionUID = -5849568638982559515L;
    private TownList towns = new TownList();
    private CityList cities = new CityList();
    private RoadList roads = new RoadList();
    private ShipList ships = new ShipList();

    public TownList getTowns()
    {
        return towns;
    }

    public RoadList getRoads()
    {
        return roads;
    }

    public CityList getCities()
    {
        return cities;
    }

    public ShipList getShips()
    {
        return ships;
    }
}
