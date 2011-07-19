package soc.common.board.resources;

import soc.common.internationalization.I18n;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.gwt.client.images.Resources;

public class Wheat extends AbstractResource
{
    private static final long serialVersionUID = -7393820454171830461L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().wheat16(), Resources
                        .icons().wheat32(), Resources.icons().wheat48());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "Wheat";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().wheat();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().wheatDescription();
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
        return "Yellow";
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
        return new Wheat();
    }

    @Override
    public int hashCode()
    {
        return 7;
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }
}
