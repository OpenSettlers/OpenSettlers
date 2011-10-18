package org.soc.common.game.pieces;

import java.io.*;

import org.soc.common.game.pieces.Cities.MutableCityList;
import org.soc.common.game.pieces.Roads.MutableRoadList;
import org.soc.common.game.pieces.Ships.MutableShipList;
import org.soc.common.game.pieces.Ships.MutableShipListImpl;
import org.soc.common.game.pieces.Towns.MutableTownList;

public class Stock
        implements
        Serializable {
  private MutableTownList towns = new MutableTownList.Impl();
  private MutableCityList cities = new MutableCityList.Impl();
  private MutableRoadList roads = new MutableRoadList.Impl();
  private MutableShipList ships = new MutableShipListImpl();

  public MutableTownList towns() {
    return towns;
  }
  public MutableRoadList roads() {
    return roads;
  }
  public MutableCityList cities() {
    return cities;
  }
  public MutableShipList ships() {
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
