package org.soc.common.board.settings;

import org.soc.common.views.meta.Meta;

public class UsePlacePortsPhase implements BoardSetting
{
    private static final long serialVersionUID = -3005152446968216259L;
    private boolean placePortsPhase;

    @Override
    public void set(BoardSettings boardSettings)
    {
        if (boardSettings.getUsePlacePortsPhase() == null)
            boardSettings.setUsePlacePortsPhase(this);

        boardSettings.getUsePlacePortsPhase().setPlacePortsPhase(
                placePortsPhase);
    }

    public UsePlacePortsPhase setPlacePortsPhase(boolean placePortsPhase)
    {
        this.placePortsPhase = placePortsPhase;
        return this;
    }

    public boolean isPlacePortsPhase()
    {
        return placePortsPhase;
    }

    @Override
    public Meta getMeta()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
