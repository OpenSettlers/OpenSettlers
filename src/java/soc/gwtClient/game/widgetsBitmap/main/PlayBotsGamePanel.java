package soc.gwtClient.game.widgetsBitmap.main;

import soc.gwtClient.game.Point2D;
import soc.gwtClient.game.widgetsAbstract.AbstractGamePanel;
import soc.gwtClient.game.widgetsInterface.actions.ActionsWidget;
import soc.gwtClient.game.widgetsInterface.dialogs.LooseCardsDialog;
import soc.gwtClient.game.widgetsInterface.dialogs.TradePlayerDialog;
import soc.gwtClient.game.widgetsInterface.main.DebugWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidgetFactory;

import com.google.gwt.user.client.ui.Widget;

public class PlayBotsGamePanel extends AbstractGamePanel
{

    /*
     * (non-Javadoc)
     * 
     * @see soc.gwtClient.game.abstractWidgets.AbstractGamePanel#initialize()
     */
    @Override
    protected void initialize()
    {
        super.initialize();

        GameWidgetFactory gameWidgetFactory = gamePanelLayoutWidget
                .getGameWidgetFactory();

        looseCardsDialog = gameWidgetFactory.createLooseCardsDialog();
    }

    @Override
    public GameWidgetFactory createGameWidgetFactory()
    {
        return null;
    }

    @Override
    public ActionsWidget getActionsWidget()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DebugWidget getDebugPanel()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LooseCardsDialog getLooseCardsDialog()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Point2D getTopLeftDiceWidgetPosition()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TradePlayerDialog getTradePlayerUI()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Widget getRootWidget()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
