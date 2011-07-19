package org.soc.common.board.pieces;

import org.soc.common.annotations.CitiesKnights;
import org.soc.common.annotations.Pioneers;
import org.soc.common.board.Board;
import org.soc.common.board.layout.HasPoint;
import org.soc.common.board.layout.HexPoint;
import org.soc.common.board.pieces.abstractPieces.AbstractPlayerPiece;
import org.soc.common.game.player.GamePlayer;
import org.soc.common.internationalization.I18n;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.meta.Meta;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual;
import org.soc.common.views.widgetsInterface.visuals.VisualFactory;

@CitiesKnights
@Pioneers
public class Wall extends AbstractPlayerPiece implements HasPoint
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
    public HasPoint setPoint(HexPoint point)
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
