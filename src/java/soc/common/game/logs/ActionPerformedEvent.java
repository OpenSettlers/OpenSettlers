package soc.common.game.logs;

import soc.common.actions.gameAction.GameAction;

import com.google.gwt.event.shared.GwtEvent;

public class ActionPerformedEvent extends GwtEvent<ActionPerformedEventHandler>
{
    public static Type<ActionPerformedEventHandler> TYPE = new Type<ActionPerformedEventHandler>(); 
    private GameAction performedAction;
    
    public ActionPerformedEvent(GameAction performedAction)
    {
        super();
        this.performedAction = performedAction;
    }

    /**
     * @return the performedAction
     */
    public GameAction getPerformedAction()
    {
        return performedAction;
    }

    @Override
    protected void dispatch(ActionPerformedEventHandler arg0)
    {
        arg0.onActionPerformed(this);
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<ActionPerformedEventHandler> getAssociatedType()
    {
        return TYPE;
    }

}
