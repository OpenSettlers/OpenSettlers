package soc.common.game.gamePhase.turnPhase;

public class TradingTurnPhase extends TurnPhase
{
    private BuildingTurnPhase buildPhase;
    
    public TradingTurnPhase()
    {
        buildPhase = new BuildingTurnPhase(this);        
    }
}
