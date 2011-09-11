package org.soc.common.game;

import java.io.Serializable;

import org.soc.common.game.PlayableChangedEvent.HasPlayableChangedHandlers;
import org.soc.common.game.PlayableChangedEvent.PlayableChangedHandler;
import org.soc.common.game.Resource.Clay;
import org.soc.common.game.Resource.Ore;
import org.soc.common.game.Resource.Sheep;
import org.soc.common.game.Resource.Timber;
import org.soc.common.game.Resource.Wheat;
import org.soc.common.game.actions.PlaceRobber;
import org.soc.common.game.actions.RobPlayer;
import org.soc.common.internationalization.I;
import org.soc.common.utils.Util;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.meta.Meta;
import org.soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidget;
import org.soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidget.DevelopmentCardWidgetFactory;
import org.soc.gwt.client.images.R;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Order;

public interface DevelopmentCard extends Meta, Serializable, HasPlayableChangedHandlers {
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
  public int id();
  public void setId(int id);
  /* Returns true when this development card can be played this turn */
  public boolean isPlayable();
  public void setPlayable(boolean isPlayable);
  public int hashCode();
  public DevelopmentCardWidget createPlayCardWidget(
          DevelopmentCardWidgetFactory factory);

  @GenEvent public class PlayableChanged {
    @Order(0) boolean playable;
  }

  public abstract class AbstractDevelopmentCard implements Serializable, DevelopmentCard {
    private static final long serialVersionUID = 3192052784726040369L;
    protected String invalidMessage;
    protected String message = "No message implemented yet for Devcard" + toString();
    protected int turnBought = 0;
    private int id = 0;
    private boolean isPlayable = false;
    private static ResourceList cost = new ResourceList();
    private transient EventBus eventBus = new SimpleEventBus();
    static {
      cost.add(new Wheat());
      cost.add(new Ore());
      cost.add(new Sheep());
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
      ResourceList copy = player.resources().copy();
      // Pay resources player can simply pay for
      copy.subtractResources(getCost());
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
    public int id() {
      return id;
    }
    public void setId(int id) {
      this.id = id;
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
    @Override public String name() {
      return Util.shortName(this.getClass());
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
  }

  /** Standard ruleset VictoryPoint development card Playable any time during a players' turn and not
   * immediately played when receiving the card */
  public class VictoryPoint extends AbstractDevelopmentCard implements VictoryPointItem {
    @Override public Icon icon() {
      return new IconImpl(R.icons().victoryPoint16(), R.icons().victoryPoint32(), R
              .icons()
              .victoryPoint48());
    }
    @Override public String getLocalizedName() {
      return I.get().constants().victoryPoint();
    }
    @Override public String getDescription() {
      return I.get().constants().victoryPointDescription();
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
  }

  /* Standard monopoly Steals all resources of a chosen type from all opponents */
  public class Monopoly extends AbstractDevelopmentCard {
    public static ResourceList staticMonoPolyableResources = new ResourceList();
    private Resource resource;

    @Override public Icon icon() {
      return new IconImpl(R.icons().monopoly16(), R.icons().monopoly32(), R.icons().monopoly48());
    }
    @Override public String name() {
      return "Monopoly";
    }
    @Override public String getLocalizedName() {
      return I.get().constants().monopoly();
    }
    @Override public String getDescription() {
      return I.get().constants().monopolyDescription();
    }

    static {
      staticMonoPolyableResources.add(new Timber());
      staticMonoPolyableResources.add(new Wheat());
      staticMonoPolyableResources.add(new Ore());
      staticMonoPolyableResources.add(new Clay());
      staticMonoPolyableResources.add(new Sheep());
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
      return staticMonoPolyableResources;
    }
    /** Valid when having non-null resource and resource is of a basic type */
    @Override public boolean isValid(Game game)
    {
      if (!(super.isValid(game)))
        return false;
      if (resource == null)
        return false;
      // Allow only one of the 5 basic resources
      if (!staticMonoPolyableResources.contains(resource))
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
          opponent.resources().moveTo(player.resources(), opponentResources);
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
  }

  public class RoadBuilding extends AbstractDevelopmentCard
  {
    @Override public Icon icon()
    {
      return new IconImpl(R.icons().roadBuilding16(),
              R.icons().roadBuilding32(), R.icons()
                      .roadBuilding48());
    }
    @Override public String name()
    {
      return "RoadBuilding";
    }
    @Override public String getLocalizedName()
    {
      return I.get().constants().roadBuilding();
    }
    @Override public String getDescription()
    {
      return I.get().constants().roadBuildingDescription();
    }
    @Override public void play(Game game, GamePlayer player)
    {
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
    @Override public boolean isAllowed(TurnPhase turnPhase)
    {
      return turnPhase.isBuilding();
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
    @Override public String name() {
      return "YearOfPlenty";
    }
    @Override public String getLocalizedName() {
      return I.get().constants().yearOfPlenty();
    }
    @Override public String getDescription() {
      return I.get().constants().yearOfPlentyDescription();
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
      player.resources().swapResourcesFrom(goldPick, game.bank());
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
    @Override public String name() {
      return "RoadBuilding";
    }
    @Override public String getLocalizedName() {
      return I.get().constants().dummyDevelopmentCard();
    }
    @Override public String getDescription() {
      return I.get().constants().dummyDevelopmentCardDescription();
    }
    @Override public DevelopmentCardWidget createPlayCardWidget(
            DevelopmentCardWidgetFactory factory)
    {
      return null;
    }
    @Override public int id() {
      return id;
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
    @Override public void setId(int id) {
      this.id = id;
    }
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
  }

  /* Represents a soldier from the standard rules development card set. */
  public class Soldier extends AbstractDevelopmentCard
  {
    @Override public Icon icon() {
      return new IconImpl(R.icons().soldier16(),
              R.icons().soldier32(), R.icons()
                      .soldier48());
    }
    @Override public String getLocalizedName() {
      return I.get().constants().soldier();
    }
    @Override public String getDescription() {
      return I.get().constants().soldierDescription();
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
  }
}