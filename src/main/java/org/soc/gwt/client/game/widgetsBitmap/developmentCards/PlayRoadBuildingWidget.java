package org.soc.gwt.client.game.widgetsBitmap.developmentCards;

import org.soc.common.game.DevelopmentCard.RoadBuilding;
import org.soc.common.game.PlayableChangedEvent;
import org.soc.common.game.PlayableChangedEvent.PlayableChangedHandler;
import org.soc.common.game.actions.PlayDevelopmentCard;
import org.soc.common.internationalization.I;
import org.soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidget;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.images.R;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class PlayRoadBuildingWidget implements DevelopmentCardWidget,
        PlayableChangedHandler
{
  private RoadBuilding roadBuilding;
  private GameWidget gameWidget;
  private PlayDevelopmentCard playDevelopmentCard = new PlayDevelopmentCard();
  private HorizontalPanel rootPanel = new HorizontalPanel();
  private Button btnPlay = new Button(I.get().constants().play());

  public PlayRoadBuildingWidget(final GameWidget gameWidget,
          RoadBuilding roadBuilding)
  {
    this.roadBuilding = roadBuilding;
    this.gameWidget = gameWidget;
    rootPanel.setSpacing(5);
    rootPanel.add(new Image(R.icons().roadBuilding48()));
    rootPanel.add(new Label(I.get().constants().roadBuilding()));
    rootPanel.add(btnPlay);
    playDevelopmentCard.setDevelopmentcard(roadBuilding);
    playDevelopmentCard.setPlayer(gameWidget.playingPlayer());
    btnPlay.addClickHandler(new ClickHandler()
    {
      @Override public void onClick(ClickEvent event)
      {
        gameWidget.doAction(playDevelopmentCard);
      }
    });
    roadBuilding.addPlayableChangedHandler(this);
  }
  @Override public Widget asWidget()
  {
    return rootPanel;
  }
  @Override public void onPlayableChanged(PlayableChangedEvent event)
  {
    btnPlay.setEnabled(event.isPlayable());
  }
}
