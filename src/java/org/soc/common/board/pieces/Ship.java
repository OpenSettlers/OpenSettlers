package org.soc.common.board.pieces;

import org.soc.common.annotations.SeaFarers;
import org.soc.common.board.Board;
import org.soc.common.board.layout.HasSide;
import org.soc.common.board.layout.HexSide;
import org.soc.common.board.pieces.abstractPieces.AbstractPlayerPiece;
import org.soc.common.board.resources.ResourceList;
import org.soc.common.board.resources.Sheep;
import org.soc.common.board.resources.Timber;
import org.soc.common.board.routing.GraphPoint;
import org.soc.common.game.player.GamePlayer;
import org.soc.common.internationalization.I18n;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.meta.Meta;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual;
import org.soc.common.views.widgetsInterface.visuals.VisualFactory;

@SeaFarers
public class Ship extends AbstractPlayerPiece implements HasSide
{
    private static final long serialVersionUID = -8125317569107776067L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(null, null, null, null);

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "Ship";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().ship();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().shipDescription();
        }
    };
    private HexSide sideLocation;

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.board.pieces.PlayerPiece#canBuild(org.soc.common.board.Board,
     * org.soc.common.game.Player)
     */
    @Override
    public boolean canBuild(Board board, GamePlayer player)
    {
        if (player.getStock().getShips().size() == 0)
            return false;

        // TODO: port to java
        // if (GetShipBuildPlaces(game, board).Count == 0) return false;

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.board.pieces.PlayerPiece#getCost()
     */
    @Override
    public ResourceList getCost()
    {
        ResourceList result = new ResourceList();

        result.add(new Timber());
        result.add(new Sheep());

        return result;
    }

    // TODO: port to java
    public boolean canMove(Board board, GamePlayer player)
    {
        // If there is no ship to move, bugger out
        // if (player.getShips().size() == 0)
        // return false;

        // when can only move a ship once per turn
        // var movedShip = (from MoveShipAction ms in
        // game.GameLog.OfType<MoveShipAction>()
        // where ms.TurnID == game.CurrentTurn
        // select ms).SingleOrDefault();
        // if (movedShip != null)
        // return false;

        // We should be able to have a moveable ship, and a location to put the
        // moved ship
        // in order to be able to move te ship
        // if (GetMoveableShips(game, game.Board).Count == 0)
        // return false;

        return true;
    }

    @Override
    public boolean isStockPiece()
    {
        return true;
    }

    @Override
    public void addToPlayer(GamePlayer player)
    {
        player.getShips().moveFrom(player.getStock().getShips(), this);
        player.getSidePieces().add(this);
    }

    @Override
    public void removeFromPlayer(GamePlayer player)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public HexSide getSide()
    {
        return sideLocation;
    }

    @Override
    public HasSide setSide(HexSide side)
    {
        this.sideLocation = side;
        return this;
    }

    @Override
    public boolean affectsRoad()
    {
        return true;
    }

    @Override
    public void addToBoard(Board board)
    {
    }

    @Override
    public void removeFromBoard(Board board)
    {
    }

    @Override
    public boolean canConnect(GraphPoint graphPoint, HasSide otherPiece)
    {
        return (player.equals(graphPoint.getPlayer()) || graphPoint.getPlayer() == null)
                        && otherPiece.connectsWithShip();
    }

    @Override
    public boolean connectsWithBridge()
    {
        return false;
    }

    @Override
    public boolean connectsWithRoad()
    {
        return false;
    }

    @Override
    public boolean connectsWithShip()
    {
        return true;
    }

    @Override
    public PieceVisual createPiece(VisualFactory visualFactory)
    {
        return visualFactory.createShipVisual(this);
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

}
