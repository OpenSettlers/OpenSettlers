package soc.common.game.developmentCards;

import com.google.gwt.event.shared.EventHandler;

public interface DevelopmentCardsChangedEventHandler extends EventHandler
{
    public void onDevelopmentCardsChanged(DevelopmentCardsChangedEvent event);
}
