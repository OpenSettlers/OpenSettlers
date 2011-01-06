package soc.gwtClient.game.abstractWidgets;

import soc.common.game.DiceChangedEventHandler;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractDiceWidget implements DiceWidget, DiceChangedEventHandler
{
    protected ComplexPanel rootPanel;
    
    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

}
