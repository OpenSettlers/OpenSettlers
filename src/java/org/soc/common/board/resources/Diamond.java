package org.soc.common.board.resources;

import org.soc.common.annotations.Sea3D;
import org.soc.common.internationalization.I18n;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.meta.Meta;

@Sea3D
public class Diamond extends AbstractResource
{
    private static final long serialVersionUID = -592207257460216530L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(null, null, null, null);

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "Diamond";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().diamond();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().diamondDescription();
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
        return "Grey";
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
        return new Diamond();
    }

    @Override
    public int hashCode()
    {
        return 2;
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

}
