package soc.gwtClient.game.behaviour.gameBoard.factories;

import soc.common.actions.gameAction.GameChat;
import soc.common.actions.gameAction.GamePhaseHasEnded;
import soc.common.actions.gameAction.HostStartsGame;
import soc.common.actions.gameAction.MessageFromServer;
import soc.common.actions.gameAction.standard.BuildCity;
import soc.common.actions.gameAction.standard.BuildRoad;
import soc.common.actions.gameAction.standard.BuildTown;
import soc.common.actions.gameAction.standard.BuyDevelopmentCard;
import soc.common.actions.gameAction.standard.ClaimVictory;
import soc.common.actions.gameAction.standard.CounterTradeOffer;
import soc.common.actions.gameAction.standard.LooseCards;
import soc.common.actions.gameAction.standard.PlaceRobber;
import soc.common.actions.gameAction.standard.PlayDevelopmentCard;
import soc.common.actions.gameAction.standard.RobPlayer;
import soc.common.actions.gameAction.standard.RollDice;
import soc.common.actions.gameAction.trading.AcceptTradeOffer;
import soc.common.actions.gameAction.trading.RejectTradeOffer;
import soc.common.actions.gameAction.trading.TradeBank;
import soc.common.actions.gameAction.trading.TradeOffer;
import soc.common.actions.gameAction.trading.TradePlayer;
import soc.common.actions.gameAction.turns.EndTurn;
import soc.common.actions.gameAction.turns.QueuedTradeResponse;
import soc.common.actions.gameAction.turns.RolledSame;
import soc.common.actions.gameAction.turns.StartingPlayerDetermined;
import soc.common.actions.gameAction.turns.TurnPhaseEnded;
import soc.gwtClient.game.behaviour.gameBoard.opponent.DefaultOpponentReceivedBehaviour;
import soc.gwtClient.game.behaviour.gameBoard.received.ReceiveGameBehaviour;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

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
