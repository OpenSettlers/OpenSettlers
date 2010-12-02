package soc.gwtClient.editor;

import soc.common.board.Territory;
import soc.common.client.behaviour.IInteractionBehaviour;

public class MapEditor implements IMapEditor
{
    protected IInteractionBehaviour editBehaviour;
    protected Territory currentTerritory;

    @Override
    public Territory getCurrentTerritory()
    {
        return currentTerritory;
    }
}
