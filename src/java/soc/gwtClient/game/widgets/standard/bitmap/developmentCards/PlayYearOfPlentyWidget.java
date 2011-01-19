package soc.gwtClient.game.widgets.standard.bitmap.developmentCards;

import soc.common.actions.gameAction.turnActions.standard.PlayDevelopmentCard;
import soc.common.board.resources.ResourceList;
import soc.common.board.resources.ResourcesChangedEvent;
import soc.common.board.resources.ResourcesChangedEventHandler;
import soc.common.game.developmentCards.standard.YearOfPlenty;
import soc.common.internationalization.I18n;
import soc.gwtClient.game.abstractWidgets.DevelopmentCardWidget;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.widgets.abstractWidgets.ResourceListWidget;
import soc.gwtClient.game.widgets.abstractWidgets.ResourcePickerWidget;
import soc.gwtClient.game.widgets.bitmap.ResourceListBitmapWidget;
import soc.gwtClient.game.widgets.bitmap.ResourcePickerBitmapWidget;
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
    private GamePanel gamePanel;
    private PlayDevelopmentCard playDevelopmentCard = new PlayDevelopmentCard();
    private HorizontalPanel rootPanel = new HorizontalPanel();
    private ResourceListWidget resourceListWidget;
    private ResourcePickerWidget resourcePickerWidget;
    private ResourceList pickedResources = new ResourceList();
    private Button btnPlay = new Button(I18n.get().constants().play());

    public PlayYearOfPlentyWidget(final YearOfPlenty yearOfPlenty,
            final GamePanel gamePanel)
    {
        this.yearOfPlenty = yearOfPlenty;
        this.gamePanel = gamePanel;

        resourceListWidget = new ResourceListBitmapWidget(pickedResources,
                gamePanel.getGame().getBank().copy(), null);
        resourceListWidget.setHeight("3em");
        resourcePickerWidget = new ResourcePickerBitmapWidget(pickedResources,
                null, gamePanel.getGame().getBank().copy(), gamePanel);

        rootPanel.setSpacing(5);
        rootPanel.add(new Image(Resources.icons().yearOfPlenty()));
        rootPanel.add(new Label(I18n.get().constants().yearOfPlenty()));
        rootPanel.add(resourcePickerWidget);
        rootPanel.add(resourceListWidget);
        rootPanel.add(btnPlay);
        btnPlay.setEnabled(false);

        playDevelopmentCard.setDevelopmentcard(yearOfPlenty);
        playDevelopmentCard.setPlayer(gamePanel.getPlayingPlayer());

        btnPlay.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                yearOfPlenty.setGoldPick(pickedResources);
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
        btnPlay.setEnabled(pickedResources.size() == 2);
    }
}
