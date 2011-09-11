package org.soc.common.game.actions;

import java.util.ArrayList;
import java.util.List;

import org.soc.common.game.Game;
import org.soc.common.game.GamePhase;
import org.soc.common.game.GamePlayer;
import org.soc.common.game.TurnPhase;
import org.soc.common.game.actions.GameAction.AbstractGameAction;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.widgetsInterface.actions.ActionWidget;
import org.soc.common.views.widgetsInterface.actions.ActionWidget.ActionWidgetFactory;
import org.soc.common.views.widgetsInterface.main.GameWidget;

public class RolledSame extends AbstractGameAction
{
  @Override public Icon icon()
  {
    return new IconImpl(null, null, null, null);
  }
  @Override public String name()
  {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public String getLocalizedName()
  {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public String getDescription()
  {
    // TODO Auto-generated method stub
    return null;
  }

  private int highRoll;
  private List<Integer> sameRollPlayers = new ArrayList<Integer>();

  /** @return the highRoll */
  public int getHighRoll()
  {
    return highRoll;
  }
  /** @param highRoll the highRoll to set */
  public RolledSame setHighRoll(int highRoll)
  {
    this.highRoll = highRoll;
    return this;
  }
  public RolledSame setSameRollingPlayers(List<Integer> playerIDs)
  {
    this.sameRollPlayers = playerIDs;
    return this;
  }
  public List<Integer> getSameRolledPlayers()
  {
    return sameRollPlayers;
  }
  @Override public boolean isAllowed(TurnPhase turnPhase)
  {
    return false;
  }
  @Override public boolean isAllowed(GamePhase gamePhase)
  {
    return gamePhase.isDetermineFirstPlayer();
  }
  /* (non-Javadoc)
   * 
   * @see org.soc.common.actions.gameAction.AbstractGameAction#perform(org.soc.common.game .Game) */
  @Override public void perform(Game game)
  {
    game.advanceTurn();
    // TODO: fix message
    message = "Rolled same: " + highRoll;
    super.perform(game);
  }
  @Override public String toDoMessage()
  {
    // TODO: fix message
    return "Highrollers should roll again, highroll:" + highRoll;
  }
  @Override public ActionWidget createActionWidget(
          ActionWidgetFactory actionWidgetFactory)
  {
    return null;
  }

  public static class RolledSameBehaviour implements ActionInGame {
    private GameWidget gameWidget;
    private RolledSame rolledSame;

    public RolledSameBehaviour(GameWidget gameWidget, RolledSame rolledSame) {
      super();
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
