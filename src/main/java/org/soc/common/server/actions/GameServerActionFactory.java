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
  public ServerAction createHostStartsGameServerAction(
          HostStartsGame hostStartsGame);
  public ServerAction createRobPlayerServerAction(RobPlayer robPlayer);
  public ServerAction createBuyDevelopmentCardServerAction(
          BuyDevelopmentCard buyDevelopmentCard);
  public ServerAction createRollDiceServerAction(RollDice rollDice);
  public ServerAction createTradeOfferAction(TradeOffer tradeOffer);
  public ServerAction createEndTurnServerAction(EndTurn endTurn);
  public ServerAction createDefaultServerAction(GameAction action);

  public class ServerActionFactory implements GameServerActionFactory {
    private GameServer gameServer;

    public ServerActionFactory(GameServer gameServer) {
      super();
      this.gameServer = gameServer;
    }
    @Override public ServerAction createBuyDevelopmentCardServerAction(
            BuyDevelopmentCard buyDevelopmentCard) {
      return new ServerBuyDevelopmentCard(gameServer, buyDevelopmentCard);
    }
    @Override public ServerAction createDefaultServerAction(GameAction action) {
      return new DefaultAction(gameServer, action);
    }
    @Override public ServerAction createEndTurnServerAction(EndTurn endTurn) {
      return new ServerEndTurn(gameServer, endTurn);
    }
    @Override public ServerAction createHostStartsGameServerAction(HostStartsGame hostStartsGame) {
      return new ServerStartGame(gameServer, hostStartsGame);
    }
    @Override public ServerAction createRobPlayerServerAction(RobPlayer robPlayer) {
      return new ServerRobPlayer(gameServer, robPlayer);
    }
    @Override public ServerAction createRollDiceServerAction(RollDice rollDice) {
      return new ServerRollDice(gameServer, rollDice);
    }
    @Override public ServerAction createTradeOfferAction(TradeOffer tradeOffer) {
      return new ServerHotseatOfferTrade(gameServer, tradeOffer);
    }
  }
}