package soc.common.game;

import com.google.gwt.event.shared.GwtEvent;

public class PlayerOnTurnChangedEvent extends GwtEvent<PlayerOnTurnChangedEventHandler>
{
    public static Type<PlayerOnTurnChangedEventHandler> TYPE = new Type<PlayerOnTurnChangedEventHandler>();
    private Player previousPlayer;
    private Player newPlayer;
    
    public PlayerOnTurnChangedEvent(Player previousPlayer, Player newPlayer)
    {
        super();
        this.previousPlayer = previousPlayer;
        this.newPlayer = newPlayer;
    }

    /**
     * @return the previousPlayer
     */
    public Player getPreviousPlayer()
    {
        return previousPlayer;
    }

    /**
     * @return the newPlayer
     */
    public Player getNewPlayer()
    {
        return newPlayer;
    }

    @Override
    protected void dispatch(PlayerOnTurnChangedEventHandler handler)
    {
        handler.onPlayerOnTurnChanged(this);
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<PlayerOnTurnChangedEventHandler> getAssociatedType()
    {
        return TYPE;
    }

}
