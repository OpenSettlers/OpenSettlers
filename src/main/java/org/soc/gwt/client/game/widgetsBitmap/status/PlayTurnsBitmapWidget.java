package org.soc.gwt.client.game.widgetsBitmap.status;

import java.util.*;
import java.util.Map.Entry;

import org.soc.common.core.GenericList.Models;
import org.soc.common.game.*;
import org.soc.common.game.GamePhase.PlayTurnsGamePhase;
import org.soc.common.game.TurnPhase.BeforeDiceRollTurnPhase;
import org.soc.common.game.TurnPhase.BuildingTurnPhase;
import org.soc.common.game.TurnPhase.RollDiceTurnPhase;
import org.soc.common.game.TurnPhase.TradingTurnPhase;
import org.soc.common.game.TurnPhaseChangedEvent.TurnPhaseChangedHandler;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.resources.client.*;
import com.google.gwt.user.client.ui.*;

public class PlayTurnsBitmapWidget implements GamePhaseWidget,
        TurnPhaseChangedHandler
{
  private VerticalPanel rootPanel = new VerticalPanel();
  private HorizontalPanel turnPhasePanel = new HorizontalPanel();
  private PlayTurnsGamePhase playTurnsGamePhase;
  private ImageResource icon;
  private Label lblTurnPhaseDescription = new Label();
  private GameWidget gameWidget;
  private ArrayList<TurnPhase> turnPhases = new ArrayList<TurnPhase>();
  private Map<TurnPhase, Image> phaseIcons = new HashMap<TurnPhase, Image>();
  private Image currentPhaseIcon;

  public PlayTurnsBitmapWidget(GameWidget gameWidget,
          PlayTurnsGamePhase playTurnsGamePhase)
  {
    super();
    this.gameWidget = gameWidget;
    this.playTurnsGamePhase = playTurnsGamePhase;
    icon = Models.mediumIcon(playTurnsGamePhase);
    rootPanel.add(lblTurnPhaseDescription);
    rootPanel.add(turnPhasePanel);
    turnPhases.add(new BeforeDiceRollTurnPhase());
    turnPhases.add(new RollDiceTurnPhase());
    turnPhases.add(new TradingTurnPhase());
    turnPhases.add(new BuildingTurnPhase());
    for (TurnPhase turnPhase : turnPhases)
    {
      Image icon = new Image(R.mediumIcon(Models.icon(turnPhase)));
      phaseIcons.put(turnPhase, icon);
      turnPhasePanel.add(icon);
    }
    gameWidget.game().addTurnPhaseChangedHandler(this);
  }
  @Override public GamePhase getGamePhase()
  {
    return playTurnsGamePhase;
  }
  @Override public ImageResource getIcon()
  {
    return icon;
  }
  @Override public Widget asWidget()
  {
    return rootPanel;
  }
  @Override public void onTurnPhaseChanged(TurnPhaseChangedEvent event)
  {
    if (currentPhaseIcon != null)
      currentPhaseIcon.setStyleName("phaseNotSelectedIcon");
    for (Entry<TurnPhase, Image> entry : phaseIcons.entrySet())
      if (entry.getKey().getClass() == event.getNewPhase().getClass())
        currentPhaseIcon = entry.getValue();
    // currentPhaseIcon = phaseIcons.get(event.getNewPhase());
    currentPhaseIcon.setStyleName("phaseSelectedIcon");
    lblTurnPhaseDescription.setText(event.getNewPhase().getMessage());
  }
}
