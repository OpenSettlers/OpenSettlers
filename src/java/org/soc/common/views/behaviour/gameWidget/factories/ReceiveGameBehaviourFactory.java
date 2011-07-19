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
import org.soc.common.actions.gameAction.trading.AcceptTradeOffer;
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
import org.soc.common.views.behaviour.gameWidget.received.ReceiveGameBehaviour;

public interface ReceiveGameBehaviourFactory
{
    public ReceiveGameBehaviour createRollDiceBehaviour(RollDice rollDice);

    public ReceiveGameBehaviour createBuildTownBehaviour(BuildTown buildTown);

    public ReceiveGameBehaviour createBuildRoadBehaviour(BuildRoad buildRoad);

    public ReceiveGameBehaviour createBuildCityBehaviour(BuildCity buildCity);

    public ReceiveGameBehaviour createBuyDevelopmentcardBehaviour(
            BuildTown buildTown);

    public ReceiveGameBehaviour createPlayDevelopmentCardBehaviour(
            PlayDevelopmentCard playDevelopmentCard);

    public ReceiveGameBehaviour createEndTurnBehaviour(EndTurn endTurn);

    public ReceiveGameBehaviour createClaimVictoryBehaviour(
            ClaimVictory claimVictory);

    public ReceiveGameBehaviour createMoveRobberBehaviour(PlaceRobber moveRobber);

    public ReceiveGameBehaviour createRobPlayerBehaviour(RobPlayer robPlayer);

    public ReceiveGameBehaviour createHostStartsBehaviour(
            HostStartsGame hostStartsGame);

    public ReceiveGameBehaviour createMessageFromServerBehaviour(
            MessageFromServer messageFromServer);

    public ReceiveGameBehaviour createAcceptOfferBehaviour(
            AcceptTradeOffer acceptTradeOffer);

    public ReceiveGameBehaviour createPlayDevelopmentCardBehaviour(
            BuyDevelopmentCard buyDevelopmentCard);

    public ReceiveGameBehaviour createCounterTradeOfferBehaviour(
            CounterTradeOffer counterTradeOffer);

    public ReceiveGameBehaviour createLooseCardsBehaviour(LooseCards looseCards);

    public ReceiveGameBehaviour createRejectOfferBehaviour(
            RejectTradeOffer rejectTradeOffer);

    public ReceiveGameBehaviour createTradeBankBehaviour(TradeBank tradeBank);

    public ReceiveGameBehaviour createTradeOfferBehaviour(TradeOffer tradeOffer);

    public ReceiveGameBehaviour createTradePlayerBehaviour(
            TradePlayer tradePlayer);

    public ReceiveGameBehaviour createQueuedTradeResponseBehaviour(
            QueuedTradeResponse queuedTradeResponse);

    public ReceiveGameBehaviour createRolledSameBehaviour(RolledSame rolledSame);

    public ReceiveGameBehaviour createStartingPlayerDeterminedBehaviour(
            StartingPlayerDetermined startingPlayerDetermined);

    public ReceiveGameBehaviour createTurnPhaseEndedBehaviour(
            TurnPhaseEnded turnPhaseEnded);

    public ReceiveGameBehaviour createGameChatBehaviour(GameChat gameChat);

    public ReceiveGameBehaviour createGamePhaseHasEndedBehaviour(
            GamePhaseHasEnded gamePhaseHasEnded);
}
