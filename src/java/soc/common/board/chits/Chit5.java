package soc.common.board.chits;

import soc.common.internationalization.I18n;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.gwtClient.images.Resources;

public class Chit5 extends AbstractChit
{
    private static final long serialVersionUID = -2537731750828852429L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().chit516(), Resources
                        .icons().chit532(), Resources.icons().chit548());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "Chit5";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().chit5();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().chit5Description();
        }

    };

    @Override
    public Meta getMeta()
    {
        return meta;
    }
    @Override
    public Chit copy()
    {
        return new Chit5();
    }

    @Override
    public int getChance()
    {
        return 4;
    }

    @Override
    public int getNumber()
    {
        return 5;
    }

}
