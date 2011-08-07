package org.soc.gwt.client.game.widgetsAbstract.visuals;

import java.util.HashMap;
import java.util.Map;

import org.soc.common.board.layout.HasSide;
import org.soc.common.board.pieces.abstractPieces.Piece;
import org.soc.common.board.pieces.pieceLists.PiecesChangedEvent;
import org.soc.common.board.pieces.pieceLists.PiecesChangedEventHandler;
import org.soc.common.board.routing.GraphPoint;
import org.soc.common.board.routing.GraphSide;
import org.soc.common.board.routing.Route;
import org.soc.common.game.Game;
import org.soc.common.views.behaviour.board.ProxyBehaviour;
import org.soc.common.views.behaviour.gameBoard.GameBoardBehaviour;
import org.soc.common.views.behaviour.gameBoard.NoBehaviour;
import org.soc.common.views.widgetsInterface.visuals.BoardVisual;
import org.soc.common.views.widgetsInterface.visuals.GameBoardVisual;
import org.soc.common.views.widgetsInterface.visuals.HexVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual;
import org.soc.common.views.widgetsInterface.visuals.PirateVisual;
import org.soc.common.views.widgetsInterface.visuals.PointVisual;
import org.soc.common.views.widgetsInterface.visuals.RobberVisual;
import org.soc.common.views.widgetsInterface.visuals.SideVisual;
import org.soc.common.views.widgetsInterface.visuals.VisualFactory;


import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractGameBoardVisual extends AbstractBoardVisual
        implements GameBoardVisual, PiecesChangedEventHandler
{
    protected VisualFactory visualFactory;
    protected Game game;
    protected BoardVisual boardVisual;
    protected RobberVisual robber;
    protected PirateVisual pirate;
    protected Map<Piece, PieceVisual> playerPieceVisuals = new HashMap<Piece, PieceVisual>();
    protected Map<GraphPoint, PointVisual> pointVisuals = new HashMap<GraphPoint, PointVisual>();
    protected Map<GraphSide, SideVisual> sideVisuals = new HashMap<GraphSide, SideVisual>();
    protected GameBoardBehaviour gameBehaviour;
    protected SimpleEventBus eventBus = new SimpleEventBus();
    protected ProxyBehaviour proxyBehaviour;
    protected NoBehaviour noBehaviour = new NoBehaviour();

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.gwt.client.visuals.abstractVisuals.GameBoardVisual#createVisualFactory
     * ()
     */
    @Override
    public VisualFactory createVisualFactory()
    {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.board.pieces.PiecesChangedEventHandler#onPiecesChanged(org.soc
     * .common.board.pieces.PiecesChangedEvent)
     */
    @Override
    public void onPiecesChanged(PiecesChangedEvent list)
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.gwt.client.visuals.abstractVisuals.AbstractBoardVisual#clicked(org.soc
     * .gwtClient.visuals.abstractVisuals.PieceVisual,
     * org.soc.gwt.client.visuals.abstractVisuals.BoardVisual)
     */
    @Override
    public void clicked(PieceVisual pieceVisual, BoardVisual board)
    {
        gameBehaviour.clicked(pieceVisual, this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.gwt.client.visuals.abstractVisuals.AbstractBoardVisual#mouseEnter(
     * org.soc.gwt.client.visuals.abstractVisuals.PieceVisual,
     * org.soc.gwt.client.visuals.abstractVisuals.BoardVisual)
     */
    @Override
    public void mouseEnter(PieceVisual pieceVisual, BoardVisual board)
    {
        gameBehaviour.mouseEnter(pieceVisual, this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.gwt.client.visuals.abstractVisuals.AbstractBoardVisual#mouseOut(org.soc
     * .gwtClient.visuals.abstractVisuals.PieceVisual,
     * org.soc.gwt.client.visuals.abstractVisuals.BoardVisual)
     */
    @Override
    public void mouseOut(PieceVisual pieceVisual, BoardVisual board)
    {
        gameBehaviour.mouseOut(pieceVisual, this);
    }

    public AbstractGameBoardVisual(Game game)
    {
        this.game = game;
        this.board = game.getBoard();
        visualFactory = createVisualFactory();

        proxyBehaviour = new ProxyBehaviour(this);
        boardVisual = visualFactory.createBoardVisual(board);
    }

    /**
     * @return the playerPieceVisuals
     */
    public Map<Piece, PieceVisual> getPlayerPieceVisuals()
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
     * @see org.soc.common.client.visuals.game.GameBoardVisual#getGame()
     */
    @Override
    public Game getGame()
    {
        return game;
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
     * @see org.soc.common.client.visuals.board.BoardVisual#resize(int, int)
     */
    @Override
    public void resize(int width, int height)
    {
        // Not supported (Yet?)
    }

    @Override
    public void setBehaviour(GameBoardBehaviour gameBehaviour)
    {
        // Get rid of visual state from old behaviour
        if (this.gameBehaviour != null)
        {
            this.gameBehaviour.setNeutral(this);
        }

        // Change th behaviour
        this.gameBehaviour = gameBehaviour;

        // Set start visual state of new behaviour
        gameBehaviour.start(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.client.visuals.game.GameBoardVisual#getBehaviour()
     */
    @Override
    public GameBoardBehaviour getBehaviour()
    {
        return gameBehaviour;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.gwt.client.visuals.abstractVisuals.AbstractBoardVisual#hideTerritories
     * ()
     */
    @Override
    public void hideTerritories()
    {
        boardVisual.hideTerritories();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.gwt.client.visuals.abstractVisuals.AbstractBoardVisual#showTerritories
     * ()
     */
    @Override
    public void showTerritories()
    {
        for (HexVisual hexVisual : boardVisual.getHexVisuals().values())
            hexVisual.getTerritory().setVisible(true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.gwt.client.visuals.abstractVisuals.GameBoardVisual#quitbehaviour()
     */
    @Override
    public void stopBehaviour()
    {
        setBehaviour(noBehaviour);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.gwt.client.visuals.abstractVisuals.GameBoardVisual#showLongestRoad()
     */
    @Override
    public void showLongestRoad(Route route)
    {
        for (Piece piece : playerPieceVisuals.keySet())
            playerPieceVisuals.get(piece).setSelected(false);

        for (GraphSide side : route.getEdgeList())
        {
            HasSide sidePiece = route.getPlayer().getSidePieces().get(
                    side.getSide());
            playerPieceVisuals.get(sidePiece).setSelected(true);
        }

        // for (PlayerPiece piece : playerPieceVisuals.keySet())
        // {
        // if (piece.getPlayer().equals(route.getPlayer())
        // && piece instanceof SidePiece)
        // {
        // SidePiece sidePiece = (SidePiece) piece;
        // GraphSide side = game.getBoard().getGraph().findGraphSide(
        // sidePiece.getSide());
        // if (route.getEdgeList().contains(side))
        // playerPieceVisuals.get(side).setSelected(true);
        // }
        //
        // }
    }
}
