package soc.common.views.behaviour.gameBoard;

import java.util.HashSet;
import java.util.Set;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.standard.BuildCity;
import soc.common.board.pieces.Town;
import soc.common.board.routing.GraphPoint;
import soc.common.game.player.GamePlayer;
import soc.common.views.behaviour.gameWidget.GameBehaviourCallback;
import soc.common.views.widgetsInterface.visuals.GameBoardVisual;
import soc.common.views.widgetsInterface.visuals.IPointVisual;
import soc.common.views.widgetsInterface.visuals.PieceVisual;

public class BuildCityBehaviour extends BuildPointBehaviour
{
    BuildCity buildCity;
    GamePlayer player;
    GameBehaviourCallback callback;
    Set<IPointVisual> pointVisuals = new HashSet<IPointVisual>();

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
     * soc.common.client.behaviour.game.BuildPointBehaviour#clicked(soc.common
     * .client.visuals.IPieceVisual,
     * soc.common.client.visuals.board.IBoardVisual)
     */
    @Override
    public void clicked(PieceVisual pieceVisual, GameBoardVisual board)
    {
        if (pieceVisual instanceof IPointVisual)
        {
            IPointVisual pointVisual = (IPointVisual) pieceVisual;
            buildCity.setLocation(pointVisual.getHexPoint());
            callback.done();
        }
    }

    @Override
    public void setNeutral(GameBoardVisual visual)
    {
        for (IPointVisual pointVisual : pointVisuals)
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
            IPointVisual pointVisual = gameVisual.getPointVisuals().get(
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