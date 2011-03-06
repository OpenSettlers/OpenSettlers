package soc.common.board.ports;

import soc.common.board.resources.Resource;
import soc.common.board.resources.Wheat;
import soc.common.internationalization.I18n;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.gwtClient.images.Resources;

public class WheatPort extends TwoToOneResourcePort
{
    private static final long serialVersionUID = -7842108867497790007L;
    public static Wheat wheat = new Wheat();
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().wheatPort16(),
                        Resources.icons().wheatPort32(), Resources.icons()
                                        .wheatPort48());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "WheatPort";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().wheatPort();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().wheatPortDescription();
        }
    };

    @Override
    public Port copy()
    {
        return new WheatPort();
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.ports.AbstractPort#getResource()
     */
    @Override
    public Resource getResource()
    {
        return wheat;
    }
}