package org.soc.common.game.board;

import java.util.*;

import org.jgrapht.jgrapht.*;
import org.soc.common.core.GenericList.HasId;
import org.soc.common.core.GenericList.Model;
import org.soc.common.core.Props.IsChild;
import org.soc.common.core.Props.PropertyList.PropertyTypeList;
import org.soc.common.core.property.*;
import org.soc.common.game.*;

/*
 * Represents a route between two cities and/or towns on two different territories.
 */
public interface TradeRoute extends Route, VictoryPointItem
{
  public Territory getFromTerritory();
  public Territory getDestinationTerritory();

  /* Represents a route from Territory A to Territory B. */
  public class TradeRouteImpl implements TradeRoute
  {
    private static final long serialVersionUID = 8219564988646779047L;

    @Override public Territory getDestinationTerritory()
    {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Territory getFromTerritory()
    {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public int length()
    {
      // TODO Auto-generated method stub
      return 0;
    }
    @Override public GamePlayer player()
    {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public List<GraphSide> getEdgeList()
    {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public GraphPoint getEndVertex()
    {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Graph<GraphPoint, GraphSide> getGraph()
    {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public GraphPoint getStartVertex()
    {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public double getWeight()
    {
      // TODO Auto-generated method stub
      return 0;
    }
    @Override public int victoryPoints()
    {
      return 1;
    }
    @Override public Route setPlayer(GamePlayer player)
    {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public boolean validate()
    {
      // TODO Auto-generated method stub
      return false;
    }
    @Override public Model copy() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Integer id() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public HasId setId(Integer id) {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public org.soc.common.core.GenericList.HasId.IdScope scope() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public IsChild getProp(StaticProperty type) {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public PropertyTypeList properties() {
      // TODO Auto-generated method stub
      return null;
    }
  }
}
