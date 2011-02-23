package soc.gwtClient.game.behaviour.gameWidget.factories;

import soc.common.actions.gameAction.meta.GameChat;
import soc.common.actions.gameAction.meta.MessageFromServer;
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
import soc.common.actions.gameAction.trading.RejectTradeOffer;
import soc.common.actions.gameAction.trading.TradeBank;
import soc.common.actions.gameAction.trading.TradeOffer;
import soc.common.actions.gameAction.trading.TradePlayer;
import soc.common.actions.gameAction.turns.EndTurn;
import soc.common.actions.gameAction.turns.GamePhaseHasEnded;
import soc.common.actions.gameAction.turns.HostStartsGame;
import soc.common.actions.gameAction.turns.QueuedTradeResponse;
import soc.common.actions.gameAction.turns.RolledSame;
import soc.common.actions.gameAction.turns.StartingPlayerDetermined;
import soc.common.actions.gameAction.turns.TurnPhaseEnded;
import soc.gwtClient.game.behaviour.gameWidget.GameBehaviour;
import soc.gwtClient.game.behaviour.gameWidget.beforeSend.BuildRoadGameBehaviour;
import soc.gwtClient.game.behaviour.gameWidget.beforeSend.BuildTownGameBehaviour;
import soc.gwtClient.game.behaviour.gameWidget.beforeSend.MoveRobberGameBehaviour;
import soc.gwtClient.game.behaviour.gameWidget.beforeSend.RobPlayerGameBehaviour;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

public class NextActionGameBehaviourFactory implements GameBehaviourFactory
{
    private GameWidget gameWidget;

    public NextActionGameBehaviourFactory(GameWidget gameWidget)
    {
        super();
        this.gameWidget = gameWidget;
    }

    @Override
    public GameBehaviour createBuildCityBehaviour(BuildCity buildCity)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameBehaviour createBuildRoadBehaviour(BuildRoad buildRoad)
    {
        return new BuildRoadGameBehaviour(gameWidget, buildRoad);
    }

    @Override
    public GameBehaviour createBuildTownBehaviour(BuildTown buildTown)
    {
        return new BuildTownGameBehaviour(gameWidget, buildTown);
    }

    @Override
    public GameBehaviour createBuyDevelopmentcardBehaviour(
            BuyDevelopmentCard buyDevelopmentCard)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameBehaviour createClaimVictoryBehaviour(ClaimVictory claimVictory)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameBehaviour createEndTurnBehaviour(EndTurn endTurn)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameBehaviour createHostStartsBehaviour(HostStartsGame hostStartsGame)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameBehaviour createMessageFromServerBehaviour(
            MessageFromServer messageFromServer)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameBehaviour createMoveRobberBehaviour(PlaceRobber moveRobber)
    {
        return new MoveRobberGameBehaviour(gameWidget, moveRobber);
    }

    @Override
    public GameBehaviour createPlayDevelopmentCardBehaviour(
            PlayDevelopmentCard playDevelopmentCard)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameBehaviour createRobPlayerBehaviour(RobPlayer robPlayer)
    {
        return new RobPlayerGameBehaviour(gameWidget);
    }

    @Override
    public GameBehaviour createRollDiceBehaviour(RollDice rollDice)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameBehaviour createCounterTradeOfferBehaviour(
            CounterTradeOffer counterTradeOffer)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameBehaviour createGameChatBehaviour(GameChat gameChat)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameBehaviour createGamePhaseHasEndedBehaviour(
            GamePhaseHasEnded gamePhaseHasEnded)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameBehaviour createLooseCardsBehaviour(LooseCards looseCards)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameBehaviour createQueuedTradeResponseBehaviour(
            QueuedTradeResponse queuedTradeResponse)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameBehaviour createRejectOfferBehaviour(
            RejectTradeOffer rejectTradeOffer)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameBehaviour createRolledSameBehaviour(RolledSame rolledSame)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameBehaviour createStartingPlayerDeterminedBehaviour(
            StartingPlayerDetermined startingPlayerDetermined)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameBehaviour createTradeBankBehaviour(TradeBank tradeBank)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameBehaviour createTradeOfferBehaviour(TradeOffer tradeOffer)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameBehaviour createTradePlayerBehaviour(TradePlayer tradePlayer)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameBehaviour createTurnPhaseEndedBehaviour(
            TurnPhaseEnded turnPhaseEnded)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
