package org.soc.gwt.client.game.widgetsBitmap.dialogs;

import java.util.HashMap;

import org.soc.common.game.Game;
import org.soc.common.game.GamePlayer;
import org.soc.common.game.GamePlayerList;
import org.soc.common.game.actions.RobPlayer;
import org.soc.common.game.actions.RobPlayer.RobPlayerGameBehaviour;
import org.soc.common.views.widgetsInterface.dialogs.StealCardWidget;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.common.views.widgetsInterface.main.StealPlayerCardWidget;
import org.soc.gwt.client.game.widgetsBitmap.generic.StealPlayerCardBitmapWidget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/** Displays ui showing a list of players and their resource cards faced down When a player clicks a
 * card, it only picked a player to steal a card from. The actual stealing is a random function
 * which is performed in the server class. */
public class StealCardDialog extends DialogBox implements StealCardWidget {
  private GameWidget gameWidget;
  private GamePlayer player;
  private VerticalPanel playersCards = new VerticalPanel();
  private HashMap<GamePlayer, StealPlayerCardWidget> playerWidgets = new HashMap<GamePlayer, StealPlayerCardWidget>();
  private RobPlayerGameBehaviour robPlayerGameBehaviour;

  public StealCardDialog(GameWidget gameWidget, GamePlayer player) {
    this();
    this.gameWidget = gameWidget;
    this.player = player;
    // Add a widget for all players to the rootpanel
    for (GamePlayer playerr : gameWidget.game().players()) {
      StealPlayerCardWidget widget = new StealPlayerCardBitmapWidget(playerr, this);
      playersCards.add(widget);
      playerWidgets.put(playerr, widget);
    }
  }
  public StealCardDialog() {
    setText("Steal a card from an opponent");
    setHTML("New dialog");
    VerticalPanel verticalPanel = new VerticalPanel();
    setWidget(verticalPanel);
    verticalPanel.setSize("100%", "100%");
    HorizontalPanel horizontalPanel = new HorizontalPanel();
    horizontalPanel.setSpacing(10);
    verticalPanel.add(horizontalPanel);
    Label lblPickACard = new Label(
            "Pick a card from any player to steal it");
    horizontalPanel.add(lblPickACard);
    playersCards.setSpacing(10);
    verticalPanel.add(playersCards);
    HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
    horizontalPanel_1.setSpacing(10);
    verticalPanel.add(horizontalPanel_1);
    Button button = new Button("New button");
    button.setText("Don't steal a card & be refreshing");
    button.addClickHandler(new ClickHandler()
    {
      @Override public void onClick(ClickEvent event)
      {
        cardPicked(null);
      }
    });
    horizontalPanel_1.add(button);
  }
  @Override public void cardPicked(GamePlayer opponent) {
    RobPlayer robPlayer = (RobPlayer) new RobPlayer()
            .setRobbedPlayer(opponent)
            .setPlayer(player);
    robPlayerGameBehaviour.robbedPlayer(robPlayer);
    hide();
  }
  @Override public void update(RobPlayerGameBehaviour robPlayerGameBehaviour) {
    this.robPlayerGameBehaviour = robPlayerGameBehaviour;
    this.player = gameWidget.playingPlayer();
    Game game = gameWidget.game();
    GamePlayerList robCandidates = game.playersAtHex(game.robber().location(), gameWidget
            .playingPlayer());
    // Only make the players' visible who can be robbed.
    for (GamePlayer playerr : playerWidgets.keySet()) {
      if (robCandidates.contains(playerr)) {
        playerWidgets.get(playerr).update(player);
        playerWidgets.get(playerr).setVisible(true);
      }
      else {
        playerWidgets.get(playerr).setVisible(false);
      }
    }
    show();
  }
}
