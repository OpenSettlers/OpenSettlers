package soc.gwtClient.game.widgetsBitmap.actions;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.trading.TradeBank;
import soc.gwtClient.game.widgetsAbstract.AbstractActionDetailWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class TradeBankDetailWidget extends AbstractActionDetailWidget
{
    private TradeBank tradeBank;

    public TradeBankDetailWidget(GameWidget gameWidget, TradeBank tradeBank)
    {
        super(gameWidget, tradeBank.getPlayer());
        this.tradeBank = tradeBank;

        rootPanel.add(new Image(Resources.icons().bankTrade()));
        // TODO: add resources
    }

    @Override
    public GameAction getGameAction()
    {
        return tradeBank;
    }

}
