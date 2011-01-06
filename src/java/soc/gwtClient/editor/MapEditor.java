package soc.gwtClient.editor;

import soc.common.board.territories.Territory;
import soc.gwtClient.visuals.behaviour.InteractionBehaviour;

public class MapEditor implements IMapEditor
{
    protected InteractionBehaviour editBehaviour;
    protected Territory currentTerritory;

    @Override
    public Territory getCurrentTerritory()
    {
        return currentTerritory;
    }
}
