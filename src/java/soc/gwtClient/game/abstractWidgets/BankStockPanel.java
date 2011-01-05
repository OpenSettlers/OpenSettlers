package soc.gwtClient.game.abstractWidgets;

import soc.common.board.resources.Resource;
import soc.common.game.Game;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;

public interface BankStockPanel extends IsWidget
{
    public ComplexPanel createRootPanel();

    public IBankStockResourceWidget createBankStockResourceWidget(
            Resource resource);

    public IDevelopmentCardsAmountWidget createDevelopmentCardsWidget(Game game);
}
