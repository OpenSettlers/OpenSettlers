package soc.common.board.chits;

import soc.common.internationalization.I18n;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.gwtClient.images.Resources;

public class Chit2 extends AbstractChit
{
    private static final long serialVersionUID = 289888659445647640L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().chit216(), Resources
                        .icons().chit232(), Resources.icons().chit248());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "Chit2";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().chit2();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().chit2Description();
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
        return new Chit2();
    }

    @Override
    public int getChance()
    {
        return 1;
    }

    @Override
    public int getNumber()
    {
        return 2;
    }
}
