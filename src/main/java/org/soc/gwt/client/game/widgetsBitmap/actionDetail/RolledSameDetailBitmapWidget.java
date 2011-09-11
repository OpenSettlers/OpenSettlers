package org.soc.gwt.client.game.widgetsBitmap.actionDetail;

import org.soc.common.game.actions.GameAction;
import org.soc.common.game.actions.RolledSame;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.actionDetail.AbstractActionDetailWidget;

import com.google.gwt.user.client.ui.Label;

public class RolledSameDetailBitmapWidget extends AbstractActionDetailWidget
{
    private RolledSame rolledSame;

    public RolledSameDetailBitmapWidget(GameWidget gameWidget,
            RolledSame rolledSame)
    {
        super(gameWidget, rolledSame.player());
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
