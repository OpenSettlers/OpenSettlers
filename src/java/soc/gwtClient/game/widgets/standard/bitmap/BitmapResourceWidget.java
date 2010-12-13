package soc.gwtClient.game.widgets.standard.bitmap;

import soc.common.board.resources.Resource;
import soc.common.board.resources.Wheat;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class BitmapResourceWidget implements IsWidget
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
}
