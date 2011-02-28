package soc.gwtClient.editor;

import soc.common.board.chits.Chit;
import soc.common.board.chits.Chit10;
import soc.common.board.chits.Chit11;
import soc.common.board.chits.Chit12;
import soc.common.board.chits.Chit2;
import soc.common.board.chits.Chit3;
import soc.common.board.chits.Chit4;
import soc.common.board.chits.Chit5;
import soc.common.board.chits.Chit6;
import soc.common.board.chits.Chit8;
import soc.common.board.chits.Chit9;
import soc.common.board.chits.RandomChit;
import soc.common.views.behaviour.board.SetChitBehaviour;
import soc.gwtClient.images.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ChitPanel extends HorizontalPanel implements HasHandlers
{
    private VerticalPanel panel1 = new VerticalPanel();
    private VerticalPanel panel2 = new VerticalPanel();
    private SetChitBehaviour editBehaviour;
    private final Chit chit2 = new Chit2();
    private final Chit chit3 = new Chit3();
    private final Chit chit4 = new Chit4();
    private final Chit chit5 = new Chit5();
    private final Chit chit6 = new Chit6();
    private final Chit chit8 = new Chit8();
    private final Chit chit9 = new Chit9();
    private final Chit chit10 = new Chit10();
    private final Chit chit11 = new Chit11();
    private final Chit chit12 = new Chit12();
    private final Chit chitRandom = new RandomChit();

    private SimpleEventBus eventBus = new SimpleEventBus();

    public HandlerRegistration addBehaviourChangedEventHandler(
            BehaviourChangedHandler handler)
    {
        return eventBus.addHandler(BehaviourChanged.TYPE, handler);
    }

    private void vuur()
    {
        eventBus.fireEvent(new BehaviourChanged(editBehaviour));
    }

    public ChitPanel(SetChitBehaviour behaviour)
    {
        super();

        editBehaviour = behaviour;
        this.add(panel1);
        this.add(panel2);

        PushButton btnChit2 = new PushButton(new Image(Resources.icons()
                .chit2()));
        btnChit2.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                editBehaviour.setCurrentChit(chit2);
                vuur();
            }
        });
        panel1.add(btnChit2);
        PushButton btnChit3 = new PushButton(new Image(Resources.icons()
                .chit3()));
        btnChit3.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                editBehaviour.setCurrentChit(chit3);
                vuur();
            }
        });
        panel1.add(btnChit3);
        PushButton btnChit4 = new PushButton(new Image(Resources.icons()
                .chit4()));
        btnChit4.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                editBehaviour.setCurrentChit(chit4);
                vuur();
            }
        });
        panel1.add(btnChit4);
        PushButton btnChit5 = new PushButton(new Image(Resources.icons()
                .chit5()));
        btnChit5.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                editBehaviour.setCurrentChit(chit5);
                vuur();
            }
        });
        panel1.add(btnChit5);
        PushButton btnChit6 = new PushButton(new Image(Resources.icons()
                .chit6()));
        btnChit6.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                editBehaviour.setCurrentChit(chit6);
                vuur();
            }
        });
        panel1.add(btnChit6);

        PushButton btnChit12 = new PushButton(new Image(Resources.icons()
                .chit12()));
        btnChit12.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                editBehaviour.setCurrentChit(chit12);
                vuur();
            }
        });
        panel2.add(btnChit12);

        PushButton btnChit11 = new PushButton(new Image(Resources.icons()
                .chit11()));
        btnChit11.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                editBehaviour.setCurrentChit(chit11);
                vuur();
            }
        });
        panel2.add(btnChit11);

        PushButton btnChit10 = new PushButton(new Image(Resources.icons()
                .chit10()));
        btnChit10.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                editBehaviour.setCurrentChit(chit10);
                vuur();
            }
        });
        panel2.add(btnChit10);

        PushButton btnChit9 = new PushButton(new Image(Resources.icons()
                .chit9()));
        btnChit9.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                editBehaviour.setCurrentChit(chit9);
                vuur();
            }
        });
        panel2.add(btnChit9);

        PushButton btnChit8 = new PushButton(new Image(Resources.icons()
                .chit8()));
        btnChit8.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                editBehaviour.setCurrentChit(chit8);
                vuur();
            }
        });
        panel2.add(btnChit8);

        PushButton btnChitRandom = new PushButton(new Image(Resources.icons()
                .randomChit()));
        btnChitRandom.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                editBehaviour.setCurrentChit(chitRandom);
                vuur();
            }
        });
        panel1.add(btnChitRandom);

        PushButton btnChitNull = new PushButton(new Image(Resources.icons()
                .blankChit()));
        btnChitNull.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                editBehaviour.setCurrentChit(null);
                vuur();
            }
        });
        panel2.add(btnChitNull);
    }

}
