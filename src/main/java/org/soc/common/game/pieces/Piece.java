package org.soc.common.game.pieces;

import org.soc.common.core.GenericList.HasId;
import org.soc.common.core.GenericList.Model;
import org.soc.common.core.OpenZettlers.OsModel;
import org.soc.common.core.Props.IsChild;
import org.soc.common.core.Props.PropertyList.PropertyTypeList;
import org.soc.common.core.property.*;
import org.soc.common.game.*;
import org.soc.common.game.Resources.MutableResourceList;
import org.soc.common.game.Resources.ResourceList;
import org.soc.common.game.board.*;
import org.soc.common.game.hexes.*;
import org.soc.common.game.pieces.MovedEvent.HasMovedHandlers;
import org.soc.common.utils.*;
import org.soc.common.views.widgetsInterface.playerInfo.*;
import org.soc.common.views.widgetsInterface.playerInfo.StockItemWidget.StockItemWidgetFactory;
import org.soc.common.views.widgetsInterface.visuals.*;

import com.gwtplatform.dispatch.annotation.*;

public interface Piece<K> extends OsModel<K> {
  public PieceVisual createPiece(VisualFactory visualFactory);

  @GenEvent public class OwnerChanged {
    @Order(0) GamePlayer oldOwner;
    @Order(1) GamePlayer newOwner;
  }

  public interface PlayerPiece<K> extends Piece<K> {
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

  public interface Buyable {
    public ResourceList cost();
  }

  public interface Producer {
    public ResourceList produce(Hex hex, GameRules rules);
    public HexPoint point();
  }

  public interface StockPiece {
    public StockItemWidget createStockItemWidget(StockItemWidgetFactory factory);
  }

  public interface Moveable extends HasMovedHandlers {
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

    public interface SidePiece extends BoardPiece {
      public HexSide getSide();
      public SidePiece setSide(HexSide side);
      public boolean canConnect(GraphPoint graphPoint, SidePiece otherPiece);
      public boolean connectsWithRoad();
      public boolean connectsWithShip();
      public boolean connectsWithBridge();
    }

    public interface PointPiece extends BoardPiece {
      public HexPoint getPoint();
      public PointPiece setPoint(HexPoint point);
    }
  }

  public abstract class AbstractPiece<K> implements Piece<K> {
    protected K id;

    @Override public Model copy() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public K id() {
      return id;
    }
    @Override public HasId setId(K id) {
      this.id = id;
      return this;
    }
    @Override public IdScope scope() {
      return IdScope.APPLICATION;
    }
    @Override public IsChild getProp(StaticProperty type) {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public PropertyTypeList properties() {
      // TODO Auto-generated method stub
      return null;
    }
  }

  public abstract class AbstractPlayerPiece<K>
          extends
          AbstractPiece<K>
          implements
          PlayerPiece<K> {
    protected GamePlayer player;

    public GamePlayer player() {
      return player;
    }
    public PlayerPiece setPlayer(GamePlayer player) {
      this.player = player;
      return this;
    }
    public ResourceList cost() {
      throw new RuntimeException(); // eh
    }
    public boolean canPay(GamePlayer player) {
      // First, create a copy so we can safely remove resources from it
      MutableResourceList copy = player.resources().copy();
      // Pay resources player can simply pay for
      copy.removeList(cost());
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
