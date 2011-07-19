package org.soc.common.board.resources;

import org.soc.common.annotations.SeaFarers;
import org.soc.common.internationalization.I18n;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.meta.Meta;
import org.soc.gwt.client.images.Resources;

@SeaFarers
public class Gold extends AbstractResource
{
    private static final long serialVersionUID = 6496837317375783863L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().gold16(), Resources
                        .icons().gold32(), Resources.icons().gold48());

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
     * @see org.soc.common.board.resources.Resource#getColor()
     */
    @Override
    public String getColor()
    {
        return "Gold";
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.board.resources.Resource#isTradeable()
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
