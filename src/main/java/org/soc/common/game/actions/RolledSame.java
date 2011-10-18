package org.soc.common.game.actions;

import java.util.*;

import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.game.*;
import org.soc.common.game.actions.Action.ActionPresenter.ActionWidgetFactory;
import org.soc.common.game.actions.GameAction.AbstractGameAction;
import org.soc.common.views.meta.*;
import org.soc.common.views.widgetsInterface.main.*;

public class RolledSame extends AbstractGameAction {
  private int highRoll;
  private List<Integer> sameRollPlayers = new ArrayList<Integer>();

  @Override public Icon icon() {
    return new IconImpl(null, null, null, null);
  }
  @Override public Name name()
  {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public Description description()
  {
    // TODO Auto-generated method stub
    return null;
  }
  public int getHighRoll() {
    return highRoll;
  }
  public RolledSame setHighRoll(int highRoll) {
    this.highRoll = highRoll;
    return this;
  }
  public RolledSame setSameRollingPlayers(List<Integer> playerIDs) {
    this.sameRollPlayers = playerIDs;
    return this;
  }
  public List<Integer> getSameRolledPlayers() {
    return sameRollPlayers;
  }
  @Override public boolean isAllowed(TurnPhase turnPhase) {
    return false;
  }
  @Override public boolean isAllowed(GamePhase gamePhase) {
    return gamePhase.isDetermineFirstPlayer();
  }
  @Override public void perform(Game game) {
    game.advanceTurn();
    // TODO: fix message
    message = "Rolled same: " + highRoll;
    super.perform(game);
  }
  @Override public String toDoMessage() {
    // TODO: fix message
    return "Highrollers should roll again, highroll:" + highRoll;
  }
  @Override public ActionPresenter createPresenter(ActionWidgetFactory actionWidgetFactory) {
    return null;
  }

  public static class RolledSameBehaviour implements GameBehaviour {
    private GameWidget gameWidget;
    private RolledSame rolledSame;

    public RolledSameBehaviour(GameWidget gameWidget, RolledSame rolledSame) {
      this.gameWidget = gameWidget;
      this.rolledSame = rolledSame;
    }
    @Override public boolean endsManually() {
      return false;
    }
    @Override public void finish() {}
    @Override public void start(GameWidget gameWidget) {
      for (int playerID : rolledSame.getSameRolledPlayers()) {
        GamePlayer player = gameWidget.game().playerById(playerID);
      }
    }
  }
}
