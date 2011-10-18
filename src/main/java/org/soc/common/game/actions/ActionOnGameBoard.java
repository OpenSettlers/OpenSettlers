package org.soc.common.game.actions;

import java.util.*;

import org.soc.common.game.board.*;
import org.soc.common.views.widgetsInterface.visuals.*;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.PointVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.SideVisual;

/** A player moved/clicked on a GameBoard */
public interface ActionOnGameBoard {
  public void start(GameBoardVisual gameVisual);
  public void setNeutral(GameBoardVisual visual);
  public void clicked(PieceVisual pieceVisual, GameBoardVisual gameBoardVisual);
  public void mouseEnter(PieceVisual pieceVisual, GameBoardVisual gameBoardVisual);
  public void mouseOut(PieceVisual pieceVisual, GameBoardVisual gameBoardVisual);
  public GameAction gameAction();

  /** Shows possible HexPoints on the board player can build onto (e.g. city, town) */
  public abstract class BuildPointOnBoard implements ActionOnGameBoard {
    protected List<HexPoint> possibleLocations = new ArrayList<HexPoint>();

    @Override public void mouseEnter(PieceVisual pieceVisual, GameBoardVisual board) {
      PointVisual pointVisual = pieceVisual.getPointVisual();
      if (pointVisual != null) {
        PointVisual hexPointVisual = (PointVisual) pieceVisual;
        hexPointVisual.setSelected(true);
      }
    }
    @Override public void mouseOut(PieceVisual pieceVisual, GameBoardVisual board) {
      PointVisual pointVisual = pieceVisual.getPointVisual();
      if (pointVisual != null) {
        PointVisual hexPointVisual = (PointVisual) pieceVisual;
        hexPointVisual.setSelected(false);
      }
    }
  }

  /** Show dark map, no mouse overs */
  public class DisabledMap implements ActionOnGameBoard {
    @Override public void clicked(PieceVisual pieceVisual, GameBoardVisual gameBoardVisual) {}
    @Override public void mouseEnter(PieceVisual pieceVisual, GameBoardVisual gameBoardVisual) {}
    @Override public void mouseOut(PieceVisual pieceVisual, GameBoardVisual gameBoardVisual) {}
    @Override public void setNeutral(GameBoardVisual gameVisual) {
      // for (HexVisual hexVisual : gameVisual.getHexVisuals().values())
      // hexVisual.setDarkened(false);
    }
    @Override public void start(GameBoardVisual gameVisual) {
      // for (HexVisual hexVisual : gameVisual.getHexVisuals().values())
      // hexVisual.setDarkened(true);
    }
    @Override public GameAction gameAction() {
      return null;
    }
  }

  /** NullActionOnBoard */
  public class NoActionOnBoard implements ActionOnGameBoard {
    @Override public void clicked(PieceVisual pieceVisual, GameBoardVisual gameBoardVisual) {}
    @Override public GameAction gameAction() {
      return null;
    }
    @Override public void mouseEnter(PieceVisual pieceVisual, GameBoardVisual gameBoardVisual) {}
    @Override public void mouseOut(PieceVisual pieceVisual, GameBoardVisual gameBoardVisual) {}
    @Override public void setNeutral(GameBoardVisual visual) {}
    @Override public void start(GameBoardVisual gameVisual) {}
  }

  /** Shows all possible sides for the user to select a side. */
  public abstract class BuildSideOnBoard implements ActionOnGameBoard {
    @Override public void setNeutral(GameBoardVisual visual) {
      // /visual.getHexSidesVisual().setVisible(false);
    }
    @Override public void start(GameBoardVisual gameVisual) {
      // gameVisual.getHexSidesVisual().setVisible(true);
    }
    @Override public void mouseEnter(PieceVisual pieceVisual, GameBoardVisual board) {
      SideVisual sideVisual = pieceVisual.sideVisual();
      if (sideVisual != null)
        pieceVisual.setSelected(true);
    }
    @Override public void mouseOut(PieceVisual pieceVisual, GameBoardVisual board) {
      SideVisual sideVisual = pieceVisual.sideVisual();
      if (sideVisual != null)
        pieceVisual.setSelected(false);
    }
  }
}
