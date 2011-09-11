package org.soc.common.views.widgetsInterface.main;

import org.soc.common.game.actions.RollDice.RollDiceInGame;

import com.google.gwt.user.client.ui.IsWidget;

/*
 * Shows a popup panel with a list of resources gained by each player
 * due to a rolled dice
 */
public interface ResourcesGainedWidget extends IsWidget
{
  public void update(RollDiceInGame rollDiceResult);
  public void show();
}
