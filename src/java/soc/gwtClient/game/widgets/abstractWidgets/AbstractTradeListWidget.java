package soc.gwtClient.game.widgets.abstractWidgets;

import soc.common.board.resources.ResourceList;
import soc.gwtClient.game.widgets.bitmap.ResourceListBitmapWidget;

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
        rootPanel.add(new Label(" in exchange for "));
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
