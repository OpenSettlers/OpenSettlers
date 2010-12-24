package soc.common.game;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.SimpleEventBus;

import soc.common.actions.gameAction.turnActions.AbstractTurnAction;
import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.board.resources.AbstractResource;
import soc.common.board.resources.Resource;
import soc.common.game.dices.IDice;
import soc.common.game.logs.ActionQueueChangedEvent;

public class GameRules implements IGameRules
{
    private List<TurnAction> possibleActions = new ArrayList<TurnAction>();
    private List<Resource> playableResources = new ArrayList<Resource>();
    private List<StockItem> stockPieces = new ArrayList<StockItem>();
    private int bankAmountPerResource = 19;
    private boolean enableLargestArmy;
    private boolean isSeaFarers = false;
    private boolean isSa3D = false;
    private boolean isCitiesKnights = false;
    private boolean isExtended = false;
    private boolean isPioneers = false;
    private boolean isTeamGame = false;
    
    // State of last rolled dice
    private IDice diceType;
    
    /**
     * @return the isSeaFarers
     */
    public boolean isSeaFarers()
    {
        return isSeaFarers;
    }

    /**
     * @param isSeaFarers the isSeaFarers to set
     */
    public GameRules setSeaFarers(boolean isSeaFarers)
    {
        this.isSeaFarers = isSeaFarers;
    
        return this;
    }

    /**
     * @return the isSa3D
     */
    public boolean isSa3D()
    {
        return isSa3D;
    }

    /**
     * @param isSa3D the isSa3D to set
     */
    public GameRules setSa3D(boolean isSa3D)
    {
        this.isSa3D = isSa3D;
    
        return this;
    }

    /**
     * @return the isCitiesKnights
     */
    public boolean isCitiesKnights()
    {
        return isCitiesKnights;
    }

    /**
     * @param isCitiesKnights the isCitiesKnights to set
     */
    public GameRules setCitiesKnights(boolean isCitiesKnights)
    {
        this.isCitiesKnights = isCitiesKnights;
    
        return this;
    }

    /**
     * @return the isExtended
     */
    public boolean isExtended()
    {
        return isExtended;
    }

    /**
     * @param isExtended the isExtended to set
     */
    public GameRules setExtended(boolean isExtended)
    {
        this.isExtended = isExtended;
    
        return this;
    }

    /**
     * @return the isPioneers
     */
    public boolean isPioneers()
    {
        return isPioneers;
    }

    /**
     * @param isPioneers the isPioneers to set
     */
    public GameRules setPioneers(boolean isPioneers)
    {
        this.isPioneers = isPioneers;
    
        return this;
    }

    /**
     * @return the isTeamGame
     */
    public boolean isTeamGame()
    {
        return isTeamGame;
    }

    /**
     * @param isTeamGame the isTeamGame to set
     */
    public GameRules setTeamGame(boolean isTeamGame)
    {
        this.isTeamGame = isTeamGame;
    
        return this;
    }

        
    @Override
    public AbstractTurnAction createPlaceRobberPirateAction()
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
    public List<Resource> getSupportedResources()
    {
        return playableResources;
    }
    
    public List<Resource> getTradeableResources()
    {
        List<Resource> tradeableResources = new ArrayList<Resource>();
        
        for (Resource resource: getSupportedResources())
        {
            if (resource.isTradeable())
                tradeableResources.add(resource);
        }
        
        return tradeableResources;
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

    /**
     * @return the enableLargestArmy
     */
    public boolean getEnableLargestArmy()
    {
        return enableLargestArmy;
    }
    /**
     * @param enableLargestArmy the enableLargestArmy to set
     */
    public GameRules setEnableLargestArmy(boolean enableLargestArmy)
    {
        this.enableLargestArmy = enableLargestArmy;
    
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }
    @Override
    public IDice getDiceType()
    {
        return diceType;
    }
    @Override
    public GameRules setDiceType(IDice diceType)
    {
        this.diceType=diceType;
        
        return this;
    }
}
