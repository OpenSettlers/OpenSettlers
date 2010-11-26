package soc.gwtClient.editor;

import soc.common.board.Board;
import soc.common.board.Territory;
import soc.common.client.behaviour.design.SetTerritoryBehaviour;
import soc.gwtClient.client.editBehaviour.BehaviourChanged;
import soc.gwtClient.client.editBehaviour.IBehaviourChangedHandler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;

@SuppressWarnings("deprecation")
public class TerritoryPanel extends HorizontalPanel
{
    SetTerritoryBehaviour behaviour;
    Board board;
    
    private HandlerManager handlerManager = new HandlerManager(this);

    @Override
    public void fireEvent(GwtEvent<?> event) {
        handlerManager.fireEvent(event);
    }

    public HandlerRegistration addBehaviourChangedEventHandler(
            IBehaviourChangedHandler handler) {
        return handlerManager.addHandler(BehaviourChanged.TYPE, handler);
    }
    
    
    public TerritoryPanel(SetTerritoryBehaviour b, Board board)
    {
        super();
        
        behaviour=b;
        this.board=board;
        
        PushButton btnShowTerritories = new PushButton(new Image("icons/32/timber.png"));
        btnShowTerritories.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // Show all territory visuals on TerritoryHexes
            }
        });
        this.add(btnShowTerritories);
        
        PushButton btnHideTerritories = new PushButton(new Image("icons/32/timber.png"));
        btnHideTerritories.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // Show all territory visuals on TerritoryHexes
            }
        });
        this.add(btnHideTerritories);
        
        for (final Territory t : board.getTerritories())
        {
            String icon = ""; 
        
            if (t.isMainland())
            {
                icon="icons/32/mainland.png";
            }
            else
            {
                icon="icons/32/island" + t.getID() + ".png";
            }
            
            PushButton btnTerritory = new PushButton(new Image(icon));
            btnTerritory.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    behaviour.setTerritory(t);
                    fireEvent(new BehaviourChanged(behaviour));
                }
            });
            this.add(btnTerritory);
        }
        
    }
}
