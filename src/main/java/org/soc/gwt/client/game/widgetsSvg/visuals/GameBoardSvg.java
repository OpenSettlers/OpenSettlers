package org.soc.gwt.client.game.widgetsSvg.visuals;

import java.util.HashMap;

import org.soc.common.game.Game;
import org.soc.common.game.GamePlayer;
import org.soc.common.game.LongestRoadChangedEvent;
import org.soc.common.game.LongestRoadChangedEvent.LongestRoadChangedHandler;
import org.soc.common.game.board.GraphPoint;
import org.soc.common.game.board.GraphSide;
import org.soc.common.game.hexes.Hex;
import org.soc.common.game.pieces.Piece;
import org.soc.common.game.pieces.PlayerPieceList.PlayerPieceListChangedEvent;
import org.soc.common.game.pieces.PlayerPieceList.PlayerPieceListChangedEventHandler;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual;
import org.soc.common.views.widgetsInterface.visuals.VisualFactory;
import org.soc.gwt.client.game.widgetsAbstract.visuals.AbstractGameBoardVisual;

import com.google.gwt.user.client.ui.Widget;

public class GameBoardSvg extends AbstractGameBoardVisual implements
        PlayerPieceListChangedEventHandler, LongestRoadChangedHandler
{
  BoardSvg boardSvg;

  /** @return the boardSvg */
  public BoardSvg getBoardSvg()
  {
    return boardSvg;
  }
  public GameBoardSvg(Game game, int width, int height)
  {
    super(game);
    boardSvg = new BoardSvg(width, height, game.board());
    boardSvg.setBoardBehaviour(new ProxyBehaviour(this));
    for (GraphSide side : board.graph().getSides())
    {
      SideVisual sideVisual = visualFactory.createSideVisual(side);
      sideVisuals.put(side, sideVisual);
      boardSvg.getDrawingArea().add(
              ((SvgVisual) sideVisual).getVectorObject());
    }
    for (GraphPoint point : board.graph().getPoints())
    {
      PointVisual pointVisual = visualFactory.createPointVisual(point);
      pointVisuals.put(point, pointVisual);
      boardSvg.getDrawingArea().add(
              ((SvgVisual) pointVisual).getVectorObject());
    }
    if (game.robber() != null)
    {
      robber = visualFactory.createRobberVisual(game.robber());
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
    for (GamePlayer player : game.players())
    {
      player.towns().addTownsChangedEventHandler(this);
      player.roads().addRoadsChangedEventHandler(this);
      player.cities().addCitiesChangedEventHandler(this);
    }
    game.longestRoute().addLongestRoadChangedEventHandler(this);
  }
  /* (non-Javadoc)
   * 
   * @see org.soc.common.client.visuals.board.AbstractBoardVisual#getHexVisuals() */
  @Override public HashMap<Hex, HexVisual> hexVisuals()
  {
    return boardSvg.hexVisuals();
  }
  @Override public Widget asWidget()
  {
    return boardSvg.getDrawingArea();
  }
  @Override public VisualFactory createVisualFactory()
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
  @Override public void onPlayerPieceListChanged(PlayerPieceListChangedEvent event)
  {
    if (event.getAddedPiece() != null)
      addPiece((Piece) event.getAddedPiece());
    if (event.getRemovedPiece() != null)
      removePiece((Piece) event.getRemovedPiece());
  }
  @Override public void onLongestRoadChanged(LongestRoadChangedEvent event)
  {
    if (event.getNewRoute() != null)
      showLongestRoad(event.getNewRoute());
  }
}
