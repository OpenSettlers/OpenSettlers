package soc.common.client.behaviour.editor;

import soc.common.board.Chit;
import soc.common.board.hexes.AbstractHex;
import soc.common.board.hexes.ResourceHex;
import soc.common.client.behaviour.IInteractionBehaviour;
import soc.common.client.visuals.IPieceVisual;
import soc.common.client.visuals.board.IBoardVisual;
import soc.common.client.visuals.board.IHexVisual;

public class SetChitBehaviour implements IInteractionBehaviour
{
    private Chit currentChit = new Chit(2);

    @Override
    public void clicked(IPieceVisual pieceVisual, IBoardVisual board)
    {
        if (pieceVisual instanceof IHexVisual)
        {
            IHexVisual hexVisual = (IHexVisual)pieceVisual;
            AbstractHex hex = hexVisual.getHex();
            if (hex instanceof ResourceHex)
            {
                ResourceHex resourceHex = (ResourceHex)hex;
                resourceHex.setChit(currentChit);
            }
        }
    }

    /**
     * @return the currentChit
     */
    public Chit getCurrentChit()
    {
        return currentChit;
    }

    /**
     * @param currentChit the currentChit to set
     */
    public SetChitBehaviour setCurrentChit(Chit currentChit)
    {
        this.currentChit = currentChit;
    
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }

    @Override
    public void mouseEnter(IPieceVisual pieceVisual, IBoardVisual board)
    {
        if (pieceVisual instanceof IHexVisual)
        {
            IHexVisual hexVisual = (IHexVisual)pieceVisual;
            if (hexVisual.getHex() instanceof ResourceHex)
            {
                pieceVisual.setSelected(true);
            }
        }
    }

    @Override
    public void mouseOut(IPieceVisual pieceVisual, IBoardVisual board)
    {
        pieceVisual.setSelected(false);
    }

}
