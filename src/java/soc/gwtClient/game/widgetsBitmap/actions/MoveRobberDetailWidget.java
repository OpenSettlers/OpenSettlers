package soc.gwtClient.game.widgetsBitmap.actions;

import soc.common.actions.gameAction.turnActions.standard.PlaceRobber;
import soc.common.board.hexes.Hex;
import soc.gwtClient.game.widgetsAbstract.AbstractPlayerDetailWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class MoveRobberDetailWidget extends AbstractPlayerDetailWidget
{
    private PlaceRobber placeRobber;

    public MoveRobberDetailWidget(GameWidget gamePanel, PlaceRobber placeRobber)
    {
        super(gamePanel, placeRobber.getPlayer());
        this.placeRobber = placeRobber;

        rootPanel.add(new Image(Resources.icons().moveRobberMedium()));
        Hex affectedHex = gamePanel.getGame().getBoard().getHexes().get(
                placeRobber.getNewLocation());
        rootPanel.add(new Image(Resources.hexIcon(affectedHex)));
    }

}
