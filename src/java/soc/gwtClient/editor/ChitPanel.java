package soc.gwtClient.editor;

import soc.common.board.Chit;
import soc.common.client.behaviour.editor.SetChitBehaviour;
import soc.gwtClient.game.widgets.bitmap.ImageLibrary;

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
    private final Chit chit2 = new Chit(2);
    private final Chit chit3 = new Chit(3);
    private final Chit chit4 = new Chit(4);
    private final Chit chit5 = new Chit(5);
    private final Chit chit6 = new Chit(6);
    private final Chit chit8 = new Chit(8);
    private final Chit chit9 = new Chit(9);
    private final Chit chit10= new Chit(10);
    private final Chit chit11= new Chit(11);
    private final Chit chit12= new Chit(12);
    private final Chit chitRandom = new Chit(0);

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
        
        PushButton btnChit2 = new PushButton(new Image(ImageLibrary.getIcon(chit2, 32)));
        btnChit2.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setCurrentChit(chit2);
                vuur();
            }
        });
        this.add(btnChit2);        
        PushButton btnChit3 = new PushButton(new Image(ImageLibrary.getIcon(chit3, 32)));
        btnChit3.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setCurrentChit(chit3);
                vuur();
            }
        });
        this.add(btnChit3);
        PushButton btnChit4 = new PushButton(new Image(ImageLibrary.getIcon(chit4, 32)));
        btnChit4.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setCurrentChit(chit4);
                vuur();
            }
        });
        this.add(btnChit4);
        PushButton btnChit5 = new PushButton(new Image(ImageLibrary.getIcon(chit5, 32)));
        btnChit5.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setCurrentChit(chit5);
                vuur();
            }
        });
        this.add(btnChit5);
        PushButton btnChit6 = new PushButton(new Image(ImageLibrary.getIcon(chit6, 32)));
        btnChit6.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setCurrentChit(chit6);
                vuur();
            }
        });
        this.add(btnChit6);
        PushButton btnChit8 = new PushButton(new Image(ImageLibrary.getIcon(chit8, 32)));
        btnChit8.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setCurrentChit(chit8);
                vuur();
            }
        });
        this.add(btnChit8);
        PushButton btnChit9 = new PushButton(new Image(ImageLibrary.getIcon(chit9, 32)));
        btnChit9.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setCurrentChit(chit9);
                vuur();
            }
        });
        this.add(btnChit9);
        PushButton btnChit10= new PushButton(new Image(ImageLibrary.getIcon(chit10, 32)));
        btnChit10.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setCurrentChit(chit10);
                vuur();
            }
        });
        this.add(btnChit10);
        
        PushButton btnChit11 = new PushButton(new Image(ImageLibrary.getIcon(chit11, 32)));
        btnChit11.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setCurrentChit(chit11);
                vuur();
            }
        });
        this.add(btnChit11);
        
        PushButton btnChit12 = new PushButton(new Image(ImageLibrary.getIcon(chit12, 32)));
        btnChit12.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setCurrentChit(chit12);
                vuur();
            }
        });
        this.add(btnChit12);   
        
        PushButton btnChitRandom = new PushButton(new Image(ImageLibrary.getIcon(chit2, 32)));
        btnChitRandom.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editBehaviour.setCurrentChit(new Chit(0));
                vuur();
            }
        });
        this.add(btnChitRandom);     
        
        PushButton btnChitNull = new PushButton(new Image(ImageLibrary.getIcon(chitRandom, 32)));
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
