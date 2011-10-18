package org.soc.gwt.client.game.widgetsBitmap.playerInfo;

import org.soc.common.core.GenericList.*;
import org.soc.common.core.GenericList.AddsList.*;
import org.soc.common.core.GenericList.RemovesList.*;
import org.soc.common.game.*;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.common.views.widgetsInterface.playerInfo.*;
import org.soc.gwt.client.game.widgetsBitmap.tooltips.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;

public class PortAmountBitmapWidget implements PortAmountWidget,
        MouseOutHandler, MouseOverHandler
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
    player.ports().addListAddedHandler(new ListAdded<Port>() {
      @Override public void listAdded(ImmutableList<Port> items) {
        updateUI();
      }
    });
    player.ports().addListRemovedHandler(new ListRemoved<Port>() {
      @Override public void listRemoved(ImmutableList<Port> items) {
        updateUI();
      }
    });
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
  @Override public void onMouseOut(MouseOutEvent event)
  {
    gameWidget.toolTipManager().hide(portListDetailWidget);
  }
  @Override public void onMouseOver(MouseOverEvent event)
  {
    gameWidget.toolTipManager().show(portListDetailWidget);
  }
}
