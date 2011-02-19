package soc.gwtClient.game.behaviour.gameBoard;

import java.util.Set;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.standard.BuildRoad;
import soc.common.board.routing.GraphSide;
import soc.common.game.gamePhase.InitialPlacementGamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.behaviour.gameWidget.GameBehaviourCallback;
import soc.gwtClient.game.widgetsInterface.visuals.GameBoardVisual;
import soc.gwtClient.game.widgetsInterface.visuals.PieceVisual;
import soc.gwtClient.game.widgetsInterface.visuals.SideVisual;

public class BuildRoadBehaviour extends BuildSideBehaviour
{
    BuildRoad buildRoad;
    Set<GraphSide> sides;
    GameBehaviourCallback callback;

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.client.behaviour.game.BuildSideBehaviour#setNeutral(soc.common
     * .client.visuals.game.GameBoardVisual)
     */
    @Override
    public void setNeutral(GameBoardVisual visual)
    {
        if (sides != null)
        {
            for (GraphSide side : sides)
            {
                visual.getSideVisuals().get(side).setVisible(false);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.client.behaviour.game.BuildSideBehaviour#start(soc.common.
     * client.visuals.game.GameBoardVisual)
     */
    @Override
    public void start(GameBoardVisual gameVisual)
    {
        if (gameVisual.getGame().getCurrentPhase() instanceof InitialPlacementGamePhase)
        {
            GamePlayer player = gameVisual.getGame().getCurrentTurn()
                    .getPlayer();
            if (player.getSidePieces().size() == 0)
            {
                sides = gameVisual.getBoard().getGraph()
                        .getRoadCandidatesFirstTown(player);
            }
            else
            {
                sides = gameVisual.getBoard().getGraph()
                        .getRoadCandidatesSecondTown(player);
            }
            for (GraphSide side : sides)
            {
                gameVisual.getSideVisuals().get(side).setVisible(true);
            }
        }
        if (gameVisual.getGame().getCurrentPhase() instanceof PlayTurnsGamePhase)
        {
            GamePlayer player = gameVisual.getGame().getCurrentTurn()
                    .getPlayer();
            sides = gameVisual.getBoard().getGraph().getRoadCandidates(player);
            for (GraphSide side : sides)
            {
                gameVisual.getSideVisuals().get(side).setVisible(true);
            }
        }
    }

    /**
     * @return the buildRoad
     */
    public BuildRoad getBuildRoad()
    {
        return buildRoad;
    }

    public BuildRoadBehaviour(BuildRoad buildRoad,
            GameBehaviourCallback callback)
    {
        this.buildRoad = buildRoad;
        this.callback = callback;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.client.behaviour.game.BuildSideBehaviour#clicked(soc.common
     * .client.visuals.IPieceVisual,
     * soc.common.client.visuals.board.IBoardVisual)
     */
    @Override
    public void clicked(PieceVisual pieceVisual, GameBoardVisual board)
    {
        if (pieceVisual instanceof SideVisual)
        {
            SideVisual sideVisual = (SideVisual) pieceVisual;
            buildRoad.setSideLocation(sideVisual.getHexSide());
            callback.done();
        }
    }

    @Override
    public GameAction getGameAction()
    {
        return buildRoad;
    }

}
