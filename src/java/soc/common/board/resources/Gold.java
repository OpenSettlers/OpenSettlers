package soc.common.board.resources;

import soc.common.annotations.SeaFarers;
import soc.common.internationalization.I18n;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.gwtClient.images.Resources;

@SeaFarers
public class Gold extends AbstractResource
{
    private static final long serialVersionUID = 6496837317375783863L;
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().goldCard(), null,
                        null, null);

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "Gold";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().gold();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().goldDescription();
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
        return "Gold";
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.resources.Resource#isTradeable()
     */
    @Override
    public boolean isTradeable()
    {
        return false;
    }

    @Override
    public Resource copy()
    {
        return new Gold();
    }

    @Override
    public int hashCode()
    {
        return 3;
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }
}
