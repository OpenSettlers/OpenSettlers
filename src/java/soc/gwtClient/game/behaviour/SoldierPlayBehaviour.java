package soc.gwtClient.game.behaviour;

import soc.common.actions.gameAction.turnActions.standard.PlaceRobber;
import soc.common.actions.gameAction.turnActions.standard.RobPlayer;
import soc.gwtClient.visuals.behaviour.gameBoard.PlaceRobberBehaviour;

/*
 * Specifies special behaviour implemented by PlaySoldierGameBehaviour and 
 * RollDiceResult, to accommodate moving the robber and stealing a resource 
 * card from an opponent.
 */
public interface SoldierPlayBehaviour
{
    // Call to notify a spot has been picked to move the robber to
    public void pickedRobberSpot(PlaceRobberBehaviour placeRobberBehaviour);

    // gets the PlaceRobber GameAction
    public PlaceRobber getPlaceRobber();

    // Call to notify an opponent has been robbed from a resource card
    public void robbedPlayer(RobPlayer robPlayer);
}
