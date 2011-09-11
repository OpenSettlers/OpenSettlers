package org.soc.gwt.client.game.widgetsBitmap.main;

import org.soc.common.game.GamePlayer;
import org.soc.common.views.widgetsInterface.actions.ActionsWidget;
import org.soc.common.views.widgetsInterface.generic.Point2D;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.common.views.widgetsInterface.main.HandCardsWidget;
import org.soc.common.views.widgetsInterface.main.PlayerStuffWidget;
import org.soc.gwt.client.game.widgetsBitmap.actions.ActionsBitmapWidget;

import com.google.gwt.user.client.ui.HorizontalPanel;

public class PlayerStuffBitmapWidget extends HorizontalPanel implements
        PlayerStuffWidget
{
    protected HandCardsWidget handCards;
    protected ActionsWidget buildPallette;
    private GameWidget gameWidget;
    private GamePlayer player;

    public PlayerStuffBitmapWidget(GameWidget gameWidget, GamePlayer player)
    {
        super();
        this.gameWidget = gameWidget;
        this.player = player;

        handCards = new HandCardsBitmapWidget(player);
        buildPallette = new ActionsBitmapWidget(gameWidget, player);

        this.add(handCards);
        this.add(buildPallette);
    }

    @Override
    public Point2D getDiceWidgetTopLeftPosition()
    {
        return buildPallette.getTopLeftDiceWidgetPosition();
    }

    @Override
    public ActionsWidget getActionsWidget()
    {
        return buildPallette;
    }

    @Override
    public HandCardsWidget getHandCardsWidget()
    {
        return handCards;
    }
}
