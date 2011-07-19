package soc.gwt.client.game.widgetsAbstract.main;

import soc.common.board.resources.Resource;
import soc.common.game.Game;
import soc.common.views.widgetsInterface.main.BankStockResourceWidget;
import soc.common.views.widgetsInterface.main.BankStockWidget;
import soc.common.views.widgetsInterface.payerInfo.DevelopmentCardsAmountWidget;
import soc.gwt.client.game.widgetsBitmap.playerInfo.BankStockResourceBitmapWidget;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AbstractBankStockWidget implements BankStockWidget
{
    protected ComplexPanel rootPanel;
    protected Game game;
    protected DevelopmentCardsAmountWidget devCards;

    public AbstractBankStockWidget(Game game)
    {
        this.game = game;
        rootPanel = createRootPanel();

        for (Resource playableResource : game.getRules()
                .getSupportedResources())
        {
            BankStockResourceWidget bankResourceWidget = createBankStockResourceWidget(playableResource);
            rootPanel.add(bankResourceWidget);
        }
    }

    @Override
    public ComplexPanel createRootPanel()
    {
        return new HorizontalPanel();
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

    @Override
    public BankStockResourceWidget createBankStockResourceWidget(
            Resource resource)
    {
        return new BankStockResourceBitmapWidget(game.getBank(), resource);
    }

    @Override
    public DevelopmentCardsAmountWidget createDevelopmentCardsWidget(Game game)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
