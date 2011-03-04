package soc.common.views.behaviour.gameBoard;

import java.util.Set;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.standard.BuildTown;
import soc.common.board.routing.GraphPoint;
import soc.common.views.behaviour.gameWidget.GameBehaviourCallback;
import soc.common.views.widgetsInterface.visuals.GameBoardVisual;
import soc.common.views.widgetsInterface.visuals.PointVisual;
import soc.common.views.widgetsInterface.visuals.PieceVisual;

public class BuildTownBehaviour extends BuildPointBehaviour
{
    BuildTown buildTown;
    GameBehaviourCallback callback;
    Set<GraphPoint> townCandidates;

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
        PointVisual pointVisual = pieceVisual.getPointVisual();
        if (pointVisual != null)
        {
            buildTown.setPointLocation(pointVisual.getHexPoint());
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
        if (gameVisual.getGame().getCurrentPhase().isInitialPlacement())
        {
            townCandidates = gameVisual.getBoard().getGraph()
                    .getTownCandidatesFirstTown(null);
        }
        if (gameVisual.getGame().getCurrentPhase().isPlayTurns())
        {
            townCandidates = gameVisual.getBoard().getGraph()
                    .getTownCandidatesTurnPhase(
                            gameVisual.getGame().getCurrentTurn().getPlayer());
        }
        for (GraphPoint point : townCandidates)
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
