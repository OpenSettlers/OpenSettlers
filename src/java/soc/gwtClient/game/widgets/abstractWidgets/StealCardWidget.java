package soc.gwtClient.game.widgets.abstractWidgets;

import soc.common.actions.gameAction.turnActions.standard.PlaceRobber;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.behaviour.RollDiceResult;

import com.google.gwt.user.client.ui.IsWidget;

public interface StealCardWidget extends IsWidget
{
    public void update(RollDiceResult result, PlaceRobber placeRobber,
            GamePlayer player);

    public void cardPicked(GamePlayer opponent);
}
