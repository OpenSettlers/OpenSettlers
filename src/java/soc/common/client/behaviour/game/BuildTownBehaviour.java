package soc.common.client.behaviour.game;

import soc.common.actions.gameAction.turnActions.standard.BuildTown;
import soc.common.client.visuals.PieceVisual;
import soc.common.client.visuals.board.BoardVisual;
import soc.common.client.visuals.game.GameBoardVisual;
import soc.common.client.visuals.game.PointVisual;

public class BuildTownBehaviour extends BuildPointBehaviour
{
    BuildTown buildTown;

    public BuildTownBehaviour(BuildTown buildTown)
    {
        super();
        this.buildTown = buildTown;
    }

    @Override
    public void clicked(PieceVisual pieceVisual, BoardVisual board)
    {

    }

    @Override
    public void setNeutral(GameBoardVisual gameVisual)
    {
        for (PointVisual pointVisual : gameVisual.getPointVisuals().values())
        {
            pointVisual.setSelected(false);
        }
    }

    @Override
    public void start(GameBoardVisual gameVisual)
    {
        for (PointVisual pointVisual : gameVisual.getPointVisuals().values())
        {
            pointVisual.setSelected(true);
        }

    }

}
