package soc.gwtClient.game.widgets.bitmap;

import soc.common.game.Player;
import soc.gwtClient.game.abstractWidgets.IGamePanel;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class BitmapResourcePickerWidget implements IsWidget
{
    private Player player;
    private IGamePanel gamePanel;
    
    // When set, adds amount of resources equal to the best port for that resource
    private boolean isPortPick;
    
    protected ComplexPanel rootPanel;
    
    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }
}
