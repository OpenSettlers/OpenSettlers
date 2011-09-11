package org.soc.gwt.client.game.widgetsAbstract.visuals;

import java.util.HashMap;
import java.util.Map;

import org.soc.common.game.Game;
import org.soc.common.game.actions.ActionOnGameBoard;
import org.soc.common.game.board.GraphPoint;
import org.soc.common.game.board.GraphSide;
import org.soc.common.game.board.HasSide;
import org.soc.common.game.board.Route;
import org.soc.common.game.pieces.Piece;
import org.soc.common.game.pieces.PiecesChangedEvent;
import org.soc.common.game.pieces.PiecesChangedEventHandler;
import org.soc.common.views.widgetsInterface.visuals.BoardVisual;
import org.soc.common.views.widgetsInterface.visuals.GameBoardVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual;
import org.soc.common.views.widgetsInterface.visuals.VisualFactory;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractGameBoardVisual extends AbstractBoardVisual
        implements GameBoardVisual, PiecesChangedEventHandler {
  protected VisualFactory visualFactory;
  protected Game game;
  protected BoardVisual boardVisual;
  protected RobberVisual robber;
  protected PirateVisual pirate;
  protected Map<Piece, PieceVisual> playerPieceVisuals = new HashMap<Piece, PieceVisual>();
  protected Map<GraphPoint, PointVisual> pointVisuals = new HashMap<GraphPoint, PointVisual>();
  protected Map<GraphSide, SideVisual> sideVisuals = new HashMap<GraphSide, SideVisual>();
  protected ActionOnGameBoard gameBehaviour;
  protected SimpleEventBus eventBus = new SimpleEventBus();
  protected ProxyBehaviour proxyBehaviour;
  protected ActionOnGameBoard.NoActionOnBoard noBehaviour = new ActionOnGameBoard.NoActionOnBoard();

  @Override public VisualFactory createVisualFactory() {
    return null;
  }
  @Override public void onPiecesChanged(PiecesChangedEvent list) {}
  @Override public void clicked(PieceVisual pieceVisual, BoardVisual board) {
    gameBehaviour.clicked(pieceVisual, this);
  }
  @Override public void mouseEnter(PieceVisual pieceVisual, BoardVisual board) {
    gameBehaviour.mouseEnter(pieceVisual, this);
  }
  @Override public void mouseOut(PieceVisual pieceVisual, BoardVisual board) {
    gameBehaviour.mouseOut(pieceVisual, this);
  }
  public AbstractGameBoardVisual(Game game) {
    this.game = game;
    this.board = game.board();
    visualFactory = createVisualFactory();
    proxyBehaviour = new ProxyBehaviour(this);
    boardVisual = visualFactory.createBoardVisual(board);
  }
  public Map<Piece, PieceVisual> getPlayerPieceVisuals()
  {
    return playerPieceVisuals;
  }
  public Map<GraphPoint, PointVisual> pointVisuals() {
    return pointVisuals;
  }
  public Map<GraphSide, SideVisual> getSideVisuals() {
    return sideVisuals;
  }
  @Override public Game game() {
    return game;
  }
  @Override public Widget asWidget() {
    return boardVisual.asWidget();
  }
  @Override public void resize(int width, int height) {
    // Not supported (Yet?)
  }
  @Override public void setBehaviour(ActionOnGameBoard gameBehaviour) {
    // Get rid of visual state from old behaviour
    if (this.gameBehaviour != null) {
      this.gameBehaviour.setNeutral(this);
    }
    // Change th behaviour
    this.gameBehaviour = gameBehaviour;
    // Set start visual state of new behaviour
    gameBehaviour.start(this);
  }
  @Override public ActionOnGameBoard getBehaviour() {
    return gameBehaviour;
  }
  @Override public void hideTerritories() {
    boardVisual.hideTerritories();
  }
  @Override public void showTerritories() {
    for (HexVisual hexVisual : boardVisual.hexVisuals().values())
      hexVisual.getTerritory().setVisible(true);
  }
  @Override public void stopBehaviour() {
    setBehaviour(noBehaviour);
  }
  @Override public void showLongestRoad(Route route)
  {
    for (Piece piece : playerPieceVisuals.keySet())
      playerPieceVisuals.get(piece).setSelected(false);
    for (GraphSide side : route.getEdgeList())
    {
      HasSide sidePiece = route.player().sidePieces().get(
              side.side());
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
