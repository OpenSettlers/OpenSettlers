package soc.common.board.ports;

import soc.common.board.resources.Clay;
import soc.common.board.resources.Resource;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.gwtClient.images.Resources;

public class ClayPort extends TwoToOneResourcePort
{
    private static final long serialVersionUID = -4470924811845087514L;
    private static Clay clay = new Clay();
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().clayPort(), null,
                        null, null);

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getLocalizedName()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getDescription()
        {
            // TODO Auto-generated method stub
            return null;
        }

    };

    @Override
    public Port copy()
    {
        return new ClayPort();
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.ports.AbstractPort#getResource()
     */
    @Override
    public Resource getResource()
    {
        return clay;
    }
}