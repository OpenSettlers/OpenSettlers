package soc.common.game.gamePhase.turnPhase;

import soc.common.actions.gameAction.*;
import soc.common.actions.gameAction.turnActions.RollDice;
import soc.common.game.Game;

public class RollDiceTurnPhase extends TurnPhase
{

    /* (non-Javadoc)
     * @see soc.common.game.gamePhase.turnPhase.TurnPhase#next()
     */
    @Override
    public TurnPhase next()
    {
        // TODO Auto-generated method stub
        return super.next();
    }
    
    /*
    * @see soc.common.game.gamePhase.turnPhase.TurnPhase#isAllowed(soc.common.actions.gameAction.GameAction)
    */
    @Override
    public boolean isAllowed(GameAction action)
    {
        // TODO Auto-generated method stub
        return super.isAllowed(action);
    }
    
    /* (non-Javadoc)
     * @see soc.common.game.gamePhase.turnPhase.TurnPhase#processAction(soc.common.actions.gameAction.GameAction, soc.common.game.Game)
     */
    @Override
    public TurnPhase processAction(GameAction action, Game game)
    {
        if (action.isAllowed(this))
        {
            if (action instanceof RollDice)
            {
                RollDice rollDice = (RollDice)action;
                
                rollDice.perform(game);

                // When a 7 is rolled, enqueue every player required to loose cards to do so
                if (rollDice.getDice().getDice() == 7)
                {
                    // Add each player required to loose cards to the queue
                    for (int i : rollDice.getLooserPlayers())
                    {
                        game.getActionsQueue().enqueue
                        (
                                null
                                // TODO: port to java
                                //new LooseCardsAction()
                                //    .setPlayer(game.getPlayer(i));
                        );
                    }

                    // Expect player to place robber/pirate and rob a player
                    /* TODO: port to java
                    game.ActionsQueue.Enqueue(new PlaceRobberPirateAction() { GamePlayer = action.GamePlayer });
                    game.ActionsQueue.Enqueue(new RobPlayerAction() { GamePlayer = action.GamePlayer });
                    */
                    // We have actions to be done, we should stay in this phase
                    return this;
                }

                // Any other number has been rolled. 
                // Proceed to trading phase
                if (game.getActionsQueue().size()== 0)
                {
                    return new TradingTurnPhase();
                }
                else
                {
                    return this;
                }
            }

            /* TODO: port to java
            RobPlayerAction robPlayer = action as RobPlayerAction;
            if (robPlayer != null)
            {
                robPlayer.PerformTurnAction(game);

                // When finished robbing, advance phase to trading
                return this;
            }
            // perform the action
            action.PerformTurnAction(game);
            EndTurnAction endTurn = action as EndTurnAction;
            if (endTurn != null)
            {
                return new BuildTurnPhase(null);
            }
            */

            // Return current state
            return this;
        }
        else
        // Action is not allowed in rollDice phase. Check if it is allowed in subsequent phases, 
        // then return that phase
        {
            TradingTurnPhase trading = new TradingTurnPhase();
            BuildingTurnPhase building = new BuildingTurnPhase(trading);
            if (action.isAllowed(trading))
            {
                return trading;
            }
            if (action.isAllowed(building))
            {
                return building;
            }
            return null;
        }
    }

}
