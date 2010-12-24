package soc.gwtClient.editor;

import soc.common.board.ports.Port;
import soc.common.board.ports.RandomPort;
import soc.common.board.ports.ThreeToOnePort;
import soc.common.board.ports.TwoToOneResourcePort;
import soc.common.board.resources.Clay;
import soc.common.board.resources.Ore;
import soc.common.board.resources.Sheep;
import soc.common.board.resources.Timber;
import soc.common.board.resources.Wheat;
import soc.common.client.behaviour.SetPortBehaviour;
import soc.gwtClient.game.widgets.bitmap.ImageLibrary;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;

@SuppressWarnings("deprecation")
public class PortPanel extends VerticalPanel
{
    private SetPortBehaviour behaviour;
    private Port threeToOnePort = new ThreeToOnePort();
    private Port wheatPort = new TwoToOneResourcePort(new Wheat());
    private Port timberPort = new TwoToOneResourcePort(new Timber());
    private Port orePort = new TwoToOneResourcePort(new Ore());
    private Port sheepPort = new TwoToOneResourcePort(new Sheep());
    private Port clayPort = new TwoToOneResourcePort(new Clay());
    private Port randomPort = new RandomPort();
    
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
        
        PushButton btn31Port = new PushButton(new Image(ImageLibrary.getIcon(threeToOnePort, 32)));
        btn31Port.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                behaviour.setPort(threeToOnePort);
                vuur();
            }
        });
        this.add(btn31Port);
        
        PushButton btnTimberPort = new PushButton(new Image(ImageLibrary.getIcon(timberPort, 32)));
        btnTimberPort.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                behaviour.setPort(timberPort);
                vuur();
            }
        });
        this.add(btnTimberPort);
        
        PushButton btnWheatPort = new PushButton(new Image(ImageLibrary.getIcon(wheatPort, 32)));
        btnWheatPort.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                behaviour.setPort(wheatPort);
                vuur();
            }
        });
        this.add(btnWheatPort);
        
        PushButton btnOrePort = new PushButton(new Image(ImageLibrary.getIcon(orePort, 32)));
        btnOrePort.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                behaviour.setPort(orePort);
                vuur();
            }
        });
        this.add(btnOrePort);
        
        PushButton btnClayPort = new PushButton(new Image(ImageLibrary.getIcon(clayPort, 32)));
        btnClayPort.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                behaviour.setPort(clayPort);
                vuur();
            }
        });
        this.add(btnClayPort);
        
        PushButton btnSheepPort = new PushButton(new Image(ImageLibrary.getIcon(sheepPort, 32)));
        btnSheepPort.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                behaviour.setPort(sheepPort);
                vuur();
            }
        });
        this.add(btnSheepPort);   
        
        PushButton btnRandomPort = new PushButton(new Image(ImageLibrary.getIcon(randomPort, 32)));
        btnRandomPort.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                behaviour.setPort(randomPort);
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
