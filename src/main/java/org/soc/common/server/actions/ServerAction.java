package org.soc.common.server.actions;

import java.util.ArrayList;
import java.util.List;

import org.soc.common.game.DevelopmentCardList;
import org.soc.common.game.Dice.StandardDice;
import org.soc.common.game.GamePlayer;
import org.soc.common.game.actions.BuyDevelopmentCard;
import org.soc.common.game.actions.CounterTradeOffer;
import org.soc.common.game.actions.EndTurn;
import org.soc.common.game.actions.GameAction;
import org.soc.common.game.actions.HostStartsGame;
import org.soc.common.game.actions.RobPlayer;
import org.soc.common.game.actions.RollDice;
import org.soc.common.game.actions.GameAction.AbstractGameAction;
import org.soc.common.game.board.Board;
import org.soc.common.game.trading.AcceptTradeOffer;
import org.soc.common.game.trading.RejectTradeOffer;
import org.soc.common.game.trading.TradeOffer;
import org.soc.common.game.trading.TradeResponse;
import org.soc.common.server.GameServer;

/** Interface for actions needed to be performed on the server side, like rolling dice, shuffling
 * development cards deck */
public interface ServerAction {
  /* Action originating from a player */
  public GameAction getAction();
  public void execute();
  /* Returns the associated gameaction, but then it's data set so that opponents don't have data
   * they should not have. For example, a BuyDevelopmentCard action's DevelopmentCard should not be
   * known to the opponents. */
  public GameAction getOpponentAction();

  /** Any action not requiring any special server side logic */
  public class DefaultAction implements ServerAction {
    protected GameAction action;
    protected GameServer gameServer;

    public DefaultAction(GameServer gameServer, GameAction action) {
      this.action = action;
      this.gameServer = gameServer;
    }
    @Override public GameAction getAction() {
      return action;
    }
    @Override public void execute() {
      gameServer.game().performAction(action);
    }
    @Override public GameAction getOpponentAction() {
      return action;
    }
  }

  public class ServerBuyDevelopmentCard implements ServerAction {
    private BuyDevelopmentCard buyDevCard;
    private GameServer gameServer;

    public ServerBuyDevelopmentCard(GameServer gameServer, BuyDevelopmentCard buyDevCard) {
      super();
      this.buyDevCard = buyDevCard;
      this.gameServer = gameServer;
    }
    @Override public void execute() {
      buyDevCard.setDevCard(gameServer.game().developmentCardStack().drawTop());
      gameServer.game().performAction(buyDevCard);
    }
    @Override public AbstractGameAction getAction() {
      return buyDevCard;
    }
    @Override public GameAction getOpponentAction() {
      return new BuyDevelopmentCard()
              .setResources(buyDevCard.getResources())
              .setPlayer(buyDevCard.player());
    }
  }

  public class ServerEndTurn implements ServerAction {
    private GameServer gameServer;
    private EndTurn endTurn;

    public ServerEndTurn(GameServer gameServer, EndTurn endTurn) {
      super();
      this.gameServer = gameServer;
      this.endTurn = endTurn;
    }
    @Override public void execute() {
      if (endTurn.player().bot() != null)
        gameServer.setBotTurnHandled(true);
      gameServer.game().performAction(endTurn);
    }
    @Override public GameAction getAction() {
      return endTurn;
    }
    @Override public GameAction getOpponentAction() {
      return null;
    }
  }

  public class ServerHotseatOfferTrade implements ServerAction {
    private TradeOffer tradeOffer;
    private GameServer gameServer;

    public ServerHotseatOfferTrade(GameServer gameServer, TradeOffer tradeOffer) {
      super();
      this.tradeOffer = tradeOffer;
      this.gameServer = gameServer;
    }
    @Override public void execute() {
      gameServer.game().performAction(tradeOffer);
      for (GamePlayer opponent : gameServer.game().players()
              .opponents(tradeOffer.player())) {
        GameAction tradeResponse = getResponse(opponent);
        gameServer.sendAction(tradeResponse);
      }
    }
    @Override public GameAction getAction() {
      return tradeOffer;
    }
    @Override public GameAction getOpponentAction() {
      // TODO Auto-generated method stub
      return null;
    }
    /* Creates and returns a random response which can be accept, reject or counter */
    public GameAction getResponse(GamePlayer opponent) {
      boolean canAccept = opponent.resources().hasAtLeast(
              tradeOffer.getRequestedResources());
      boolean canCounter = opponent.resources().size() > 0;
      List<GameAction> responses = new ArrayList<GameAction>();
      responses.add(new RejectTradeOffer());
      if (canAccept)
        responses.add(new AcceptTradeOffer());
      if (canCounter) {
        CounterTradeOffer counter = new CounterTradeOffer();
        counter.getOfferedResources().addList(
                tradeOffer.getRequestedResources());
        counter.getRequestedResources().add(
                opponent.resources().getRandom(gameServer.random()));
        responses.add(counter);
      }
      int size = responses.size();
      size = size == 0 ? 0 : size + 1;
      int randomResponse = gameServer.random().nextInt(size, false);
      GameAction response = responses.get(randomResponse);
      response.setPlayer(opponent);
      ((TradeResponse) response).setOriginatingOffer(tradeOffer);
      return response;
    }
  }

  public class ServerRollDice implements ServerAction {
    RollDice rollDice;
    GameServer gameServer;

    public ServerRollDice(GameServer gameServer, RollDice rollDice) {
      super();
      this.rollDice = rollDice;
      this.gameServer = gameServer;
    }
    @Override public void execute() {
      StandardDice dice = new StandardDice();
      dice.roll(gameServer.random());
      rollDice.setDice(dice);
      gameServer.game().performAction(rollDice);
    }
    @Override public GameAction getAction() {
      return rollDice;
    }
    @Override public GameAction getOpponentAction() {
      return rollDice;
    }
  }

  public class ServerStartGame implements ServerAction {
    HostStartsGame hostStartsGame;
    GameServer gameServer;

    public ServerStartGame(GameServer gameServer, HostStartsGame hostStartsGame) {
      super();
      this.hostStartsGame = hostStartsGame;
      this.gameServer = gameServer;
    }
    @Override public AbstractGameAction getAction() {
      return hostStartsGame;
    }
    @Override public void execute() {
      // Make sure we have a valid game instance. If not, make a default one.
      if (hostStartsGame.getGame() == null)
        createNewGame();
      else
        hostStartsGame.getGame().initialize();
      gameServer.startGame(hostStartsGame.getGame());
      gameServer.game().performAction(hostStartsGame);
    }
    private void createNewGame() {
      gameServer.game().setBoard(new Board());
      hostStartsGame.setGame(gameServer.game());
    }
    private DevelopmentCardList shuffleDevcardsDeck(DevelopmentCardList devcards) {
      // DevelopmentCardList result = new DevelopmentCardList();
      // TODO: reimplement without GWT unsupported Hashtable
      // Create a list to associate random value to each development card
      // Map<Integer, DevelopmentCard> list = new Hashtable<Integer,
      // DevelopmentCard>();
      // Associate the random int value to each development card, put them
      // into sortable treemap
      // /for (DevelopmentCard dev : devcards)
      // list.put(gameServer.getRandom().nextInt(2100000000), dev);
      // Populate result with randomly ordered devcards
      // for (DevelopmentCard dev : list.values())
      // result.add(dev);
      return null;
    }
    @Override public AbstractGameAction getOpponentAction() {
      return hostStartsGame;
    }
  }

  public class ServerRobPlayer implements ServerAction {
    private RobPlayer robPlayer;
    private GameServer gameServer;

    public ServerRobPlayer(GameServer gameServer, RobPlayer robPlayer) {
      super();
      this.robPlayer = robPlayer;
      this.gameServer = gameServer;
    }
    @Override public void execute() {
      GamePlayer victim = gameServer.game().playerById(robPlayer.getVictimID());
      if (victim != null) {
        robPlayer.setStolenResource(victim.resources().getRandom(
                gameServer.random()));
      }
      gameServer.game().performAction(robPlayer);
    }
    @Override public AbstractGameAction getAction() {
      return robPlayer;
    }
    @Override public AbstractGameAction getOpponentAction() {
      return robPlayer;
    }
  }
}
