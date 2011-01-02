package soc.gwtClient.editor;

import soc.common.board.territories.Territory;
import soc.common.client.behaviour.InteractionBehaviour;

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
