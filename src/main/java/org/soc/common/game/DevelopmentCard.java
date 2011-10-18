package org.soc.common.game;

import org.soc.common.core.GenericList.HasId;
import org.soc.common.core.GenericList.Model;
import org.soc.common.core.OpenZettlers.OsModel;
import org.soc.common.core.Props.PropertyList.PropertyTypeList;
import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.core.property.*;
import org.soc.common.game.PickedResourceEvent.HasPickedResourceHandlers;
import org.soc.common.game.PickedResourceEvent.PickedResourceHandler;
import org.soc.common.game.PlayableChangedEvent.HasPlayableChangedHandlers;
import org.soc.common.game.PlayableChangedEvent.PlayableChangedHandler;
import org.soc.common.game.Resources.MutableResourceList;
import org.soc.common.game.Resources.ResourceList;
import org.soc.common.game.actions.*;
import org.soc.common.game.actions.WantsPlayDevelopmentCardEvent.HasWantsPlayDevelopmentCardHandlers;
import org.soc.common.game.actions.WantsPlayDevelopmentCardEvent.WantsPlayDevelopmentCardHandler;
import org.soc.common.game.actions.WantsPlayDevelopmentCardEvent;
import org.soc.common.internationalization.*;
import org.soc.common.utils.*;
import org.soc.common.views.meta.*;
import org.soc.common.views.widgetsInterface.developmentCards.*;
import org.soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidget.DevelopmentCardWidgetFactory;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.event.shared.*;
import com.google.gwt.user.client.ui.*;
import com.gwtplatform.dispatch.annotation.*;

import static org.soc.common.game.Resource.*;

import static org.soc.common.game.Resources.*;

public interface DevelopmentCard extends OsModel<Integer>, HasPlayableChangedHandlers {
  public boolean hasLimitOnePerTurn();
  /* True when player can't play the DevelopmentCard in the turn he bought it */
  public boolean hasSummoningSickness();
  public void play(Game game, GamePlayer player);
  public boolean isValid(Game game);
  /* Default is not to keep the DevelopmentCard in stock after playing */
  public boolean keepInStock();
  /* Returns true if player is allowed to play this card in given TurnPhase */
  public boolean isAllowed(TurnPhase turnPhase);
  /* Returns true if player is allowed to play this card in given GamePhase */
  public boolean isAllowed(GamePhase turnPhase);
  public String invalidMessage();
  public String message();
  /* Gets the number of the turn this development card was bought */
  public int turnBought();
  /* Sets the turn ID (==turn number) of the turn this development card was bought */
  public void setTurnBought(int turnBought);
  /* Returns true when this development card can be played this turn */
  public boolean isPlayable();
  public void setPlayable(boolean isPlayable);
  public int hashCode();
  public DevelopmentCardWidget createPlayCardWidget(
          DevelopmentCardWidgetFactory factory);

  @GenEvent public class PlayableChanged {
    @Order(0) boolean playable;
  }

  public interface DevelopmentCardView extends HasWantsPlayDevelopmentCardHandlers {
    @GenEvent public class WantsPlayDevelopmentCard {}

    @GenEvent public class PickedResource {
      @Order(0) Resource pickedResource;
      @Order(1) Resource unpickedResource;
    }

    public void enable();
    public void disable();
  }

  public interface DevelopmentCardPresenter {}

  public abstract class AbstractDevelopmentCard implements DevelopmentCard {
    private static final long serialVersionUID = 3192052784726040369L;
    protected String invalidMessage;
    protected String message = "No message implemented yet for Devcard" + toString();
    protected int turnBought = 0;
    private Integer id = 0;
    private boolean isPlayable = false;
    private static ResourceList cost = newResources(Resource.wheat, ore, sheep);
    private transient EventBus eventBus = new SimpleEventBus();

    @Override public Integer id() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Model copy() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public HasId setId(Integer id) {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public IdScope scope() {
      // TODO Auto-generated method stub
      return null;
    }
    public boolean hasLimitOnePerTurn() {
      return true;
    }
    /* Returns true when the given phase is in PlayTurns */
    @Override public boolean isAllowed(GamePhase turnPhase) {
      return turnPhase.isPlayTurns();
    }
    public boolean hasSummoningSickness() {
      return true;
    }
    public static boolean canPay(GamePlayer player) {
      // TODO: add diamonds support
      // First, create a copy so we can safely remove resources from it
      MutableResourceList copy = player.resources().copy();
      // Pay resources player can simply pay for
      copy.removeList(getCost());
      // Calculate amount of gold we need
      int neededGold =
              // amount of resources this piece needs, minus...
              getCost().size() -
                      // the resources the player can simply pay for
                      (player.resources().size() - copy.size());
      // Player can pay given piece if he can trade exactly or more gold as
      // needed
      return player.ports().amountGold(copy) >= neededGold;
    }
    public static ResourceList getCost() {
      return cost;
    }
    public void play(Game game, GamePlayer player) {
      isPlayable = false;
      player.playedDevelopmentCards().add(this);
      player.developmentCards().remove(this);
    }
    public boolean isValid(Game game) {
      return true;
    }
    /* Default is not to keep the DevelopmentCard in stock */
    public boolean keepInStock() {
      return false;
    }
    public String invalidMessage() {
      return invalidMessage;
    }
    public String message() {
      return message;
    }
    public int turnBought() {
      return turnBought;
    }
    public void setTurnBought(int turnBought) {
      this.turnBought = turnBought;
    }
    public boolean isPlayable() {
      return isPlayable;
    }
    public void setPlayable(boolean isPlayable) {
      this.isPlayable = isPlayable;
      eventBus.fireEvent(new PlayableChangedEvent(isPlayable));
    }
    @Override public String toString() {
      return Util.shortName(this.getClass());
    }
    @Override public int hashCode() {
      return id;
    }
    @Override public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      AbstractDevelopmentCard other = (AbstractDevelopmentCard) obj;
      if (id != other.id)
        return false;
      return true;
    }
    @Override public Name name() {
      return new Name.Impl(Util.shortName(this.getClass()));
    }
    public abstract DevelopmentCardWidget createPlayCardWidget(
            DevelopmentCardWidgetFactory factory);
    @Override public HandlerRegistration addPlayableChangedHandler(
            PlayableChangedHandler handler)
    {
      return eventBus.addHandler(PlayableChangedEvent.getType(), handler);
    }
    @Override public void fireEvent(GwtEvent<?> event) {
      eventBus.fireEvent(event);
    }
    @Override public Property getProp(Property type) {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public PropertyTypeList properties() {
      // TODO Auto-generated method stub
      return null;
    }
  }

  /** Standard ruleset VictoryPoint development card Playable any time during a players' turn and not
   * immediately played when receiving the card */
  public class VictoryPoint extends AbstractDevelopmentCard implements VictoryPointItem {
    @Override public Icon icon() {
      return new IconImpl(R.icons().victoryPoint16(), R.icons().victoryPoint32(), R
              .icons()
              .victoryPoint48());
    }
    @Override public Name name() {
      return new Name.Impl(I.get().constants().victoryPoint());
    }
    @Override public Description description() {
      return new Description.Impl(I.get().constants().victoryPointDescription());
    }
    /** A victoryPoint development card returns into stock after playing */
    @Override public boolean keepInStock() {
      return true;
    }
    /** A VictoryPoint development card is always valid */
    @Override public boolean isValid(Game game) {
      return true;
    }
    @Override public int victoryPoints() {
      return 1;
    }
    /** VictoryPoint card can be played instantly */
    @Override public boolean hasSummoningSickness() {
      return false;
    }
    /** VictoryPoint cards can be played without limits */
    @Override public boolean hasLimitOnePerTurn() {
      return false;
    }
    @Override public void play(Game game, GamePlayer player) {
      player.victoryPoints().add(this);
      super.play(game, player);
    }
    @Override public DevelopmentCardWidget createPlayCardWidget(
            DevelopmentCardWidgetFactory factory) {
      return factory.createVictoryPointWidget(this);
    }
    @Override public boolean isAllowed(TurnPhase turnPhase) {
      return turnPhase.isBeforeDiceRoll() || turnPhase.isBuilding();
    }
    @Override public boolean isPlayable() {
      return true;
    }
    /** Ignored, VictoryPoint card is always playable */
    @Override public void setPlayable(boolean isPlayable) {}

    public interface VictoryPointView extends DevelopmentCardView {}

    public static class PlayVictoryPointWidget implements DevelopmentCardWidget {
      private VictoryPoint victoryPoint;
      private GameWidget gameWidget;
      private PlayDevelopmentCard playDevelopmentCard = new PlayDevelopmentCard();
      private VictoryPointView view;

      public PlayVictoryPointWidget(final GameWidget gameWidget, VictoryPoint victoryPoint) {
        this.victoryPoint = victoryPoint;
        this.gameWidget = gameWidget;
        playDevelopmentCard.setDevelopmentcard(victoryPoint);
        playDevelopmentCard.setPlayer(gameWidget.playingPlayer());
        view.addWantsPlayDevelopmentCardHandler(new WantsPlayDevelopmentCardHandler() {
          @Override public void onWantsPlayDevelopmentCard(WantsPlayDevelopmentCardEvent event) {
            gameWidget.doAction(playDevelopmentCard);
          }
        });
      }
      @Override public Widget asWidget() {
        return (Widget) view;
      }
    }

    @Override public Model copy() {
      // TODO Auto-generated method stub
      return null;
    }
  }

  /** Standard monopoly steals all resources of a chosen type from all opponents */
  public class Monopoly extends AbstractDevelopmentCard {
    private Resource resource;

    @Override public Icon icon() {
      return new IconImpl(R.icons().monopoly16(), R.icons().monopoly32(), R.icons().monopoly48());
    }
    @Override public Name name() {
      return new Name.Impl(I.get().constants().monopoly());
    }
    @Override public Description description() {
      return new Description.Impl(I.get().constants().monopolyDescription());
    }
    public Resource getResource()
    {
      return resource;
    }
    public Monopoly setResource(Resource resource)
    {
      this.resource = resource;
      return this;
    }
    public ResourceList getMonopolyableResources()
    {
      return Resources.monoPolyableResources;
    }
    /** Valid when having non-null resource and resource is of a basic type */
    @Override public boolean isValid(Game game)
    {
      if (!(super.isValid(game)))
        return false;
      if (resource == null)
        return false;
      // Allow only one of the 5 basic resources
      if (!monoPolyableResources.contains(resource))
        return false;
      return true;
    }
    /** Steals all resources from opponents with given resource type */
    @Override public void play(Game game, GamePlayer player) {
      // TODO: fix message
      // msg.append(String.format("%s stole ", player.getName()));
      for (GamePlayer opponent : game.players().opponents(player)) {
        ResourceList opponentResources = opponent.resources().ofType(resource);
        // steal only if there are resources to steal
        if (opponentResources.notEmpty()) {
          // msg.append(String.format("%s %s from %s, ",
          // opponentResources.size(), resource.toString(),
          // player.getName()));
          // Move resources from victims to player
          player.resources().moveListFrom(opponent.resources(), opponentResources);
        }
      }
      // remove the trailing ","
      // msg.toString().substring(0,msg.toString().length() - 2);
      message = player.user().name() + "played a monopoly";
      super.play(game, player);
    }
    @Override public DevelopmentCardWidget
            createPlayCardWidget(DevelopmentCardWidgetFactory factory) {
      return factory.createMonopolyWidget(this);
    }
    @Override public boolean isAllowed(TurnPhase turnPhase) {
      return turnPhase.isBuilding();
    }

    public interface MonopolyView extends DevelopmentCardView, HasPickedResourceHandlers {
      public Resource pickedResource();
    }

    public static class PlayMonopolyWidget implements DevelopmentCardWidget,
            PlayableChangedHandler
    {
      private Monopoly monopoly;
      private GameWidget gameWidget;
      private PlayDevelopmentCard playDevelopmentCard = new PlayDevelopmentCard();
      private MonopolyView view;

      public PlayMonopolyWidget(final GameWidget gameWidget, final Monopoly monopoly) {
        this.monopoly = monopoly;
        this.gameWidget = gameWidget;
        playDevelopmentCard.setDevelopmentcard(monopoly);
        playDevelopmentCard.setPlayer(gameWidget.playingPlayer());
        view.addWantsPlayDevelopmentCardHandler(new WantsPlayDevelopmentCardHandler() {
          @Override public void onWantsPlayDevelopmentCard(WantsPlayDevelopmentCardEvent event) {
            monopoly.setResource(view.pickedResource());
            gameWidget.doAction(playDevelopmentCard);
          }
        });
        view.addPickedResourceHandler(new PickedResourceHandler() {
          @Override public void onPickedResource(PickedResourceEvent event) {
            if (view.pickedResource() == null) {
              view.disable();
            } else {
              view.enable();
            }
          }
        });
        monopoly.addPlayableChangedHandler(this);
      }
      @Override public Widget asWidget() {
        return (Widget) view;
      }
      private void updateUI() {
        if (view.pickedResource() != null && monopoly.isPlayable()) {
          view.enable();
        } else {
          view.disable();
        }
      }
      @Override public void onPlayableChanged(PlayableChangedEvent event) {
        updateUI();
      }
    }
  }

  public class RoadBuilding extends AbstractDevelopmentCard {
    @Override public Icon icon() {
      return new IconImpl(R.icons().roadBuilding16(),
              R.icons().roadBuilding32(), R.icons()
                      .roadBuilding48());
    }
    @Override public Name name() {
      return new Name.Impl(I.get().constants().roadBuilding());
    }
    @Override public Description description() {
      return new Description.Impl(I.get().constants().roadBuildingDescription());
    }
    @Override public void play(Game game, GamePlayer player) {
      int roadBuildingTokens = player.roadBuildingTokens();
      player.setRoadBuildingTokens(roadBuildingTokens += 2);
      message = player.user().name() + " played a road building card";
      super.play(game, player);
    }
    @Override public DevelopmentCardWidget createPlayCardWidget(
            DevelopmentCardWidgetFactory factory)
    {
      return factory.createRoadBuildingWidget(this);
    }
    @Override public boolean isAllowed(TurnPhase turnPhase) {
      return turnPhase.isBuilding();
    }

    public interface RoadBuildingView extends DevelopmentCardView {}

    public static class PlayRoadBuildingWidget implements DevelopmentCardWidget,
            PlayableChangedHandler
    {
      private RoadBuilding roadBuilding;
      private GameWidget gameWidget;
      private PlayDevelopmentCard playDevelopmentCard = new PlayDevelopmentCard();
      private RoadBuildingView view;

      public PlayRoadBuildingWidget(final GameWidget gameWidget, RoadBuilding roadBuilding) {
        this.roadBuilding = roadBuilding;
        this.gameWidget = gameWidget;
        playDevelopmentCard.setDevelopmentcard(roadBuilding);
        playDevelopmentCard.setPlayer(gameWidget.playingPlayer());
        view.addWantsPlayDevelopmentCardHandler(new WantsPlayDevelopmentCardHandler() {
          @Override public void onWantsPlayDevelopmentCard(WantsPlayDevelopmentCardEvent event) {
            gameWidget.doAction(playDevelopmentCard);
          }
        });
        roadBuilding.addPlayableChangedHandler(this);
      }
      @Override public Widget asWidget() {
        return (Widget) view;
      }
      @Override public void onPlayableChanged(PlayableChangedEvent event) {
        if (event.isPlayable()) {
          view.enable();
        } else {
          view.disable();
        }
      }
    }

    @Override public Model copy() {
      // TODO Auto-generated method stub
      return null;
    }
  }

  public class YearOfPlenty extends AbstractDevelopmentCard {
    // actual picked resources by player
    private ResourceList goldPick;

    @Override public Icon icon() {
      return new IconImpl(R.icons().yearOfPlenty16(),
              R.icons().yearOfPlenty32(), R.icons()
                      .yearOfPlenty48());
    }
    @Override public Name name() {
      return new Name.Impl(I.get().constants().yearOfPlenty());
    }
    @Override public Description description() {
      return new Description.Impl(I.get().constants().yearOfPlentyDescription());
    }
    public YearOfPlenty setGoldPick(ResourceList goldPick) {
      this.goldPick = goldPick;
      return this;
    }
    public ResourceList getGoldPick() {
      return goldPick;
    }
    @Override public void play(Game game, GamePlayer player)
    {
      // TODO: fix mssage
      // message =
      // String.format("%s gained %s by playing a Year of Plenty card",
      // player.getName(), goldPick.toString());
      // give player the resources
      player.resources().moveListFrom(game.bank(), goldPick);
      super.play(game, player);
    }
    @Override public boolean isValid(Game game)
    {
      if (!super.isValid(game))
        return false;
      if (goldPick == null)
      {
        invalidMessage = "Resources cannot be null";
        return false;
      }
      if (goldPick.size() != 2)
      {
        invalidMessage = "Exactly 2 resources should be picked";
        return false;
      }
      if (!(game.bank().hasAtLeast(goldPick)))
      {
        invalidMessage = "Bank does not have picked resources";
        return false;
      }
      return true;
    }
    @Override public DevelopmentCardWidget createPlayCardWidget(
            DevelopmentCardWidgetFactory factory)
    {
      return factory.createYearOfPlentyWidget(this);
    }
    @Override public boolean isAllowed(TurnPhase turnPhase)
    {
      return turnPhase.isBuilding();
    }

    public interface YearOfPlentyView extends DevelopmentCardView, HasPickedResourceHandlers {
      public ResourceList pickedResources();
    }

    public static class PlayYearOfPlentyWidget implements DevelopmentCardWidget,
            PlayableChangedHandler
    {
      private YearOfPlenty yearOfPlenty;
      private GameWidget gameWidget;
      private PlayDevelopmentCard playDevelopmentCard = new PlayDevelopmentCard();
      private YearOfPlentyView view;

      public PlayYearOfPlentyWidget(final GameWidget gameWidget,
              final YearOfPlenty yearOfPlenty)
      {
        this.yearOfPlenty = yearOfPlenty;
        this.gameWidget = gameWidget;
        playDevelopmentCard.setDevelopmentcard(yearOfPlenty);
        playDevelopmentCard.setPlayer(gameWidget.playingPlayer());
        view.addWantsPlayDevelopmentCardHandler(new WantsPlayDevelopmentCardHandler() {
          @Override public void onWantsPlayDevelopmentCard(WantsPlayDevelopmentCardEvent event) {
            yearOfPlenty.setGoldPick(view.pickedResources());
            gameWidget.doAction(playDevelopmentCard);
          }
        });
        view.addPickedResourceHandler(new PickedResourceHandler() {
          @Override public void onPickedResource(PickedResourceEvent event) {
            updateView();
          }
        });
        yearOfPlenty.addPlayableChangedHandler(this);
      }
      @Override public Widget asWidget() {
        return (Widget) view;
      }
      private void updateView() {
        if (view.pickedResources().size() == 2) {
          view.enable();
        } else {
          view.disable();
        }
      }
      @Override public void onPlayableChanged(PlayableChangedEvent event)
      {
        if (event.isPlayable()) {
          view.enable();
        } else {
          view.disable();
        }
      }
    }

    @Override public Integer id() {
      // TODO Auto-generated method stub
      return null;
    }
  }

  /* Placeholder for a development card bought by an opponent, as seen from the perspective of the
   * player. A ServerBuyDevelopmentCard will replace the development card of the corresponding
   * BuyDevelopmentCard to an instance of this type. */
  public class DummyDevelopmentCard implements DevelopmentCard {
    private int id;
    private int turnBought;

    @Override public Icon icon() {
      return new IconImpl(R.icons().developmentCardBack16(), R.icons().developmentCardBack32(), R
              .icons().developmentCardBack48());
    }
    @Override public Name name() {
      return new Name.Impl(I.get().constants().dummyDevelopmentCard());
    }
    @Override public Description description() {
      return new Description.Impl(I.get().constants().dummyDevelopmentCardDescription());
    }
    @Override public DevelopmentCardWidget createPlayCardWidget(
            DevelopmentCardWidgetFactory factory)
    {
      return null;
    }
    @Override public String invalidMessage() {
      return null;
    }
    @Override public String message() {
      return "Dummy devcard";
    }
    @Override public int turnBought() {
      return 0;
    }
    @Override public boolean isAllowed(TurnPhase turnPhase) {
      return false;
    }
    @Override public boolean isAllowed(GamePhase turnPhase) {
      return false;
    }
    @Override public boolean hasSummoningSickness() {
      return true;
    }
    @Override public boolean hasLimitOnePerTurn() {
      return true;
    }
    @Override public boolean isPlayable() {
      return false;
    }
    @Override public boolean isValid(Game game) {
      return false;
    }
    @Override public boolean keepInStock() {
      return false;
    }
    @Override public void play(Game game, GamePlayer player) {}
    @Override public void setPlayable(boolean isPlayable) {}
    @Override public void setTurnBought(int turnBought) {
      this.turnBought = turnBought;
    }
    @Override public HandlerRegistration addPlayableChangedHandler(
            PlayableChangedHandler handler) {
      return null;
    }
    @Override public void fireEvent(GwtEvent<?> event) {
      // TODO Auto-generated method stub
    }
    @Override public Model copy() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public HasId setId(Integer id) {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public org.soc.common.core.GenericList.HasId.IdScope scope() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Integer id() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Property getProp(Property type) {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public PropertyTypeList properties() {
      // TODO Auto-generated method stub
      return null;
    }
  }

  /* Represents a soldier from the standard rules development card set. */
  public class Soldier extends AbstractDevelopmentCard
  {
    @Override public Icon icon() {
      return new IconImpl(R.icons().soldier16(),
              R.icons().soldier32(), R.icons()
                      .soldier48());
    }
    @Override public Name name() {
      return new Name.Impl(I.get().constants().soldier());
    }
    @Override public Description description() {
      return new Description.Impl(I.get().constants().soldierDescription());
    }
    @Override public boolean isValid(Game game) {
      if (!super.isValid(game))
        return false;
      return true;
    }
    @Override public void play(Game game, GamePlayer player) {
      // Update the army
      player.army().addSoldier(this);
      game.switchLargestArmyIfNeeded(player);
      // Make sure next thing the player does is moving the robber...
      game.actionsQueue().enqueue(new PlaceRobber().setPlayer(player), true);
      // ... and after that, rob a player
      game.actionsQueue().enqueue(new RobPlayer().setPlayer(player), true);
      message = player.user().name() + " played a soldier";
      super.play(game, player);
    }
    @Override public DevelopmentCardWidget createPlayCardWidget(
            DevelopmentCardWidgetFactory factory)
    {
      return factory.createSoldierWidget(this);
    }
    @Override public boolean isAllowed(TurnPhase turnPhase) {
      return turnPhase.isBeforeDiceRoll() || turnPhase.isBuilding();
    }

    public interface SoldierView extends DevelopmentCardView {}

    public static class PlaySoldierWidget implements DevelopmentCardWidget, PlayableChangedHandler {
      private Soldier soldier;
      private GameWidget gameWidget;
      private PlayDevelopmentCard playDevelopmentCard = new PlayDevelopmentCard();
      private HorizontalPanel rootPanel = new HorizontalPanel();
      private Button btnPlay = new Button(I.get().constants().play());
      private SoldierView view;

      public PlaySoldierWidget(final GameWidget gameWidget, Soldier soldier) {
        this.soldier = soldier;
        this.gameWidget = gameWidget;
        playDevelopmentCard.setDevelopmentcard(soldier);
        playDevelopmentCard.setPlayer(gameWidget.playingPlayer());
        view.addWantsPlayDevelopmentCardHandler(new WantsPlayDevelopmentCardHandler() {
          @Override public void onWantsPlayDevelopmentCard(WantsPlayDevelopmentCardEvent event) {
            gameWidget.doAction(playDevelopmentCard);
          }
        });
        soldier.addPlayableChangedHandler(this);
      }
      @Override public Widget asWidget() {
        return rootPanel;
      }
      @Override public void onPlayableChanged(PlayableChangedEvent event) {
        btnPlay.setEnabled(event.isPlayable());
      }
    }
  }
}