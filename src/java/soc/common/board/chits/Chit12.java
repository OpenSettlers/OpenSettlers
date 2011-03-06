package soc.common.board.chits;

import soc.common.internationalization.I18n;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.gwtClient.images.Resources;

public class Chit12 extends AbstractChit
{
    private static final long serialVersionUID = -2356981320798618769L;
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().chit1216(),
                        Resources.icons().chit1232(), Resources.icons()
                                        .chit1248());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "Chit12";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().chit12();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().chit12Description();
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
        return new Chit12();
    }

    @Override
    public int getChance()
    {
        return 1;
    }

    @Override
    public int getNumber()
    {
        return 12;
    }

}
