package soc.common.views.behaviour.board;

import soc.common.board.chits.Chit;
import soc.common.board.chits.Chit2;
import soc.common.board.hexes.Hex;
import soc.common.views.widgetsInterface.visuals.BoardVisual;
import soc.common.views.widgetsInterface.visuals.HexVisual;
import soc.common.views.widgetsInterface.visuals.PieceVisual;

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
