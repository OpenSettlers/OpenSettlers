package soc.common.client.visuals.board;

import soc.common.board.Chit;
import soc.common.board.hexes.ChitChangedEvent;
import soc.common.board.hexes.ChitChangedEventHandler;
import soc.common.board.hexes.Hex;
import soc.common.board.hexes.ITerritoryHex;
import soc.common.board.hexes.ResourceHex;
import soc.common.board.hexes.SeaHex;
import soc.common.board.hexes.TerritoryChangedEvent;
import soc.common.board.hexes.TerritoryChangedEventHandler;
import soc.common.board.ports.Port;
import soc.common.client.visuals.IPieceVisual;

public class HexVisual implements IHexVisual, ChitChangedEventHandler, TerritoryChangedEventHandler
{
    protected Hex hex;

	protected IChitVisual chit;
	protected ITerritoryVisual territory;
	protected IPortVisual port;
	protected IPortPossibilitiesVisual portPossibilities;
	
	protected boolean visible = true;
	protected boolean selected = false;
	protected boolean enabled = true;

    protected IBoardVisual parent;
    
    protected void updateHexVisual() {}
    protected void updatePortPossibilitiesVisual() {}
    protected void updateEnabled() {}
    protected void updateSelected() {}
    protected void updateVisible() {}

    protected void updatePortVisual() 
    {
        Port p = ((SeaHex)hex).getPort();
        if (p !=null)
        {
            port.setPort(p);
            port.setVisible(true);
        }
        else
        {
            port.setVisible(false);
        }       
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
	/**
	 * @return the selected
	 */
	public boolean isSelected() 
	{
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	@Override
	public IPieceVisual setSelected(boolean selected)
	{
		this.selected = selected;
	
		updateSelected();
		// Enables fluent interface usage
		// http://en.wikipedia.org/wiki/Fluent_interface
		return this;
	}
	

    @Override
    public IPieceVisual setVisible(boolean visible)
    {
        this.visible = visible;
        
        this.updateVisible();
        
        return this;
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
		
        // TODO: implement event handler
        if (hex instanceof ResourceHex)
        {
            ResourceHex resourceHex =(ResourceHex)hex;
            resourceHex.addChitChangedEventHandler(this);
            updateChitVisual();
        }

        if (hex instanceof SeaHex)
            updatePortVisual();
        
        if (hex instanceof ITerritoryHex)
        {
            ITerritoryHex territoryHex = (ITerritoryHex)hex;
            territoryHex.addTerritoryChangedEventHandler(this);
            updateTerritoryVisual();
        }
        
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;		
	}
    
    @Override
    public boolean isEnabled()
    {
        return enabled;
    }
    
    @Override
    public IPieceVisual setEnabled(boolean enabled)
    {
        this.enabled=enabled;
        
        updateEnabled();
        
        return this;
    }
    
    @Override
    public boolean isVisible()
    {
        return visible;
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

}
