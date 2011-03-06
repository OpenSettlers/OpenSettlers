package soc.common.board.chits;

import soc.common.internationalization.I18n;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.gwtClient.images.Resources;

public class Chit4 extends AbstractChit
{
    private static final long serialVersionUID = 8551201477474416255L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().chit416(), Resources
                        .icons().chit432(), Resources.icons().chit448());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "Chit4";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().chit4();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().chit4Description();
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
        return new Chit4();
    }

    @Override
    public int getChance()
    {
        return 3;
    }

    @Override
    public int getNumber()
    {
        return 4;
    }

}
