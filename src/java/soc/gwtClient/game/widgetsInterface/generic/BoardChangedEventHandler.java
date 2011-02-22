package soc.gwtClient.game.widgetsInterface.generic;

import com.google.gwt.event.shared.EventHandler;

public interface BoardChangedEventHandler extends EventHandler
{
    public void onBoardChanged(BoardChangedEvent event);
}
