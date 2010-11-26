package soc.common.board.hexes;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;

import soc.common.board.Territory;

@SuppressWarnings("deprecation")
public class RandomHex extends Hex implements ITerritoryHex
{
    private Territory territory;
    private HandlerManager handlerManager = new HandlerManager(this);

    @Override
    public Territory getTerritory()
    {
        // TODO Auto-generated method stub
        return territory;
    }

    @Override
    public ITerritoryHex setTerritory(Territory t)
    {
        // TODO Auto-generated method stub
        territory = t;
        return (ITerritoryHex)this;
    }

    /* (non-Javadoc)
     * @see soc.common.board.hexes.Hex#Copy()
     */
    @Override
    public Hex Copy()
    {
        // TODO Auto-generated method stub
        return new RandomHex();
    }

    /* (non-Javadoc)
     * @see soc.common.board.hexes.Hex#getColor()
     */
    @Override
    public String getColor()
    {
        // TODO Auto-generated method stub
        return "White";
    }

    @Override
    public HandlerRegistration addTerritoryChangedEventHandler(
            TerritoryChangedEventHandler handler)
    {
        return handlerManager.addHandler(TerritoryChangedEvent.TYPE, handler);
    }

    @Override
    public void fireEvent(GwtEvent<?> event)
    {
        handlerManager.fireEvent(event);
    }
}
