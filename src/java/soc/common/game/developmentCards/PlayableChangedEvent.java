package soc.common.game.developmentCards;

import com.google.gwt.event.shared.GwtEvent;

public class PlayableChangedEvent extends GwtEvent<PlayableChangedEventHandler>
{
    public static Type<PlayableChangedEventHandler> TYPE = new Type<PlayableChangedEventHandler>();
    private boolean playable;

    public PlayableChangedEvent(boolean playable)
    {
        super();
        this.playable = playable;
    }

    /** @return the playable */
    public boolean isPlayable()
    {
        return playable;
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<PlayableChangedEventHandler> getAssociatedType()
    {
        return TYPE;
    }

    @Override
    protected void dispatch(PlayableChangedEventHandler handler)
    {
        handler.onPlayableChanged(this);
    }

}
