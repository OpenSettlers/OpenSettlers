package org.soc.common.views.behaviour.board;

import org.soc.common.board.chits.Chit;
import org.soc.common.board.chits.Chit2;
import org.soc.common.board.hexes.Hex;
import org.soc.common.views.widgetsInterface.visuals.BoardVisual;
import org.soc.common.views.widgetsInterface.visuals.HexVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual;

public class SetChitBehaviour implements BoardBehaviour
{
    private Chit currentChit = new Chit2();

    @Override
    public void clicked(PieceVisual pieceVisual, BoardVisual board)
    {
        HexVisual hexVisual = pieceVisual.getHexVisual();
        if (hexVisual != null)
        {
            Hex hex = hexVisual.getHex();

            if (hex.canHaveResource())
                hex.setChit(currentChit);
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
        return this;
    }

    @Override
    public void mouseEnter(PieceVisual pieceVisual, BoardVisual board)
    {
        HexVisual hexVisual = pieceVisual.getHexVisual();

        if (hexVisual != null && hexVisual.getHex().canHaveResource())
            pieceVisual.setSelected(true);
    }

    @Override
    public void mouseOut(PieceVisual pieceVisual, BoardVisual board)
    {
        pieceVisual.setSelected(false);
    }

}
