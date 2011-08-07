package org.soc.gwt.client.game.widgetsBitmap.developmentCards;

import org.soc.common.actions.gameAction.standard.PlayDevelopmentCard;
import org.soc.common.game.developmentCards.PlayableChangedEvent;
import org.soc.common.game.developmentCards.PlayableChangedEventHandler;
import org.soc.common.game.developmentCards.standard.Soldier;
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

public class PlaySoldierWidget implements DevelopmentCardWidget,
                PlayableChangedEventHandler
{
    private Soldier soldier;
    private GameWidget gameWidget;
    private PlayDevelopmentCard playDevelopmentCard = new PlayDevelopmentCard();
    private HorizontalPanel rootPanel = new HorizontalPanel();
    private Button btnPlay = new Button(I18n.get().constants().play());

    public PlaySoldierWidget(final GameWidget gameWidget, Soldier soldier)
    {
        this.soldier = soldier;
        this.gameWidget = gameWidget;

        rootPanel.setSpacing(5);
        rootPanel.add(new Image(Resources.largeIcon(soldier)));
        rootPanel.add(new Label(I18n.get().constants().soldier()));
        rootPanel.add(btnPlay);

        playDevelopmentCard.setDevelopmentcard(soldier);
        playDevelopmentCard.setPlayer(gameWidget.getPlayingPlayer());

        btnPlay.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                gameWidget.sendAction(playDevelopmentCard);
            }
        });

        soldier.addPlayableChangedEventHandler(this);
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

    @Override
    public void onPlayableChanged(PlayableChangedEvent event)
    {
        btnPlay.setEnabled(event.isPlayable());
    }

}
