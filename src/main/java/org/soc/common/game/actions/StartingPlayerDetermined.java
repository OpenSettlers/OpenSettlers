package org.soc.common.game.actions;

import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.game.*;
import org.soc.common.game.actions.Action.ActionPresenter.ActionWidgetFactory;
import org.soc.common.game.actions.GameAction.AbstractGameAction;
import org.soc.common.internationalization.*;
import org.soc.common.views.meta.*;

public class StartingPlayerDetermined extends AbstractGameAction
{
  @Override public Icon icon()
  {
    return new IconImpl(null, null, null, null);
  }
  @Override public Name name() {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public Description description() {
    // TODO Auto-generated method stub
    return null;
  }

  private int diceRoll;
  private GamePlayer gameStarter;

  /** @return the gameStarter */
  public GamePlayer getGameStarter()
  {
    return gameStarter;
  }
  /** @param gameStarter the gameStarter to set */
  public StartingPlayerDetermined setGameStarter(GamePlayer gameStarter)
  {
    this.gameStarter = gameStarter;
    return this;
  }
  /** @return the diceRoll */
  public int getDiceRoll()
  {
    return diceRoll;
  }
  /** @param diceRoll the diceRoll to set */
  public StartingPlayerDetermined setDiceRoll(int diceRoll)
  {
    this.diceRoll = diceRoll;
    return this;
  }
  @Override public boolean isAllowed(TurnPhase turnPhase)
  {
    return false;
  }
  @Override public boolean isAllowed(GamePhase gamePhase)
  {
    return gamePhase.isDetermineFirstPlayer();
  }
  @Override public String toDoMessage()
  {
    return I.get().actions().noToDo();
  }
  @Override public void perform(Game game)
  {
    // TODO: It would be nice if players are re-ordered such that the
    // highroller starts, and the player after the highroller comes second,
    // etc.
    game.setStartPlayer(gameStarter);
    game.advanceTurn();
    message = "Starting player determined: "
            + game.getStartPlayer().user().name();
    super.perform(game);
  }
  @Override public ActionPresenter createPresenter(
          ActionWidgetFactory actionWidgetFactory)
  {
    return null;
  }
}
