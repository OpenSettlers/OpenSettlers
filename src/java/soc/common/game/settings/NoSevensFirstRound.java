package soc.common.game.settings;

import soc.common.ui.Graphics;
import soc.common.ui.Icon;
import soc.common.ui.meta.Meta;
import soc.gwtClient.game.widgetsInterface.generic.ToolTip;

public class NoSevensFirstRound implements Setting
{
    private static final long serialVersionUID = -3205225202914063292L;
    private static Meta meta = new Meta()
    {
        @Override
        public Icon icon()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Graphics graphics()
        {
            // TODO Auto-generated method stub
            return null;
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

        @Override
        public ToolTip createToolTip()
        {
            // TODO Auto-generated method stub
            return null;
        }
    };

    private boolean noSevensFirstRound = true;

    /**
     * @return the noSevensFirstRound
     */
    public boolean isNoSevensFirstRound()
    {
        return noSevensFirstRound;
    }

    /**
     * @param noSevensFirstRound
     *            the noSevensFirstRound to set
     */
    public NoSevensFirstRound setNoSevensFirstRound(boolean noSevensFirstRound)
    {
        this.noSevensFirstRound = noSevensFirstRound;
        return this;
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }
}
