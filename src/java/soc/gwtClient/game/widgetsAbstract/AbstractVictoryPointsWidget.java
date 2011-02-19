package soc.gwtClient.game.widgetsAbstract;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsInterface.playerDetail.PlayerDetailWidget;
import soc.gwtClient.game.widgetsInterface.playerDetail.VictoryPointAmountWidget;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractVictoryPointsWidget implements
        VictoryPointAmountWidget, MouseOutHandler, MouseOverHandler
{
    protected ComplexPanel rootPanel;
    protected GamePlayer player;
    protected GameWidget gamePanel;
    protected PlayerDetailWidget vpDetailWidget;

    public AbstractVictoryPointsWidget(GameWidget gamePanel, GamePlayer player)
    {
        this.gamePanel = gamePanel;
        this.player = player;

        rootPanel = createRootPanel();
        vpDetailWidget = createPlayerDetailWidget();

        rootPanel.addDomHandler(this, MouseOutEvent.getType());
        rootPanel.addDomHandler(this, MouseOverEvent.getType());
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

    @Override
    public void onMouseOut(MouseOutEvent event)
    {
        gamePanel.getDetailContainerManager().hideMouseOverDetail(player);
    }

    @Override
    public void onMouseOver(MouseOverEvent event)
    {
        gamePanel.getDetailContainerManager().showMouseOverDetail(player,
                vpDetailWidget);
    }

}
