package soc.common.game.settings;

import soc.common.views.meta.Icon;
import soc.common.views.meta.Meta;

public class NoSevenRounds implements GameSetting
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

    private int noSevenRounds = 0;

    @Override
    public Meta getMeta()
    {
        return meta;
    }

    @Override
    public void executeGameSetting(GameSettings gameSettings)
    {
        gameSettings.setNoSevenRounds(this);
    }

    public NoSevenRounds setNoSevenRounds(int noSevenRounds)
    {
        this.noSevenRounds = noSevenRounds;
        return this;
    }

    public int getNoSevenRounds()
    {
        return noSevenRounds;
    }
}
