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
import soc.common.game.gamePhase.DetermineFirstPlayerGamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.gwtClient.game.behaviour.gameBoard.opponent.DefaultOpponentReceivedBehaviour;
import soc.gwtClient.game.behaviour.gameBoard.received.GameOverGameBehaviour;
import soc.gwtClient.game.behaviour.gameBoard.received.ReceiveGameBehaviour;
import soc.gwtClient.game.behaviour.gameBoard.received.RollDiceResult;
import soc.gwtClient.game.behaviour.gameWidget.ErrorReceivedGameBehaviour;
import soc.gwtClient.game.behaviour.gameWidget.StartGameGameBehaviour;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

public class ReceivedActionGameBehaviourFactory implements
        ReceiveGameBehaviourFactory
{
    private GameWidget gamePanel;

    public ReceivedActionGameBehaviourFactory(GameWidget gamePanel)
    {
        super();
        this.gamePanel = gamePanel;
    }

    // if (gameAction.getPlayer().getBot() != null)
    // return new DefaultOpponentReceivedBehaviour(gamePanel, gameAction);

    @Override
    public ReceiveGameBehaviour createBuildCityBehaviour(BuildCity buildCity)
    {
        return new DefaultOpponentReceivedBehaviour(gamePanel, buildCity);
    }

    @Override
    public ReceiveGameBehaviour createBuildRoadBehaviour(BuildRoad buildRoad)
    {
        return new DefaultOpponentReceivedBehaviour(gamePanel, buildRoad);
    }

    @Override
    public ReceiveGameBehaviour createBuildTownBehaviour(BuildTown buildTown)
    {
        return new DefaultOpponentReceivedBehaviour(gamePanel, buildTown);
    }

    @Override
    public ReceiveGameBehaviour createBuyDevelopmentcardBehaviour(
            BuildTown buildTown)
    {
        return new DefaultOpponentReceivedBehaviour(gamePanel, buildTown);
    }

    @Override
    public ReceiveGameBehaviour createClaimVictoryBehaviour(
            ClaimVictory claimVictory)
    {
        return new GameOverGameBehaviour(gamePanel, claimVictory);
    }

    @Override
    public ReceiveGameBehaviour createEndTurnBehaviour(EndTurn endTurn)
    {
        return new DefaultOpponentReceivedBehaviour(gamePanel, endTurn);
    }

    @Override
    public ReceiveGameBehaviour createHostStartsBehaviour(
            HostStartsGame hostStartsGame)
    {
        return new StartGameGameBehaviour(gamePanel);
    }

    @Override
    public ReceiveGameBehaviour createMessageFromServerBehaviour(
            MessageFromServer messageFromServer)
    {
        return new ErrorReceivedGameBehaviour(messageFromServer);
    }

    @Override
    public ReceiveGameBehaviour createMoveRobberBehaviour(PlaceRobber moveRobber)
    {
        return new DefaultOpponentReceivedBehaviour(gamePanel, moveRobber);
    }

    @Override
    public ReceiveGameBehaviour createPlayDevelopmentCardBehaviour(
            PlayDevelopmentCard playDevelopmentCard)
    {
        return new DefaultOpponentReceivedBehaviour(gamePanel,
                playDevelopmentCard);
    }

    @Override
    public ReceiveGameBehaviour createRobPlayerBehaviour(RobPlayer robPlayer)
    {
        return new DefaultOpponentReceivedBehaviour(gamePanel, robPlayer);
    }

    @Override
    public ReceiveGameBehaviour createRollDiceBehaviour(RollDice rollDice)
    {
        if (gamePanel.getGame().getCurrentPhase() instanceof DetermineFirstPlayerGamePhase)
        {

        }
        if (gamePanel.getGame().getCurrentPhase() instanceof PlayTurnsGamePhase)
        {
            return new RollDiceResult(gamePanel, rollDice);
        }

        return null;
    }

    @Override
    public ReceiveGameBehaviour createAcceptOfferBehaviour(
            AcceptTradeOffer acceptTradeOffer)
    {
        return new DefaultOpponentReceivedBehaviour(gamePanel, acceptTradeOffer);
    }

    @Override
    public ReceiveGameBehaviour createCounterTradeOfferBehaviour(
            CounterTradeOffer counterTradeOffer)
    {
        return new DefaultOpponentReceivedBehaviour(gamePanel,
                counterTradeOffer);
    }

    @Override
    public ReceiveGameBehaviour createGameChatBehaviour(GameChat gameChat)
    {
        return new DefaultOpponentReceivedBehaviour(gamePanel, gameChat);
    }

    @Override
    public ReceiveGameBehaviour createGamePhaseHasEndedBehaviour(
            GamePhaseHasEnded gamePhaseHasEnded)
    {
        return new DefaultOpponentReceivedBehaviour(gamePanel,
                gamePhaseHasEnded);
    }

    @Override
    public ReceiveGameBehaviour createLooseCardsBehaviour(LooseCards looseCards)
    {
        return new DefaultOpponentReceivedBehaviour(gamePanel, looseCards);
    }

    @Override
    public ReceiveGameBehaviour createPlayDevelopmentCardBehaviour(
            BuyDevelopmentCard buyDevelopmentCard)
    {
        return new DefaultOpponentReceivedBehaviour(gamePanel,
                buyDevelopmentCard);
    }

    @Override
    public ReceiveGameBehaviour createQueuedTradeResponseBehaviour(
            QueuedTradeResponse queuedTradeResponse)
    {
        return new DefaultOpponentReceivedBehaviour(gamePanel,
                queuedTradeResponse);
    }

    @Override
    public ReceiveGameBehaviour createRejectOfferBehaviour(
            RejectTradeOffer rejectTradeOffer)
    {
        return new DefaultOpponentReceivedBehaviour(gamePanel, rejectTradeOffer);
    }

    @Override
    public ReceiveGameBehaviour createRolledSameBehaviour(RolledSame rolledSame)
    {
        return new DefaultOpponentReceivedBehaviour(gamePanel, rolledSame);
    }

    @Override
    public ReceiveGameBehaviour createStartingPlayerDeterminedBehaviour(
            StartingPlayerDetermined startingPlayerDetermined)
    {
        return new DefaultOpponentReceivedBehaviour(gamePanel,
                startingPlayerDetermined);
    }

    @Override
    public ReceiveGameBehaviour createTradeBankBehaviour(TradeBank tradeBank)
    {
        return new DefaultOpponentReceivedBehaviour(gamePanel, tradeBank);
    }

    @Override
    public ReceiveGameBehaviour createTradeOfferBehaviour(TradeOffer tradeOffer)
    {
        return new DefaultOpponentReceivedBehaviour(gamePanel, tradeOffer);
    }

    @Override
    public ReceiveGameBehaviour createTradePlayerBehaviour(
            TradePlayer tradePlayer)
    {
        return new DefaultOpponentReceivedBehaviour(gamePanel, tradePlayer);
    }

    @Override
    public ReceiveGameBehaviour createTurnPhaseEndedBehaviour(
            TurnPhaseEnded turnPhaseEnded)
    {
        return new DefaultOpponentReceivedBehaviour(gamePanel, turnPhaseEnded);
    }
}
