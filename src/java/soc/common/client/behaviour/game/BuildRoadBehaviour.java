package soc.common.client.behaviour.game;

import soc.common.actions.gameAction.turnActions.standard.BuildRoad;
import soc.common.client.visuals.IPieceVisual;
import soc.common.client.visuals.board.IBoardVisual;

public class BuildRoadBehaviour extends BuildSideBehaviour
{
    BuildRoad buildRoad;
    /**
     * @return the buildRoad
     */
    public BuildRoad getBuildRoad()
    {
        return buildRoad;
    }
    public BuildRoadBehaviour(BuildRoad buildRoad)
    {
        this.buildRoad=buildRoad;
    }
    /* (non-Javadoc)
     * @see soc.common.client.behaviour.game.BuildSideBehaviour#clicked(soc.common.client.visuals.IPieceVisual, soc.common.client.visuals.board.IBoardVisual)
     */
    @Override
    public void clicked(IPieceVisual pieceVisual, IBoardVisual board)
    {
        super.clicked(pieceVisual, board);
    }

}
