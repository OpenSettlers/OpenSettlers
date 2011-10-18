package org.soc.gwt.client.game.widgetsSvg.visuals;

import java.util.*;

import org.soc.common.core.GenericList.Adds.Added;
import org.soc.common.core.GenericList.Removes.Removed;
import org.soc.common.game.*;
import org.soc.common.game.LongestRoadChangedEvent.LongestRoadChangedHandler;
import org.soc.common.game.board.*;
import org.soc.common.game.hexes.*;
import org.soc.common.game.pieces.*;
import org.soc.common.views.widgetsInterface.visuals.*;
import org.soc.gwt.client.game.widgetsAbstract.visuals.*;

import com.google.gwt.user.client.ui.*;

public class GameBoardSvg extends AbstractGameBoardVisual implements
        LongestRoadChangedHandler
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
    for (GraphSide side : board.graph().sides())
    {
      SideVisual sideVisual = visualFactory.createSideVisual(side);
      sideVisuals.put(side, sideVisual);
      boardSvg.getDrawingArea().add(
              ((SvgVisual) sideVisual).getVectorObject());
    }
    for (GraphPoint point : board.graph().points())
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
  private void addHandlers() {
    for (GamePlayer player : game.players()) {
      player.towns().addAddedHandler(new Added<Town>() {
        @Override public void added(Town item) {
          onPieceAdded(item);
        }
      });
      player.towns().addRemovedHandler(new Removed<Town>() {
        @Override public void removed(Town item) {
          onPieceRemoved(item);
        }
      });
      player.roads().addAddedHandler(new Added<Road>() {
        @Override public void added(Road item) {
          onPieceAdded(item);
        }
      });
      player.roads().addRemovedHandler(new Removed<Road>() {
        @Override public void removed(Road item) {
          onPieceRemoved(item);
        }
      });
      player.cities().addAddedHandler(new Added<City>() {
        @Override public void added(City item) {
          onPieceAdded(item);
        }
      });
      player.cities().addRemovedHandler(new Removed<City>() {
        @Override public void removed(City item) {
          onPieceRemoved(item);
        }
      });
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
  private void onPieceRemoved(Piece removed) {
    if (removed != null)
      removePiece(removed);
  }
  private void onPieceAdded(Piece added) {
    if (added != null)
      addPiece(added);
  }
  @Override public void onLongestRoadChanged(LongestRoadChangedEvent event)
  {
    if (event.getNewRoute() != null)
      showLongestRoad(event.getNewRoute());
  }
}
