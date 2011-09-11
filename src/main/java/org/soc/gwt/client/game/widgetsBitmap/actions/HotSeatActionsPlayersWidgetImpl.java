package org.soc.gwt.client.game.widgetsBitmap.actions;

import java.util.HashMap;

import org.soc.common.game.GamePlayer;
import org.soc.common.views.widgetsInterface.actions.ActionsWidget;
import org.soc.common.views.widgetsInterface.actions.HotSeatActionsPlayersWidget;
import org.soc.common.views.widgetsInterface.generic.Point2D;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.common.views.widgetsInterface.main.GameWidget.GameWidgetFactory;
import org.soc.common.views.widgetsInterface.main.HandCardsWidget;
import org.soc.common.views.widgetsInterface.main.PlayerStuffWidget;

import com.google.gwt.user.client.ui.DeckPanel;

public class HotSeatActionsPlayersWidgetImpl extends DeckPanel implements
        PlayerStuffWidget, HotSeatActionsPlayersWidget
{
  private HashMap<GamePlayer, Integer> playersStuff = new HashMap<GamePlayer, Integer>();
  private HashMap<Integer, PlayerStuffWidget> indexedWidgets = new HashMap<Integer, PlayerStuffWidget>();
  private GameWidget gameWidget;
  private PlayerStuffWidget currentWidget;

  /** @return the currentWidget */
  public ActionsWidget getCurrentActionsWidget()
  {
    return currentWidget.getActionsWidget();
  }
  public HotSeatActionsPlayersWidgetImpl(GameWidget gameWidget,
          GameWidgetFactory factory)
  {
    super();
    this.gameWidget = gameWidget;
    int index = 0;
    for (GamePlayer player : gameWidget.game().players())
    {
      if (player.bot() == null)
      {
        PlayerStuffWidget widget = factory
                .createPlayerStuffWidget(player);
        this.add(widget.asWidget());
        playersStuff.put(player, index);
        indexedWidgets.put(index, widget);
        if (currentWidget == null)
        {
          currentWidget = widget;
        }
        index++;
      }
    }
    // Only show first widget when present, bot-only games don't need player
    // widgets
    if (playersStuff.size() > 0)
      this.showWidget(0);
  }
  public void setPlayer(GamePlayer player)
  {
    if (playersStuff.size() > 0)
    {
      int index = playersStuff.get(player);
      currentWidget = indexedWidgets.get(index);
      this.showWidget(index);
    }
  }
  @Override public ActionsWidget getActionsWidget()
  {
    int index = playersStuff.get(gameWidget.playingPlayer());
    currentWidget = indexedWidgets.get(index);
    return currentWidget.getActionsWidget();
  }
  @Override public Point2D getDiceWidgetTopLeftPosition()
  {
    if (playersStuff.get(gameWidget.playingPlayer()) == null)
    {
      int y = 2;
      y++;
    }
    int index = playersStuff.get(gameWidget.playingPlayer());
    PlayerStuffWidget widget = indexedWidgets.get(index);
    return widget.getDiceWidgetTopLeftPosition();
  }
  @Override public HandCardsWidget getHandCardsWidget()
  {
    int index = playersStuff.get(gameWidget.playingPlayer());
    currentWidget = indexedWidgets.get(index);
    return currentWidget.getHandCardsWidget();
  }
}
