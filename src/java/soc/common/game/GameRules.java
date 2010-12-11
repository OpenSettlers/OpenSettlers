package soc.common.game;

import java.util.ArrayList;
import java.util.List;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.board.resources.Resource;

public class GameRules implements IGameRules
{
    private List<TurnAction> possibleActions = new ArrayList<TurnAction>();
    private List<Resource> playableResources = new ArrayList<Resource>();
    private List<StockItem> stockPieces = new ArrayList<StockItem>();
    private int bankAmountPerResource = 19;
    
    @Override
    public TurnAction createPlaceRobberPirateAction()
    {
        return null;
    }

    /**
     * @return the possibleActions
     */
    public List<TurnAction> getPossibleActions()
    {
        return possibleActions;
    }

    /**
     * @return the playableResources
     */
    public List<Resource> getPlayableResources()
    {
        return playableResources;
    }

    /**
     * @return the stockPieces
     */
    public List<StockItem> getStockPieces()
    {
        return stockPieces;
    }

    /**
     * @return the bankAmountPerResource
     */
    public int getBankAmountPerResource()
    {
        return bankAmountPerResource;
    }


}
