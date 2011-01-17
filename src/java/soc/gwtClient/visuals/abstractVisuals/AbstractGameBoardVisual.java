package soc.gwtClient.visuals.abstractVisuals;

import java.util.HashMap;
import java.util.Map;

import soc.common.board.pieces.PiecesChangedEvent;
import soc.common.board.pieces.PiecesChangedEventHandler;
import soc.common.board.pieces.PlayerPiece;
import soc.common.board.routing.GraphPoint;
import soc.common.board.routing.GraphSide;
import soc.common.game.Game;
import soc.gwtClient.visuals.behaviour.ProxyBehaviour;
import soc.gwtClient.visuals.behaviour.gameBoard.GameBoardBehaviour;
import soc.gwtClient.visuals.behaviour.gameBoard.NoBehaviour;

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
    protected Map<PlayerPiece, PieceVisual> playerPieceVisuals = new HashMap<PlayerPiece, PieceVisual>();
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
     * soc.gwtClient.visuals.abstractVisuals.GameBoardVisual#createVisualFactory
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
     * soc.common.board.pieces.PiecesChangedEventHandler#onPiecesChanged(soc
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
     * soc.gwtClient.visuals.abstractVisuals.AbstractBoardVisual#clicked(soc
     * .gwtClient.visuals.abstractVisuals.PieceVisual,
     * soc.gwtClient.visuals.abstractVisuals.BoardVisual)
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
     * soc.gwtClient.visuals.abstractVisuals.AbstractBoardVisual#mouseEnter(
     * soc.gwtClient.visuals.abstractVisuals.PieceVisual,
     * soc.gwtClient.visuals.abstractVisuals.BoardVisual)
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
     * soc.gwtClient.visuals.abstractVisuals.AbstractBoardVisual#mouseOut(soc
     * .gwtClient.visuals.abstractVisuals.PieceVisual,
     * soc.gwtClient.visuals.abstractVisuals.BoardVisual)
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
     * @see soc.common.client.visuals.game.GameBoardVisual#getBehaviour()
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
     * soc.gwtClient.visuals.abstractVisuals.AbstractBoardVisual#hideTerritories
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
     * soc.gwtClient.visuals.abstractVisuals.AbstractBoardVisual#showTerritories
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
     * soc.gwtClient.visuals.abstractVisuals.GameBoardVisual#quitbehaviour()
     */
    @Override
    public void stopBehaviour()
    {
        setBehaviour(noBehaviour);
    }
}
