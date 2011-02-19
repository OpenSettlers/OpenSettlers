package soc.gwtClient.game.widgetsInterface.main;

import soc.common.board.resources.Resource;
import soc.common.game.Game;
import soc.gwtClient.game.widgetsInterface.playerDetail.DevelopmentCardsAmountWidget;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;

public interface BankStockWidget extends IsWidget
{
    public ComplexPanel createRootPanel();

    public BankStockResourceWidget createBankStockResourceWidget(
            Resource resource);

    public DevelopmentCardsAmountWidget createDevelopmentCardsWidget(Game game);
}
