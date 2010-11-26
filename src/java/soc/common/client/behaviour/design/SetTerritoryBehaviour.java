package soc.common.client.behaviour.design;

import soc.common.board.*;
import soc.common.board.hexes.ITerritoryHex;
import soc.common.client.behaviour.IInteractionBehaviour;
import soc.common.client.visuals.IPieceVisual;
import soc.common.client.visuals.board.IBoardVisual;
import soc.common.client.visuals.board.IHexVisual;

public class SetTerritoryBehaviour implements IInteractionBehaviour
{
    private Territory territory;
    
    @Override
    public void clicked(IPieceVisual pieceVisual, IBoardVisual board)
    {
        if (pieceVisual instanceof IHexVisual)
        {
            IHexVisual hexVisual = (IHexVisual)pieceVisual;
            if (hexVisual.getHex() instanceof ITerritoryHex)
            {
                ITerritoryHex terrHex = (ITerritoryHex)hexVisual.getHex();
                terrHex.setTerritory(territory);
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
     * @param territory the territory to set
     */
    public SetTerritoryBehaviour setTerritory(Territory territory)
    {
        this.territory = territory;
    
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }

    public void mouseEnter(IPieceVisual pieceVisual, IBoardVisual board)
    {
        if (pieceVisual instanceof IHexVisual)
        {
            IHexVisual hexVisual = (IHexVisual)pieceVisual;
            if (hexVisual.getHex() instanceof ITerritoryHex)
            {
                hexVisual.setSelected(true);
            }
        }
    }

    @Override
    public void mouseOut(IPieceVisual hex, IBoardVisual board)
    {
        hex.setSelected(false);
    }

}
