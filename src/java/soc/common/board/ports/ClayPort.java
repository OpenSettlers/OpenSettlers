package soc.common.board.ports;

import soc.common.board.resources.Clay;
import soc.common.board.resources.Resource;
import soc.common.internationalization.I18n;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.gwt.client.images.Resources;

public class ClayPort extends TwoToOneResourcePort
{
    private static final long serialVersionUID = -4470924811845087514L;
    private static Clay clay = new Clay();
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().clayPort16(),
                        Resources.icons().clayPort32(), Resources.icons()
                                        .clayPort48());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "ClayPort";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().clayPort();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().clayPortDescription();
        }

    };

    @Override
    public Port copy()
    {
        return new ClayPort();
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
        return clay;
    }
}