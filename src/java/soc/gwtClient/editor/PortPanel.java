package soc.gwtClient.editor;

import soc.common.board.ports.RandomPort;
import soc.common.board.ports.ThreeToOnePort;
import soc.common.board.ports.TwoToOneResourcePort;
import soc.common.board.resources.Clay;
import soc.common.board.resources.Ore;
import soc.common.board.resources.Sheep;
import soc.common.board.resources.Timber;
import soc.common.board.resources.Wheat;
import soc.common.client.behaviour.SetPortBehaviour;
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
public class PortPanel extends HorizontalPanel
{
    private SetPortBehaviour behaviour;
    
    private HandlerManager handlerManager = new HandlerManager(this);

    @Override
    public void fireEvent(GwtEvent<?> event) {
        handlerManager.fireEvent(event);
    }

    public HandlerRegistration addBehaviourChangedEventHandler(
            IBehaviourChangedHandler handler) {
        return handlerManager.addHandler(BehaviourChanged.TYPE, handler);
    }
    
    public PortPanel(SetPortBehaviour b)
    {
        super();
        
        behaviour=b;
        
        PushButton btn31Port = new PushButton(new Image("icons/32/31port.png"));
        btn31Port.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                behaviour.setPort(new ThreeToOnePort());
                vuur();
            }
        });
        this.add(btn31Port);
        
        PushButton btnTimberPort = new PushButton(new Image("icons/32/timberport.png"));
        btnTimberPort.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                behaviour.setPort(new TwoToOneResourcePort(new Timber()));
                vuur();
            }
        });
        this.add(btnTimberPort);
        
        PushButton btnWheatPort = new PushButton(new Image("icons/32/wheatport.png"));
        btnWheatPort.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                behaviour.setPort(new TwoToOneResourcePort(new Wheat()));
                vuur();
            }
        });
        this.add(btnWheatPort);
        
        PushButton btnOrePort = new PushButton(new Image("icons/32/oreport.png"));
        btnOrePort.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                behaviour.setPort(new TwoToOneResourcePort(new Ore()));
                vuur();
            }
        });
        this.add(btnOrePort);
        
        PushButton btnClayPort = new PushButton(new Image("icons/32/31port.png"));
        btnClayPort.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                behaviour.setPort(new TwoToOneResourcePort(new Clay()));
                vuur();
            }
        });
        this.add(btnClayPort);
        
        PushButton btnSheepPort = new PushButton(new Image("icons/32/31port.png"));
        btnSheepPort.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                behaviour.setPort(new TwoToOneResourcePort(new Sheep()));
                vuur();
            }
        });
        this.add(btnSheepPort);   
        
        PushButton btnRandomPort = new PushButton(new Image("icons/32/randomport.png"));
        btnRandomPort.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                behaviour.setPort(new RandomPort());
                vuur();
            }
        });
        this.add(btnRandomPort);
    }
    private void vuur()
    {
        fireEvent(new BehaviourChanged(behaviour));
    }
}
