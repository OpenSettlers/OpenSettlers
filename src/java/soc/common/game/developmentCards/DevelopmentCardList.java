package soc.common.game.developmentCards;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

public class DevelopmentCardList implements Iterable<DevelopmentCard>
{
    private List<DevelopmentCard> devCards = new ArrayList<DevelopmentCard>();
    private SimpleEventBus eventBus;
    
    private void safelyFireEvent(DevelopmentCardsChangedEvent event)
    {
        if (eventBus != null)
        {
            eventBus.fireEvent(event);
        }
    }
    
    public void add(DevelopmentCard card)
    {
        devCards.add(card);
        safelyFireEvent(new DevelopmentCardsChangedEvent(card, null));
    }
    
    public void remove(DevelopmentCard cardToRemove)
    {
        devCards.remove(cardToRemove);
        safelyFireEvent(new DevelopmentCardsChangedEvent(null, cardToRemove));
    }
    
    public static DevelopmentCardList standard()
    {
        DevelopmentCardList result = new DevelopmentCardList();
        
        for (int i=0; i<14; i++)
            result.add(new Soldier());
        
        for (int i=0; i<5; i++)
            result.add(new VictoryPoint());
        
        for (int i=0; i<2; i++)
            result.add(new RoadBuilding());
        
        for (int i=0; i<2; i++)
            result.add(new Monopoly());
        
        for (int i=0; i<2; i++)
            result.add(new YearOfPlenty());
        
        return result;
    }    
    
    public static DevelopmentCardList extended()
    {
        DevelopmentCardList result = new DevelopmentCardList();
        
        for (int i=0; i<19; i++)
            result.add(new Soldier());
        
        for (int i=0; i<5; i++)
            result.add(new VictoryPoint());
        
        for (int i=0; i<3; i++)
            result.add(new RoadBuilding());
        
        for (int i=0; i<3; i++)
            result.add(new Monopoly());
        
        for (int i=0; i<3; i++)
            result.add(new YearOfPlenty());
        
        return result;
    }

    public ArrayList<DevelopmentCard> ofType(DevelopmentCard type)
    {
        ArrayList<DevelopmentCard> result = new ArrayList<DevelopmentCard>();
        
        for (DevelopmentCard devcard : this)
        {
            if (devcard.getClass() == type.getClass())
                result.add(devcard);
        }
        
        return result;
    }

    @Override
    public Iterator<DevelopmentCard> iterator()
    {
        return devCards.iterator();
    }
    
    public int size()
    {
        return devCards.size();
    }
    
    public DevelopmentCard drawTop()
    {
        // Get top dvelopmentcard
        DevelopmentCard result = devCards.get(devCards.size()-1);
        
        // remove it from the deck
        devCards.remove(devCards.size()-1);
        
        // return the card
        return result;
    }
    
    public HandlerRegistration addDevelopmentCardsChangedEventHandler(DevelopmentCardsChangedEventHandler handler)
    {
        return getEventBus().addHandler(DevelopmentCardsChangedEvent.TYPE, handler);
    }

    private SimpleEventBus getEventBus()
    {
        if (eventBus == null)
        {
            eventBus = new SimpleEventBus();
        }
        
        return eventBus;
    }
}
