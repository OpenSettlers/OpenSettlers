package org.soc.common.game.actions;

import org.soc.common.core.GenericList.AddsList.ListAdded;
import org.soc.common.core.GenericList.ImmutableList;
import org.soc.common.core.GenericList.RemovesList.ListRemoved;
import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.game.*;
import org.soc.common.game.DevelopmentCard.AbstractDevelopmentCard;
import org.soc.common.game.GamePhaseChangedEvent.GamePhaseChangedHandler;
import org.soc.common.game.Resources.ResourceList;
import org.soc.common.game.TurnChangedEvent.TurnChangedHandler;
import org.soc.common.game.TurnPhaseChangedEvent.TurnPhaseChangedHandler;
import org.soc.common.game.actions.Action.ActionPresenter.ActionWidgetFactory;
import org.soc.common.game.actions.GameBehaviour.TradeFirst;
import org.soc.common.game.actions.TurnAction.AbstractTurnAction;
import org.soc.common.game.actions.WantsBuyDevelopmentCardEvent.HasWantsBuyDevelopmentCardHandlers;
import org.soc.common.game.actions.WantsBuyDevelopmentCardEvent.WantsBuyDevelopmentCardHandler;
import org.soc.common.internationalization.*;
import org.soc.common.server.actions.*;
import org.soc.common.views.meta.*;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.gwt.client.game.widgetsAbstract.actions.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.user.client.ui.*;
import com.gwtplatform.dispatch.annotation.*;

public class BuyDevelopmentCard extends AbstractTurnAction
{
  @Override public Icon icon() {
    return new IconImpl(R.icons()
            .buyDvelopmentCard16(), R.icons()
            .buyDvelopmentCard32(), R.icons()
            .buyDvelopmentCard48());
  }
  @Override public Name name() {
    // TODO Auto-generated method stub 
    return null;
  }
  @Override public Description description() {
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
  /** Support future Diamonds by specifying a list of resources, such that e.g. 1 wheat, 2 diamonds
   * can be used to buy a development card */
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
  @Override public void perform(Game game) {
    player.resources().removeList(resources);
    game.bank().addList(resources);
    devCard.setPlayable(false);
    devCard.setTurnBought(game.turn().getID());
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
  @Override public ActionPresenter createPresenter(ActionWidgetFactory factory) {
    return factory.createBuyDevelopmentCardWidget();
  }
  @Override public GameBehaviour begin(GameWidget gameWidget) {
    return new BuyDevelopmentCardGameBehaviour(gameWidget, this);
  }
  @Override public ServerAction createServerAction(GameServerActionFactory factory) {
    return factory.createBuyDevelopmentCard(this);
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
    @Override public boolean endsManually() {
      // TODO Auto-generated method stub
      return false;
    }
  }

  public interface BuyDevelopmentCardView extends HasWantsBuyDevelopmentCardHandlers {
    @GenEvent public class WantsBuyDevelopmentCard {}

    public void enable();
    public void disable();
    public void setTrade1Visible(boolean visible);
    public void setTrade2Visible(boolean visible);
    public void setTrade3Visible(boolean visible);
  }

  public static class BuyDevelopmentCardBitmapWidget extends AbstractActionPresenter
          implements TurnChangedHandler, TurnPhaseChangedHandler,
          GamePhaseChangedHandler
  {
    BuyDevelopmentCard buyDevelopmentCard = new BuyDevelopmentCard();
    BuyDevelopmentCardView view;

    public BuyDevelopmentCardBitmapWidget(final GameWidget gameWidget,
            final GamePlayer player)
    {
      super(gameWidget, player);
      player.resources().addListRemovedHandler(new ListRemoved<Resource>() {
        @Override public void listRemoved(ImmutableList<Resource> items) {
          checkEnabled();
        }
      });
      player.resources().addListAddedHandler(new ListAdded<Resource>() {
        @Override public void listAdded(ImmutableList<Resource> items) {
          checkEnabled();
        }
      });
      gameWidget.game().addTurnChangedHandler(this);
      gameWidget.game().addTurnPhaseChangedHandler(this);
      gameWidget.game().addGamePhaseChangedHandler(this);
      view.addWantsBuyDevelopmentCardHandler(new WantsBuyDevelopmentCardHandler() {
        @Override public void onWantsBuyDevelopmentCard(WantsBuyDevelopmentCardEvent event) {
          gameWidget.startAction(new BuyDevelopmentCard()
                  .setResources(AbstractDevelopmentCard.getCost())
                  .setPlayer(player));
        }
      });
    }
    @Override public Widget asWidget() {
      return (Widget) view;
    }
    @Override public void onTurnChanged(TurnChangedEvent event) {
      checkEnabled();
    }
    /* Update state of the panel */
    private void checkEnabled() {
      // TODO: make logic accurate for diamonds & trades
      setTradesNeededToBuild();
      if (enabled && player.isOnTurn()) {
        if (gameWidget.game().isAllowed(buyDevelopmentCard)
                && AbstractDevelopmentCard.canPay(player)) {
          view.enable();
          return;
        }
      }
      view.disable();
    }
    @Override public void onTurnPhaseChanged(TurnPhaseChangedEvent event) {
      checkEnabled();
    }
    @Override public void onGamePhaseChanged(GamePhaseChangedEvent event) {
      checkEnabled();
    }
    private void setTradesNeededToBuild() {
      if (AbstractDevelopmentCard.canPay(player)) {
        int amountTradesNeeded = player
                .resources()
                .getNeededResources(
                        AbstractDevelopmentCard.getCost())
                .size();
        view.setTrade1Visible(amountTradesNeeded >= 1);
        view.setTrade1Visible(amountTradesNeeded >= 2);
        view.setTrade1Visible(amountTradesNeeded >= 3);
      } else {
        view.setTrade1Visible(false);
        view.setTrade2Visible(false);
        view.setTrade3Visible(false);
      }
    }
    @Override protected void updateEnabled() {
      // TODO Auto-generated method stub
    }
  }
}
