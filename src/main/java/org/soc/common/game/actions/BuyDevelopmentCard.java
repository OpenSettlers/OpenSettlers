package org.soc.common.game.actions;

import org.soc.common.game.DevelopmentCard;
import org.soc.common.game.DevelopmentCard.AbstractDevelopmentCard;
import org.soc.common.game.Game;
import org.soc.common.game.GamePhase;
import org.soc.common.game.GamePlayer;
import org.soc.common.game.ResourceList;
import org.soc.common.game.TurnPhase;
import org.soc.common.game.actions.GameBehaviour.TradeFirst;
import org.soc.common.game.actions.TurnAction.AbstractTurnAction;
import org.soc.common.internationalization.I;
import org.soc.common.server.actions.GameServerActionFactory;
import org.soc.common.server.actions.ServerAction;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.widgetsInterface.actions.ActionWidget;
import org.soc.common.views.widgetsInterface.actions.ActionWidget.ActionWidgetFactory;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.images.R;

public class BuyDevelopmentCard extends AbstractTurnAction
{
  @Override public Icon icon()
  {
    return new IconImpl(R.icons()
            .buyDvelopmentCard16(), R.icons()
            .buyDvelopmentCard32(), R.icons()
            .buyDvelopmentCard48());
  }
  @Override public String name() {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public String getLocalizedName() {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public String getDescription() {
    // TODO Auto-generated method stub
    return null;
  }

  private ResourceList resources;
  private DevelopmentCard devCard;

  public DevelopmentCard getDevCard() {
    return devCard;
  }
  public BuyDevelopmentCard setDevCard(DevelopmentCard devCard) {
    this.devCard = devCard;
    return this;
  }
  public ResourceList getResources() {
    return resources;
  }
  public BuyDevelopmentCard setResources(ResourceList resources) {
    this.resources = resources;
    return this;
  }
  @Override public boolean isValid(Game game)
  {
    if (!super.isValid(game))
      return false;
    // we need resources
    if (resources == null)
    {
      invalidMessage = "Resources cannot be null";
      return false;
    }
    // ...and a devcard too.
    if (game.developmentCardStack().size() == 0)
    {
      invalidMessage = "Development cards are all gone!";
      return false;
    }
    // we need just three resources
    if (resources.size() != 3)
    {
      invalidMessage = "Player needs three resources to buy a development card";
      return false;
    }
    GamePlayer player = game.playerById(sender);
    if (!player.resources().hasAtLeast(resources))
    {
      invalidMessage = "Player does not have given resources";
      return false;
    }
    return true;
  }
  @Override public void perform(Game game)
  {
    // Perform resources administration
    player.resources().subtractResources(resources);
    game.bank().addList(resources);
    // Player should wait a turn before able to play new devcard
    devCard.setPlayable(false);
    devCard.setTurnBought(game.turn().getID());
    // Administer devcards
    player.developmentCards().add(devCard);
    game.developmentCardStack().remove(devCard);
    // TODO: fix message
    // message = String.Format("{0} bought a development card",
    // gamePlayer.XmlPlayer.Name);
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
  @Override public ActionWidget createActionWidget(ActionWidgetFactory factory) {
    return factory.createBuyDevelopmentCardWidget();
  }
  @Override public GameBehaviour begin(GameWidget gameWidget) {
    return new BuyDevelopmentCardGameBehaviour(gameWidget, this);
  }
  @Override public ServerAction createServerAction(GameServerActionFactory factory) {
    return factory.createBuyDevelopmentCardServerAction(this);
  }

  public static class BuyDevelopmentCardGameBehaviour implements GameBehaviour, TradeFirst {
    private BuyDevelopmentCard buyDev;
    private GameWidget gameWidget;

    public BuyDevelopmentCardGameBehaviour(GameWidget gameWidget, BuyDevelopmentCard buyDev) {
      super();
      this.gameWidget = gameWidget;
      this.buyDev = buyDev;
    }
    @Override public void finish() {}
    @Override public void onCancelTrade() {}
    @Override public void start(GameWidget gameWidget) {
      GamePlayer player = gameWidget.playingPlayer();
      if (player.resources().hasAtLeast(AbstractDevelopmentCard.getCost())) {
        gameWidget.doAction(buyDev);
      } else {
        gameWidget.bankTradeDialog().setDevcardTrade(this);
      }
    }
    @Override public void onTraded() {
      gameWidget.doAction(buyDev);
    }
  }
}
