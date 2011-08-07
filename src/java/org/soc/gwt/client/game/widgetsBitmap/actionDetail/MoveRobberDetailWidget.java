package org.soc.gwt.client.game.widgetsBitmap.actionDetail;

import org.soc.common.actions.gameAction.GameAction;
import org.soc.common.actions.gameAction.standard.PlaceRobber;
import org.soc.common.board.hexes.Hex;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.actionDetail.AbstractActionDetailWidget;
import org.soc.gwt.client.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class MoveRobberDetailWidget extends AbstractActionDetailWidget
{
    private PlaceRobber placeRobber;

    public MoveRobberDetailWidget(GameWidget gameWidget, PlaceRobber placeRobber)
    {
        super(gameWidget, placeRobber.getPlayer());
        this.placeRobber = placeRobber;

        rootPanel.add(new Image(Resources.mediumIcon(placeRobber)));
        Hex affectedHex = gameWidget.getGame().getBoard().getHexes()
                        .get(placeRobber.getNewLocation());
        rootPanel.add(new Image(Resources.mediumIcon(affectedHex)));
    }
    @Override
    public GameAction getGameAction()
    {
        return placeRobber;
    }

}
