package soc.common.client.behaviour.editor;

import soc.common.board.territories.Territory;
import soc.common.client.behaviour.InteractionBehaviour;
import soc.common.client.visuals.PieceVisual;
import soc.common.client.visuals.board.BoardVisual;
import soc.common.client.visuals.board.HexVisual;

public class SetTerritoryBehaviour implements InteractionBehaviour
{
    private Territory territory;

    @Override
    public void clicked(PieceVisual pieceVisual, BoardVisual board)
    {
        if (pieceVisual instanceof HexVisual)
        {
            HexVisual hexVisual = (HexVisual) pieceVisual;
            if (hexVisual.getHex().getTerritory() != null)
            {
                hexVisual.getTerritory().setTerritory(territory);
                hexVisual.getPortPossibilitiesVisual().updatePossibilities();
            }
        }
    }

    /**
     * @return the territory
     */
    public Territory getTerritory()
    {
        return territory;
    }

    /**
     * @param territory
     *            the territory to set
     */
    public SetTerritoryBehaviour setTerritory(Territory territory)
    {
        this.territory = territory;

        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }

    public void mouseEnter(PieceVisual pieceVisual, BoardVisual board)
    {
        if (pieceVisual instanceof HexVisual)
        {
            HexVisual hexVisual = (HexVisual) pieceVisual;
            hexVisual.setSelected(hexVisual.getTerritory() != null);
        }
    }

    @Override
    public void mouseOut(PieceVisual hex, BoardVisual board)
    {
        hex.setSelected(false);
    }

}
