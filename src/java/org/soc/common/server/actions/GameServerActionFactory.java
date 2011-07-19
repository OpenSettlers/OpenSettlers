package org.soc.common.server.actions;

import org.soc.common.actions.gameAction.GameAction;
import org.soc.common.actions.gameAction.standard.BuyDevelopmentCard;
import org.soc.common.actions.gameAction.standard.RobPlayer;
import org.soc.common.actions.gameAction.standard.RollDice;
import org.soc.common.actions.gameAction.trading.TradeOffer;
import org.soc.common.actions.gameAction.turns.EndTurn;
import org.soc.common.actions.gameAction.turns.HostStartsGame;

/*
 * Creates a server side action for given GameAction. Returning null is not allowed,
 * instead return a null object or a simple wrapper like DefaultAction
 */
public interface GameServerActionFactory
{
    public ServerAction createHostStartsGameServerAction(
            HostStartsGame hostStartsGame);

    public ServerAction createRobPlayerServerAction(RobPlayer robPlayer);

    public ServerAction createBuyDevelopmentCardServerAction(
            BuyDevelopmentCard buyDevelopmentCard);

    public ServerAction createRollDiceServerAction(RollDice rollDice);

    public ServerAction createTradeOfferAction(TradeOffer tradeOffer);

    public ServerAction createEndTurnServerAction(EndTurn endTurn);

    /*
     * Returns associated server action for given GameAction.
     */
    public ServerAction createDefaultServerAction(GameAction action);
}