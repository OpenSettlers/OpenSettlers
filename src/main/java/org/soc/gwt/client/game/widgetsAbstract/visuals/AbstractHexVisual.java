package org.soc.gwt.client.game.widgetsAbstract.visuals;

import org.soc.common.board.hexes.PortChangedEvent;
import org.soc.common.game.hexes.ChitChangedEvent;
import org.soc.common.game.hexes.Hex;
import org.soc.common.game.hexes.TerritoryChangedEvent;
import org.soc.common.views.widgetsInterface.visuals.BoardVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.HexVisual;

public abstract class AbstractHexVisual extends AbstractPieceVisual implements
        HexVisual
{
  protected Hex hex;
  protected boolean darkened;
  protected ChitVisual chit;
  protected TerritoryVisual territory;
  protected PortVisual port;
  protected PortPossibilitiesVisual portPossibilities;
  protected BoardVisual parent;

  protected void updateDarkened()
  {}
  protected void updateHexVisual()
  {}
  protected void updatePortPossibilitiesVisual()
  {}
  protected void updatePortVisual()
  {
    port.setPort(hex.port());
  }
  protected void updateChitVisual()
  {
    chit.setChit(hex.chit());
  }
  protected void updateTerritoryVisual()
  {
    territory.setTerritory(hex.territory());
  }
  @Override public HexVisual setDarkened(boolean darkened)
  {
    this.darkened = darkened;
    updateDarkened();
    return this;
  }
  public AbstractHexVisual(Hex h, BoardVisual parent)
  {
    this.hex = h;
    this.parent = parent;
  }
  public Hex hex() {
    return hex;
  }
  public HexVisual setHex(Hex hex) {
    this.hex = hex;
    // First, update self
    updateHexVisual();
    // Update hex specific visuals
    if (hex.canHaveChit())
    {
      hex.addChitChangedHandler(this);
      chit.setVisible(true);
      updateChitVisual();
    }
    else
    {
      chit.setVisible(false);
    }
    if (hex.canHavePort())
    {
      hex.addPortChangedHandler(this);
      port.setVisible(true);
      updatePortVisual();
    }
    else
    {
      port.setVisible(false);
    }
    hex.addTerritoryChangedHandler(this);
    territory.setVisible(hex.territory() != null);
    updateTerritoryVisual();
    return this;
  }
  @Override public ChitVisual getChitVisual()
  {
    return chit;
  }
  @Override public PortPossibilitiesVisual getPortPossibilitiesVisual()
  {
    return portPossibilities;
  }
  @Override public TerritoryVisual getTerritory()
  {
    return territory;
  }
  @Override public BoardVisual getParent()
  {
    return parent;
  }
  @Override public void onChitChanged(ChitChangedEvent event)
  {
    updateChitVisual();
  }
  @Override public void onTerritoryChanged(TerritoryChangedEvent event)
  {
    updateTerritoryVisual();
  }
  @Override public void onPortChanged(PortChangedEvent event)
  {
    updatePortVisual();
  }
  @Override public HexVisual hexVisual()
  {
    return this;
  }
}
