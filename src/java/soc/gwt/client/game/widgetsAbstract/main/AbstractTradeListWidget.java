package soc.gwt.client.game.widgetsAbstract.main;

import soc.common.board.resources.ResourceList;
import soc.common.views.widgetsInterface.generic.ResourceListWidget;
import soc.common.views.widgetsInterface.main.TradeListWidget;
import soc.gwt.client.game.widgetsBitmap.generic.ResourceListBitmapWidget;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractTradeListWidget implements TradeListWidget
{
    private HorizontalPanel rootPanel = new HorizontalPanel();
    private ResourceListWidget wantResourceList;
    private ResourceListWidget giveResourceList;
    private ResourceList wantResources = new ResourceList();
    private ResourceList giveResources = new ResourceList();

    public AbstractTradeListWidget()
    {
        wantResourceList = new ResourceListBitmapWidget(wantResources, null,
                null);
        wantResourceList.setHeight("3em");
        giveResourceList = new ResourceListBitmapWidget(giveResources, null,
                null);
        giveResourceList.setHeight("3em");
        rootPanel.add(wantResourceList);
        rootPanel.add(new Label(" for "));
        rootPanel.add(giveResourceList);

        rootPanel.setWidth("200px");
        rootPanel.setHeight("3em");
    }

    /**
     * @return the wantResources
     */
    public ResourceList getWantResources()
    {
        return wantResources;
    }

    /**
     * @return the giveResources
     */
    public ResourceList getGiveResources()
    {
        return giveResources;
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

}
