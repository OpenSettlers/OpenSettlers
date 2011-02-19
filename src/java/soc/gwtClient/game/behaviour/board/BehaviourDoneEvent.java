package soc.gwtClient.game.behaviour.board;

import soc.gwtClient.game.behaviour.gameBoard.GameBoardBehaviour;

import com.google.gwt.event.shared.GwtEvent;

public class BehaviourDoneEvent extends GwtEvent<BehaviourDoneEventHandler>
{
    public static Type<BehaviourDoneEventHandler> TYPE = new Type<BehaviourDoneEventHandler>();
    private GameBoardBehaviour gameBehaviour;
    private boolean success = true;

    public BehaviourDoneEvent(GameBoardBehaviour gameBehaviour, boolean success)
    {
        super();
        this.gameBehaviour = gameBehaviour;
        this.success = success;
    }

    /**
     * @return the gameBehaviour
     */
    public GameBoardBehaviour getGameBehaviour()
    {
        return gameBehaviour;
    }

    /**
     * @return the success
     */
    public boolean isSuccess()
    {
        return success;
    }

    @Override
    protected void dispatch(BehaviourDoneEventHandler handler)
    {
        handler.onBehaviourDone(this);
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<BehaviourDoneEventHandler> getAssociatedType()
    {
        return TYPE;
    }

}
