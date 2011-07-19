package org.soc.gwt.client.game.widgetsBitmap.developmentCards;

import org.soc.common.actions.gameAction.standard.PlayDevelopmentCard;
import org.soc.common.board.resources.ResourceList;
import org.soc.common.board.resources.ResourcesChangedEvent;
import org.soc.common.board.resources.ResourcesChangedEventHandler;
import org.soc.common.game.developmentCards.PlayableChangedEvent;
import org.soc.common.game.developmentCards.PlayableChangedEventHandler;
import org.soc.common.game.developmentCards.standard.Monopoly;
import org.soc.common.internationalization.I18n;
import org.soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidget;
import org.soc.common.views.widgetsInterface.generic.ResourceListWidget;
import org.soc.common.views.widgetsInterface.generic.ResourcePickerWidget;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsBitmap.generic.ResourceListBitmapWidget;
import org.soc.gwt.client.game.widgetsBitmap.generic.ResourcePickerBitmapWidget;
import org.soc.gwt.client.images.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class PlayMonopolyWidget implements DevelopmentCardWidget,
                ResourcesChangedEventHandler, PlayableChangedEventHandler
{
    private Monopoly monopoly;
    private GameWidget gameWidget;
    private PlayDevelopmentCard playDevelopmentCard = new PlayDevelopmentCard();
    private HorizontalPanel rootPanel = new HorizontalPanel();
    private Button btnPlay = new Button(I18n.get().constants().play());
    private ResourceListWidget resourceListWidget;
    private ResourcePickerWidget resourcePickerWidget;
    private ResourceList pickedResources = new ResourceList();

    public PlayMonopolyWidget(final GameWidget gameWidget,
                    final Monopoly monopoly)
    {
        this.monopoly = monopoly;
        this.gameWidget = gameWidget;

        resourceListWidget = new ResourceListBitmapWidget(pickedResources,
                        gameWidget.getGame().getBank().copy(), null);
        resourceListWidget.setHeight("3em");
        resourcePickerWidget = new ResourcePickerBitmapWidget(pickedResources,
                        null, gameWidget.getGame().getBank().copy(), gameWidget);

        rootPanel.setSpacing(5);
        rootPanel.add(new Image(Resources.mediumIcon(monopoly)));
        rootPanel.add(new Label(I18n.get().constants().monopoly()));
        rootPanel.add(resourcePickerWidget);
        rootPanel.add(resourceListWidget);
        rootPanel.add(btnPlay);

        playDevelopmentCard.setDevelopmentcard(monopoly);
        playDevelopmentCard.setPlayer(gameWidget.getPlayingPlayer());

        btnPlay.setEnabled(false);
        btnPlay.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                monopoly.setResource(pickedResources.get(0));
                gameWidget.sendAction(playDevelopmentCard);
            }
        });

        pickedResources.addResourcesChangedEventHandler(this);
        monopoly.addPlayableChangedEventHandler(this);
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

    @Override
    public void onResourcesChanged(ResourcesChangedEvent resourcesChanged)
    {
        updateUI();
    }

    private void updateUI()
    {
        btnPlay.setEnabled(pickedResources.size() == 1 && monopoly.isPlayable());
    }

    @Override
    public void onPlayableChanged(PlayableChangedEvent event)
    {
        updateUI();
    }
}
