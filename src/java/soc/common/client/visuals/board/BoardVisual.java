package soc.common.client.visuals.board;

import soc.common.board.Board;
import soc.common.client.behaviour.DefaultBehaviour;
import soc.common.client.behaviour.IInteractionBehaviour;
import soc.common.client.visuals.IPieceVisual;
import soc.gwtClient.client.editBehaviour.BehaviourChanged;
import soc.gwtClient.client.editBehaviour.IBehaviourChangedHandler;

public abstract class BoardVisual implements IBoardVisual, IBehaviourChangedHandler
{
    protected IInteractionBehaviour editBehaviour = new DefaultBehaviour();
    protected Board board; 
    protected boolean enabled;
    protected boolean visible;

    @Override
    public Board getBoard()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isEnabled()
    {
        // TODO Auto-generated method stub
        return enabled;
    }

    @Override
    public boolean isSelected()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isVisible()
    {
        // TODO Auto-generated method stub
        return this.visible;
    }

    @Override
    public IPieceVisual setEnabled(boolean enabled)
    {
        this.enabled=enabled;
        
        // TODO Auto-generated method stub
        return this;
    }

    @Override
    public IPieceVisual setSelected(boolean selected)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IPieceVisual setVisible(boolean visible)
    {
        this.visible=visible;
        // TODO Auto-generated method stub
        return this;
    }

    @Override
    public IInteractionBehaviour getInteractionBehaviour()
    {
        // TODO Auto-generated method stub
        return editBehaviour;
    }

    @Override
    public IBoardVisual setInteractionBehaviour(IInteractionBehaviour behaviour)
    {
        editBehaviour=behaviour;
        
        updateBehaviour();
        
        return this;
    }

    private void updateBehaviour()
    {
        
    }

    @Override
    public void onBehaviourChanged(BehaviourChanged behaviourChanged)
    {
        setInteractionBehaviour(behaviourChanged.getBehaviour());
    }

}
