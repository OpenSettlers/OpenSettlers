package soc.gwtClient.visuals.behaviour.gameBoard;

import java.util.Set;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.standard.BuildTown;
import soc.common.board.routing.GraphPoint;
import soc.common.game.gamePhase.InitialPlacementGamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.gwtClient.game.behaviour.GameBehaviourCallback;
import soc.gwtClient.visuals.abstractVisuals.GameBoardVisual;
import soc.gwtClient.visuals.abstractVisuals.PieceVisual;
import soc.gwtClient.visuals.abstractVisuals.PointVisual;

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
        if (gameVisual.getGame().getCurrentPhase() instanceof InitialPlacementGamePhase)
        {
            townCandidates = gameVisual.getBoard().getGraph()
                    .getTownCandidatesFirstTown(null);
        }
        if (gameVisual.getGame().getCurrentPhase() instanceof PlayTurnsGamePhase)
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