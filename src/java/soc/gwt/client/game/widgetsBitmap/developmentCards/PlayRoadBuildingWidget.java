package soc.gwt.client.game.widgetsBitmap.developmentCards;

import soc.common.actions.gameAction.standard.PlayDevelopmentCard;
import soc.common.game.developmentCards.PlayableChangedEvent;
import soc.common.game.developmentCards.PlayableChangedEventHandler;
import soc.common.game.developmentCards.standard.RoadBuilding;
import soc.common.internationalization.I18n;
import soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidget;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.gwt.client.images.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class PlayRoadBuildingWidget implements DevelopmentCardWidget,
                PlayableChangedEventHandler
{
    private RoadBuilding roadBuilding;
    private GameWidget gameWidget;
    private PlayDevelopmentCard playDevelopmentCard = new PlayDevelopmentCard();
    private HorizontalPanel rootPanel = new HorizontalPanel();
    private Button btnPlay = new Button(I18n.get().constants().play());

    public PlayRoadBuildingWidget(final GameWidget gameWidget,
                    RoadBuilding roadBuilding)
    {
        this.roadBuilding = roadBuilding;
        this.gameWidget = gameWidget;

        rootPanel.setSpacing(5);
        rootPanel.add(new Image(Resources.icons().roadBuilding48()));
        rootPanel.add(new Label(I18n.get().constants().roadBuilding()));
        rootPanel.add(btnPlay);

        playDevelopmentCard.setDevelopmentcard(roadBuilding);
        playDevelopmentCard.setPlayer(gameWidget.getPlayingPlayer());

        btnPlay.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                gameWidget.sendAction(playDevelopmentCard);
            }
        });

        roadBuilding.addPlayableChangedEventHandler(this);
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
