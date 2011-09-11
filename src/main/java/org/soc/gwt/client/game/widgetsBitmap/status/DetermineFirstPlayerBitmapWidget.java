package org.soc.gwt.client.game.widgetsBitmap.status;

import org.soc.common.game.GamePhase;
import org.soc.common.game.GamePhase.DetermineFirstPlayerGamePhase;
import org.soc.common.views.widgetsInterface.main.GamePhaseWidget;
import org.soc.gwt.client.images.R;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class DetermineFirstPlayerBitmapWidget implements GamePhaseWidget
{
  private VerticalPanel rootPanel = new VerticalPanel();
  private DetermineFirstPlayerGamePhase determineFirstPlayerGamePhase;
  private ImageResource icon;

  public DetermineFirstPlayerBitmapWidget(
          DetermineFirstPlayerGamePhase determineFirstPlayerGamePhase)
  {
    this.determineFirstPlayerGamePhase = determineFirstPlayerGamePhase;
    icon = R.mediumIcon(determineFirstPlayerGamePhase);
    rootPanel.add(new Label("Highroller starts the game"));
    rootPanel.add(new Image(icon));
    rootPanel.setStyleName("phasePanel");
  }
  @Override public GamePhase getGamePhase()
  {
    return determineFirstPlayerGamePhase;
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
