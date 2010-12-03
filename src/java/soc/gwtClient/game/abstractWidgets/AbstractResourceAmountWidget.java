package soc.gwtClient.client.game;

import soc.common.game.Player;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AbstractResourceAmountWidget implements IResourceAmountWidget
{
    protected Player player;
    protected ComplexPanel rootPanel;

    public AbstractResourceAmountWidget(Player player)
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
