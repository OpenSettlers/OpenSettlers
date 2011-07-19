package org.soc.gwt.client.game.widgetsAbstract.playerInfo;

import org.soc.common.game.player.GamePlayer;
import org.soc.common.views.widgetsInterface.payerInfo.LongestRoadDetailWidget;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractLongestRoadWidget implements LongestRoadDetailWidget
{
    protected ComplexPanel rootPanel;
    protected GamePlayer player;
    
    public AbstractLongestRoadWidget(GamePlayer player)
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
