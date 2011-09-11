package org.soc.gwt.client.game.widgetsBitmap.playerInfo;

import org.soc.common.board.PortListChangedEvent;
import org.soc.common.board.PortListChangedEvent.PortListChangedHandler;
import org.soc.common.game.GamePlayer;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.common.views.widgetsInterface.playerInfo.PortAmountWidget;
import org.soc.gwt.client.game.widgetsBitmap.tooltips.PortListToolTip;
import org.soc.gwt.client.images.R;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class PortAmountBitmapWidget implements PortAmountWidget,
        PortListChangedHandler, MouseOutHandler, MouseOverHandler
{
  private VerticalPanel rootPanel = new VerticalPanel();
  private Image imgPort = new Image(R.icons().port16());
  private Label lblPortAmount = new Label();
  private GamePlayer player;
  private PortListToolTip portListDetailWidget;
  private GameWidget gameWidget;

  public PortAmountBitmapWidget(GameWidget gameWidget, GamePlayer player)
  {
    super();
    this.gameWidget = gameWidget;
    this.player = player;
    portListDetailWidget = new PortListToolTip(gameWidget, player);
    updateUI();
    rootPanel.add(imgPort);
    rootPanel.add(lblPortAmount);
    player.ports().addPortListChangedHandler(this);
    rootPanel.addDomHandler(this, MouseOutEvent.getType());
    rootPanel.addDomHandler(this, MouseOverEvent.getType());
  }
  @Override public Widget asWidget()
  {
    return rootPanel;
  }
  private void updateUI()
  {
    lblPortAmount.setText(Integer.toString(player.ports().size() - 1));
  }
  @Override public void onPortListChanged(PortListChangedEvent event)
  {
    updateUI();
  }
  @Override public void onMouseOut(MouseOutEvent event)
  {
    gameWidget.toolTipManager().hide(portListDetailWidget);
  }
  @Override public void onMouseOver(MouseOverEvent event)
  {
    gameWidget.toolTipManager().show(portListDetailWidget);
  }
}
