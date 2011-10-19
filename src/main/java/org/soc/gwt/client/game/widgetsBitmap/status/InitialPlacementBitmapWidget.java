package org.soc.gwt.client.game.widgetsBitmap.status;

import org.soc.common.core.GenericList.Models;
import org.soc.common.game.*;
import org.soc.common.game.GamePhase.InitialPlacementGamePhase;
import org.soc.common.views.widgetsInterface.main.*;

import com.google.gwt.resources.client.*;
import com.google.gwt.user.client.ui.*;

public class InitialPlacementBitmapWidget implements GamePhaseWidget
{
  private VerticalPanel rootPanel = new VerticalPanel();
  private InitialPlacementGamePhase gamePhase;
  private ImageResource icon;

  public InitialPlacementBitmapWidget(InitialPlacementGamePhase gamePhase)
  {
    super();
    this.gamePhase = gamePhase;
    icon = Models.mediumIcon(gamePhase);
    rootPanel.add(new Label("Place initial towns & roads"));
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
