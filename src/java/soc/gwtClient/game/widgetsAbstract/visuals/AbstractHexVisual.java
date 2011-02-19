package soc.gwtClient.game.widgetsAbstract.visuals;

import soc.common.board.hexes.ChitChangedEvent;
import soc.common.board.hexes.Hex;
import soc.common.board.hexes.ResourceHex;
import soc.common.board.hexes.SeaHex;
import soc.common.board.hexes.TerritoryChangedEvent;
import soc.common.board.ports.PortChangedEvent;
import soc.gwtClient.game.widgetsInterface.visuals.BoardVisual;
import soc.gwtClient.game.widgetsInterface.visuals.ChitVisual;
import soc.gwtClient.game.widgetsInterface.visuals.HexVisual;
import soc.gwtClient.game.widgetsInterface.visuals.PortPossibilitiesVisual;
import soc.gwtClient.game.widgetsInterface.visuals.PortVisual;
import soc.gwtClient.game.widgetsInterface.visuals.TerritoryVisual;

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
        port.setPort(((SeaHex) hex).getPort());
    }

    protected void updateChitVisual()
    {
        chit.setChit(((ResourceHex) hex).getChit());
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
        if (hex instanceof ResourceHex)
        {
            ResourceHex resourceHex = (ResourceHex) hex;
            resourceHex.addChitChangedEventHandler(this);
            chit.setVisible(true);
            updateChitVisual();
        }
        else
        {
            chit.setVisible(false);
        }

        if (hex instanceof SeaHex)
        {
            SeaHex seaHex = (SeaHex) hex;
            seaHex.addPortChangedEventHandler(this);
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
}
