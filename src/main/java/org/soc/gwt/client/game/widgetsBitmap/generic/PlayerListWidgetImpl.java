package org.soc.gwt.client.game.widgetsBitmap.generic;

import java.util.List;

import org.soc.common.game.GamePlayer;
import org.soc.common.utils.Util;
import org.soc.gwt.client.game.widgetsBitmap.generic.PlayersChangedEvent.PlayersChangedHandler;
import org.soc.gwt.client.images.R;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.ImageResourceCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

public class PlayerListWidgetImpl extends VerticalPanel implements
        PlayerListWidget
{
  private CellTable<GamePlayer> celltablePlayers = new CellTable<GamePlayer>(
          8);
  private ListDataProvider<GamePlayer> playerProvider = new ListDataProvider<GamePlayer>();
  private SimpleEventBus eventBus = new SimpleEventBus();

  public PlayerListWidgetImpl()
  {
    setStyleName("common-panel");
    setWidth("100%");
    Column<GamePlayer, ?> columnID = new Column<GamePlayer, Number>(
            new NumberCell())
    {
      @Override public Number getValue(GamePlayer object)
      {
        return object.user().id();
      }
    };
    celltablePlayers.addColumn(columnID, "Id");
    Column<GamePlayer, ImageResource> columnBotHuman = new Column<GamePlayer, ImageResource>(
            new ImageResourceCell())
    {
      @Override public ImageResource getValue(GamePlayer object)
      {
        return object.bot() == null ? R.icons().player16()
                : R.icons().bot16();
      }
    };
    celltablePlayers.addColumn(columnBotHuman, "Bot/Human");
    TextColumn columnPlayerType = new TextColumn<GamePlayer>()
    {
      @Override public String getValue(GamePlayer object)
      {
        return object.bot() == null ? "Human" : Util
                .shortName(object.bot().getClass());
      }
    };
    celltablePlayers.addColumn(columnPlayerType, "Type");
    Column<GamePlayer, String> colorColumn = new Column<GamePlayer, String>(
            new ColorCell())
    {
      @Override public String getValue(GamePlayer object)
      {
        return object.color();
      }
    };
    celltablePlayers.addColumn(colorColumn, "Color");
    TextColumn columnName = new TextColumn<GamePlayer>()
    {
      @Override public String getValue(GamePlayer object)
      {
        return object.user().name();
      }
    };
    celltablePlayers.addColumn(columnName, "Name");
    Column<GamePlayer, String> columnRemove = new Column<GamePlayer, String>(
            new ButtonCell())
    {
      @Override public String getValue(GamePlayer object)
      {
        return "Remove";
      }
    };
    columnRemove.setFieldUpdater(new FieldUpdater<GamePlayer, String>()
    {
      @Override public void update(int index, GamePlayer object, String value)
      {
        removePlayer(object);
      }
    });
    celltablePlayers.addColumn(columnRemove, "Remove");
    celltablePlayers.setSize("100%", "20em");
    add(celltablePlayers);
    playerProvider.addDataDisplay(celltablePlayers);
  }
  @Override public void addPlayer(GamePlayer newPlayer)
  {
    playerProvider.getList().add(newPlayer);
    eventBus.fireEvent(new PlayersChangedEvent(newPlayer, null));
  }
  @Override public List<GamePlayer> getPlayers()
  {
    return playerProvider.getList();
  }
  @Override public void removePlayer(GamePlayer playerToRemove)
  {
    playerProvider.getList().remove(playerToRemove);
    eventBus.fireEvent(new PlayersChangedEvent(null, playerToRemove));
  }
  @Override public int amountPlayers()
  {
    return playerProvider.getList().size();
  }
  @Override public HandlerRegistration addPlayersChangedHandler(PlayersChangedHandler handler) {
    return eventBus.addHandler(PlayersChangedEvent.getType(), handler);
  }
}
