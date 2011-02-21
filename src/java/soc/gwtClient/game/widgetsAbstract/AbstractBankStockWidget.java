package soc.gwtClient.game.widgetsAbstract;

import soc.common.board.resources.Resource;
import soc.common.game.Game;
import soc.gwtClient.game.widgetsBitmap.playerDetail.BankStockResourceBitmapWidget;
import soc.gwtClient.game.widgetsInterface.main.BankStockWidget;
import soc.gwtClient.game.widgetsInterface.main.BankStockResourceWidget;
import soc.gwtClient.game.widgetsInterface.playerDetail.DevelopmentCardsAmountWidget;

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