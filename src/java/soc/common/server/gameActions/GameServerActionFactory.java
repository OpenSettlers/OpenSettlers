package soc.common.server.gameActions;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.standard.BuyDevelopmentCard;
import soc.common.actions.gameAction.standard.RobPlayer;
import soc.common.actions.gameAction.standard.RollDice;
import soc.common.actions.gameAction.trading.TradeOffer;
import soc.common.actions.gameAction.turns.EndTurn;
import soc.common.actions.gameAction.turns.HostStartsGame;

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