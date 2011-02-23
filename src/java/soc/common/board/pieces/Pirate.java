package soc.common.board.pieces;

import soc.common.annotations.SeaFarers;
import soc.common.board.HexLocation;
import soc.common.board.pieces.abstractPieces.AbstractPiece;
import soc.common.ui.Graphics;
import soc.common.ui.Icon;
import soc.common.ui.IconImpl;
import soc.common.ui.meta.Meta;
import soc.gwtClient.game.widgetsInterface.generic.ToolTip;
import soc.gwtClient.game.widgetsInterface.visuals.PieceVisual;
import soc.gwtClient.game.widgetsInterface.visuals.VisualFactory;

@SeaFarers
public class Pirate extends AbstractPiece
{
    private static final long serialVersionUID = -2575172800909661845L;
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(null, null, null, null);

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public Graphics graphics()
        {
            return null;
        }

        @Override
        public String getName()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getLocalizedName()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getDescription()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public ToolTip createToolTip()
        {
            // TODO Auto-generated method stub
            return null;
        }
    };
    private HexLocation location;

    public Pirate(HexLocation hexLocation)
    {
        this.location = hexLocation;
    }

    /**
     * @return the location
     */
    public HexLocation getLocation()
    {
        return location;
    }

    /**
     * @param location
     *            the location to set
     */
    public Pirate setLocation(HexLocation location)
    {
        this.location = location;
        return this;
    }

    @Override
    public PieceVisual createPiece(VisualFactory visualFactory)
    {
        return visualFactory.createPirateVisual(this);
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }
}
