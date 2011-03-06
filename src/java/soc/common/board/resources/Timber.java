package soc.common.board.resources;

import soc.common.internationalization.I18n;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.gwtClient.images.Resources;

public class Timber extends AbstractResource
{
    private static final long serialVersionUID = -6537069648667733465L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().timber16(),
                        Resources.icons().timber32(), Resources.icons()
                                        .timber48());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "Timber";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().timber();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().timberDescription();
        }

    };

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.resources.Resource#getColor()
     */
    @Override
    public String getColor()
    {
        return "DarkGreen";
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.resources.AbstractResource#isTradeable()
     */
    @Override
    public boolean isTradeable()
    {
        return true;
    }

    @Override
    public Resource copy()
    {
        return new Timber();
    }

    @Override
    public int hashCode()
    {
        return 6;
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }
}
