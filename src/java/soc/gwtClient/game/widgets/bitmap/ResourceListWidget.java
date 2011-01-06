package soc.gwtClient.game.widgets.bitmap;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class ResourceListWidget implements IsWidget
{
    private HorizontalPanel rootPanel = new HorizontalPanel();

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

}
