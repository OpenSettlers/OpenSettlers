package org.soc.gwt.client.game.widgetsBitmap.dialogs;

import org.soc.common.game.actions.ClaimVictory.GameOverGameBehaviour;
import org.soc.common.views.widgetsInterface.dialogs.GameOverDialog;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class HotSeatGameOverDialog extends DialogBox implements GameOverDialog
{
  private Label lblMessage;

  public HotSeatGameOverDialog() {
    setHTML("Game over!");
    VerticalPanel verticalPanel = new VerticalPanel();
    verticalPanel.setSpacing(10);
    setWidget(verticalPanel);
    verticalPanel.setSize("100%", "100%");
    lblMessage = new Label("New label");
    verticalPanel.add(lblMessage);
    Button btnOK = new Button("Yey. ");
    verticalPanel.add(btnOK);
  }
  @Override public void update(GameOverGameBehaviour claimVictoryGameBehaviour)
  {
    lblMessage.setText(claimVictoryGameBehaviour.getClaimVictory()
            .player().user().name()
            + "won the game. yey.");
  }
}
