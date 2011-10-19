package org.soc.common.game.trading;

import org.soc.common.core.GenericList.Models;
import org.soc.common.game.*;
import org.soc.common.game.Resources.ResourceList;
import org.soc.common.game.actions.Action.ActionPresenter.ActionWidgetFactory;
import org.soc.common.game.actions.TurnAction.AbstractTurnAction;
import org.soc.common.internationalization.*;
import org.soc.common.views.widgetsInterface.actions.*;
import org.soc.common.views.widgetsInterface.actions.ActionDetailWidget.ActionDetailWidgetFactory;

public class TradeBank extends AbstractTurnAction {
  private ResourceList wantedResources;
  private ResourceList offeredResources;

  public ResourceList getWantedResources() {
    return wantedResources;
  }
  public TradeBank setWantedResources(ResourceList wantedResources) {
    this.wantedResources = wantedResources;
    return this;
  }
  public ResourceList getOfferedResources() {
    return offeredResources;
  }
  public TradeBank setOfferedResources(ResourceList offeredResources) {
    this.offeredResources = offeredResources;
    return this;
  }
  @Override public boolean isValid(Game game) {
    if (!super.isValid(game))
      return false;
    if (offeredResources == null || wantedResources == null) {
      invalidMessage = "OfferedCards or WantedCards cannot be null";
      return false;
    }
    if (offeredResources.nonTradeableResources().size() > 0) {
      invalidMessage = "You offer resources which are not tradeable";
      return false;
    }
    if (wantedResources.nonTradeableResources().size() > 0) {
      invalidMessage = "You want resources which are not tradeable";
      return false;
    }
    if (offeredResources.size() == 0) {
      invalidMessage = "You need to offer at last one resource";
      return false;
    }
    if (wantedResources.size() == 0) {
      invalidMessage = "You need to desire at last one resource";
      return false;
    }
    player = game.playerById(sender);
    // check if the player has the offered cards in hand
    if (!player.resources().hasAtLeast(offeredResources)) {
      invalidMessage = "You don't have the resource available you are offering";
      return false;
    }
    for (Resource resource : game.rules().supportedResources()) {
      int amountOfType = offeredResources.ofType(resource).size();
      if (amountOfType > 0) {
        int amountNeeded = player.ports().amountNeededToTrade(resource);
        if ((amountOfType % amountNeeded) != 0) {
          invalidMessage = "For " + Models.name(resource)
                  + " you need to offer a multiple of "
                  + amountNeeded;
          return false;
        }
      }
    }
    if (player.ports().amountGold(offeredResources) != wantedResources.size()) {
      invalidMessage = "Amount of desired resources should match offered resources divided by portdiveder";
      return false;
    }
    return true;
  }
  @Override public void perform(Game game) {
    player = game.playerById(sender);
    player.resources().removeList(offeredResources);
    player.resources().addList(wantedResources);
    game.bank().addList(offeredResources);
    game.bank().removeList(wantedResources);
    message = player.user().name() + " exchanges "
            + offeredResources.toString() + " for "
            + wantedResources.toString() + ".";
    super.perform(game);
  }
  @Override public boolean isAllowed(TurnPhase turnPhase) {
    return turnPhase.isBuilding();
  }
  @Override public boolean isAllowed(GamePhase gamePhase) {
    return gamePhase.isPlayTurns();
  }
  @Override public String toDoMessage() {
    return I.get().actions().noToDo();
  }
  @Override public ActionPresenter createPresenter(ActionWidgetFactory actionWidgetFactory) {
    return actionWidgetFactory.createTradeBankWidget();
  }
  @Override public ActionDetailWidget createDetailWidget(ActionDetailWidgetFactory factory) {
    return factory.getTradeBankDetailWidget(this);
  }
}
