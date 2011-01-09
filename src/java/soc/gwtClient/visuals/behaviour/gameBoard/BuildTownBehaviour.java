package soc.gwtClient.visuals.behaviour.gameBoard;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.standard.BuildTown;
import soc.common.board.routing.GraphPoint;
import soc.gwtClient.game.behaviour.GameBehaviourCallback;
import soc.gwtClient.visuals.abstractVisuals.GameBoardVisual;
import soc.gwtClient.visuals.abstractVisuals.PieceVisual;
import soc.gwtClient.visuals.abstractVisuals.PointVisual;

public class BuildTownBehaviour extends BuildPointBehaviour
{
    BuildTown buildTown;
    GameBehaviourCallback callback;

    public BuildTownBehaviour(BuildTown buildTown,
            GameBehaviourCallback callback)
    {
        super();
        this.buildTown = buildTown;
        this.callback = callback;
    }

    @Override
    public void clicked(PieceVisual pieceVisual, GameBoardVisual board)
    {
        if (pieceVisual instanceof PointVisual)
        {
            PointVisual point = (PointVisual) pieceVisual;
            buildTown.setPointLocation(point.getHexPoint());
            callback.done();
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
