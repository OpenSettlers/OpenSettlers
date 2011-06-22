package soc.common.board.pieces;

import soc.common.annotations.SeaFarers;
import soc.common.board.layout.HexLocation;
import soc.common.board.pieces.abstractPieces.AbstractPiece;
import soc.common.internationalization.I18n;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.common.views.widgetsInterface.visuals.PieceVisual;
import soc.common.views.widgetsInterface.visuals.VisualFactory;

@SeaFarers
public class Pirate extends AbstractPiece
{
    private static final long serialVersionUID = -2575172800909661845L;
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
            return "Pirate";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().pirate();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().pirateDescription();
        }

    };
    private HexLocation location;

    public Pirate(HexLocation hexLocation)
    {
        this.location = hexLocation;
    }

    public Pirate()
    {
        // Default empty serializable constructor
    }

    /** @return the location */
    public HexLocation getLocation()
    {
        return location;
    }

    /** @param location
     *            the location to set */
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
