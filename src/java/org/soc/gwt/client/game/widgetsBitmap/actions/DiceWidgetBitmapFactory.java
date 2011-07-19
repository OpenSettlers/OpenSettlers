package org.soc.gwt.client.game.widgetsBitmap.actions;

import org.soc.common.game.player.GamePlayer;
import org.soc.common.views.widgetsInterface.actions.DiceWidget;
import org.soc.common.views.widgetsInterface.actions.DiceWidgetFactory;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsSvg.actions.SvgStandardDiceWidget;

public class DiceWidgetBitmapFactory implements DiceWidgetFactory
{
    private GameWidget gameWidget;
    private GamePlayer player;

    public DiceWidgetBitmapFactory(GameWidget gameWidget, GamePlayer player)
    {
        super();
        this.gameWidget = gameWidget;
        this.player = player;
    }

    @Override
    public DiceWidget createCardsDeckDiceWidget()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DiceWidget createCitiesKnightDiceWidget()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DiceWidget createStandardDiceWidget()
    {
        return new SvgStandardDiceWidget(gameWidget, player);
    }

    @Override
    public DiceWidget createVolcanoDiceWidget()
    {
        // TODO Auto-generated method stub
        return null;
    }
}
