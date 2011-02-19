package soc.gwtClient.game.widgetsBitmap.actions;

import soc.common.actions.gameAction.turnActions.standard.TradeBank;
import soc.gwtClient.game.widgetsAbstract.AbstractPlayerDetailWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class TradeBankDetailWidget extends AbstractPlayerDetailWidget
{
    private TradeBank tradeBank;

    public TradeBankDetailWidget(GameWidget gamePanel, TradeBank tradeBank)
    {
        super(gamePanel, tradeBank.getPlayer());
        this.tradeBank = tradeBank;

        rootPanel.add(new Image(Resources.icons().bankTrade()));
        // TODO: add resources
    }

}
