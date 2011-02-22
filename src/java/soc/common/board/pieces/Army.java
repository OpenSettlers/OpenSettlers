package soc.common.board.pieces;

import java.util.ArrayList;
import java.util.List;

import soc.common.board.pieces.abstractPieces.AbstractPlayerPiece;
import soc.common.game.VictoryPointItem;
import soc.common.game.developmentCards.standard.Soldier;
import soc.common.game.player.GamePlayer;
import soc.common.ui.Graphics;
import soc.common.ui.Icon;
import soc.common.ui.IconImpl;
import soc.common.ui.meta.Meta;
import soc.gwtClient.game.widgetsInterface.generic.ToolTip;
import soc.gwtClient.game.widgetsInterface.visuals.PieceVisual;
import soc.gwtClient.game.widgetsInterface.visuals.VisualFactory;
import soc.gwtClient.images.Resources;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

public class Army extends AbstractPlayerPiece implements VictoryPointItem
{
    private static final long serialVersionUID = 8037446795922927966L;
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().soldier(), null,
                null, null);

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public Graphics graphics()
        {
            return null;
        }

        @Override
        public String getName()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getLocalizedName()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getDescription()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public ToolTip createToolTip()
        {
            // TODO Auto-generated method stub
            return null;
        }
    };
    public static Army LARGESTARMY = new Army();
    private List<Soldier> soldiers = new ArrayList<Soldier>();
    private boolean isLargest = false;
    private transient SimpleEventBus eventBus = new SimpleEventBus();

    /**
     * @return the soldiers
     */
    public List<Soldier> getSoldiers()
    {
        return soldiers;
    }

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
        boolean wasLargest = isLargest;
        int oldSoldierAmount = getSoldiersAmount();
        soldiers.add(soldier);
        ArmyChangedEvent event = new ArmyChangedEvent(getSoldiersAmount(),
                isLargest, wasLargest, oldSoldierAmount, null, soldier);
        eventBus.fireEvent(event);
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

    @Override
    public boolean affectsRoad()
    {
        return false;
    }

    public HandlerRegistration addSoldiersChangedEventHandler(
            ArmyChangedEventHandler handler)
    {
        return eventBus.addHandler(ArmyChangedEvent.TYPE, handler);
    }

    @Override
    public PieceVisual createPiece(VisualFactory visualFactory)
    {
        return null;
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

}
