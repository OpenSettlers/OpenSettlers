package soc.common.game;

import com.google.gwt.event.shared.EventHandler;

public interface RoadTokensChangedEventHandler extends EventHandler
{
    public void onRoadTokensChanged(RoadTokensChangedEvent event);
}
