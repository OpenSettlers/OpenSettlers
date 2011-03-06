package soc.common.board.chits;

import soc.common.internationalization.I18n;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.gwtClient.images.Resources;

public class Chit10 extends AbstractChit
{
    private static final long serialVersionUID = 3847669375763234881L;
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
            return "Chit10";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().chit10();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().chit10Description();
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
        return new Chit10();
    }

    @Override
    public int getChance()
    {
        return 3;
    }

    @Override
    public int getNumber()
    {
        return 10;
    }

}
