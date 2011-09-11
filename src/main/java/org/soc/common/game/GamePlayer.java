package org.soc.common.game;

import java.io.Serializable;

import org.soc.common.game.DevelopmentCard.AbstractDevelopmentCard;
import org.soc.common.game.RoadTokensChangedEvent.HasRoadTokensChangedHandlers;
import org.soc.common.game.RoadTokensChangedEvent.RoadTokensChangedHandler;
import org.soc.common.game.VictoryPointsChangedEvent.HasVictoryPointsChangedHandlers;
import org.soc.common.game.VictoryPointsChangedEvent.VictoryPointsChangedHandler;
import org.soc.common.game.bots.Bot;
import org.soc.common.game.pieces.Army;
import org.soc.common.game.pieces.CityList;
import org.soc.common.game.pieces.PointPieceList;
import org.soc.common.game.pieces.ProducerList;
import org.soc.common.game.pieces.RoadList;
import org.soc.common.game.pieces.ShipList;
import org.soc.common.game.pieces.SidePieceList;
import org.soc.common.game.pieces.Stock;
import org.soc.common.game.pieces.TownList;
import org.soc.common.server.entities.User;
import org.soc.common.server.entities.User.Player;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Order;

/*
 * Represents a player playing a game
 */
public interface GamePlayer extends Serializable, HasRoadTokensChangedHandlers,
        HasVictoryPointsChangedHandlers {
  public User user();
  public GamePlayer setColor(String color);
  public String color();
  public TownList towns();
  public CityList cities();
  public RoadList roads();
  public ShipList ships();
  public ProducerList producers();
  public SidePieceList sidePieces();
  public PointPieceList pointPieces();
  public Stock stock();
  public int roadBuildingTokens();
  public GamePlayer setRoadBuildingTokens(int roadBuildingTokens);
  public VictoryPointsList victoryPoints();
  public DevelopmentCardList playedDevelopmentCards();
  public boolean isOnTurn();
  public GamePlayer setStock(Stock stock);
  public PortList ports();
  public DevelopmentCardList developmentCards();
  public GamePlayer setOnTurn(boolean isOnTurn);
  public int maximumCardsInHandWhenSeven();
  public void setMaximumCardsInHandWhenSeven(int maximumCardsInHandWhenSeven);
  public ResourceList resources();
  public Army army();
  public Bot bot();
  public boolean isNot(GamePlayer other);

  @GenEvent public class RoadTokensChanged {
    @Order(0) int newTokenAmount;
  }

  @GenEvent public class VictoryPointsChanged {
    @Order(0) VictoryPointItem addedPoint;
    @Order(1) VictoryPointItem removedPoint;
  }

  public class GamePlayerImpl implements GamePlayer {
    private User user;
    private transient SimpleEventBus eventBus = new SimpleEventBus();
    // Hand resource cards
    private ResourceList resources = new ResourceList();
    private int roadBuildingTokens;
    private String color;
    // Maximum cards the player may have in his hand
    // This is player specific, as a wall may increase this number
    private int maximumCardsInHandWhenSeven = 7;
    // Stock of the player: list of roads, ships, towns, cities, knights etc a
    // player has in stock
    private Stock stock = new Stock();
    private TownList towns = new TownList();
    private CityList cities = new CityList();
    private RoadList roads = new RoadList();
    private ShipList ships = new ShipList();
    private ProducerList producables = new ProducerList();
    private PointPieceList pointPieces = new PointPieceList();
    private SidePieceList sidePieces = new SidePieceList();
    // Keep track of being on turn
    private boolean isOnTurn = false;
    // List of ports the player has developed
    private PortList ports = new PortList();
    // Development cards in hand
    private DevelopmentCardList developmentCards = new DevelopmentCardList();
    // Played development cards
    private DevelopmentCardList playedDevelopmentCards = new DevelopmentCardList();
    // List of victory points
    private VictoryPointsList victoryPoints = new VictoryPointsList();
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
    public VictoryPointsList victoryPoints() {
      return victoryPoints;
    }
    public DevelopmentCardList playedDevelopmentCards() {
      return playedDevelopmentCards;
    }
    public void removeResources(ResourceList resources) {
      resources.subtractResources(resources);
    }
    public void addResources(ResourceList resources) {
      this.resources.addList(resources);
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
    public PortList ports() {
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
    public ResourceList resources() {
      return resources;
    }
    public void setResources(ResourceList resources) {
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
    @Override public CityList cities() {
      return cities;
    }
    @Override public TownList towns() {
      return towns;
    }
    @Override public RoadList roads() {
      return roads;
    }
    @Override public PointPieceList pointPieces() {
      return pointPieces;
    }
    @Override public ProducerList producers() {
      return producables;
    }
    @Override public ShipList ships() {
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