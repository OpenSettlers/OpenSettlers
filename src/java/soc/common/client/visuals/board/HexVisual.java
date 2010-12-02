package soc.common.client.visuals.board;

import soc.common.board.hexes.ChitChangedEvent;
import soc.common.board.hexes.ChitChangedEventHandler;
import soc.common.board.hexes.Hex;
import soc.common.board.hexes.ITerritoryHex;
import soc.common.board.hexes.ResourceHex;
import soc.common.board.hexes.SeaHex;
import soc.common.board.hexes.TerritoryChangedEvent;
import soc.common.board.hexes.TerritoryChangedEventHandler;
import soc.common.board.ports.Port;
import soc.common.board.ports.PortChangedEvent;
import soc.common.board.ports.PortChangedEventHandler;
import soc.common.client.visuals.IPieceVisual;
import soc.common.client.visuals.PieceVisual;

public abstract class HexVisual extends PieceVisual implements 
    IHexVisual, ChitChangedEventHandler, TerritoryChangedEventHandler, PortChangedEventHandler
{
    protected Hex hex;

	protected IChitVisual chit;
	protected ITerritoryVisual territory;
	protected IPortVisual port;
	protected IPortPossibilitiesVisual portPossibilities;

    protected IBoardVisual parent;
    
    protected void updateHexVisual() {}
    protected void updatePortPossibilitiesVisual() {}

    protected void updatePortVisual() 
    {
        port.setPort(
                ((SeaHex)hex).getPort());
    }
    
    protected void updateChitVisual() 
    {
        chit.setChit(
                ((ResourceHex)hex).getChit());
    }
    protected void updateTerritoryVisual() 
    {
        territory.setTerritory(
                ((ITerritoryHex)hex).getTerritory());
    }

    public HexVisual(Hex h, IBoardVisual parent)
	{
		this.hex = h;
		this.parent=parent;
	}
	
	/**
	 * @return the hex
	 */
	public Hex getHex() 
	{
		return hex; 
	}

	/**
	 * @param hex the hex to set
	 */
	public IHexVisual setHex(Hex hex) 
	{
		this.hex = hex;

		// First, update self
		updateHexVisual();
		
		// Update hex specific visuals
		if (hex instanceof ResourceHex)
        {
            ResourceHex resourceHex =(ResourceHex)hex;
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
            SeaHex seaHex = (SeaHex)hex;
            seaHex.addPortChangedEventHandler(this);
            port.setVisible(true);
            updatePortVisual();
        }
        else
        {
            port.setVisible(false);
        }
        
        if (hex instanceof ITerritoryHex)
        {
            ITerritoryHex territoryHex = (ITerritoryHex)hex;
            territoryHex.addTerritoryChangedEventHandler(this);
            territory.setVisible(true);
            updateTerritoryVisual();
        }
        else
        {
            territory.setVisible(false);
        }
        
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;		
	}
    
    @Override
    public IChitVisual getChitVisual()
    {
        return chit;
    }
    
    @Override
    public IPortPossibilitiesVisual getPortPossibilitiesVisual()
    {
        return portPossibilities;
    }
    
    @Override
    public ITerritoryVisual getTerritory()
    {
        return territory;
    }
    
    @Override
    public IBoardVisual getParent()
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
