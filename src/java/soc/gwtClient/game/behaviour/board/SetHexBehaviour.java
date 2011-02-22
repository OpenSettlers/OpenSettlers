package soc.gwtClient.game.behaviour.board;

import soc.common.board.hexes.Hex;
import soc.common.board.hexes.TimberHex;
import soc.gwtClient.game.widgetsInterface.visuals.BoardVisual;
import soc.gwtClient.game.widgetsInterface.visuals.HexVisual;
import soc.gwtClient.game.widgetsInterface.visuals.PieceVisual;

public class SetHexBehaviour implements BoardBehaviour
{
    private Hex hex = new TimberHex();

    /**
     * @return the hex
     */
    public Hex getHex()
    {
        return hex;
    }

    /**
     * @param hex
     *            the hex to set
     */
    public SetHexBehaviour setHex(Hex h)
    {
        this.hex = h;
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
