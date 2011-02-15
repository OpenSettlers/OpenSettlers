package soc.gwtClient.game.widgets.standard.bitmap.playerDetail.actions;

import soc.common.actions.gameAction.turnActions.standard.TradeBank;
import soc.gwtClient.game.abstractWidgets.AbstractPlayerDetailWidget;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class TradeBankDetailWidget extends AbstractPlayerDetailWidget
{
    private TradeBank tradeBank;

    public TradeBankDetailWidget(GamePanel gamePanel, TradeBank tradeBank)
    {
        super(gamePanel, tradeBank.getPlayer());
        this.tradeBank = tradeBank;

        rootPanel.add(new Image(Resources.icons().bankTrade()));
        // TODO: add resources
    }

}
