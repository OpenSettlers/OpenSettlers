package soc.gwtClient.editor;

import soc.common.board.territories.Territory;
import soc.common.views.behaviour.board.BoardBehaviour;

public class MapEditor implements IMapEditor
{
    protected BoardBehaviour editBehaviour;
    protected Territory currentTerritory;

    @Override
    public Territory getCurrentTerritory()
    {
        return currentTerritory;
    }
}
