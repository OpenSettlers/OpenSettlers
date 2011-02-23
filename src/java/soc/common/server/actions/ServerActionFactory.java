package soc.common.server.actions;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.standard.BuyDevelopmentCard;
import soc.common.actions.gameAction.standard.RobPlayer;
import soc.common.actions.gameAction.standard.RollDice;
import soc.common.actions.gameAction.trading.TradeOffer;
import soc.common.actions.gameAction.turns.EndTurn;
import soc.common.actions.gameAction.turns.HostStartsGame;
import soc.common.server.GameServer;

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
