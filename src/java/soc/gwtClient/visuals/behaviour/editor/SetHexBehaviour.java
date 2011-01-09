package soc.gwtClient.visuals.behaviour.editor;

import soc.common.board.hexes.AbstractHex;
import soc.common.board.hexes.Hex;
import soc.common.board.hexes.ResourceHex;
import soc.common.board.resources.Timber;
import soc.gwtClient.visuals.abstractVisuals.BoardVisual;
import soc.gwtClient.visuals.abstractVisuals.HexVisual;
import soc.gwtClient.visuals.abstractVisuals.PieceVisual;
import soc.gwtClient.visuals.behaviour.BoardBehaviour;

public class SetHexBehaviour implements BoardBehaviour
{
    private AbstractHex hex = new ResourceHex(new Timber());

    /**
     * @return the hex
     */
    public AbstractHex getHex()
    {
        return hex;
    }

    /**
     * @param hex
     *            the hex to set
     */
    public SetHexBehaviour setHex(AbstractHex h)
    {
        this.hex = h;

        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }

    @Override
    public void clicked(PieceVisual pieceVisual, BoardVisual board)
    {
        if (pieceVisual instanceof HexVisual)
        {
            HexVisual hexVisual = (HexVisual) pieceVisual;
            Hex newHex = hex.copy();
            newHex.setLocation(hexVisual.getHex().getLocation());
            board.getBoard().getHexes().set(hexVisual.getHex().getLocation(),
                    newHex);
        }
    }

    @Override
    public void mouseEnter(PieceVisual pieceVisual, BoardVisual board)
    {
        pieceVisual.setSelected(true);
    }

    @Override
    public void mouseOut(PieceVisual pieceVisual, BoardVisual board)
    {
        pieceVisual.setSelected(false);

    }
}
