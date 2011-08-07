package org.soc.common.game.settings;

import org.soc.common.views.meta.Meta;

public class MaxCardsInHandWhen7 implements GameSetting
{
    private static final long serialVersionUID = 7512816350991029293L;
    private int maxCardsInHand = 7;

    @Override
    public void executeGameSetting(GameSettings gameSettings)
    {
        gameSettings.getMaxCardsInHandWhen7().setMaxCardsInHand(maxCardsInHand);
    }

    @Override
    public Meta getMeta()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public MaxCardsInHandWhen7 setMaxCardsInHand(int maxCardsInHand)
    {
        this.maxCardsInHand = maxCardsInHand;
        return this;
    }

    public int getMaxCardsInHand()
    {
        return maxCardsInHand;
    }

}
