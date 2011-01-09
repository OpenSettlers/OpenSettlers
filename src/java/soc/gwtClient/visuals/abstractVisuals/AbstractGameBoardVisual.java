package soc.gwtClient.visuals.abstractVisuals;

import java.util.HashMap;
import java.util.Map;

import soc.common.board.pieces.PiecesChangedEventHandler;
import soc.common.board.pieces.PlayerPiece;
import soc.common.board.routing.GraphPoint;
import soc.common.board.routing.GraphSide;
import soc.common.game.Game;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.visuals.behaviour.BehaviourDoneEvent;
import soc.gwtClient.visuals.behaviour.BehaviourDoneEventHandler;
import soc.gwtClient.visuals.behaviour.gameBoard.GameBoardBehaviour;

import com.google.gwt.event.shared.HandlerRegistration;
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
     * soc.common.client.visuals.game.GameBoardVisual#addBehaviourDoneEventHandler
     * (soc.common.client.behaviour.BehaviourDoneEventHandler)
     */
    @Override
    public HandlerRegistration addBehaviourDoneEventHandler(
            BehaviourDoneEventHandler handler)
    {
        return eventBus.addHandler(BehaviourDoneEvent.TYPE, handler);
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.client.visuals.game.GameBoardVisual#onBehaviourDone()
     */
    @Override
    public void onBehaviourDone()
    {
        gameBehaviour.setNeutral(this);
        eventBus.fireEvent(new BehaviourDoneEvent(gameBehaviour, true));
    }
}
