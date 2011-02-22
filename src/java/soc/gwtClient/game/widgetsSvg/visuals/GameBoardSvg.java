package soc.gwtClient.game.widgetsSvg.visuals;

import java.util.HashMap;

import soc.common.board.hexes.Hex;
import soc.common.board.pieces.abstractPieces.Piece;
import soc.common.board.pieces.pieceLists.PlayerPieceListChangedEvent;
import soc.common.board.pieces.pieceLists.PlayerPieceListChangedEventHandler;
import soc.common.board.routing.GraphPoint;
import soc.common.board.routing.GraphSide;
import soc.common.game.Game;
import soc.common.game.LongestRoadChangedEvent;
import soc.common.game.LongestRoadChangedEventHandler;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.behaviour.board.ProxyBehaviour;
import soc.gwtClient.game.widgetsAbstract.visuals.AbstractGameBoardVisual;
import soc.gwtClient.game.widgetsInterface.visuals.HexVisual;
import soc.gwtClient.game.widgetsInterface.visuals.PieceVisual;
import soc.gwtClient.game.widgetsInterface.visuals.PointVisual;
import soc.gwtClient.game.widgetsInterface.visuals.SideVisual;
import soc.gwtClient.game.widgetsInterface.visuals.VisualFactory;

import com.google.gwt.user.client.ui.Widget;

public class GameBoardSvg extends AbstractGameBoardVisual implements
        PlayerPieceListChangedEventHandler, LongestRoadChangedEventHandler
{
    BoardSvg boardSvg;

    /**
     * @return the boardSvg
     */
    public BoardSvg getBoardSvg()
    {
        return boardSvg;
    }

    public GameBoardSvg(Game game, int width, int height)
    {
        super(game);

        boardSvg = new BoardSvg(width, height, game.getBoard());
        boardSvg.setBoardBehaviour(new ProxyBehaviour(this));

        for (GraphSide side : board.getGraph().getSides())
        {
            SideVisual sideVisual = visualFactory.createSideVisual(side);
            sideVisuals.put(side, sideVisual);
            boardSvg.getDrawingArea().add(
                    ((SvgVisual) sideVisual).getVectorObject());
        }

        for (GraphPoint point : board.getGraph().getPoints())
        {
            PointVisual pointVisual = visualFactory.createPointVisual(point);
            pointVisuals.put(point, pointVisual);
            boardSvg.getDrawingArea().add(
                    ((SvgVisual) pointVisual).getVectorObject());
        }
        if (game.getRobber() != null)
        {
            robber = visualFactory.createRobberVisual(game.getRobber());
            boardSvg.getDrawingArea().add(
                    ((SvgVisual) robber).getVectorObject());
        }
        if (game.getPirate() != null)
        {
            pirate = visualFactory.createPirateVisual(game.getPirate());
        }

        addHandlers();
    }

    private void addHandlers()
    {
        for (GamePlayer player : game.getPlayers())
        {
            player.getTowns().addTownsChangedEventHandler(this);
            player.getRoads().addRoadsChangedEventHandler(this);
            player.getCities().addCitiesChangedEventHandler(this);
        }

        game.getLongestRoute().addLongestRoadChangedEventHandler(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.client.visuals.board.AbstractBoardVisual#getHexVisuals()
     */
    @Override
    public HashMap<Hex, HexVisual> getHexVisuals()
    {
        return boardSvg.getHexVisuals();
    }

    @Override
    public Widget asWidget()
    {
        return boardSvg.getDrawingArea();
    }

    @Override
    public VisualFactory createVisualFactory()
    {
        return new SvgVisualFactory(this);
    }

    private void addPiece(Piece piece)
    {
        // Create a new visual for the added player piece
        PieceVisual newPieceVisual = piece.createPiece(visualFactory);

        // Keep track of it
        playerPieceVisuals.put(piece, newPieceVisual);

        // Add to the svg canvas
        boardSvg.getDrawingArea().add(
                ((SvgVisual) newPieceVisual).getVectorObject());
    }

    private void removePiece(Piece piece)
    {
        PieceVisual pieceVisual = playerPieceVisuals.get(piece);
        playerPieceVisuals.remove(piece);
        boardSvg.getDrawingArea().remove(
                ((SvgVisual) pieceVisual).getVectorObject());
    }

    @Override
    public void onPlayerPieceListChanged(PlayerPieceListChangedEvent event)
    {
        if (event.getAddedPiece() != null)
            addPiece((Piece) event.getAddedPiece());

        if (event.getRemovedPiece() != null)
            removePiece((Piece) event.getRemovedPiece());
    }

    @Override
    public void onLongestRoadChanged(LongestRoadChangedEvent event)
    {
        if (event.getNewRoute() != null)
            showLongestRoad(event.getNewRoute());
    }
}
