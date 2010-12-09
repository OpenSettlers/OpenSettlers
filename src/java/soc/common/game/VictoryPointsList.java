package soc.common.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.event.shared.SimpleEventBus;

public class VictoryPointsList implements Iterable<IVictoryPointItem>
{
    private List<IVictoryPointItem> points = new ArrayList<IVictoryPointItem>();
    private SimpleEventBus eventBus;
    
    private SimpleEventBus getEventBus()
    {
        if (eventBus == null)
        {
            eventBus = new SimpleEventBus();
        }
        
        return eventBus;
    }
    
    private void safelyFireEvent(VictoryPointsChangedEvent event)
    {
        if (eventBus != null)
        {
            eventBus.fireEvent(event);
        }
    }
    
    public void add(IVictoryPointItem item)
    {
        points.add(item);
        
        safelyFireEvent(new VictoryPointsChangedEvent(item, null));
    }
    
    public void remove(IVictoryPointItem item)
    {
        points.remove(item);
        
        safelyFireEvent(new VictoryPointsChangedEvent(null, item));
    }
    
    /*
     * Returns total amount of victory points in this list 
     */
    public int getTotalPoints()
    {
        int result=0;
        
        for (IVictoryPointItem vp : points)
        {
            result += vp.getVictoryPoints();
        }
        
        return result;
    }

    @Override
    public Iterator<IVictoryPointItem> iterator()
    {
        return points.iterator();
    }
    
    public void addVictoryPointsChangedListener(VictoryPointsChangedEventHandler handler)
    {
        getEventBus().addHandler(VictoryPointsChangedEvent.TYPE, handler);
    }
}
