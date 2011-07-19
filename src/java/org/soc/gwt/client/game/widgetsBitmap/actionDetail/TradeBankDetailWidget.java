package org.soc.gwt.client.game.widgetsBitmap.actionDetail;

import org.soc.common.actions.gameAction.GameAction;
import org.soc.common.actions.gameAction.trading.TradeBank;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.actionDetail.AbstractActionDetailWidget;
import org.soc.gwt.client.images.Resources;

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
