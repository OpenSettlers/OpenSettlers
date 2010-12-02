package soc.gwtClient.editor;

import soc.common.board.Chit;
import soc.common.client.behaviour.editor.SetChitBehaviour;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.*;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;


public class ChitPanel extends VerticalPanel implements HasHandlers
{
    private SetChitBehaviour editBehaviour;
    
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
    private void vuur()
    {
        fireEvent(new BehaviourChanged(editBehaviour));
    }
    public ChitPanel(SetChitBehaviour behaviour)
    {
        super();
        
        editBehaviour=behaviour;
        
        PushButton btnChit2 = new PushButton(new Image("icons/32/chit2.png"));
        btnChit2.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setCurrentChit(new Chit(2));
                vuur();
            }
        });
        this.add(btnChit2);        
        PushButton btnChit3 = new PushButton(new Image("icons/32/chit3.png"));
        btnChit3.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setCurrentChit(new Chit(3));
                vuur();
            }
        });
        this.add(btnChit3);
        PushButton btnChit4 = new PushButton(new Image("icons/32/chit4.png"));
        btnChit4.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setCurrentChit(new Chit(4));
                vuur();
            }
        });
        this.add(btnChit4);
        PushButton btnChit5 = new PushButton(new Image("icons/32/chit5.png"));
        btnChit5.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setCurrentChit(new Chit(5));
                vuur();
            }
        });
        this.add(btnChit5);
        PushButton btnChit6 = new PushButton(new Image("icons/32/chit6.png"));
        btnChit6.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setCurrentChit(new Chit(6));
                vuur();
            }
        });
        this.add(btnChit6);
        PushButton btnChit8 = new PushButton(new Image("icons/32/chit8.png"));
        btnChit8.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setCurrentChit(new Chit(8));
                vuur();
            }
        });
        this.add(btnChit8);
        PushButton btnChit9 = new PushButton(new Image("icons/32/chit9.png"));
        btnChit9.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setCurrentChit(new Chit(9));
                vuur();
            }
        });
        this.add(btnChit9);
        PushButton btnChit10= new PushButton(new Image("icons/32/chit10.png"));
        btnChit10.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setCurrentChit(new Chit(10));
                vuur();
            }
        });
        this.add(btnChit10);
        
        PushButton btnChit11 = new PushButton(new Image("icons/32/chit11.png"));
        btnChit11.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setCurrentChit(new Chit(11));
                vuur();
            }
        });
        this.add(btnChit11);
        
        PushButton btnChit12 = new PushButton(new Image("icons/32/chit12.png"));
        btnChit12.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setCurrentChit(new Chit(12));
                vuur();
            }
        });
        this.add(btnChit12);   
        
        PushButton btnChitRandom = new PushButton(new Image("icons/32/chitrandom.png"));
        btnChitRandom.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setCurrentChit(new Chit(0));
                vuur();
            }
        });
        this.add(btnChitRandom);     
        
        PushButton btnChitNull = new PushButton(new Image("icons/32/chit.png"));
        btnChitNull.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setCurrentChit(null);
                vuur();
            }
        });
        this.add(btnChitNull);
    }
}
