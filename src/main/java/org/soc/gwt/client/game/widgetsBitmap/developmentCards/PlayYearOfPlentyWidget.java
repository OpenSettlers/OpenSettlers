package org.soc.gwt.client.game.widgetsBitmap.developmentCards;

import org.soc.common.game.DevelopmentCard.YearOfPlenty;
import org.soc.common.game.PlayableChangedEvent;
import org.soc.common.game.PlayableChangedEvent.PlayableChangedHandler;
import org.soc.common.game.ResourceList;
import org.soc.common.game.ResourcesChangedEvent;
import org.soc.common.game.ResourcesChangedEvent.ResourcesChangedHandler;
import org.soc.common.game.actions.PlayDevelopmentCard;
import org.soc.common.internationalization.I;
import org.soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidget;
import org.soc.common.views.widgetsInterface.generic.ResourceListWidget;
import org.soc.common.views.widgetsInterface.generic.ResourcePickerWidget;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsBitmap.generic.ResourceListBitmapWidget;
import org.soc.gwt.client.game.widgetsBitmap.generic.ResourcePickerBitmapWidget;
import org.soc.gwt.client.images.R;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class PlayYearOfPlentyWidget implements DevelopmentCardWidget,
        ResourcesChangedHandler, PlayableChangedHandler
{
  private YearOfPlenty yearOfPlenty;
  private GameWidget gameWidget;
  private PlayDevelopmentCard playDevelopmentCard = new PlayDevelopmentCard();
  private HorizontalPanel rootPanel = new HorizontalPanel();
  private ResourceListWidget resourceListWidget;
  private ResourcePickerWidget resourcePickerWidget;
  private ResourceList pickedResources = new ResourceList();
  private Button buttonnPlay = new Button(I.get().constants().play());

  public PlayYearOfPlentyWidget(final GameWidget gameWidget,
          final YearOfPlenty yearOfPlenty)
  {
    this.yearOfPlenty = yearOfPlenty;
    this.gameWidget = gameWidget;
    resourceListWidget = new ResourceListBitmapWidget(pickedResources,
            gameWidget.game().bank().copy(), null);
    resourceListWidget.setHeight("3em");
    resourcePickerWidget = new ResourcePickerBitmapWidget(pickedResources,
            null, gameWidget.game().bank().copy(), gameWidget);
    rootPanel.setSpacing(5);
    rootPanel.add(new Image(R.largeIcon(yearOfPlenty)));
    rootPanel.add(new Label(I.get().constants().yearOfPlenty()));
    rootPanel.add(resourcePickerWidget);
    rootPanel.add(resourceListWidget);
    rootPanel.add(buttonnPlay);
    buttonnPlay.setEnabled(false);
    playDevelopmentCard.setDevelopmentcard(yearOfPlenty);
    playDevelopmentCard.setPlayer(gameWidget.playingPlayer());
    buttonnPlay.addClickHandler(new ClickHandler()
    {
      @Override public void onClick(ClickEvent event)
      {
        yearOfPlenty.setGoldPick(pickedResources);
        gameWidget.doAction(playDevelopmentCard);
      }
    });
    pickedResources.addResourcesChangedHandler(this);
    yearOfPlenty.addPlayableChangedHandler(this);
  }
  @Override public Widget asWidget()
  {
    return rootPanel;
  }
  @Override public void onResourcesChanged(ResourcesChangedEvent resourcesChanged)
  {
    buttonnPlay.setEnabled(pickedResources.size() == 2);
  }
  @Override public void onPlayableChanged(PlayableChangedEvent event)
  {
    buttonnPlay.setEnabled(event.isPlayable());
  }
}
