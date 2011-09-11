package org.soc.common.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.soc.common.game.InitialPlacement.TwoTowns;
import org.soc.common.game.Variant.Standard;
import org.soc.common.game.actions.TurnAction;
import org.soc.common.game.actions.TurnAction.AbstractTurnAction;
import org.soc.common.game.board.HexLocation;
import org.soc.common.game.hexes.Hex;
import org.soc.common.game.pieces.Army;
import org.soc.common.game.pieces.City;
import org.soc.common.game.pieces.Piece;
import org.soc.common.game.pieces.Road;
import org.soc.common.game.pieces.Robber;
import org.soc.common.game.pieces.Town;
import org.soc.common.game.pieces.Piece.StockPiece;

/** Abstracted properties for game {@see IRuleSet} to modify */
public interface GameRules extends Serializable {
  public int getStockRoadAmount();
  public GameRules setStockRoadAmount(int stockRoads);
  public int getStockCityAmount();
  public GameRules setStockCityAmount(int stockCities);
  public int getStockTownAmount();
  public GameRules setStockTownAmount(int stockTowns);
  public int getStockWallAmount();
  public GameRules setStockWallAmount(int stockWalls);
  public int getStockBridgeAmount();
  public GameRules setStockBridgeAmount(int stockBridges);
  public int getStockShipAmount();
  public GameRules setStockShipAmount(int stockShips);
  /* Creates an action for what to do when a 7 is rolled or a soldier development card is played.
   * Standard will return PlaceRobber, where SeaFarers will return a PlaceRobberPirate */
  public TurnAction createPlaceRobberPirateAction();
  /* Returns a list of piece types allowed in stock along with amount of items maximum in stock for
   * any player */
  public List<StockPiece> stockPieces();
  /* Returns a list of TurnAction types allowed to be played during a turn */
  public List<TurnAction> possibleActions();
  /* Returns list of basic resource types */
  public List<Resource> supportedResources();
  /* Returns a list of resources allowed to trade to players and bank */
  public List<Resource> tradeableResources();
  /* Returns a list of supported hex types */
  public List<Hex> hexTypes();
  /* Returns amount of resources in the bank at beginning of the game */
  public int getBankAmountPerResource();
  // Returns the type of the dice rolled by the player on turn in the
  // RollDiceTurnPhase
  public Dice getDiceType();
  // Sets the type of the dice
  public GameRules setDiceType(Dice diceType);
  public List<Piece> playablePieces();
  public Army getLargestArmy();
  public GameRules setLargestArmy(Army largestArmy);
  public void setRules(Game game);
  public GameRules setDevelopmentCardStack(DevelopmentCardList devCards);
  public GameRules setSupportedPorts(PortList ports);
  public PortList getSupportedPorts();
  public List<GamePhase> supportedPhases();
  public InitialPlacement getInitialPlacementStrategy();
  public GameRules setInitialPlacementStrategy(
          InitialPlacement strategy);
  public PortList portsAtStart();

  public class GameRulesImpl implements GameRules {
    private static final long serialVersionUID = -6260028559443830085L;
    private Game game;
    private List<Variant> variants = new ArrayList<Variant>();
    private List<TurnAction> possibleActions = new ArrayList<TurnAction>();
    private List<GamePhase> supportedGamePhases = new ArrayList<GamePhase>();
    private List<Resource> playableResources = new ArrayList<Resource>();
    private List<Piece> playablePieces = new ArrayList<Piece>();
    private List<Hex> playableHexTypes = new ArrayList<Hex>();
    private List<StockPiece> stockPieceTypes = new ArrayList<StockPiece>();
    private List<Resource> tradeableResources = new ArrayList<Resource>();
    private DevelopmentCardList devCards;
    private PortList supportedPorts = new PortList(true);
    private PortList portsAtStart = new PortList();
    private int stockTowns;
    private int stockCities;
    private int stockRoads;
    private int stockShips;
    private int stockWalls;
    private int stockBridges;
    private int bankAmountPerResource = 19;
    private boolean isSeaFarers = false;
    private boolean isSa3D = false;
    private boolean isCitiesKnights = false;
    private boolean isExtended = false;
    private boolean isPioneers = false;
    private boolean isTeamGame = false;
    private Army largestArmy;
    // State of last rolled dice
    private Dice diceType;
    private InitialPlacement placementStrategy = new TwoTowns();

    public GameRulesImpl(Game game) {
      this.game = game;
      // Add standard rule set per default
      variants.add(new Standard(game));
    }
    public GameRulesImpl() {}
    /** @return the isSeaFarers */
    public boolean isSeaFarers() {
      return isSeaFarers;
    }
    public GameRulesImpl setSeaFarers(boolean isSeaFarers) {
      this.isSeaFarers = isSeaFarers;
      return this;
    }
    public boolean isSa3D() {
      return isSa3D;
    }
    public GameRulesImpl setSa3D(boolean isSa3D) {
      this.isSa3D = isSa3D;
      return this;
    }
    public boolean isCitiesKnights() {
      return isCitiesKnights;
    }
    public GameRulesImpl setCitiesKnights(boolean isCitiesKnights) {
      this.isCitiesKnights = isCitiesKnights;
      return this;
    }
    public boolean isExtended() {
      return isExtended;
    }
    public GameRulesImpl setExtended(boolean isExtended) {
      this.isExtended = isExtended;
      return this;
    }
    public boolean isPioneers() {
      return isPioneers;
    }
    public GameRulesImpl setPioneers(boolean isPioneers) {
      this.isPioneers = isPioneers;
      return this;
    }
    public boolean isTeamGame() {
      return isTeamGame;
    }
    public GameRulesImpl setTeamGame(boolean isTeamGame) {
      this.isTeamGame = isTeamGame;
      return this;
    }
    @Override public AbstractTurnAction createPlaceRobberPirateAction() {
      return null;
    }
    public List<TurnAction> possibleActions() {
      return possibleActions;
    }
    public List<Resource> supportedResources() {
      return playableResources;
    }
    public List<Resource> tradeableResources() {
      return tradeableResources;
    }
    public List<StockPiece> stockPieces() {
      return stockPieceTypes;
    }
    public int getBankAmountPerResource() {
      return bankAmountPerResource;
    }
    @Override public Dice getDiceType() {
      return diceType;
    }
    @Override public GameRulesImpl setDiceType(Dice diceType) {
      this.diceType = diceType;
      return this;
    }
    @Override public List<Hex> hexTypes() {
      return playableHexTypes;
    }
    @Override public Army getLargestArmy() {
      return largestArmy;
    }
    @Override public List<Piece> playablePieces() {
      return playablePieces;
    }
    @Override public GameRules setLargestArmy(Army largestArmy) {
      this.largestArmy = largestArmy;
      return this;
    }
    @Override public void setRules(Game game) {
      // Assuming standard is always present and always first
      for (Variant variant : variants)
        variant.setRules(this);
      createBank();
      createTradeableResources();
      createPlayerStocks();
      createInitialPortList();
      createStockPieces();
      game.setDice(getDiceType());
      game.setDevelopmentCards(devCards);
      // Robber is hardcoded, not yet any variant known not using it.
      game.setRobber(new Robber(new HexLocation(0, 0)));
    }
    private void createStockPieces() {
      for (Piece piece : playablePieces)
        if (piece instanceof StockPiece)
          stockPieceTypes.add((StockPiece) piece);
    }
    /* Give each player a stock with contents based on */
    private void createPlayerStocks() {
      for (GamePlayer player : game.players()) {
        for (int i = 0; i < stockCities; i++)
          player.stock().cities()
                  .add((City) new City().setPlayer(player));
        for (int i = 0; i < stockTowns; i++)
          player.stock().towns()
                  .add((Town) new Town().setPlayer(player));
        for (int i = 0; i < stockRoads; i++)
          player.stock().roads()
                  .add((Road) new Road().setPlayer(player));
      }
    }
    private void createTradeableResources() {
      for (Resource resource : supportedResources())
        if (resource.isTradeable())
          tradeableResources.add(resource);
    }
    private void createInitialPortList() {
      for (GamePlayer player : game.players())
        for (Port port : portsAtStart)
          player.ports().add(port.copy());
    }
    /* Creates a bank. Adds X amount of resources per resource type found in the list of playable
     * resources, where X is amount per resource */
    private void createBank() {
      for (Resource resource : supportedResources())
        for (int i = 0; i < getBankAmountPerResource(); i++)
          game.bank().add(resource.copy());
    }
    @Override public int getStockBridgeAmount() {
      return stockBridges;
    }
    @Override public int getStockCityAmount() {
      return stockCities;
    }
    @Override public int getStockRoadAmount() {
      return stockRoads;
    }
    @Override public int getStockShipAmount() {
      return stockShips;
    }
    @Override public int getStockTownAmount() {
      return stockTowns;
    }
    @Override public int getStockWallAmount() {
      return stockWalls;
    }
    @Override public GameRules setStockBridgeAmount(int stockBridges) {
      this.stockBridges = stockBridges;
      return this;
    }
    @Override public GameRules setStockCityAmount(int stockCities) {
      this.stockCities = stockCities;
      return this;
    }
    @Override public GameRules setStockRoadAmount(int stockRoads) {
      this.stockRoads = stockRoads;
      return this;
    }
    @Override public GameRules setStockShipAmount(int stockShips) {
      this.stockShips = stockShips;
      return this;
    }
    @Override public GameRules setStockTownAmount(int stockTowns) {
      this.stockTowns = stockTowns;
      return this;
    }
    @Override public GameRules setStockWallAmount(int stockWalls) {
      this.stockWalls = stockWalls;
      return this;
    }
    @Override public GameRules setDevelopmentCardStack(DevelopmentCardList devCards) {
      this.devCards = devCards;
      return this;
    }
    @Override public PortList getSupportedPorts() {
      return supportedPorts;
    }
    @Override public GameRules setSupportedPorts(PortList ports) {
      supportedPorts = ports;
      return this;
    }
    @Override public List<GamePhase> supportedPhases() {
      return supportedGamePhases;
    }
    @Override public InitialPlacement getInitialPlacementStrategy() {
      return placementStrategy;
    }
    @Override public GameRules setInitialPlacementStrategy(InitialPlacement strategy) {
      this.placementStrategy = strategy;
      return this;
    }
    public PortList portsAtStart() {
      return portsAtStart;
    }
  }
}
