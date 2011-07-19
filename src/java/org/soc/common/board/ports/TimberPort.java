package org.soc.common.board.ports;

import org.soc.common.board.resources.Resource;
import org.soc.common.board.resources.Timber;
import org.soc.common.internationalization.I18n;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.meta.Meta;
import org.soc.gwt.client.images.Resources;

public class TimberPort extends TwoToOneResourcePort
{
    private static final long serialVersionUID = 5272778324471373594L;
    private Timber timber = new Timber();
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().timberPort32(),
                        null, null, null);

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return null;
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().timberPort();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().timberPortDescription();
        }

    };

    @Override
    public Port copy()
    {
        return new TimberPort();
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
        return timber;
    }
}