package org.soc.gwt.client.editor;

import org.soc.common.board.territories.Territory;
import org.soc.common.views.behaviour.board.BoardBehaviour;

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
