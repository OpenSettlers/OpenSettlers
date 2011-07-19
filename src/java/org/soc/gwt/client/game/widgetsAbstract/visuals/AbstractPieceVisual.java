package org.soc.gwt.client.game.widgetsAbstract.visuals;

import org.soc.common.views.widgetsInterface.visuals.ChitVisual;
import org.soc.common.views.widgetsInterface.visuals.CityVisual;
import org.soc.common.views.widgetsInterface.visuals.HexVisual;
import org.soc.common.views.widgetsInterface.visuals.IslandBonusVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual;
import org.soc.common.views.widgetsInterface.visuals.PirateVisual;
import org.soc.common.views.widgetsInterface.visuals.PointVisual;
import org.soc.common.views.widgetsInterface.visuals.PortVisual;
import org.soc.common.views.widgetsInterface.visuals.RoadVisual;
import org.soc.common.views.widgetsInterface.visuals.RobberVisual;
import org.soc.common.views.widgetsInterface.visuals.ShipVisual;
import org.soc.common.views.widgetsInterface.visuals.SideVisual;
import org.soc.common.views.widgetsInterface.visuals.TerritoryVisual;
import org.soc.common.views.widgetsInterface.visuals.TownVisual;
import org.soc.common.views.widgetsInterface.visuals.WallVisual;

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
    {
    }

    protected void updateVisible()
    {
    }

    protected void updateEnabled()
    {
    }

    @Override
    public boolean isEnabled()
    {
        return enabled;
    }

    @Override
    public boolean isSelected()
    {
        return selected;
    }

    @Override
    public boolean isVisible()
    {
        return visible;
    }

    @Override
    public PieceVisual setEnabled(boolean enabled)
    {
        this.enabled = enabled;

        updateEnabled();

        return this;
    }

    @Override
    public PieceVisual setSelected(boolean selected)
    {
        this.selected = selected;

        updateSelected();

        return this;
    }

    @Override
    public PieceVisual setVisible(boolean visible)
    {
        this.visible = visible;

        updateVisible();

        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.views.widgetsInterface.visuals.PieceVisual#getChitVisual()
     */
    @Override
    public ChitVisual getChitVisual()
    {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.views.widgetsInterface.visuals.PieceVisual#getCityVisual()
     */
    @Override
    public CityVisual getCityVisual()
    {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.views.widgetsInterface.visuals.PieceVisual#getHexVisual()
     */
    @Override
    public HexVisual getHexVisual()
    {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.views.widgetsInterface.visuals.PieceVisual#getIslandBonusVisual
     * ()
     */
    @Override
    public IslandBonusVisual getIslandBonusVisual()
    {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.views.widgetsInterface.visuals.PieceVisual#getPirateVisual()
     */
    @Override
    public PirateVisual getPirateVisual()
    {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.views.widgetsInterface.visuals.PieceVisual#getPointVisual()
     */
    @Override
    public PointVisual getPointVisual()
    {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.views.widgetsInterface.visuals.PieceVisual#getPortVisual()
     */
    @Override
    public PortVisual getPortVisual()
    {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.views.widgetsInterface.visuals.PieceVisual#getRoadVisual()
     */
    @Override
    public RoadVisual getRoadVisual()
    {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.views.widgetsInterface.visuals.PieceVisual#getRobberVisual()
     */
    @Override
    public RobberVisual getRobberVisual()
    {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.views.widgetsInterface.visuals.PieceVisual#getShipVisual()
     */
    @Override
    public ShipVisual getShipVisual()
    {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.views.widgetsInterface.visuals.PieceVisual#getSideVisual()
     */
    @Override
    public SideVisual getSideVisual()
    {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.views.widgetsInterface.visuals.PieceVisual#getTerritoryVisual
     * ()
     */
    @Override
    public TerritoryVisual getTerritoryVisual()
    {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.views.widgetsInterface.visuals.PieceVisual#getTownVisual()
     */
    @Override
    public TownVisual getTownVisual()
    {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.views.widgetsInterface.visuals.PieceVisual#getWallVisual()
     */
    @Override
    public WallVisual getWallVisual()
    {
        return null;
    }

}
