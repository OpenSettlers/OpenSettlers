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
import org.soc.common.views.behaviour.gameWidget.receivedOpponent.DefaultOpponentReceivedBehaviour;
import org.soc.common.views.widgetsInterface.main.GameWidget;

public class OpponentBehaviourFactory implements ReceiveGameBehaviourFactory
{
    private GameWidget gameWidget;

    public OpponentBehaviourFactory(GameWidget gameWidget)
    {
        super();
        this.gameWidget = gameWidget;
    }

    @Override
    public ReceiveGameBehaviour createBuildCityBehaviour(BuildCity buildCity)
    {
        return new DefaultOpponentReceivedBehaviour(gameWidget, buildCity);
    }

    @Override
    public ReceiveGameBehaviour createBuildRoadBehaviour(BuildRoad buildRoad)
    {
        return new DefaultOpponentReceivedBehaviour(gameWidget, buildRoad);
    }

    @Override
    public ReceiveGameBehaviour createBuildTownBehaviour(BuildTown buildTown)
    {
        return new DefaultOpponentReceivedBehaviour(gameWidget, buildTown);
    }

    @Override
    public ReceiveGameBehaviour createBuyDevelopmentcardBehaviour(
            BuildTown buildTown)
    {
        return new DefaultOpponentReceivedBehaviour(gameWidget, buildTown);
    }

    @Override
    public ReceiveGameBehaviour createClaimVictoryBehaviour(
            ClaimVictory claimVictory)
    {
        return new DefaultOpponentReceivedBehaviour(gameWidget, claimVictory);
    }

    @Override
    public ReceiveGameBehaviour createEndTurnBehaviour(EndTurn endTurn)
    {
        return new DefaultOpponentReceivedBehaviour(gameWidget, endTurn);
    }

    @Override
    public ReceiveGameBehaviour createHostStartsBehaviour(
            HostStartsGame hostStartsGame)
    {
        return new DefaultOpponentReceivedBehaviour(gameWidget, hostStartsGame);
    }

    @Override
    public ReceiveGameBehaviour createMessageFromServerBehaviour(
            MessageFromServer messageFromServer)
    {
        return new DefaultOpponentReceivedBehaviour(gameWidget,
                messageFromServer);
    }

    @Override
    public ReceiveGameBehaviour createMoveRobberBehaviour(PlaceRobber moveRobber)
    {
        return new DefaultOpponentReceivedBehaviour(gameWidget, moveRobber);
    }

    @Override
    public ReceiveGameBehaviour createPlayDevelopmentCardBehaviour(
            PlayDevelopmentCard playDevelopmentCard)
    {
        return new DefaultOpponentReceivedBehaviour(gameWidget,
                playDevelopmentCard);
    }

    @Override
    public ReceiveGameBehaviour createRobPlayerBehaviour(RobPlayer robPlayer)
    {
        return new DefaultOpponentReceivedBehaviour(gameWidget, robPlayer);
    }

    @Override
    public ReceiveGameBehaviour createRollDiceBehaviour(RollDice rollDice)
    {
        return new DefaultOpponentReceivedBehaviour(gameWidget, rollDice);
    }

    @Override
    public ReceiveGameBehaviour createAcceptOfferBehaviour(
            AcceptTradeOffer acceptTradeOffer)
    {
        return new DefaultOpponentReceivedBehaviour(gameWidget, acceptTradeOffer);
    }

    @Override
    public ReceiveGameBehaviour createCounterTradeOfferBehaviour(
            CounterTradeOffer counterTradeOffer)
    {
        return new DefaultOpponentReceivedBehaviour(gameWidget,
                counterTradeOffer);
    }

    @Override
    public ReceiveGameBehaviour createGameChatBehaviour(GameChat gameChat)
    {
        return new DefaultOpponentReceivedBehaviour(gameWidget, gameChat);
    }

    @Override
    public ReceiveGameBehaviour createGamePhaseHasEndedBehaviour(
            GamePhaseHasEnded gamePhaseHasEnded)
    {
        return new DefaultOpponentReceivedBehaviour(gameWidget,
                gamePhaseHasEnded);
    }

    @Override
    public ReceiveGameBehaviour createLooseCardsBehaviour(LooseCards looseCards)
    {
        return new DefaultOpponentReceivedBehaviour(gameWidget, looseCards);
    }

    @Override
    public ReceiveGameBehaviour createPlayDevelopmentCardBehaviour(
            BuyDevelopmentCard buyDevelopmentCard)
    {
        return new DefaultOpponentReceivedBehaviour(gameWidget,
                buyDevelopmentCard);
    }

    @Override
    public ReceiveGameBehaviour createQueuedTradeResponseBehaviour(
            QueuedTradeResponse queuedTradeResponse)
    {
        return new DefaultOpponentReceivedBehaviour(gameWidget,
                queuedTradeResponse);
    }

    @Override
    public ReceiveGameBehaviour createRejectOfferBehaviour(
            RejectTradeOffer rejectTradeOffer)
    {
        return new DefaultOpponentReceivedBehaviour(gameWidget, rejectTradeOffer);
    }

    @Override
    public ReceiveGameBehaviour createRolledSameBehaviour(RolledSame rolledSame)
    {
        return new DefaultOpponentReceivedBehaviour(gameWidget, rolledSame);
    }

    @Override
    public ReceiveGameBehaviour createStartingPlayerDeterminedBehaviour(
            StartingPlayerDetermined startingPlayerDetermined)
    {
        return new DefaultOpponentReceivedBehaviour(gameWidget,
                startingPlayerDetermined);
    }

    @Override
    public ReceiveGameBehaviour createTradeBankBehaviour(TradeBank tradeBank)
    {
        return new DefaultOpponentReceivedBehaviour(gameWidget, tradeBank);
    }

    @Override
    public ReceiveGameBehaviour createTradeOfferBehaviour(TradeOffer tradeOffer)
    {
        return new DefaultOpponentReceivedBehaviour(gameWidget, tradeOffer);
    }

    @Override
    public ReceiveGameBehaviour createTradePlayerBehaviour(
            TradePlayer tradePlayer)
    {
        return new DefaultOpponentReceivedBehaviour(gameWidget, tradePlayer);
    }

    @Override
    public ReceiveGameBehaviour createTurnPhaseEndedBehaviour(
            TurnPhaseEnded turnPhaseEnded)
    {
        return new DefaultOpponentReceivedBehaviour(gameWidget, turnPhaseEnded);
    }

}
