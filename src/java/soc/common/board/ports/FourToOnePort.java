package soc.common.board.ports;

import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;
import soc.common.internationalization.I18n;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.gwtClient.images.Resources;

public class FourToOnePort extends AbstractPort
{
    private static final long serialVersionUID = -9000999299490338479L;
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
            return "FourToOnePort";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().fourToOnePort();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().fourToOnePortDescription();
        }

    };

    /*
     * Performs a 4:1 trade on a list of resources
     * 
     * @see
     * soc.common.board.ports.Port#divide(soc.common.board.resources.ResourceList
     * , soc.common.board.resources.Resource)
     */
    @Override
    public int divide(ResourceList resources, Resource type)
    {
        return resources.size() / getInAmount();
    }

    /*
     * Four resources are needed for one trade
     * 
     * @see soc.common.board.ports.Port#getInAmount()
     */
    @Override
    public int getInAmount()
    {
        return 4;
    }

    /*
     * One gold is gained from one trade
     * 
     * @see soc.common.board.ports.Port#getOutAmount()
     */
    @Override
    public int getOutAmount()
    {
        return 1;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.board.ports.Port#canTrade(soc.common.board.resources.Resource)
     */
    @Override
    public boolean canTrade(Resource resource)
    {
        return true;
    }

    @Override
    public Port copy()
    {
        return new FourToOnePort();
    }

    @Override
    public String getColor()
    {
        return "White";
    }

    @Override
    public Meta getMeta()
    {
        return null;
    }

    @Override
    public boolean hasResource()
    {
        return false;
    }
}