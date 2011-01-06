package soc.gwtClient.visuals.behaviour.game;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.standard.BuildTown;
import soc.common.board.routing.GraphPoint;
import soc.gwtClient.visuals.abstractVisuals.GameBoardVisual;
import soc.gwtClient.visuals.abstractVisuals.PieceVisual;
import soc.gwtClient.visuals.abstractVisuals.PointVisual;

public class BuildTownBehaviour extends BuildPointBehaviour
{
    BuildTown buildTown;

    public BuildTownBehaviour(BuildTown buildTown)
    {
        super();
        this.buildTown = buildTown;
    }

    @Override
    public void clicked(PieceVisual pieceVisual, GameBoardVisual board)
    {
        if (pieceVisual instanceof PointVisual)
        {
            PointVisual point = (PointVisual) pieceVisual;
            buildTown.setPointLocation(point.getHexPoint());
            board.onBehaviourDone();
        }
    }

    @Override
    public void setNeutral(GameBoardVisual gameVisual)
    {
        for (PointVisual pointVisual : gameVisual.getPointVisuals().values())
        {
            pointVisual.setVisible(false);
        }
    }

    @Override
    public void start(GameBoardVisual gameVisual)
    {
        for (GraphPoint point : gameVisual.getBoard().getGraph()
                .getTownCandidatesFirstTown(null))
        {
            gameVisual.getPointVisuals().get(point).setVisible(true);
        }

    }

    @Override
    public GameAction getGameAction()
    {
        return buildTown;
    }
}
