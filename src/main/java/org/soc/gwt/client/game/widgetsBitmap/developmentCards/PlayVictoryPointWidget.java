package org.soc.gwt.client.game.widgetsBitmap.developmentCards;

import org.soc.common.game.DevelopmentCard.VictoryPoint;
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

public class PlayVictoryPointWidget implements DevelopmentCardWidget
{
  private VictoryPoint victoryPoint;
  private GameWidget gameWidget;
  private PlayDevelopmentCard playDevelopmentCard = new PlayDevelopmentCard();
  private HorizontalPanel rootPanel = new HorizontalPanel();
  private Button btnPlay = new Button(I.get().constants().play());

  public PlayVictoryPointWidget(final GameWidget gameWidget,
          VictoryPoint victoryPoint)
  {
    this.victoryPoint = victoryPoint;
    this.gameWidget = gameWidget;
    rootPanel.setSpacing(5);
    rootPanel.add(new Image(R.largeIcon(victoryPoint)));
    rootPanel.add(new Label(I.get().constants().victoryPoint()));
    rootPanel.add(btnPlay);
    playDevelopmentCard.setDevelopmentcard(victoryPoint);
    playDevelopmentCard.setPlayer(gameWidget.playingPlayer());
    btnPlay.addClickHandler(new ClickHandler()
    {
      @Override public void onClick(ClickEvent event)
      {
        gameWidget.doAction(playDevelopmentCard);
      }
    });
  }
  @Override public Widget asWidget()
  {
    return rootPanel;
  }
}