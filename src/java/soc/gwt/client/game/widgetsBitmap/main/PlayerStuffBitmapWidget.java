package soc.gwt.client.game.widgetsBitmap.main;

import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.actions.ActionsWidget;
import soc.common.views.widgetsInterface.generic.Point2D;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.common.views.widgetsInterface.main.HandCardsWidget;
import soc.common.views.widgetsInterface.main.PlayerStuffWidget;
import soc.gwt.client.game.widgetsBitmap.actions.ActionsBitmapWidget;

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
