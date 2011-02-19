package soc.gwtClient.game.widgetsBitmap.main;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.Point2D;
import soc.gwtClient.game.widgetsBitmap.actions.ActionsBitmapWidget;
import soc.gwtClient.game.widgetsInterface.actions.ActionsWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsInterface.main.HandCardsWidget;
import soc.gwtClient.game.widgetsInterface.main.PlayerStuffWidget;

import com.google.gwt.user.client.ui.HorizontalPanel;

public class PlayerStuffBitmapWidget extends HorizontalPanel implements
        PlayerStuffWidget
{
    protected HandCardsWidget handCards;
    protected ActionsWidget buildPallette;
    private GameWidget gamePanel;
    private GamePlayer player;

    public PlayerStuffBitmapWidget(GameWidget gamePanel, GamePlayer player)
    {
        super();
        this.gamePanel = gamePanel;
        this.player = player;

        handCards = new HandCardsBitmapWidget(player);
        buildPallette = new ActionsBitmapWidget(gamePanel, player);

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
