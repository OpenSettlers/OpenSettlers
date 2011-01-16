package soc.gwtClient.game.abstractWidgets;

import soc.gwtClient.game.behaviour.received.GameOverGameBehaviour;

import com.google.gwt.user.client.ui.IsWidget;

public interface GameOverDialog extends IsWidget
{
    public void update(GameOverGameBehaviour claimVictoryGameBehaviour);
}
