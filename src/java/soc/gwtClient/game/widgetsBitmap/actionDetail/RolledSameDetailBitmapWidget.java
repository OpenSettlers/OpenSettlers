package soc.gwtClient.game.widgetsBitmap.actionDetail;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turns.RolledSame;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsAbstract.actionDetail.AbstractActionDetailWidget;

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
