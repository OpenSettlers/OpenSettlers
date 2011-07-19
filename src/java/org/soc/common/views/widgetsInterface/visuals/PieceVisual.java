package org.soc.common.views.widgetsInterface.visuals;

/*
 * Base interface of every visual representation of a piece
 * Every piece on the board should be able to be selected, enabled and visibility toggled on/off. 
 */
public interface PieceVisual
{
    public PieceVisual setSelected(boolean selected);

    public boolean isSelected();

    public PieceVisual setEnabled(boolean enabled);

    public boolean isEnabled();

    public PieceVisual setVisible(boolean visible);

    public boolean isVisible();

    public ChitVisual getChitVisual();

    public CityVisual getCityVisual();

    public HexVisual getHexVisual();

    public PointVisual getPointVisual();

    public SideVisual getSideVisual();

    public IslandBonusVisual getIslandBonusVisual();

    public PirateVisual getPirateVisual();

    public PortVisual getPortVisual();

    public RoadVisual getRoadVisual();

    public RobberVisual getRobberVisual();

    public ShipVisual getShipVisual();

    public TerritoryVisual getTerritoryVisual();

    public TownVisual getTownVisual();

    public WallVisual getWallVisual();
}
