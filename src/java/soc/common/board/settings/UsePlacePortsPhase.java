package soc.common.board.settings;

public class UsePlacePortsPhase implements BoardSetting
{
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

}
