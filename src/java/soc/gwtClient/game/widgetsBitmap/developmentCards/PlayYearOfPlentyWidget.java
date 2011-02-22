package soc.gwtClient.game.widgetsBitmap.developmentCards;

import soc.common.actions.gameAction.standard.PlayDevelopmentCard;
import soc.common.board.resources.ResourceList;
import soc.common.board.resources.ResourcesChangedEvent;
import soc.common.board.resources.ResourcesChangedEventHandler;
import soc.common.game.developmentCards.standard.YearOfPlenty;
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

public class PlayYearOfPlentyWidget implements DevelopmentCardWidget,
        ResourcesChangedEventHandler
{
    private YearOfPlenty yearOfPlenty;
    private GameWidget gameWidget;
    private PlayDevelopmentCard playDevelopmentCard = new PlayDevelopmentCard();
    private HorizontalPanel rootPanel = new HorizontalPanel();
    private ResourceListWidget resourceListWidget;
    private ResourcePickerWidget resourcePickerWidget;
    private ResourceList pickedResources = new ResourceList();
    private Button buttonnPlay = new Button(I18n.get().constants().play());

    public PlayYearOfPlentyWidget(final GameWidget gameWidget,
            final YearOfPlenty yearOfPlenty)
    {
        this.yearOfPlenty = yearOfPlenty;
        this.gameWidget = gameWidget;

        resourceListWidget = new ResourceListBitmapWidget(pickedResources,
                gameWidget.getGame().getBank().copy(), null);
        resourceListWidget.setHeight("3em");
        resourcePickerWidget = new ResourcePickerBitmapWidget(pickedResources,
                null, gameWidget.getGame().getBank().copy(), gameWidget);

        rootPanel.setSpacing(5);
        rootPanel.add(new Image(Resources.icons().yearOfPlenty()));
        rootPanel.add(new Label(I18n.get().constants().yearOfPlenty()));
        rootPanel.add(resourcePickerWidget);
        rootPanel.add(resourceListWidget);
        rootPanel.add(buttonnPlay);
        buttonnPlay.setEnabled(false);

        playDevelopmentCard.setDevelopmentcard(yearOfPlenty);
        playDevelopmentCard.setPlayer(gameWidget.getPlayingPlayer());

        buttonnPlay.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                yearOfPlenty.setGoldPick(pickedResources);
                gameWidget.sendAction(playDevelopmentCard);
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
        buttonnPlay.setEnabled(pickedResources.size() == 2);
    }
}
