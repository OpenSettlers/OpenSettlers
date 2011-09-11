package org.soc.gwt.client.game.widgetsAbstract.visuals;

import org.soc.common.views.widgetsInterface.visuals.PieceVisual;

/*
 * Base implementation of visual representation of a board object.
 * Implements selected, visible and enabled properties. 
 */
public abstract class AbstractPieceVisual implements PieceVisual
{
  protected boolean visible = true;
  protected boolean selected = false;
  protected boolean enabled = true;

  protected void updateSelected()
  {}
  protected void updateVisible()
  {}
  protected void updateEnabled()
  {}
  @Override public boolean isEnabled()
  {
    return enabled;
  }
  @Override public boolean isSelected()
  {
    return selected;
  }
  @Override public boolean isVisible()
  {
    return visible;
  }
  @Override public PieceVisual setEnabled(boolean enabled)
  {
    this.enabled = enabled;
    updateEnabled();
    return this;
  }
  @Override public PieceVisual setSelected(boolean selected)
  {
    this.selected = selected;
    updateSelected();
    return this;
  }
  @Override public PieceVisual setVisible(boolean visible)
  {
    this.visible = visible;
    updateVisible();
    return this;
  }
  @Override public ChitVisual getChitVisual()
  {
    return null;
  }
  @Override public CityVisual getCityVisual()
  {
    return null;
  }
  @Override public HexVisual hexVisual()
  {
    return null;
  }
  @Override public IslandBonusVisual getIslandBonusVisual()
  {
    return null;
  }
  @Override public PirateVisual getPirateVisual()
  {
    return null;
  }
  @Override public PointVisual getPointVisual()
  {
    return null;
  }
  @Override public PortVisual portVisual()
  {
    return null;
  }
  @Override public RoadVisual getRoadVisual()
  {
    return null;
  }
  @Override public RobberVisual getRobberVisual()
  {
    return null;
  }
  @Override public ShipVisual getShipVisual()
  {
    return null;
  }
  @Override public SideVisual sideVisual()
  {
    return null;
  }
  @Override public TerritoryVisual getTerritoryVisual()
  {
    return null;
  }
  @Override public TownVisual getTownVisual()
  {
    return null;
  }
  @Override public WallVisual getWallVisual()
  {
    return null;
  }
}
