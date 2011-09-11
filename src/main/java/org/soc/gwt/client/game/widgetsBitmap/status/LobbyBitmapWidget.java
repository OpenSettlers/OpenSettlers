package org.soc.gwt.client.game.widgetsBitmap.status;

import org.soc.common.game.GamePhase;
import org.soc.common.game.GamePhase.LobbyGamePhase;
import org.soc.common.views.widgetsInterface.main.GamePhaseWidget;
import org.soc.gwt.client.images.R;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class LobbyBitmapWidget implements GamePhaseWidget
{
  private VerticalPanel rootPanel = new VerticalPanel();
  private LobbyGamePhase gamePhase;
  private ImageResource icon;

  public LobbyBitmapWidget(LobbyGamePhase gamePhase)
  {
    super();
    this.gamePhase = gamePhase;
    icon = R.mediumIcon(gamePhase);
    rootPanel.add(new Label("Lobby phase"));
    rootPanel.add(new Image(icon));
    rootPanel.setStyleName("phasePanel");
  }
  @Override public GamePhase getGamePhase()
  {
    return gamePhase;
  }
  @Override public ImageResource getIcon()
  {
    return icon;
  }
  @Override public Widget asWidget()
  {
    return rootPanel;
  }
}