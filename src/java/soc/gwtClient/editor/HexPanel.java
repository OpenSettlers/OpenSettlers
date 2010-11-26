package soc.gwtClient.editor;

import soc.common.board.hexes.DesertHex;
import soc.common.board.hexes.DiscoveryHex;
import soc.common.board.hexes.NoneHex;
import soc.common.board.hexes.RandomHex;
import soc.common.board.hexes.ResourceHex;
import soc.common.board.hexes.SeaHex;
import soc.common.board.hexes.VolcanoHex;
import soc.common.board.resources.Clay;
import soc.common.board.resources.Diamond;
import soc.common.board.resources.Gold;
import soc.common.board.resources.Ore;
import soc.common.board.resources.Sheep;
import soc.common.board.resources.Timber;
import soc.common.board.resources.Wheat;
import soc.common.client.behaviour.design.SetHexBehaviour;
import soc.gwtClient.client.editBehaviour.BehaviourChanged;
import soc.gwtClient.client.editBehaviour.IBehaviourChangedHandler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.*;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;

public class HexPanel extends HorizontalPanel implements HasHandlers
{
    private SetHexBehaviour editBehaviour;
    
    @SuppressWarnings("deprecation")
    private HandlerManager handlerManager = new HandlerManager(this);

    @SuppressWarnings("deprecation")
    @Override
    public void fireEvent(GwtEvent<?> event) {
        handlerManager.fireEvent(event);
    }

    @SuppressWarnings("deprecation")
    public HandlerRegistration addBehaviourChangedEventHandler(
            IBehaviourChangedHandler handler) {
        return handlerManager.addHandler(BehaviourChanged.TYPE, handler);
    }
    
    public void addActivatedHandler(Event event)
    {
        
    }
    private void vuur()
    {
        fireEvent(new BehaviourChanged(editBehaviour));
    }
    public HexPanel(SetHexBehaviour behaviour)
    {
        super();
        
        editBehaviour=behaviour;
        
        PushButton btnTimber = new PushButton(new Image("icons/32/timber.png"));
        btnTimber.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setHex(new ResourceHex(new Timber()));
                vuur();
            }
        });
        this.add(btnTimber);
        
        PushButton btnWheat = new PushButton(new Image("icons/32/wheat.png"));
        btnWheat.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setHex(new ResourceHex(new Wheat()));
                vuur();
            }
        });
        this.add(btnWheat);
        
        PushButton btnOre = new PushButton(new Image("icons/32/ore.png"));
        btnOre.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setHex(new ResourceHex(new Ore()));
                vuur();
            }
        });
        this.add(btnOre);
        
        PushButton btnClay = new PushButton(new Image("icons/32/clay.png"));
        btnClay.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setHex(new ResourceHex(new Clay()));
                vuur();
            }
        });
        this.add(btnClay);
        
        PushButton btnSheep = new PushButton(new Image("icons/32/sheep.png"));
        btnSheep.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setHex(new ResourceHex(new Sheep()));
                vuur();
            }
        });
        this.add(btnSheep);
        
        PushButton btnGold = new PushButton(new Image("icons/32/gold.png"));
        btnGold.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setHex(new ResourceHex(new Gold()));
                vuur();
            }
        });
        this.add(btnGold);
        
        PushButton btnJungle = new PushButton(new Image("icons/32/jungle.png"));
        btnJungle.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setHex(new ResourceHex(new Diamond()));
                vuur();
            }
        });
        this.add(btnJungle);
        
        PushButton btnVolcano = new PushButton(new Image("icons/32/volcano.png"));
        btnVolcano.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setHex(new VolcanoHex());
                vuur();
            }
        });
        this.add(btnVolcano);
        
        PushButton btnDesert = new PushButton(new Image("icons/32/desert.png"));
        btnDesert.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setHex(new DesertHex());
                vuur();
            }
        });
        this.add(btnDesert);
        
        PushButton btnSea = new PushButton(new Image("icons/32/sea.png"));
        btnSea.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setHex(new SeaHex());
                vuur();
            }
        });        
        this.add(btnSea);   

        PushButton btnNone = new PushButton(new Image("icons/32/none.png"));
        btnNone.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setHex(new NoneHex());
                vuur();
            }
        });
        this.add(btnNone);
        
        PushButton btnRandom = new PushButton(new Image("icons/32/random.png"));
        btnRandom.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setHex(new RandomHex());
                vuur();
            }
        });        
        this.add(btnRandom);
        
        PushButton btnDiscovery = new PushButton(new Image("icons/32/unknown.png"));
        btnDiscovery.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setHex(new DiscoveryHex());
                vuur();
            }
        });
        this.add(btnDiscovery);
    }


}
