package soc.common.board.ports;

import soc.common.board.HexLocation;
import soc.common.board.RotationPosition;
import soc.common.internationalization.I18n;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.gwtClient.images.Resources;

/*
 * Represents a port which can be placed on the board.
 */
public class PossiblePort extends AbstractPort
{
    private static final long serialVersionUID = 5430147957433152867L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().port16(), Resources
                        .icons().port32(), Resources.icons().port48());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "PossiblePort";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().possiblePort();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().possiblePortDescription();
        }

    };

    public PossiblePort()
    {
    }

    public PossiblePort(HexLocation hexLocation,
                    RotationPosition rotationPosition)
    {
        super(hexLocation, rotationPosition);
    }

    @Override
    public Port copy()
    {
        return new PossiblePort();
    }

    @Override
    public String getColor()
    {
        return "White";
    }

    @Override
    public Meta getMeta()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasResource()
    {
        return false;
    }
}
