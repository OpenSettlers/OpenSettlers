package org.soc.common.game.pieces;

import java.io.Serializable;

import org.soc.common.board.pieces.MovedEventEvent.HasMovedEventHandlers;
import org.soc.common.game.GamePlayer;
import org.soc.common.game.GameRules;
import org.soc.common.game.ResourceList;
import org.soc.common.game.board.Board;
import org.soc.common.game.board.GraphPoint;
import org.soc.common.game.board.HexLocation;
import org.soc.common.game.board.HexPoint;
import org.soc.common.game.board.HexSide;
import org.soc.common.game.hexes.Hex;
import org.soc.common.utils.Util;
import org.soc.common.views.meta.Meta;
import org.soc.common.views.widgetsInterface.playerInfo.StockItemWidget;
import org.soc.common.views.widgetsInterface.playerInfo.StockItemWidget.StockItemWidgetFactory;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual;
import org.soc.common.views.widgetsInterface.visuals.VisualFactory;

import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Order;

public interface Piece extends Serializable, Meta {
  public PieceVisual createPiece(VisualFactory visualFactory);

  @GenEvent public class OwnerChanged {
    @Order(0) GamePlayer oldOwner;
    @Order(1) GamePlayer newOwner;
  }

  public interface PlayerPiece extends Piece {
    public GamePlayer player();
    public PlayerPiece setPlayer(GamePlayer player);
    public ResourceList cost();
    public boolean canPay(GamePlayer player);
    public String getName();
    public boolean isStockPiece();
    public void addTo(GamePlayer player);
    public void removeFrom(GamePlayer player);
    public boolean affectsRoad();
  }

  public interface PointPiece extends BoardPiece {
    public HexPoint getPoint();
    public PointPiece setPoint(HexPoint point);
  }

  public interface Producable {
    public ResourceList produce(Hex hex, GameRules rules);
    public HexPoint point();
  }

  public interface SidePiece extends BoardPiece {
    public HexSide getSide();
    public SidePiece setSide(HexSide side);
    public boolean canConnect(GraphPoint graphPoint, SidePiece otherPiece);
    public boolean connectsWithRoad();
    public boolean connectsWithShip();
    public boolean connectsWithBridge();
  }

  public interface StockPiece {
    public StockItemWidget createStockItemWidget(StockItemWidgetFactory factory);
  }

  public interface Moveable extends HasMovedEventHandlers {
    public void move(HexLocation newLocation);

    @GenEvent public class Moved {
      @Order(0) HexLocation newLocation;
      @Order(1) HexLocation oldLocation;
    }
  }

  /* Represents a piece that is put on the board upon playing */
  public interface BoardPiece {
    public void addToBoard(Board board);
    public void removeFromBoard(Board board);
  }

  public abstract class AbstractPiece implements Piece {
    @Override public String name() {
      return Util.shortName(this.getClass());
    }
  }

  public abstract class AbstractPlayerPiece extends AbstractPiece implements
          PlayerPiece
  {
    protected GamePlayer player;

    public GamePlayer player() {
      return player;
    }
    public PlayerPiece setPlayer(GamePlayer player) {
      this.player = player;
      return this;
    }
    public ResourceList cost() {
      throw new RuntimeException();
    }
    public boolean canPay(GamePlayer player) {
      // First, create a copy so we can safely remove resources from it
      ResourceList copy = player.resources().copy();
      // Pay resources player can simply pay for
      copy.subtractResources(cost());
      // Calculate amount of gold we need
      int neededGold =
              // amount of resources this piece needs, minus...
              cost().size() -
                      // the resources the player can simply pay for
                      (player.resources().size() - copy.size());
      // Player can pay given piece if he can trade exactly or more gold as
      // needed
      return player.ports().amountGold(copy) >= neededGold;
    }
    public boolean canBuild(Board board, GamePlayer player) {
      throw new RuntimeException();
    }
    public String getName() {
      return Util.shortName(this.getClass());
    }
  }
}
