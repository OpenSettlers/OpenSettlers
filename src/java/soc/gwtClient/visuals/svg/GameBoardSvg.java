package soc.gwtClient.visuals.svg;

import java.util.HashMap;

import soc.common.board.hexes.Hex;
import soc.common.board.pieces.PlayerPiece;
import soc.common.board.pieces.PlistChangedEvent;
import soc.common.board.pieces.PlistChangedEventHandler;
import soc.common.board.routing.GraphPoint;
import soc.common.board.routing.GraphSide;
import soc.common.game.Game;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.visuals.abstractVisuals.AbstractGameBoardVisual;
import soc.gwtClient.visuals.abstractVisuals.HexVisual;
import soc.gwtClient.visuals.abstractVisuals.PieceVisual;
import soc.gwtClient.visuals.abstractVisuals.PointVisual;
import soc.gwtClient.visuals.abstractVisuals.SideVisual;
import soc.gwtClient.visuals.abstractVisuals.VisualFactory;
import soc.gwtClient.visuals.behaviour.ProxyBehaviour;

import com.google.gwt.user.client.ui.Widget;

public class GameBoardSvg extends AbstractGameBoardVisual implements
        PlistChangedEventHandler
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
            /*
             * player.getTowns().addTownsChangedEventHandler( new
             * PlistChangedEventHandler<Town>() {
             * 
             * @Override public void onPlistChanged(PlistChangedEvent<Town>
             * event) { updatePieces(event); } });
             * player.getRoads().addRoadsChangedEventHandler( new
             * PlistChangedEventHandler<Road>() {
             * 
             * @Override public void onPlistChanged(PlistChangedEvent<Road>
             * event) { updatePieces(event); } });
             * player.getCities().addCitiesChangedEventHandler( new
             * PlistChangedEventHandler<City>() {
             * 
             * @Override public void onPlistChanged(PlistChangedEvent<City>
             * event) { updatePieces(event); } });
             */
        }
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

    private void addPiece(PlayerPiece piece)
    {
        // Create a new visual for the added player piece
        PieceVisual newPieceVisual = visualFactory
                .createPlayerPieceVisual(piece);

        // Keep track of it
        playerPieceVisuals.put(piece, newPieceVisual);

        // Add to the svg canvas
        boardSvg.getDrawingArea().add(
                ((SvgVisual) newPieceVisual).getVectorObject());
    }

    private void removePiece(PlayerPiece piece)
    {
        PieceVisual pieceVisual = playerPieceVisuals.get(piece);
        playerPieceVisuals.remove(piece);
        boardSvg.getDrawingArea().remove(
                ((SvgVisual) pieceVisual).getVectorObject());
    }

    @Override
    public void onPlistChanged(PlistChangedEvent event)
    {
        if (event.getAddedPiece() != null)
        {
            addPiece((PlayerPiece) event.getAddedPiece());
        }
        if (event.getRemovedPiece() != null)
        {
            removePiece((PlayerPiece) event.getRemovedPiece());
        }
    }
}
