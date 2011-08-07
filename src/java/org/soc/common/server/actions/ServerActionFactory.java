package org.soc.common.server.actions;

import org.soc.common.actions.gameAction.GameAction;
import org.soc.common.actions.gameAction.standard.BuyDevelopmentCard;
import org.soc.common.actions.gameAction.standard.RobPlayer;
import org.soc.common.actions.gameAction.standard.RollDice;
import org.soc.common.actions.gameAction.trading.TradeOffer;
import org.soc.common.actions.gameAction.turns.EndTurn;
import org.soc.common.actions.gameAction.turns.HostStartsGame;
import org.soc.common.server.GameServer;

public class ServerActionFactory implements GameServerActionFactory
{
    private GameServer gameServer;

    public ServerActionFactory(GameServer gameServer)
    {
        super();
        this.gameServer = gameServer;
    }

    @Override
    public ServerAction createBuyDevelopmentCardServerAction(
            BuyDevelopmentCard buyDevelopmentCard)
    {
        return new ServerBuyDevelopmentCard(gameServer, buyDevelopmentCard);
    }

    @Override
    public ServerAction createDefaultServerAction(GameAction action)
    {
        return new DefaultAction(gameServer, action);
    }

    @Override
    public ServerAction createEndTurnServerAction(EndTurn endTurn)
    {
        return new ServerEndTurn(gameServer, endTurn);
    }

    @Override
    public ServerAction createHostStartsGameServerAction(
            HostStartsGame hostStartsGame)
    {
        return new ServerStartGame(gameServer, hostStartsGame);

    }

    @Override
    public ServerAction createRobPlayerServerAction(RobPlayer robPlayer)
    {
        return new ServerRobPlayer(gameServer, robPlayer);
    }

    @Override
    public ServerAction createRollDiceServerAction(RollDice rollDice)
    {
        return new ServerRollDice(gameServer, rollDice);
    }

    @Override
    public ServerAction createTradeOfferAction(TradeOffer tradeOffer)
    {
        return new ServerHotseatOfferTrade(gameServer, tradeOffer);
    }

}
