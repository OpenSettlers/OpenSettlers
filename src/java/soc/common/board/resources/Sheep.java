package soc.common.board.resources;

import soc.common.internationalization.I18n;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.gwtClient.images.Resources;

public class Sheep extends AbstractResource
{
    private static final long serialVersionUID = 8756575918873023940L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().sheep16(), Resources
                        .icons().sheep32(), Resources.icons().sheep48());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "Sheep";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().sheep();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().sheepDescription();
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
        return "LightGreen";
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
        return new Sheep();
    }

    @Override
    public int hashCode()
    {
        return 5;
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }
}
