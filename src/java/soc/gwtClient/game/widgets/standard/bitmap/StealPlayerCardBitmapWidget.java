package soc.gwtClient.game.widgets.standard.bitmap;

import soc.common.game.Player;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HasVerticalAlignment;

public class StealPlayerCardBitmapWidget extends HorizontalPanel
{
    private Player player;
    Label lblPlayerName = new Label("playername");
    HorizontalPanel cardsPanel = new HorizontalPanel();
    
    /**
     * @wbp.parser.constructor
     */
    public StealPlayerCardBitmapWidget()
    {
        setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        setWidth("400px");
        
        Image image = new Image("icons/48/User48.png");
        add(image);
        
        add(lblPlayerName);
        
        cardsPanel.setHeight("58px");
        cardsPanel.setSpacing(5);
        add(cardsPanel);
    }

    public StealPlayerCardBitmapWidget(Player player)
    {
        this();
        this.player = player;
        
        lblPlayerName.setText(player.getName());
        for (int i=0; i< player.getResourcesCount(); i++)
        {
            PushButton btn = new PushButton(new Image("icons/48/BlankCard48.png"));
            btn.addClickHandler(new ClickHandler()
            {
                @Override
                public void onClick(ClickEvent arg0)
                {
                    // TODO: add logic 
                }
            });
            cardsPanel.add(btn);
        }
        if (player.getResourcesCount() == 0)
        {
            cardsPanel.add(new Label(player.getName() + " has no cards"));
        }
    }
    
}
