package soc.common.client.behaviour.game;

import soc.common.actions.gameAction.turnActions.standard.BuildCity;
import soc.common.client.visuals.IPieceVisual;
import soc.common.client.visuals.board.IBoardVisual;

public class BuildCityBehaviour extends BuildPointBehaviour
{
    BuildCity buildCity;
    
    public BuildCityBehaviour(BuildCity buildCity)
    {
        super();
        this.buildCity = buildCity;
    }

    /* (non-Javadoc)
     * @see soc.common.client.behaviour.game.BuildPointBehaviour#clicked(soc.common.client.visuals.IPieceVisual, soc.common.client.visuals.board.IBoardVisual)
     */
    @Override
    public void clicked(IPieceVisual pieceVisual, IBoardVisual board)
    {
        super.clicked(pieceVisual, board);
    }

}
