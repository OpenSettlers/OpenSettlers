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
import org.soc.common.views.behaviour.gameWidget.beforeSend.BuildCityGameBehaviour;
import org.soc.common.views.behaviour.gameWidget.beforeSend.BuildRoadGameBehaviour;
import org.soc.common.views.behaviour.gameWidget.beforeSend.BuildTownGameBehaviour;
import org.soc.common.views.behaviour.gameWidget.beforeSend.BuyDevelopmentCardGameBehaviour;
import org.soc.common.views.widgetsInterface.main.GameWidget;

public class SendGameBehaviourFactory implements GameBehaviourFactory
{
    private GameWidget gameWidget;

    public SendGameBehaviourFactory(GameWidget gameWidget)
    {
        this.gameWidget = gameWidget;
    }

    @Override
    public GameBehaviour createBuildCityBehaviour(BuildCity buildCity)
    {
        return new BuildCityGameBehaviour(gameWidget, buildCity);
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
        return new BuyDevelopmentCardGameBehaviour(gameWidget,
                buyDevelopmentCard);
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
        // TODO Auto-generated method stub
        return null;
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
        // TODO Auto-generated method stub
        return null;
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
