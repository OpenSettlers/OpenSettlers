package soc.gwtClient.game.widgetsAbstract.visuals;

import soc.common.board.hexes.AbstractHex;
import soc.common.board.hexes.ChitChangedEvent;
import soc.common.board.hexes.Hex;
import soc.common.board.hexes.TerritoryChangedEvent;
import soc.common.board.ports.PortChangedEvent;
import soc.common.views.widgetsInterface.visuals.BoardVisual;
import soc.common.views.widgetsInterface.visuals.ChitVisual;
import soc.common.views.widgetsInterface.visuals.HexVisual;
import soc.common.views.widgetsInterface.visuals.PortPossibilitiesVisual;
import soc.common.views.widgetsInterface.visuals.PortVisual;
import soc.common.views.widgetsInterface.visuals.TerritoryVisual;

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
    {
    }

    protected void updateHexVisual()
    {
    }

    protected void updatePortPossibilitiesVisual()
    {
    }

    protected void updatePortVisual()
    {
        port.setPort(((AbstractHex) hex).getPort());
    }

    protected void updateChitVisual()
    {
        chit.setChit(((AbstractHex) hex).getChit());
    }

    protected void updateTerritoryVisual()
    {
        territory.setTerritory(hex.getTerritory());
    }

    @Override
    public HexVisual setDarkened(boolean darkened)
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

    /**
     * @return the hex
     */
    public Hex getHex()
    {
        return hex;
    }

    /**
     * @param hex
     *            the hex to set
     */
    public HexVisual setHex(Hex hex)
    {
        this.hex = hex;

        // First, update self
        updateHexVisual();

        // Update hex specific visuals
        if (hex.hasChit())
        {
            hex.addChitChangedEventHandler(this);
            chit.setVisible(true);
            updateChitVisual();
        }
        else
        {
            chit.setVisible(false);
        }

        if (hex.hasPort())
        {
            hex.addPortChangedEventHandler(this);
            port.setVisible(true);
            updatePortVisual();
        }
        else
        {
            port.setVisible(false);
        }

        hex.addTerritoryChangedEventHandler(this);
        territory.setVisible(hex.getTerritory() != null);
        updateTerritoryVisual();

        return this;
    }

    @Override
    public ChitVisual getChitVisual()
    {
        return chit;
    }

    @Override
    public PortPossibilitiesVisual getPortPossibilitiesVisual()
    {
        return portPossibilities;
    }

    @Override
    public TerritoryVisual getTerritory()
    {
        return territory;
    }

    @Override
    public BoardVisual getParent()
    {
        return parent;
    }

    @Override
    public void onChitChanged(ChitChangedEvent event)
    {
        updateChitVisual();
    }

    @Override
    public void onTerritoryChanged(TerritoryChangedEvent event)
    {
        updateTerritoryVisual();
    }

    @Override
    public void onPortChanged(PortChangedEvent event)
    {
        updatePortVisual();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.gwtClient.game.widgetsAbstract.visuals.AbstractPieceVisual#getHexVisual
     * ()
     */
    @Override
    public HexVisual getHexVisual()
    {
        return this;
    }
}
