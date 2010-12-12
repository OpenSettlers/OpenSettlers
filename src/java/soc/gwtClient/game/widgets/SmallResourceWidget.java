package soc.gwtClient.game.widgets;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

import soc.common.board.resources.Clay;
import soc.common.board.resources.Ore;
import soc.common.board.resources.Resource;
import soc.common.board.resources.Sheep;
import soc.common.board.resources.Timber;
import soc.common.board.resources.Wheat;
import soc.gwtClient.game.widgets.abstractWidgets.IResourceWidget;

public class SmallResourceWidget implements IResourceWidget
{
    private Image image;
    private Resource resource;
    
    public SmallResourceWidget(Resource resource)
    {
        this.resource=resource;
        String imgLocation = "icons/32/";
        if (resource instanceof Timber)
        {
            imgLocation += "timber.png";
        }
        if (resource instanceof Wheat)
        {
            imgLocation += "wheat.png";
        }
        if (resource instanceof Ore)
        {
            imgLocation += "ore.png";
        }
        if (resource instanceof Clay)
        {
            imgLocation += "clay.png";
        }
        if (resource instanceof Sheep)
        {
            imgLocation += "sheep.png";
        }
        image = new Image(imgLocation);
    }

    @Override
    public Resource getResource()
    {
        return resource;
    }

    @Override
    public Widget asWidget()
    {
        return image;
    }
}
