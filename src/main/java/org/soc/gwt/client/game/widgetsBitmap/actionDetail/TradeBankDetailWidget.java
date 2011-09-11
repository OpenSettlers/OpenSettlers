package org.soc.gwt.client.game.widgetsBitmap.actionDetail;

import org.soc.common.game.actions.GameAction;
import org.soc.common.game.trading.TradeBank;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.actionDetail.AbstractActionDetailWidget;
import org.soc.gwt.client.images.R;

import com.google.gwt.user.client.ui.Image;

public class TradeBankDetailWidget extends AbstractActionDetailWidget
{
    private TradeBank tradeBank;

    public TradeBankDetailWidget(GameWidget gameWidget, TradeBank tradeBank)
    {
        super(gameWidget, tradeBank.player());
        this.tradeBank = tradeBank;

        rootPanel.add(new Image(R.mediumIcon(tradeBank)));
    }

    @Override
    public GameAction getGameAction()
    {
        return tradeBank;
    }

}
