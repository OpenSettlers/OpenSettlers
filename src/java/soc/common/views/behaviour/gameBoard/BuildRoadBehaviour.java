package soc.common.views.behaviour.gameBoard;

import java.util.Set;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.standard.BuildRoad;
import soc.common.board.routing.GraphSide;
import soc.common.game.player.GamePlayer;
import soc.common.views.behaviour.gameWidget.GameBehaviourCallback;
import soc.common.views.widgetsInterface.visuals.GameBoardVisual;
import soc.common.views.widgetsInterface.visuals.PieceVisual;
import soc.common.views.widgetsInterface.visuals.SideVisual;

public class BuildRoadBehaviour extends BuildSideBehaviour
{
    BuildRoad buildRoad;
    Set<GraphSide> sides;
    GameBehaviourCallback callback;

    /*
     * Toggles displaying candidates off
     * 
     * @see
     * soc.common.client.behaviour.game.BuildSideBehaviour#setNeutral(soc.common
     * .client.visuals.game.GameBoardVisual)
     */
    @Override
    public void setNeutral(GameBoardVisual visual)
    {
        if (sides != null)
            for (GraphSide side : sides)
                visual.getSideVisuals().get(side).setVisible(false);
    }

    /*
     * Shows road candidates based on the on current turn of the game
     * 
     * @see
     * soc.common.client.behaviour.game.BuildSideBehaviour#start(soc.common.
     * client.visuals.game.GameBoardVisual)
     */
    @Override
    public void start(GameBoardVisual gameVisual)
    {
        // Reset and see if there are candidates
        sides = null;

        // During initial setup phase
        if (gameVisual.getGame().getCurrentPhase().isInitialPlacement())
        {
            // Grab player
            GamePlayer player = gameVisual.getGame().getCurrentTurn()
                    .getPlayer();

            // Grab road candidates for players' first or second town
            if (player.getSidePieces().size() == 0)
                sides = gameVisual.getBoard().getGraph()
                        .getRoadCandidatesFirstTown(player);
            else
                sides = gameVisual.getBoard().getGraph()
                        .getRoadCandidatesSecondTown(player);
        }
        // During play turns GamePhase
        else if (gameVisual.getGame().getCurrentPhase().isPlayTurns())
        {
            GamePlayer player = gameVisual.getGame().getCurrentTurn()
                    .getPlayer();
            sides = gameVisual.getBoard().getGraph().getRoadCandidates(player);
        }

        // If any candidates found, show side visuals
        if (sides != null)
            for (GraphSide side : sides)
                gameVisual.getSideVisuals().get(side).setVisible(true);
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
     * Picks a candidate from the shown road candidates
     * 
     * @see
     * soc.common.client.behaviour.game.BuildSideBehaviour#clicked(soc.common
     * .client.visuals.IPieceVisual,
     * soc.common.client.visuals.board.IBoardVisual)
     */
    @Override
    public void clicked(PieceVisual pieceVisual, GameBoardVisual board)
    {
        SideVisual sideVisual = pieceVisual.getSideVisual();
        if (sideVisual != null)
        {
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
