package org.soc.gwt.client.game.widgetsBitmap.developmentCards;

import org.soc.common.actions.gameAction.standard.PlayDevelopmentCard;
import org.soc.common.game.developmentCards.standard.VictoryPoint;
import org.soc.common.internationalization.I18n;
import org.soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidget;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.images.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class PlayVictoryPointWidget implements DevelopmentCardWidget
{
    private VictoryPoint victoryPoint;
    private GameWidget gameWidget;
    private PlayDevelopmentCard playDevelopmentCard = new PlayDevelopmentCard();
    private HorizontalPanel rootPanel = new HorizontalPanel();
    private Button btnPlay = new Button(I18n.get().constants().play());

    public PlayVictoryPointWidget(final GameWidget gameWidget,
                    VictoryPoint victoryPoint)
    {
        this.victoryPoint = victoryPoint;
        this.gameWidget = gameWidget;

        rootPanel.setSpacing(5);
        rootPanel.add(new Image(Resources.largeIcon(victoryPoint)));
        rootPanel.add(new Label(I18n.get().constants().victoryPoint()));
        rootPanel.add(btnPlay);

        playDevelopmentCard.setDevelopmentcard(victoryPoint);
        playDevelopmentCard.setPlayer(gameWidget.getPlayingPlayer());

        btnPlay.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                gameWidget.sendAction(playDevelopmentCard);
            }
        });
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }
}