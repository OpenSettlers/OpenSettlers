package org.soc.common.game;

import java.io.*;

import org.soc.common.game.DevelopmentCard.AbstractDevelopmentCard;
import org.soc.common.game.Ports.MutablePortList;
import org.soc.common.game.Ports.MutablePortListImpl;
import org.soc.common.game.Resources.MutableResourceList;
import org.soc.common.game.Resources.MutableResourceListImpl;
import org.soc.common.game.Resources.ResourceList;
import org.soc.common.game.RoadTokensChangedEvent.HasRoadTokensChangedHandlers;
import org.soc.common.game.RoadTokensChangedEvent.RoadTokensChangedHandler;
import org.soc.common.game.VictoryPoints.MutableVictoryPointsList;
import org.soc.common.game.VictoryPoints.MutableVictoryPointsList.MutableVictoryPointsListImpl;
import org.soc.common.game.VictoryPointsChangedEvent.HasVictoryPointsChangedHandlers;
import org.soc.common.game.VictoryPointsChangedEvent.VictoryPointsChangedHandler;
import org.soc.common.game.bots.*;
import org.soc.common.game.pieces.*;
import org.soc.common.game.pieces.Cities.MutableCityList;
import org.soc.common.game.pieces.Roads.MutableRoadList;
import org.soc.common.game.pieces.Ships.MutableShipList;
import org.soc.common.game.pieces.Ships.MutableShipListImpl;
import org.soc.common.game.pieces.Towns.MutableTownList;
import org.soc.common.server.entities.*;
import org.soc.common.server.entities.User.Player;

import com.google.gwt.event.shared.*;
import com.gwtplatform.dispatch.annotation.*;

/*
 * Represents a player playing a game
 */
public interface GamePlayer extends Serializable, HasRoadTokensChangedHandlers,
        HasVictoryPointsChangedHandlers {
  public User user();
  public GamePlayer setColor(String color);
  public String color();
  public MutableTownList towns();
  public MutableCityList cities();
  public MutableRoadList roads();
  public MutableShipList ships();
  public ProducerList producers();
  public SidePieceList sidePieces();
  public PointPieceList pointPieces();
  public Stock stock();
  public int roadBuildingTokens(); // Tokens added when playing RoadBuilding card
  public GamePlayer setRoadBuildingTokens(int roadBuildingTokens);
  public MutableVictoryPointsList victoryPoints();
  public DevelopmentCardList playedDevelopmentCards();
  public boolean isOnTurn();
  public GamePlayer setStock(Stock stock);
  public MutablePortList ports();
  public DevelopmentCardList developmentCards();
  public GamePlayer setOnTurn(boolean isOnTurn);
  public int maximumCardsInHandWhenSeven();
  public void setMaximumCardsInHandWhenSeven(int maximumCardsInHandWhenSeven);
  public MutableResourceList resources();
  public Army army();
  public Bot bot();
  public boolean isNot(GamePlayer other);

  @GenEvent public class RoadTokensChanged {
    @Order(0) int newTokenAmount;
  }

  // TODO: move to VictoryPointsList
  @GenEvent public class VictoryPointsChanged {
    @Order(0) VictoryPointItem addedPoint;
    @Order(1) VictoryPointItem removedPoint;
  }

  public class GamePlayerImpl implements GamePlayer {
    private User user;
    private transient SimpleEventBus eventBus = new SimpleEventBus();
    // Hand resource cards
    private MutableResourceList resources = new MutableResourceListImpl();
    private int roadBuildingTokens;
    private String color;
    // Maximum cards the player may have in his hand
    // This is player specific, as a wall may increase this number
    private int maximumCardsInHandWhenSeven = 7;
    // Stock of the player: list of roads, ships, towns, cities, knights etc a
    // player has in stock
    private Stock stock = new Stock();
    private MutableTownList towns = new MutableTownList.Impl();
    private MutableCityList cities = new MutableCityList.Impl();
    private MutableRoadList roads = new MutableRoadList.Impl();
    private MutableShipList ships = new MutableShipListImpl();
    private ProducerList producables = new ProducerList();
    private PointPieceList pointPieces = new PointPieceList();
    private SidePieceList sidePieces = new SidePieceList();
    // Keep track of being on turn
    private boolean isOnTurn = false;
    // List of ports the player has developed
    private MutablePortList ports = new MutablePortListImpl();
    // Development cards in hand
    private DevelopmentCardList developmentCards = new DevelopmentCardList();
    // Played development cards
    private DevelopmentCardList playedDevelopmentCards = new DevelopmentCardList();
    // List of victory points
    private MutableVictoryPointsList victoryPoints = new MutableVictoryPointsListImpl();
    // Largest army
    private Army army = new Army();
    private Bot bot = null;

    public GamePlayerImpl() {/** empty serialization constructor */
    }
    public GamePlayerImpl(int id, String name) {
      user = new Player()
              .setId(id)
              .setName(name);
    }
    public GamePlayerImpl setUser(User user) {
      this.user = user;
      return this;
    }
    public int roadBuildingTokens() {
      return roadBuildingTokens;
    }
    public GamePlayer setRoadBuildingTokens(int roadBuildingTokens) {
      this.roadBuildingTokens = roadBuildingTokens;
      eventBus.fireEvent(new RoadTokensChangedEvent(roadBuildingTokens));
      return this;
    }
    public MutableVictoryPointsList victoryPoints() {
      return victoryPoints;
    }
    public DevelopmentCardList playedDevelopmentCards() {
      return playedDevelopmentCards;
    }
    public void useDevelopmentCard(AbstractDevelopmentCard developmentCard) {
      // Get rid of the card in our list of devcards
      developmentCards.remove(developmentCard);
      // only place it in stock when we should
      if (developmentCard.keepInStock()) {
        playedDevelopmentCards.add(developmentCard);
      }
    }
    public void addDevelopmentCard(AbstractDevelopmentCard developmentCard) {
      developmentCards.add(developmentCard);
    }
    public int getDevelopmentCardsCount() {
      return developmentCards.size();
    }
    public int getResourcesCount() {
      return resources.size();
    }
    public boolean isOnTurn() {
      return isOnTurn;
    }
    public Stock stock() {
      return stock;
    }
    public GamePlayer setStock(Stock stock) {
      this.stock = stock;
      return this;
    }
    public MutablePortList ports() {
      return ports;
    }
    public DevelopmentCardList developmentCards() {
      return developmentCards;
    }
    /* Returns amount of gold a player can trade for at the bank using given ResourceList */
    public int amountGold(ResourceList resourcesToTradeWith) {
      return ports.amountGold(resourcesToTradeWith);
    }
    /* Returns amount of gold a player can trade for */
    public int amountGold() {
      return ports.amountGold(this.resources);
    }
    public GamePlayer setOnTurn(boolean isOnTurn) {
      this.isOnTurn = isOnTurn;
      return this;
    }
    public int maximumCardsInHandWhenSeven() {
      return maximumCardsInHandWhenSeven;
    }
    public void setMaximumCardsInHandWhenSeven(int maximumCardsInHandWhenSeven) {
      this.maximumCardsInHandWhenSeven = maximumCardsInHandWhenSeven;
    }
    public MutableResourceList resources() {
      return resources;
    }
    public void setResources(MutableResourceList resources) {
      this.resources = resources;
    }
    public GamePlayer setColor(String color) {
      this.color = color;
      return this;
    }
    public String color() {
      return color;
    }
    @Override public User user() {
      return user;
    }
    @Override public int hashCode() {
      return user.id();
    }
    @Override public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      GamePlayerImpl other = (GamePlayerImpl) obj;
      if (user == null)
      {
        if (other.user != null)
          return false;
      }
      else if (!user.equals(other.user()))
        return false;
      return true;
    }
    @Override public Army army() {
      return army;
    }
    @Override public MutableCityList cities() {
      return cities;
    }
    @Override public MutableTownList towns() {
      return towns;
    }
    @Override public MutableRoadList roads() {
      return roads;
    }
    @Override public PointPieceList pointPieces() {
      return pointPieces;
    }
    @Override public ProducerList producers() {
      return producables;
    }
    @Override public MutableShipList ships() {
      return ships;
    }
    @Override public SidePieceList sidePieces() {
      return sidePieces;
    }
    @Override public Bot bot()
    {
      return bot;
    }
    public void setBot(Bot newBot)
    {
      this.bot = newBot;
      this.user = newBot;
    }
    @Override public HandlerRegistration addRoadTokensChangedHandler(
            RoadTokensChangedHandler handler) {
      return eventBus.addHandler(RoadTokensChangedEvent.getType(), handler);
    }
    @Override public void fireEvent(GwtEvent<?> event) {
      eventBus.fireEvent(event);
    }
    @Override public HandlerRegistration addVictoryPointsChangedHandler(
            VictoryPointsChangedHandler handler) {
      return eventBus.addHandler(VictoryPointsChangedEvent.getType(), handler);
    }
    @Override public boolean isNot(GamePlayer other) {
      return !equals(other);
    }
  }
}