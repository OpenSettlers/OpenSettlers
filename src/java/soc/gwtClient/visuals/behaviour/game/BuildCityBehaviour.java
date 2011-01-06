package soc.gwtClient.visuals.behaviour.game;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.standard.BuildCity;
import soc.gwtClient.visuals.abstractVisuals.GameBoardVisual;
import soc.gwtClient.visuals.abstractVisuals.PieceVisual;

public class BuildCityBehaviour extends BuildPointBehaviour
{
    BuildCity buildCity;

    public BuildCityBehaviour(BuildCity buildCity)
    {
        super();
        this.buildCity = buildCity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.client.behaviour.game.BuildPointBehaviour#clicked(soc.common
     * .client.visuals.IPieceVisual,
     * soc.common.client.visuals.board.IBoardVisual)
     */
    @Override
    public void clicked(PieceVisual pieceVisual, GameBoardVisual board)
    {
        super.clicked(pieceVisual, board);
    }

    @Override
    public void setNeutral(GameBoardVisual visual)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void start(GameBoardVisual gameVisual)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public GameAction getGameAction()
    {
        return buildCity;
    }
}
