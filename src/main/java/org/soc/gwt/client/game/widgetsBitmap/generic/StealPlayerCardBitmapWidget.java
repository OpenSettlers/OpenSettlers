package org.soc.gwt.client.game.widgetsBitmap.generic;

import org.soc.common.game.GamePlayer;
import org.soc.common.views.widgetsInterface.dialogs.StealCardWidget;
import org.soc.common.views.widgetsInterface.main.StealPlayerCardWidget;
import org.soc.gwt.client.images.R;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;

public class StealPlayerCardBitmapWidget extends HorizontalPanel implements
                StealPlayerCardWidget
{
    private GamePlayer player;
    private Label lblPlayerName = new Label("playername");
    private HorizontalPanel cardsPanel = new HorizontalPanel();
    private StealCardWidget parent;

    /** @wbp.parser.constructor */
    public StealPlayerCardBitmapWidget(StealCardWidget parent)
    {
        this.parent = parent;
        setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        setWidth("400px");

        Image image = new Image(R.icons().user());
        add(image);

        add(lblPlayerName);

        cardsPanel.setHeight("58px");
        cardsPanel.setSpacing(5);
        add(cardsPanel);
    }

    public StealPlayerCardBitmapWidget(final GamePlayer player,
                    final StealCardWidget parent)
    {
        this(parent);
        this.player = player;

        fillWithDummyCards();
    }

    @Override
    public void update(GamePlayer player)
    {
        fillWithDummyCards();
    }

    private void fillWithDummyCards()
    {
        cardsPanel.clear();

        lblPlayerName.setText(player.user().name());
        for (int i = 0; i < player.resources().size(); i++)
        {
            PushButton btn = new PushButton(new Image(R.icons()
                            .blankCard16()));
            btn.addClickHandler(new ClickHandler()
            {
                @Override
                public void onClick(ClickEvent arg0)
                {
                    parent.cardPicked(player);
                }
            });
            cardsPanel.add(btn);
        }
        if (player.resources().size() == 0)
        {
            cardsPanel.add(new Label(player.user().name()
                            + " has no cards"));
        }
    }

}
