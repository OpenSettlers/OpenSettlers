package soc.common.client.visuals.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import soc.common.board.HexPoint;
import soc.common.board.HexSide;
import soc.common.board.hexes.Hex;
import soc.common.board.pieces.PiecesChangedEventHandler;
import soc.common.board.pieces.PlayerPiece;
import soc.common.board.routing.GraphPoint;
import soc.common.board.routing.GraphSide;
import soc.common.client.behaviour.InteractionBehaviour;
import soc.common.client.behaviour.game.GameBehaviour;
import soc.common.client.visuals.PieceVisual;
import soc.common.client.visuals.board.AbstractBoardVisual;
import soc.common.client.visuals.board.BoardVisual;
import soc.common.game.Game;
import soc.common.game.GamePlayer;

import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractGameBoardVisual extends AbstractBoardVisual
        implements GameBoardVisual, PiecesChangedEventHandler
{
    protected VisualFactory visualFactory;
    protected Game game;
    protected BoardVisual boardVisual;
    protected RobberVisual robber;
    protected PirateVisual pirate;
    protected Map<PlayerPiece, PieceVisual> playerPieceVisuals = new HashMap<PlayerPiece, PieceVisual>();
    protected Map<GraphPoint, PointVisual> pointVisuals = new HashMap<GraphPoint, PointVisual>();
    protected Map<GraphSide, SideVisual> sideVisuals = new HashMap<GraphSide, SideVisual>();
    protected GameBehaviour interactionBehaviour;

    public AbstractGameBoardVisual(Game game)
    {
        this.game = game;
        this.board = game.getBoard();
        visualFactory = createVisualFactory();

        boardVisual = visualFactory.createBoardVisual(board);

        if (game.getRobber() != null)
        {
            robber = visualFactory.createRobberVisual(game.getRobber());
        }
        if (game.getPirate() != null)
        {
            pirate = visualFactory.createPirateVisual(game.getPirate());
        }

        for (GamePlayer player : game.getPlayers())
        {
            player.getBuildPieces().addPiecesChangedEventHandler(this);
        }
    }

    /**
     * @return the playerPieceVisuals
     */
    public Map<PlayerPiece, PieceVisual> getPlayerPieceVisuals()
    {
        return playerPieceVisuals;
    }

    /**
     * @return the sideVisuals
     */
    public Map<GraphPoint, PointVisual> getPointVisuals()
    {
        return pointVisuals;
    }

    /**
     * @return the pointVisuals
     */
    public Map<GraphSide, SideVisual> getSideVisuals()
    {
        return sideVisuals;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.client.visuals.game.GameBoardVisual#getGame()
     */
    @Override
    public Game getGame()
    {
        return game;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.client.visuals.game.IGameBoardVisual#disableHexes()
     */
    @Override
    public void disableHexes()
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.client.visuals.game.IGameBoardVisual#disablePoints()
     */
    @Override
    public void disablePoints()
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.client.visuals.game.IGameBoardVisual#disableSides()
     */
    @Override
    public void disableSides()
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.client.visuals.game.IGameBoardVisual#setSelectableHexes(java
     * .util.List)
     */
    @Override
    public void setSelectableHexes(List<Hex> hexes)
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.client.visuals.game.IGameBoardVisual#setSelectablePoints(java
     * .util.List)
     */
    @Override
    public void setSelectablePoints(List<HexPoint> points)
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.client.visuals.game.IGameBoardVisual#setSelectableSides(java
     * .util.List)
     */
    @Override
    public void setSelectableSides(List<HexSide> sides)
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.user.client.ui.IsWidget#asWidget()
     */
    @Override
    public Widget asWidget()
    {
        return boardVisual.asWidget();
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.client.visuals.board.BoardVisual#resize(int, int)
     */
    @Override
    public void resize(int width, int height)
    {
        // Not supported (Yet?)
    }

    @Override
    public void setBehaviour(GameBehaviour gameBehaviour)
    {
        interactionBehaviour = gameBehaviour;
        interactionBehaviour.start(this);
    }

    @Override
    public InteractionBehaviour getCurrentBehaviour()
    {
        return interactionBehaviour;
    }

    @Override
    public BoardVisual setInteractionBehaviour(InteractionBehaviour behaviour)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
