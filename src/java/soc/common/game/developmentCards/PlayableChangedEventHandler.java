package soc.common.game.developmentCards;

import com.google.gwt.event.shared.EventHandler;

public interface PlayableChangedEventHandler extends EventHandler
{
    public void onPlayableChanged(PlayableChangedEvent event);
}
