package org.soc.common.server.actions;

import org.soc.common.game.actions.BuyDevelopmentCard;
import org.soc.common.game.actions.EndTurn;
import org.soc.common.game.actions.GameAction;
import org.soc.common.game.actions.HostStartsGame;
import org.soc.common.game.actions.RobPlayer;
import org.soc.common.game.actions.RollDice;
import org.soc.common.game.trading.TradeOffer;
import org.soc.common.server.GameServer;
import org.soc.common.server.actions.ServerAction.DefaultAction;
import org.soc.common.server.actions.ServerAction.ServerBuyDevelopmentCard;
import org.soc.common.server.actions.ServerAction.ServerEndTurn;
import org.soc.common.server.actions.ServerAction.ServerHotseatOfferTrade;
import org.soc.common.server.actions.ServerAction.ServerRobPlayer;
import org.soc.common.server.actions.ServerAction.ServerRollDice;
import org.soc.common.server.actions.ServerAction.ServerStartGame;

/** Creates a server side action for given GameAction. Returning null is not allowed, instead return
 * a null object or a simple wrapper like DefaultAction */
public interface GameServerActionFactory {
  public ServerAction createHostStartsGame(
          HostStartsGame hostStartsGame);
  public ServerAction createRobPlayer(RobPlayer robPlayer);
  public ServerAction createBuyDevelopmentCard(
          BuyDevelopmentCard buyDevelopmentCard);
  public ServerAction createRollDice(RollDice rollDice);
  public ServerAction createTrade(TradeOffer tradeOffer);
  public ServerAction createEndTurn(EndTurn endTurn);
  public ServerAction createDefault(GameAction action);

  public class ServerActionFactory implements GameServerActionFactory {
    private GameServer gameServer;

    public ServerActionFactory(GameServer gameServer) {
      super();
      this.gameServer = gameServer;
    }
    @Override public ServerAction createBuyDevelopmentCard(
            BuyDevelopmentCard buyDevelopmentCard) {
      return new ServerBuyDevelopmentCard(gameServer, buyDevelopmentCard);
    }
    @Override public ServerAction createDefault(GameAction action) {
      return new DefaultAction(gameServer, action);
    }
    @Override public ServerAction createEndTurn(EndTurn endTurn) {
      return new ServerEndTurn(gameServer, endTurn);
    }
    @Override public ServerAction createHostStartsGame(HostStartsGame hostStartsGame) {
      return new ServerStartGame(gameServer, hostStartsGame);
    }
    @Override public ServerAction createRobPlayer(RobPlayer robPlayer) {
      return new ServerRobPlayer(gameServer, robPlayer);
    }
    @Override public ServerAction createRollDice(RollDice rollDice) {
      return new ServerRollDice(gameServer, rollDice);
    }
    @Override public ServerAction createTrade(TradeOffer tradeOffer) {
      return new ServerHotseatOfferTrade(gameServer, tradeOffer);
    }
  }
}