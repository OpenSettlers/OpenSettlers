package org.soc.common.board.ports;

import org.soc.common.board.resources.Resource;
import org.soc.common.board.resources.Sheep;
import org.soc.common.internationalization.I18n;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.meta.Meta;
import org.soc.gwt.client.images.Resources;

public class SheepPort extends TwoToOneResourcePort
{
    private static final long serialVersionUID = -8884377093715212493L;
    private static Sheep sheep = new Sheep();
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().sheepPort16(),
                        Resources.icons().sheepPort32(), Resources.icons()
                                        .sheepPort48());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "Sheep port";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().sheepPort();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().sheepPortDescription();
        }

    };

    @Override
    public Port copy()
    {
        return new SheepPort();
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
        return sheep;
    }
}
