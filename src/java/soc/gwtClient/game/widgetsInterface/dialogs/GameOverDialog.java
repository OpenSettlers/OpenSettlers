package soc.gwtClient.game.widgetsInterface.dialogs;

import soc.gwtClient.game.behaviour.gameBoard.received.GameOverGameBehaviour;

import com.google.gwt.user.client.ui.IsWidget;

public interface GameOverDialog extends IsWidget
{
    public void update(GameOverGameBehaviour claimVictoryGameBehaviour);
}
