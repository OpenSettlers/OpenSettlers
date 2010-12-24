package soc.common.game.logs;

import java.util.List;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.actions.gameAction.turnActions.standard.RollDice;
import soc.common.game.Game;
import soc.common.game.Player;

/*
 * List of GameActions which occurred during a game
 */
public interface IGameLog extends Iterable<AbstractGameAction>
{
    /*
     * Adds an action to this gamelog
     */
    public void addAction(AbstractGameAction inGameAction);
    
    /*
     * Returns a list of RollDice actions performed during current 
     * dicerolling round
     */
    public List<RollDice> getCurrentRoundRolls(Game game);
    
    /*
     * Returns the beginning player which won the dice rolling round if 
     * this player exists, otherwise returns null 
     */
    public Player firstPlayerIsDetermined(Game game, int highRoll);
    
    /*
     * Adds an actionPerformed event listener to this gamelog
     */
    public void addActionPerformedEventHandler(ActionPerformedEventHandler handler);

}
