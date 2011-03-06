package soc.common.board.pieces;

import soc.common.board.HexLocation;
import soc.common.board.pieces.abstractPieces.AbstractPiece;
import soc.common.internationalization.I18n;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.common.views.widgetsInterface.visuals.PieceVisual;
import soc.common.views.widgetsInterface.visuals.VisualFactory;
import soc.gwtClient.images.Resources;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

public class Robber extends AbstractPiece
{
    private static final long serialVersionUID = 2162591486291994070L;
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().robber16(),
                        Resources.icons().robber32(), Resources.icons()
                                        .robber48());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "Robber";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().robber();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().robberDescription();
        }
    };
    private HexLocation location;
    private transient SimpleEventBus eventBus = new SimpleEventBus();

    public Robber(HexLocation hexLocation)
    {
        this.location = hexLocation;
    }

    /** @return the location */
    public HexLocation getLocation()
    {
        return location;
    }

    /** @param location
     *            the location to set */
    public Robber setLocation(HexLocation location)
    {
        if (!this.location.equals(location))
        {
            HexLocation oldLocation = this.location;
            this.location = location;
            eventBus.fireEvent(new MovedEvent(location, oldLocation));
        }

        return this;
    }

    public HandlerRegistration addMoveEventHandler(MovedEventHandler handler)
    {
        return eventBus.addHandler(MovedEvent.TYPE, handler);
    }

    @Override
    public PieceVisual createPiece(VisualFactory visualFactory)
    {
        return visualFactory.createRobberVisual(this);
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }
}
