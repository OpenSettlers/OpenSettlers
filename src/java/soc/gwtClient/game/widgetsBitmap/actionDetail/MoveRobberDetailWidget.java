package soc.gwtClient.game.widgetsBitmap.actionDetail;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.standard.PlaceRobber;
import soc.common.board.hexes.Hex;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsAbstract.actionDetail.AbstractActionDetailWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class MoveRobberDetailWidget extends AbstractActionDetailWidget
{
    private PlaceRobber placeRobber;

    public MoveRobberDetailWidget(GameWidget gameWidget, PlaceRobber placeRobber)
    {
        super(gameWidget, placeRobber.getPlayer());
        this.placeRobber = placeRobber;

        rootPanel.add(new Image(Resources.icons().moveRobberMedium()));
        Hex affectedHex = gameWidget.getGame().getBoard().getHexes().get(
                placeRobber.getNewLocation());
        rootPanel.add(new Image(affectedHex.getMeta().icon().iconDefault()));
    }

    @Override
    public GameAction getGameAction()
    {
        return placeRobber;
    }

}
