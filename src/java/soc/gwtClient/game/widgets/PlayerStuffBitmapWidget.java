package soc.gwtClient.game.widgets;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.Point2D;
import soc.gwtClient.game.abstractWidgets.ActionsWidget;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.abstractWidgets.HandCardsWidget;
import soc.gwtClient.game.widgets.abstractWidgets.PlayerStuffWidget;
import soc.gwtClient.game.widgets.standard.bitmap.HandCardsBitmapWidget;
import soc.gwtClient.game.widgets.standard.bitmap.actions.ActionsBitmapWidget;

import com.google.gwt.user.client.ui.HorizontalPanel;

public class PlayerStuffBitmapWidget extends HorizontalPanel implements
        PlayerStuffWidget
{
    protected HandCardsWidget handCards;
    protected ActionsWidget buildPallette;
    private GamePanel gamePanel;
    private GamePlayer player;

    public PlayerStuffBitmapWidget(GamePanel gamePanel, GamePlayer player)
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
