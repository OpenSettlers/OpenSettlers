package soc.gwtClient.client.game;

import soc.common.game.Player;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AbstractVictoryPointsWidget implements IVictoryPointsWidget
{
    protected ComplexPanel rootPanel;
    protected Player player;
    
    public AbstractVictoryPointsWidget(Player player)
    {
        this.player=player;
        
        rootPanel = createRootPanel();
    }

    @Override
    public ComplexPanel createRootPanel()
    {
        return new VerticalPanel();
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

}
