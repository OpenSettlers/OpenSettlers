package soc.gwt.client.game.widgetsBitmap.actions;

import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.actions.DiceWidget;
import soc.common.views.widgetsInterface.actions.DiceWidgetFactory;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.gwt.client.game.widgetsSvg.actions.SvgStandardDiceWidget;

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
