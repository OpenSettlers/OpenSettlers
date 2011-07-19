package org.soc.common.views.behaviour.gameBoard;

import java.util.HashSet;
import java.util.Set;

import org.soc.common.actions.gameAction.GameAction;
import org.soc.common.actions.gameAction.standard.BuildCity;
import org.soc.common.board.pieces.Town;
import org.soc.common.board.routing.GraphPoint;
import org.soc.common.game.player.GamePlayer;
import org.soc.common.views.behaviour.gameWidget.GameBehaviourCallback;
import org.soc.common.views.widgetsInterface.visuals.GameBoardVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual;
import org.soc.common.views.widgetsInterface.visuals.PointVisual;


public class BuildCityBehaviour extends BuildPointBehaviour
{
    BuildCity buildCity;
    GamePlayer player;
    GameBehaviourCallback callback;
    Set<PointVisual> pointVisuals = new HashSet<PointVisual>();

    public BuildCityBehaviour(BuildCity buildCity, GamePlayer player,
            GameBehaviourCallback callback)
    {
        super();
        this.buildCity = buildCity;
        this.player = player;
        this.callback = callback;
    }

    /**
     * @return the buildCity
     */
    public BuildCity getBuildCity()
    {
        return buildCity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.client.behaviour.game.BuildPointBehaviour#clicked(org.soc.common
     * .client.visuals.IPieceVisual,
     * org.soc.common.client.visuals.board.IBoardVisual)
     */
    @Override
    public void clicked(PieceVisual pieceVisual, GameBoardVisual board)
    {
        PointVisual pointVisual = pieceVisual.getPointVisual();
        if (pointVisual != null)
        {
            buildCity.setLocation(pointVisual.getHexPoint());
            callback.done();
        }
    }

    @Override
    public void setNeutral(GameBoardVisual visual)
    {
        for (PointVisual pointVisual : pointVisuals)
        {
            pointVisual.setVisible(false);
        }
    }

    @Override
    public void start(GameBoardVisual gameVisual)
    {
        for (Town town : player.getTowns())
        {
            GraphPoint graphPoint = gameVisual.getBoard().getGraph()
                    .findGraphPoint(town.getPoint());
            PointVisual pointVisual = gameVisual.getPointVisuals().get(
                    graphPoint);
            pointVisual.setVisible(true);
            pointVisuals.add(pointVisual);
        }
    }

    @Override
    public GameAction getGameAction()
    {
        return buildCity;
    }
}