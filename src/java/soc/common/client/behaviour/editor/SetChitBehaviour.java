package soc.common.client.behaviour.editor;

import soc.common.board.Chit;
import soc.common.board.hexes.Hex;
import soc.common.board.hexes.ResourceHex;
import soc.common.client.behaviour.InteractionBehaviour;
import soc.common.client.visuals.PieceVisual;
import soc.common.client.visuals.board.BoardVisual;
import soc.common.client.visuals.board.HexVisual;

public class SetChitBehaviour implements InteractionBehaviour
{
    private Chit currentChit = new Chit(2);

    @Override
    public void clicked(PieceVisual pieceVisual, BoardVisual board)
    {
        if (pieceVisual instanceof HexVisual)
        {
            HexVisual hexVisual = (HexVisual) pieceVisual;
            Hex hex = hexVisual.getHex();
            if (hex instanceof ResourceHex)
            {
                ResourceHex resourceHex = (ResourceHex) hex;
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
     * @param currentChit
     *            the currentChit to set
     */
    public SetChitBehaviour setCurrentChit(Chit currentChit)
    {
        this.currentChit = currentChit;

        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }

    @Override
    public void mouseEnter(PieceVisual pieceVisual, BoardVisual board)
    {
        if (pieceVisual instanceof HexVisual)
        {
            HexVisual hexVisual = (HexVisual) pieceVisual;
            if (hexVisual.getHex() instanceof ResourceHex)
            {
                pieceVisual.setSelected(true);
            }
        }
    }

    @Override
    public void mouseOut(PieceVisual pieceVisual, BoardVisual board)
    {
        pieceVisual.setSelected(false);
    }

}
