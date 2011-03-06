package soc.common.board.pieces;

import soc.common.annotations.CitiesKnights;
import soc.common.annotations.Pioneers;
import soc.common.board.Board;
import soc.common.board.HexPoint;
import soc.common.board.pieces.abstractPieces.AbstractPlayerPiece;
import soc.common.board.pieces.abstractPieces.PointPiece;
import soc.common.game.player.GamePlayer;
import soc.common.internationalization.I18n;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.common.views.widgetsInterface.visuals.PieceVisual;
import soc.common.views.widgetsInterface.visuals.VisualFactory;

@CitiesKnights
@Pioneers
public class Wall extends AbstractPlayerPiece implements PointPiece
{
    private static final long serialVersionUID = 5417867350330920841L;
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
            return "Wall";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().wall();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().wallDescription();
        }
    };
    private HexPoint pointLocation;

    @Override
    public HexPoint getPoint()
    {
        return pointLocation;
    }

    @Override
    public PointPiece setPoint(HexPoint point)
    {
        this.pointLocation = point;
        return this;
    }

    @Override
    public boolean isStockPiece()
    {
        return true;
    }

    @Override
    public void addToPlayer(GamePlayer player)
    {
    }

    @Override
    public void removeFromPlayer(GamePlayer player)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean affectsRoad()
    {
        return false;
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
    public PieceVisual createPiece(VisualFactory visualFactory)
    {
        return visualFactory.createWallVisual(this);
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }
}
