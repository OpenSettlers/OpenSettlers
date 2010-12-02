package soc.common.board;

import java.util.ArrayList;

import soc.common.annotations.SeaFarers;

@SeaFarers
public class TerritoryList extends ArrayList<Territory>
{

    public Territory findByID(int id)
    {
        for (Territory t : this)
        {
            if (t.getID() == id)
                return t;
        }
        
        throw new RuntimeException();
    }
   
    public TerritoryList addNew(Territory t)
    {
        this.add(t);
        
        return this;
    }
    
    private boolean containsMainland()
    {
        for (Territory territory : this)
        {
            if (territory.isMainland())
                return true;
        }
        return false;
    }
    
    public Territory createTerritory(boolean mainland)
    {
        Territory result = new Territory();
    
        if (mainland)
        {
            result.setID(0);
        }
        else
        {
            int id = this.size();
            
            if (!containsMainland())
            {
                id--;
            }
            result.setID(id);
        }
                
        return result;
    }
}
