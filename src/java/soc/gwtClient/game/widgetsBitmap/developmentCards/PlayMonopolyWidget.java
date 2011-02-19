package soc.gwtClient.game.widgetsBitmap.developmentCards;

import soc.common.actions.gameAction.turnActions.standard.PlayDevelopmentCard;
import soc.common.board.resources.ResourceList;
import soc.common.board.resources.ResourcesChangedEvent;
import soc.common.board.resources.ResourcesChangedEventHandler;
import soc.common.game.developmentCards.standard.Monopoly;
import soc.common.internationalization.I18n;
import soc.gwtClient.game.widgetsBitmap.generic.ResourceListBitmapWidget;
import soc.gwtClient.game.widgetsBitmap.generic.ResourcePickerBitmapWidget;
import soc.gwtClient.game.widgetsInterface.developmentCards.DevelopmentCardWidget;
import soc.gwtClient.game.widgetsInterface.generic.ResourceListWidget;
import soc.gwtClient.game.widgetsInterface.generic.ResourcePickerWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class PlayMonopolyWidget implements DevelopmentCardWidget,
        ResourcesChangedEventHandler
{
    private Monopoly monopoly;
    private GameWidget gamePanel;
    private PlayDevelopmentCard playDevelopmentCard = new PlayDevelopmentCard();
    private HorizontalPanel rootPanel = new HorizontalPanel();
    private Button btnPlay = new Button(I18n.get().constants().play());
    private ResourceListWidget resourceListWidget;
    private ResourcePickerWidget resourcePickerWidget;
    private ResourceList pickedResources = new ResourceList();

    public PlayMonopolyWidget(final Monopoly monopoly, final GameWidget gamePanel)
    {
        this.monopoly = monopoly;
        this.gamePanel = gamePanel;

        resourceListWidget = new ResourceListBitmapWidget(pickedResources,
                gamePanel.getGame().getBank().copy(), null);
        resourceListWidget.setHeight("3em");
        resourcePickerWidget = new ResourcePickerBitmapWidget(pickedResources,
                null, gamePanel.getGame().getBank().copy(), gamePanel);

        rootPanel.setSpacing(5);
        rootPanel.add(new Image(Resources.icons().monopoly()));
        rootPanel.add(new Label(I18n.get().constants().monopoly()));
        rootPanel.add(resourcePickerWidget);
        rootPanel.add(resourceListWidget);
        rootPanel.add(btnPlay);

        playDevelopmentCard.setDevelopmentcard(monopoly);
        playDevelopmentCard.setPlayer(gamePanel.getPlayingPlayer());

        btnPlay.setEnabled(false);
        btnPlay.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                monopoly.setResource(pickedResources.get(0));
                gamePanel.sendAction(playDevelopmentCard);
            }
        });

        pickedResources.addResourcesChangedEventHandler(this);
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

    @Override
    public void onResourcesChanged(ResourcesChangedEvent resourcesChanged)
    {
        btnPlay.setEnabled(pickedResources.size() == 1);
    }
}
