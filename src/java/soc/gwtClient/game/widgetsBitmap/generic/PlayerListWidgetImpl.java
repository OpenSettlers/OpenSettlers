package soc.gwtClient.game.widgetsBitmap.generic;

import java.util.List;

import soc.common.game.player.GamePlayer;
import soc.common.utils.ClassUtils;
import soc.gwtClient.images.Resources;

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
            @Override
            public Number getValue(GamePlayer object)
            {
                return object.getUser().getId();
            }
        };
        celltablePlayers.addColumn(columnID, "Id");

        Column<GamePlayer, ImageResource> columnBotHuman = new Column<GamePlayer, ImageResource>(
                        new ImageResourceCell())
        {
            @Override
            public ImageResource getValue(GamePlayer object)
            {
                return object.getBot() == null ? Resources.icons().player16()
                                : Resources.icons().bot16();
            }
        };
        celltablePlayers.addColumn(columnBotHuman, "Bot/Human");

        TextColumn columnPlayerType = new TextColumn<GamePlayer>()
        {
            @Override
            public String getValue(GamePlayer object)
            {
                return object.getBot() == null ? "Human" : ClassUtils
                                .getSimpleClassName(object.getBot().getClass()
                                                .getName());
            }
        };
        celltablePlayers.addColumn(columnPlayerType, "Type");

        Column<GamePlayer, String> colorColumn = new Column<GamePlayer, String>(
                        new ColorCell())
        {
            @Override
            public String getValue(GamePlayer object)
            {
                return object.getColor();
            }
        };
        celltablePlayers.addColumn(colorColumn, "Color");

        TextColumn columnName = new TextColumn<GamePlayer>()
        {
            @Override
            public String getValue(GamePlayer object)
            {
                return object.getUser().getName();
            }
        };
        celltablePlayers.addColumn(columnName, "Name");

        Column<GamePlayer, String> columnRemove = new Column<GamePlayer, String>(
                        new ButtonCell())
        {
            @Override
            public String getValue(GamePlayer object)
            {
                return "Remove";
            }
        };

        columnRemove.setFieldUpdater(new FieldUpdater<GamePlayer, String>()
        {
            @Override
            public void update(int index, GamePlayer object, String value)
            {
                removePlayer(object);
            }
        });
        celltablePlayers.addColumn(columnRemove, "Remove");
        celltablePlayers.setSize("100%", "20em");
        add(celltablePlayers);
        playerProvider.addDataDisplay(celltablePlayers);

    }

    @Override
    public void addPlayer(GamePlayer newPlayer)
    {
        playerProvider.getList().add(newPlayer);
        eventBus.fireEvent(new PlayersChangedEvent(newPlayer, null));
    }

    @Override
    public List<GamePlayer> getPlayers()
    {
        return playerProvider.getList();
    }

    @Override
    public void removePlayer(GamePlayer playerToRemove)
    {
        playerProvider.getList().remove(playerToRemove);
        eventBus.fireEvent(new PlayersChangedEvent(null, playerToRemove));
    }

    @Override
    public HandlerRegistration addPlayersChangedEventHandler(
                    PlayersChangedEventHandler handler)
    {
        return eventBus.addHandler(PlayersChangedEvent.TYPE, handler);
    }

    @Override
    public int amountPlayers()
    {
        return playerProvider.getList().size();
    }
}
