package soc.gwtClient.game.widgets.abstractWidgets;

import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractTradeListWidget implements ITradeListWidget
{
    private ResourceList wantResources = new ResourceList();
    private ResourceList giveResources = new ResourceList();
    private HorizontalPanel rootPanel = new HorizontalPanel();

    public AbstractTradeListWidget(ResourceList wantResources,
            ResourceList giveResources)
    {
        rootPanel.setWidth("200px");
        this.wantResources = wantResources;
        this.giveResources = giveResources;

        for (Resource resource : wantResources)
        {
            rootPanel.add(new Image(Resources.card(resource)));
        }
        rootPanel.add(new Label(" in exchange for "));
        for (Resource resource : giveResources)
        {
            rootPanel.add(new Image(Resources.card(resource)));
        }
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
