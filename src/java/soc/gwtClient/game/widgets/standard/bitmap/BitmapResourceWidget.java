package soc.gwtClient.game.widgets.standard.bitmap;

import soc.common.board.resources.Resource;
import soc.common.board.resources.Wheat;
import soc.gwtClient.game.widgets.abstractWidgets.IResourceWidget;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class BitmapResourceWidget implements IResourceWidget
{
    Image image;
    Resource resource;
    
    public BitmapResourceWidget(Resource resource)
    {
        super();
        this.resource = resource;
        String location="icons/32/" + resource.getName().toLowerCase() + ".png";
        image = new Image(location);
    }

    @Override
    public Widget asWidget()
    {
        return image;
    }

    @Override
    public Resource getResource()
    {
        return resource;
    }

    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler)
    {
        return image.addClickHandler(handler);
    }
}
