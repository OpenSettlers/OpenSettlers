package org.soc.common.board.pieces;

import org.soc.common.annotations.SeaFarers;
import org.soc.common.board.layout.HexLocation;
import org.soc.common.board.pieces.abstractPieces.AbstractPiece;
import org.soc.common.internationalization.I18n;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.meta.Meta;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual;
import org.soc.common.views.widgetsInterface.visuals.VisualFactory;

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
