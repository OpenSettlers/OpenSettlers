package org.soc.common.board.ports;

import org.soc.common.board.resources.Ore;
import org.soc.common.board.resources.Resource;
import org.soc.common.internationalization.I18n;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.meta.Meta;
import org.soc.gwt.client.images.Resources;

public class OrePort extends TwoToOneResourcePort
{
    private static final long serialVersionUID = 1142423242419232130L;
    private static Ore ore = new Ore();
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().orePort16(),
                        Resources.icons().orePort32(), Resources.icons()
                                        .orePort48());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "OrePort";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().orePort();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().orePortDescription();
        }

    };

    @Override
    public Port copy()
    {
        return new OrePort();
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.board.ports.AbstractPort#getResource()
     */
    @Override
    public Resource getResource()
    {
        return ore;
    }

}
