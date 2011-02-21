package soc.gwtClient.game.widgetsBitmap.actions;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turns.RolledSame;
import soc.gwtClient.game.widgetsAbstract.AbstractActionDetailWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

import com.google.gwt.user.client.ui.Label;

public class RolledSameDetailBitmapWidget extends AbstractActionDetailWidget
{
    private RolledSame rolledSame;

    public RolledSameDetailBitmapWidget(GameWidget gameWidget,
            RolledSame rolledSame)
    {
        super(gameWidget, rolledSame.getPlayer());
        this.rolledSame = rolledSame;

        Label labelSameRoll = new Label(Integer.toString(rolledSame
                .getHighRoll()));
        labelSameRoll.setStyleName("label-title");
        rootPanel.add(labelSameRoll);
    }

    @Override
    public GameAction getGameAction()
    {
        return rolledSame;
    }

}
