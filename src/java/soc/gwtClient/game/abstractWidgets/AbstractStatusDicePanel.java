package soc.gwtClient.game.abstractWidgets;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractStatusDicePanel implements IStatusDicePanel
{
    protected DockLayoutPanel rootPanel = new DockLayoutPanel(Unit.EM);
    protected Label lblStatus = new Label("Some text here to tell the player what to do");
    protected IDiceWidget diceWidget;
    protected IGamePanel gamePanel;
    
    protected abstract ComplexPanel createRootPanel();
    protected abstract IDiceWidget createDiceWidget();
    
    public AbstractStatusDicePanel(IGamePanel gamePanel)
    {
        super();
        this.gamePanel = gamePanel;
        
        diceWidget = createDiceWidget();
        
        rootPanel.addEast(diceWidget.asWidget(), 10);
        rootPanel.add(lblStatus);
    }
    
    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }
}