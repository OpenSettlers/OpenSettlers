package soc.common.game;

import java.util.List;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.board.resources.Resource;
import soc.common.game.dices.IDice;

/*
 * Abstracted properties for game {@see IRuleSet} to modify
 */
public interface IGameRules
{
    /*
     * Creates an action for what to do when a 7 is rolled or a 
     * soldier development card is played.
     * Standard will return PlaceRobber, where SeaFarers will return a
     * PlaceRobberPirate
     */
    public TurnAction createPlaceRobberPirateAction();
    
    /*
     * Returns a list of piece types allowed in stock along with amount
     * of items maximum in stock for any player
     */
    public List<StockItem> getStockPieces();
    
    /*
     * Returns a list of TurnAction types allowed to be played during a turn
     */
    public List<TurnAction> getPossibleActions();
    
    public List<Resource> getPlayableResources();
    
    /*
     * Returns amount of resources in the bank at beginning of the game
     */
    public int getBankAmountPerResource();

    public boolean getEnableLargestArmy();
    public GameRules setEnableLargestArmy(boolean enableLargestArmy);

    // Returns the type of the dice rolled by the player on turn in the RollDiceTurnPhase
    public IDice getDiceType();
    
    // Sets the type of the dice 
    public GameRules setDiceType(IDice diceType);
}
