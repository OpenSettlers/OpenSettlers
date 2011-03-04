package soc.common.lobby;


import com.google.gwt.event.shared.GwtEvent;

public class GameListChangedEvent extends GwtEvent<GameListChangedEventHandler>
{
    public static Type<GameListChangedEventHandler> TYPE = new Type<GameListChangedEventHandler>();
    private GameInfo addedGame;
    private GameInfo removedGame;

    public GameListChangedEvent(GameInfo addedGame, GameInfo removedGame)
    {
        super();
        this.addedGame = addedGame;
        this.removedGame = removedGame;
    }

    /**
     * @return the addedGame
     */
    public GameInfo getAddedGame()
    {
        return addedGame;
    }

    /**
     * @return the removedGame
     */
    public GameInfo getRemovedGame()
    {
        return removedGame;
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<GameListChangedEventHandler> getAssociatedType()
    {
        return TYPE;
    }

    @Override
    protected void dispatch(GameListChangedEventHandler handler)
    {
        handler.onGameListChanged(this);
    }

}
