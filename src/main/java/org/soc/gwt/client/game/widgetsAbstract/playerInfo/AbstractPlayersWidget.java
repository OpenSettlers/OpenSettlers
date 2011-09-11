package org.soc.gwt.client.game.widgetsAbstract.playerInfo;

import java.util.HashMap;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.OrderChangedEvent;
import org.soc.common.game.OrderChangedEvent.OrderChangedHandler;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.common.views.widgetsInterface.playerInfo.PlayerInfoWidget;
import org.soc.common.views.widgetsInterface.playerInfo.PlayersInfoWidget;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractPlayersWidget implements PlayersInfoWidget,
        OrderChangedHandler
{
  protected ComplexPanel rootPanel;
  protected GameWidget gameWidget;
  protected GamePlayer player;
  protected HashMap<GamePlayer, PlayerInfoWidget> playersWidgets = new HashMap<GamePlayer, PlayerInfoWidget>();

  public AbstractPlayersWidget(GameWidget gameWidget)
  {
    this.gameWidget = gameWidget;
    rootPanel = createRootPanel();
    for (GamePlayer player : gameWidget.game().players())
    {
      PlayerInfoWidget widget = createPlayerWidget(gameWidget, player);
      rootPanel.add(widget);
      playersWidgets.put(player, widget);
    }
    gameWidget.game().players().addOrderChangedHandler(this);
    rootPanel.setWidth("100%");
  }
  @Override public ComplexPanel createRootPanel()
  {
    return new VerticalPanel();
  }
  @Override public Widget asWidget()
  {
    return rootPanel;
  }
  @Override public void onOrderChanged(OrderChangedEvent event)
  {
    rootPanel.clear();
    for (GamePlayer player : gameWidget.game().players())
      rootPanel.add(playersWidgets.get(player));
  }
}
