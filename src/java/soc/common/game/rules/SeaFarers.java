package soc.common.game.rules;

import soc.common.actions.gameAction.turnActions.seaFarers.BuildShip;
import soc.common.board.pieces.Ship;
import soc.common.board.resources.Diamond;
import soc.common.board.resources.ResourceList;
import soc.common.game.Game;

public class SeaFarers extends RuleSet
{

    public SeaFarers(Game game)
    {
        super(game);
    }

    @Override
    public void addBuildablePieces()
    {
        game.getGameRules().getPossibleActions().add(new BuildShip());
    }

    @Override
    public void setRules()
    {
        // TODO Auto-generated method stub
        
    }

}
