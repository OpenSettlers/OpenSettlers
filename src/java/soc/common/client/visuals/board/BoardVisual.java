package soc.common.client.visuals.board;

import java.util.ArrayList;
import java.util.List;

import soc.common.board.Board;
import soc.common.client.behaviour.DefaultBehaviour;
import soc.common.client.behaviour.IInteractionBehaviour;
import soc.common.client.visuals.PieceVisual;
import soc.gwtClient.editor.BehaviourChanged;
import soc.gwtClient.editor.IBehaviourChangedHandler;

public abstract class BoardVisual extends PieceVisual 
    implements IBoardVisual, IBehaviourChangedHandler
{
    protected IInteractionBehaviour editBehaviour = new DefaultBehaviour();
    protected Board board; 
    protected List<IHexVisual> hexVisuals = new ArrayList<IHexVisual>();

    @Override
    public Board getBoard()
    {
        return board;
    }

    @Override
    public IInteractionBehaviour getInteractionBehaviour()
    {
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
    
    @Override
    public void hideTerritories()
    {
        for (IHexVisual hexVisual : hexVisuals)
        {
            hexVisual.getTerritory().setVisible(false);
        }
    }

    @Override
    public void showTerritories()
    {
        for (IHexVisual hexVisual : hexVisuals)
        {
            hexVisual.getTerritory().setVisible(true);
        }
    }
}
