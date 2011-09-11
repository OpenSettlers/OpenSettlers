package org.soc.common.game.pieces;

import java.io.Serializable;

public class Stock implements Serializable {
  private static final long serialVersionUID = -5849568638982559515L;
  private TownList towns = new TownList();
  private CityList cities = new CityList();
  private RoadList roads = new RoadList();
  private ShipList ships = new ShipList();

  public TownList towns() {
    return towns;
  }
  public RoadList roads() {
    return roads;
  }
  public CityList cities() {
    return cities;
  }
  public ShipList ships() {
    return ships;
  }
  public boolean hasShips() {
    return ships.size() != 0;
  }
  public boolean hasRoads() {
    return roads.size() != 0;
  }
  public boolean hasCities() {
    return cities.size() != 0;
  }
  public boolean hasTowns() {
    return towns.size() != 0;
  }
}
