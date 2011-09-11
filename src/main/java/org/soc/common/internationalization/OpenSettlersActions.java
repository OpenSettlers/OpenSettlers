package org.soc.common.internationalization;

import com.google.gwt.i18n.client.Messages;

/** Messages internationalization for all actions */
public interface OpenSettlersActions extends Messages {
  @DefaultMessage("{0} has built a town") String builtTown(String playerName);
  @DefaultMessage("{0} should build a town") String builtTownToDo(String playerName);
  @DefaultMessage("{0} should build a city") String builtCityToDo(String playerName);
  @DefaultMessage("{0} has built a city") String builtCity(String playerName);
  @DefaultMessage("{0} should build a road") String builtRoadToDo(String playerName);
  @DefaultMessage("{0} has built a road") String builtRoad(String playerName);
  @DefaultMessage("{0} should claim victory") String claimVictoryToDo(String playerName);
  @DefaultMessage("{0} should move the robber") String placeRobberToDo(String playerName);
  @DefaultMessage("{0} should rob an opponent") String robPlayerToDo(String playerName);
  @DefaultMessage("{0} should roll the dice") String rollDiceToDo(String playerName);
  @DefaultMessage("{0} rolled the dice and threw {1}") String rollDice(String playerName,
          int diceRoll);
  @DefaultMessage("This action does not have a ToDo message implemented") String noToDo();
  @DefaultMessage("{0} ended turn") String endedTurn(String playerName);
  @DefaultMessage("{0} should place a port") String placePortToDo(String playerName);
  @DefaultMessage("{0} should build a ship") String buildShipToDo(String playerName);
  @DefaultMessage("{0} should accept the settings") String acceptSettingsToDo(String name);
  @DefaultMessage("{0} should loose {1} cards") String looseCardsToDo(String playerName,
          int amountCards);
}
