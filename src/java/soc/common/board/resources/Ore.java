package soc.common.board.resources;

import soc.common.internationalization.I18n;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.gwt.client.images.Resources;

public class Ore extends AbstractResource
{
    private static final long serialVersionUID = -8778560987710166668L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().ore16(), Resources
                        .icons().ore32(), Resources.icons().ore48());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "Ore";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().ore();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().oreDescription();
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
        return "Purple";
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
        return new Ore();
    }

    @Override
    public int hashCode()
    {
        return 4;
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }
}
