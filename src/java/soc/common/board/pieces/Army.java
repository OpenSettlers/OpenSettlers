package soc.common.board.pieces;

import java.util.ArrayList;
import java.util.List;

import soc.common.game.VictoryPointItem;
import soc.common.game.developmentCards.standard.Soldier;
import soc.common.game.player.GamePlayer;

import com.google.gwt.event.shared.SimpleEventBus;

public class Army extends AbstractPlayerPiece implements VictoryPointItem
{
    private static final long serialVersionUID = 8037446795922927966L;
    public static Army LARGESTARMY = new Army();
    private List<Soldier> soldiers = new ArrayList<Soldier>();
    private boolean isLargest = false;
    private SimpleEventBus eventBus = new SimpleEventBus();

    @Override
    public int getVictoryPoints()
    {
        return 2;
    }

    public int getSoldiersAmount()
    {
        return soldiers.size();
    }

    public void addSoldier(Soldier soldier)
    {
        soldiers.add(soldier);
    }

    @Override
    public boolean isStockPiece()
    {
        return false;
    }

    public Army setLargest(boolean isLargest)
    {
        this.isLargest = isLargest;
        return this;
    }

    public boolean isLargest()
    {
        return isLargest;
    }

    @Override
    public void addToPlayer(GamePlayer player)
    {
        player.getVictoryPoints().add(this);
        setLargest(true);
    }

    @Override
    public void removeFromPlayer(GamePlayer player)
    {
        player.getVictoryPoints().remove(this);
        setLargest(false);
    }

}
