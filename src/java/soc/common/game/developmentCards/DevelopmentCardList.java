package soc.common.game.developmentCards;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import soc.common.game.developmentCards.standard.Monopoly;
import soc.common.game.developmentCards.standard.RoadBuilding;
import soc.common.game.developmentCards.standard.Soldier;
import soc.common.game.developmentCards.standard.VictoryPoint;
import soc.common.game.developmentCards.standard.YearOfPlenty;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

public class DevelopmentCardList implements Iterable<AbstractDevelopmentCard>,
        Serializable
{
    private static final long serialVersionUID = 3119667710831059077L;
    private List<AbstractDevelopmentCard> devCards = new ArrayList<AbstractDevelopmentCard>();
    private transient SimpleEventBus eventBus;

    private void safelyFireEvent(DevelopmentCardsChangedEvent event)
    {
        if (eventBus != null)
        {
            eventBus.fireEvent(event);
        }
    }

    public void add(AbstractDevelopmentCard card)
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

        for (int i = 0; i < 14; i++)
            result.add(new Soldier());

        for (int i = 0; i < 5; i++)
            result.add(new VictoryPoint());

        for (int i = 0; i < 2; i++)
            result.add(new RoadBuilding());

        for (int i = 0; i < 2; i++)
            result.add(new Monopoly());

        for (int i = 0; i < 2; i++)
            result.add(new YearOfPlenty());

        return result;
    }

    public static DevelopmentCardList extended()
    {
        DevelopmentCardList result = new DevelopmentCardList();

        for (int i = 0; i < 19; i++)
            result.add(new Soldier());

        for (int i = 0; i < 5; i++)
            result.add(new VictoryPoint());

        for (int i = 0; i < 3; i++)
            result.add(new RoadBuilding());

        for (int i = 0; i < 3; i++)
            result.add(new Monopoly());

        for (int i = 0; i < 3; i++)
            result.add(new YearOfPlenty());

        return result;
    }

    public ArrayList<AbstractDevelopmentCard> ofType(DevelopmentCard type)
    {
        ArrayList<AbstractDevelopmentCard> result = new ArrayList<AbstractDevelopmentCard>();

        for (AbstractDevelopmentCard devcard : this)
        {
            if (devcard.getClass() == type.getClass())
                result.add(devcard);
        }

        return result;
    }

    @Override
    public Iterator<AbstractDevelopmentCard> iterator()
    {
        return devCards.iterator();
    }

    public int size()
    {
        return devCards.size();
    }

    public DevelopmentCard drawTop()
    {
        // Get top developmentcard
        DevelopmentCard result = devCards.get(devCards.size() - 1);

        // remove it from the deck
        devCards.remove(devCards.size() - 1);

        // return the card
        return result;
    }

    public HandlerRegistration addDevelopmentCardsChangedEventHandler(
            DevelopmentCardsChangedEventHandler handler)
    {
        return getEventBus().addHandler(DevelopmentCardsChangedEvent.TYPE,
                handler);
    }

    private SimpleEventBus getEventBus()
    {
        if (eventBus == null)
        {
            eventBus = new SimpleEventBus();
        }

        return eventBus;
    }

    public boolean contains(DevelopmentCard devCard)
    {
        return devCards.contains(devCard);
    }
}
