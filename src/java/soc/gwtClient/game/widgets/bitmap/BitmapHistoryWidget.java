package soc.gwtClient.game.widgets.bitmap;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.actions.gameAction.turnActions.EndTurn;
import soc.common.game.logs.ActionPerformedEvent;
import soc.common.game.logs.ActionPerformedEventHandler;
import soc.gwtClient.game.abstractWidgets.IGameHistoryWidget;
import soc.gwtClient.game.abstractWidgets.IGamePanel;

public class BitmapHistoryWidget implements IGameHistoryWidget, ActionPerformedEventHandler
{
    StringActionCell cell = new StringActionCell();
    ScrollPanel rootPanel = new ScrollPanel();
    CellList<AbstractGameAction> actionsList = new CellList<AbstractGameAction>(cell);
    IGamePanel gamePanel;
    ListDataProvider<AbstractGameAction> dataProvider = new ListDataProvider<AbstractGameAction>();
    
    public BitmapHistoryWidget(IGamePanel gamePanel)
    {
        super();
        this.gamePanel = gamePanel;
        
        dataProvider.addDataDisplay(actionsList);
        
        gamePanel.getGame().getGameLog().addActionPerformedEventHandler(this);
        
        rootPanel.add(actionsList);
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

    @Override
    public void onActionPerformed(ActionPerformedEvent event)
    {
        dataProvider.getList().add(0, event.getPerformedAction());
    }

}
