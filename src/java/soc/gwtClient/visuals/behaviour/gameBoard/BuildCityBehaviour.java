package soc.gwtClient.visuals.behaviour.gameBoard;

import java.util.HashSet;
import java.util.Set;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.standard.BuildCity;
import soc.common.board.pieces.PlayerPiece;
import soc.common.board.pieces.PlayerPieceList;
import soc.common.board.pieces.Town;
import soc.common.board.routing.GraphPoint;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.behaviour.GameBehaviourCallback;
import soc.gwtClient.visuals.abstractVisuals.GameBoardVisual;
import soc.gwtClient.visuals.abstractVisuals.PieceVisual;
import soc.gwtClient.visuals.abstractVisuals.PointVisual;

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
     * soc.common.client.behaviour.game.BuildPointBehaviour#clicked(soc.common
     * .client.visuals.IPieceVisual,
     * soc.common.client.visuals.board.IBoardVisual)
     */
    @Override
    public void clicked(PieceVisual pieceVisual, GameBoardVisual board)
    {
        if (pieceVisual instanceof PointVisual)
        {
            PointVisual pointVisual = (PointVisual) pieceVisual;
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
        PlayerPieceList towns = player.getBuildPieces().ofType(Town.TOWN);
        for (PlayerPiece playerPiece : towns)
        {
            Town town = (Town) playerPiece;
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