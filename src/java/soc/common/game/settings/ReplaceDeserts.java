package soc.common.game.settings;

import soc.common.views.meta.Meta;

/*
 *  Whether or not deserts will be replaced by given Hex, which can be either
 *  a volcano or a Jungle.
 */
public class ReplaceDeserts implements GameSetting
{
    private static final long serialVersionUID = 8048348633205957371L;

    @Override
    public void executeGameSetting(GameSettings gameSettings)
    {
        gameSettings.setReplaceDeserts(this);
    }

    @Override
    public Meta getMeta()
    {
        // TODO Auto-generated method stub
        return null;
    }
}
