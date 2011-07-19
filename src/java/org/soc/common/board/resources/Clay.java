package org.soc.common.board.resources;

import org.soc.common.internationalization.I18n;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.meta.Meta;
import org.soc.gwt.client.images.Resources;

public class Clay extends AbstractResource
{
    private static final long serialVersionUID = -4151296314674387899L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().clay16(), Resources
                        .icons().clay32(), Resources.icons().clay48());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "Clay";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().clay();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().clayDescription();
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
        return "Red";
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.board.resources.AbstractResource#isTradeable()
     */
    @Override
    public boolean isTradeable()
    {
        return true;
    }

    @Override
    public Resource copy()
    {
        return new Clay();
    }

    @Override
    public int hashCode()
    {
        return 1;
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }
}
