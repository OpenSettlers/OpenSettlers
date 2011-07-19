package org.soc.common.views.behaviour.gameWidget.factories;

import org.soc.common.actions.gameAction.meta.GameChat;
import org.soc.common.actions.gameAction.meta.MessageFromServer;
import org.soc.common.actions.gameAction.standard.BuildCity;
import org.soc.common.actions.gameAction.standard.BuildRoad;
import org.soc.common.actions.gameAction.standard.BuildTown;
import org.soc.common.actions.gameAction.standard.BuyDevelopmentCard;
import org.soc.common.actions.gameAction.standard.ClaimVictory;
import org.soc.common.actions.gameAction.standard.CounterTradeOffer;
import org.soc.common.actions.gameAction.standard.LooseCards;
import org.soc.common.actions.gameAction.standard.PlaceRobber;
import org.soc.common.actions.gameAction.standard.PlayDevelopmentCard;
import org.soc.common.actions.gameAction.standard.RobPlayer;
import org.soc.common.actions.gameAction.standard.RollDice;
import org.soc.common.actions.gameAction.trading.QueuedTradeResponse;
import org.soc.common.actions.gameAction.trading.RejectTradeOffer;
import org.soc.common.actions.gameAction.trading.TradeBank;
import org.soc.common.actions.gameAction.trading.TradeOffer;
import org.soc.common.actions.gameAction.trading.TradePlayer;
import org.soc.common.actions.gameAction.turns.EndTurn;
import org.soc.common.actions.gameAction.turns.GamePhaseHasEnded;
import org.soc.common.actions.gameAction.turns.HostStartsGame;
import org.soc.common.actions.gameAction.turns.RolledSame;
import org.soc.common.actions.gameAction.turns.StartingPlayerDetermined;
import org.soc.common.actions.gameAction.turns.TurnPhaseEnded;
import org.soc.common.views.behaviour.gameWidget.GameBehaviour;

/*
 * Relates a TurnAction to a viewport GameBehaviour. 
 * Different variants will have different factories depending on the TurnActions
 * specific for that variant.
 */
public interface GameBehaviourFactory
{
    public GameBehaviour createRollDiceBehaviour(RollDice rollDice);

    public GameBehaviour createBuildTownBehaviour(BuildTown buildTown);

    public GameBehaviour createBuildRoadBehaviour(BuildRoad buildRoad);

    public GameBehaviour createBuildCityBehaviour(BuildCity buildCity);

    public GameBehaviour createBuyDevelopmentcardBehaviour(
            BuyDevelopmentCard buyDevelopmentCard);

    public GameBehaviour createPlayDevelopmentCardBehaviour(
            PlayDevelopmentCard playDevelopmentCard);

    public GameBehaviour createEndTurnBehaviour(EndTurn endTurn);

    public GameBehaviour createClaimVictoryBehaviour(ClaimVictory claimVictory);

    public GameBehaviour createMoveRobberBehaviour(PlaceRobber moveRobber);

    public GameBehaviour createRobPlayerBehaviour(RobPlayer robPlayer);

    public GameBehaviour createHostStartsBehaviour(HostStartsGame hostStartsGame);

    public GameBehaviour createMessageFromServerBehaviour(
            MessageFromServer messageFromServer);

    public GameBehaviour createCounterTradeOfferBehaviour(
            CounterTradeOffer counterTradeOffer);

    public GameBehaviour createLooseCardsBehaviour(LooseCards looseCards);

    public GameBehaviour createRejectOfferBehaviour(
            RejectTradeOffer rejectTradeOffer);

    public GameBehaviour createTradeBankBehaviour(TradeBank tradeBank);

    public GameBehaviour createTradeOfferBehaviour(TradeOffer tradeOffer);

    public GameBehaviour createTradePlayerBehaviour(TradePlayer tradePlayer);

    public GameBehaviour createQueuedTradeResponseBehaviour(
            QueuedTradeResponse queuedTradeResponse);

    public GameBehaviour createRolledSameBehaviour(RolledSame rolledSame);

    public GameBehaviour createStartingPlayerDeterminedBehaviour(
            StartingPlayerDetermined startingPlayerDetermined);

    public GameBehaviour createTurnPhaseEndedBehaviour(
            TurnPhaseEnded turnPhaseEnded);

    public GameBehaviour createGameChatBehaviour(GameChat gameChat);

    public GameBehaviour createGamePhaseHasEndedBehaviour(
            GamePhaseHasEnded gamePhaseHasEnded);
}
