package soc.common.views.widgetsInterface.main;

import soc.common.board.resources.Resource;
import soc.common.game.Game;
import soc.common.views.widgetsInterface.payerInfo.DevelopmentCardsAmountWidget;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;

public interface BankStockWidget extends IsWidget
{
    public ComplexPanel createRootPanel();

    public BankStockResourceWidget createBankStockResourceWidget(
            Resource resource);

    public DevelopmentCardsAmountWidget createDevelopmentCardsWidget(Game game);
}
