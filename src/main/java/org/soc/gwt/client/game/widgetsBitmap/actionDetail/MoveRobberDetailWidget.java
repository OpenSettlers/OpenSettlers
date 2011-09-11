package org.soc.gwt.client.game.widgetsBitmap.actionDetail;

import org.soc.common.game.actions.GameAction;
import org.soc.common.game.actions.PlaceRobber;
import org.soc.common.game.hexes.Hex;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.actionDetail.AbstractActionDetailWidget;
import org.soc.gwt.client.images.R;

import com.google.gwt.user.client.ui.Image;

public class MoveRobberDetailWidget extends AbstractActionDetailWidget
{
    private PlaceRobber placeRobber;

    public MoveRobberDetailWidget(GameWidget gameWidget, PlaceRobber placeRobber)
    {
        super(gameWidget, placeRobber.player());
        this.placeRobber = placeRobber;

        rootPanel.add(new Image(R.mediumIcon(placeRobber)));
        Hex affectedHex = gameWidget.game().board().hexes()
                        .get(placeRobber.getNewLocation());
        rootPanel.add(new Image(R.mediumIcon(affectedHex)));
    }
    @Override
    public GameAction getGameAction()
    {
        return placeRobber;
    }

}
