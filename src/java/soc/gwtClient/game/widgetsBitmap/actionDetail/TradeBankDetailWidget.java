package soc.gwtClient.game.widgetsBitmap.actionDetail;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.trading.TradeBank;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsAbstract.actionDetail.AbstractActionDetailWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class TradeBankDetailWidget extends AbstractActionDetailWidget
{
    private TradeBank tradeBank;

    public TradeBankDetailWidget(GameWidget gameWidget, TradeBank tradeBank)
    {
        super(gameWidget, tradeBank.getPlayer());
        this.tradeBank = tradeBank;

        rootPanel.add(new Image(Resources.mediumIcon(tradeBank)));
    }

    @Override
    public GameAction getGameAction()
    {
        return tradeBank;
    }

}
