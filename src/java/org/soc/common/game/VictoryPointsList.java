package org.soc.common.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.event.shared.SimpleEventBus;

public class VictoryPointsList implements Iterable<VictoryPointItem>,
                Serializable
{
    private static final long serialVersionUID = 5192452564267423722L;
    private List<VictoryPointItem> points = new ArrayList<VictoryPointItem>();
    private transient SimpleEventBus eventBus = new SimpleEventBus();

    public void add(VictoryPointItem item)
    {
        points.add(item);

        eventBus.fireEvent(new VictoryPointsChangedEvent(item, null));
    }

    public void remove(VictoryPointItem item)
    {
        points.remove(item);

        eventBus.fireEvent(new VictoryPointsChangedEvent(null, item));
    }

    /*
     * Returns total amount of victory points in this list
     */
    public int getTotalPoints()
    {
        int result = 0;

        for (VictoryPointItem vp : points)
            result += vp.getVictoryPoints();

        return result;
    }

    @Override
    public Iterator<VictoryPointItem> iterator()
    {
        return points.iterator();
    }

    public void addVictoryPointsChangedListener(
                    VictoryPointsChangedEventHandler handler)
    {
        eventBus.addHandler(VictoryPointsChangedEvent.TYPE, handler);
    }

    public VictoryPointsList ofType(VictoryPointItem type)
    {
        VictoryPointsList result = new VictoryPointsList();

        for (VictoryPointItem point : points)
            if (point.getClass() == type.getClass())
                result.add(point);

        return result;
    }

    public int size()
    {
        return points.size();
    }

    public VictoryPointItem get(int index)
    {
        return points.get(index);
    }

    public boolean contains(VictoryPointItem item)
    {
        return points.contains(item);
    }
}
