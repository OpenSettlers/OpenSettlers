package soc.common.board.pieces.pieceLists;

/*
 * Represents a stock of a player.
 */
public class Stock
{
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
