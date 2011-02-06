package soc.gwtClient.game.dialogs;

import java.util.List;

import soc.common.board.Board;
import soc.common.bots.Bot;
import soc.common.game.Game;
import soc.common.game.GameUtils;
import soc.common.game.player.GamePlayer;
import soc.common.game.player.GamePlayerImpl;
import soc.common.server.data.BoardProvider;
import soc.common.server.data.ConstructorBoardProvider;
import soc.common.server.data.Player;
import soc.common.server.random.ClientRandom;
import soc.common.utils.ClassUtils;
import soc.gwtClient.game.widgets.BoardViewerWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.ImageResourceCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

public class NewHotseatGame extends Composite
{
    private BoardProvider boardProvider = new ConstructorBoardProvider();
    private CellTable<Board> cellTable;
    private List<Board> boards = boardProvider.getAllBoards();
    private Game newGame = new Game();
    private ListDataProvider<GamePlayer> playerProvider = new ListDataProvider<GamePlayer>();
    private int userID = 0;

    private ProvidesKey<Board> boardKeyProvider = new ProvidesKey<Board>()
    {
        @Override
        public Object getKey(Board item)
        {
            return item == null ? null : item.getBoardSettings().getId();
        }
    };
    private SimplePanel panelBoardPreview;
    private Label lblSelectedBoard;

    public NewHotseatGame()
    {
        final SingleSelectionModel selectionModel = new SingleSelectionModel<Board>();
        selectionModel
                .addSelectionChangeHandler(new SelectionChangeEvent.Handler()
                {
                    @Override
                    public void onSelectionChange(SelectionChangeEvent event)
                    {
                        if (selectionModel.getSelectedObject() != null)
                        {
                            Board selectedBoard = (Board) selectionModel
                                    .getSelectedObject();
                            int width = panelBoardPreview.getOffsetWidth();
                            int height = panelBoardPreview.getOffsetHeight();
                            BoardViewerWidget widget = new BoardViewerWidget(
                                    selectedBoard, width, height);
                            panelBoardPreview.setWidget(widget);
                            lblSelectedBoard.setText(selectedBoard
                                    .getBoardSettings().getName());
                        }
                        else
                        {
                            panelBoardPreview.setWidget(null);
                            lblSelectedBoard.setText("[none selected]");
                        }

                    }
                });

        ScrollPanel scrollPanel = new ScrollPanel();
        scrollPanel.setStyleName("newHotseatGame");
        initWidget(scrollPanel);

        VerticalPanel verticalPanel_3 = new VerticalPanel();
        scrollPanel.setWidget(verticalPanel_3);
        verticalPanel_3.setSize("100%", "100%");

        HorizontalPanel horizontalPanel = new HorizontalPanel();
        verticalPanel_3.add(horizontalPanel);

        Label lblNewHotseatGame = new Label("New hotseat game");
        lblNewHotseatGame.setStyleName("label-title");
        horizontalPanel.add(lblNewHotseatGame);

        HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
        verticalPanel_3.add(horizontalPanel_1);

        VerticalPanel verticalPanel_1 = new VerticalPanel();
        verticalPanel_1.setSpacing(5);
        horizontalPanel_1.add(verticalPanel_1);
        verticalPanel_1.setWidth("439px");

        Label lblPlayers = new Label("Players");
        lblPlayers.setStyleName("header-label");
        verticalPanel_1.add(lblPlayers);

        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.setStyleName("common-panel");
        verticalPanel_1.add(verticalPanel);

        HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
        verticalPanel.add(horizontalPanel_2);
        horizontalPanel_2.setSpacing(5);

        final TextBox textBox = new TextBox();
        textBox.addKeyUpHandler(new KeyUpHandler()
        {
            public void onKeyUp(KeyUpEvent event)
            {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER)
                    addPlayer(textBox.getText());
            }

        });
        horizontalPanel_2.add(textBox);

        Button button = new Button("New button");
        button.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                addPlayer(textBox.getText());
            }
        });
        button.setText("add player");
        horizontalPanel_2.add(button);

        HorizontalPanel horizontalPanel_4 = new HorizontalPanel();
        verticalPanel.add(horizontalPanel_4);
        horizontalPanel_4.setSpacing(5);

        final ListBox comboBox = new ListBox();
        horizontalPanel_4.add(comboBox);
        comboBox.setWidth("201px");

        Button button_1 = new Button("New button");
        button_1.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                int selectedIndex = comboBox.getSelectedIndex();
                addBot(GameUtils.getAllBots().get(selectedIndex));
            }

        });
        horizontalPanel_4.add(button_1);
        button_1.setText("add bot");

        CellTable<GamePlayer> celltablePlayers = new CellTable<GamePlayer>();
        verticalPanel.add(celltablePlayers);

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
                return object.getBot() == null ? Resources.icons()
                        .playerSmall() : Resources.icons().botSmall();
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
                playerProvider.getList().remove(object);
            }
        });
        celltablePlayers.addColumn(columnRemove, "Remove");
        celltablePlayers.setWidth("100%");

        playerProvider.addDataDisplay(celltablePlayers);

        VerticalPanel verticalPanel_2 = new VerticalPanel();
        verticalPanel_3.add(verticalPanel_2);
        verticalPanel_2.setSpacing(5);
        verticalPanel_2.setWidth("100%");

        HorizontalPanel horizontalPanel_5 = new HorizontalPanel();
        horizontalPanel_5
                .setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        verticalPanel_2.add(horizontalPanel_5);

        Label lblBoard = new Label("Board ");
        horizontalPanel_5.add(lblBoard);
        lblBoard.setStyleName("header-label");

        lblSelectedBoard = new Label("[none selected]");
        horizontalPanel_5.add(lblSelectedBoard);

        VerticalPanel verticalPanel_4 = new VerticalPanel();
        verticalPanel_2.add(verticalPanel_4);

        HorizontalPanel horizontalPanel_3 = new HorizontalPanel();
        horizontalPanel_3.setStyleName("common-panel");
        verticalPanel_4.add(horizontalPanel_3);
        horizontalPanel_3.setHeight("");

        cellTable = new CellTable<Board>(boardKeyProvider);

        cellTable.setSelectionModel(selectionModel);

        TextColumn nameColumn = new TextColumn<Board>()
        {
            @Override
            public String getValue(Board object)
            {
                return object.getBoardSettings().getName();
            }
        };
        cellTable.addColumn(nameColumn, "Name");

        TextColumn amountPlayersColumn = new TextColumn<Board>()
        {
            @Override
            public String getValue(Board object)
            {
                if (object.getBoardSettings().getMinPlayers() == object
                        .getBoardSettings().getMaxPlayers())
                {
                    return Integer.toString(object.getBoardSettings()
                            .getMinPlayers());
                }
                else
                {
                    return object.getBoardSettings().getMinPlayers() + " - "
                            + object.getBoardSettings().getMaxPlayers();
                }
            }
        };
        cellTable.addColumn(amountPlayersColumn, "# players");

        Column<Board, ?> columnVP = new Column<Board, Number>(new NumberCell())
        {
            @Override
            public Number getValue(Board object)
            {
                return object.getBoardSettings().getVpToWin();
            }
        };
        cellTable.addColumn(columnVP, "Points");

        TextColumn designerColumn = new TextColumn<Board>()
        {
            @Override
            public String getValue(Board object)
            {
                return object.getBoardSettings().getDesigner();
            }
        };
        cellTable.addColumn(designerColumn, "Designer");
        horizontalPanel_3.add(cellTable);
        cellTable.setWidth("15em");

        panelBoardPreview = new SimplePanel();
        horizontalPanel_3.add(panelBoardPreview);
        panelBoardPreview.setSize("500px", "500px");

        cellTable.setRowCount(boards.size());
        cellTable.setRowData(0, boards);

        HorizontalPanel panelOkCancel = new HorizontalPanel();
        verticalPanel_3.add(panelOkCancel);
        panelOkCancel.setWidth("100%");
        panelOkCancel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        panelOkCancel.setSpacing(10);
        panelOkCancel
                .setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

        Button buttonCancel = new Button("New button");
        buttonCancel.setStyleName("cancel-button");
        buttonCancel.setText("Cancel & leave");
        panelOkCancel.add(buttonCancel);

        Button buttonOK = new Button("New button");
        buttonOK.setStyleName("ok-button");
        buttonOK.setText("OK & start");
        panelOkCancel.add(buttonOK);

        for (Class bot : GameUtils.getAllBots())
            comboBox.addItem(ClassUtils.getSimpleClassName(bot.getName()));
    }

    private void addPlayer(String text)
    {
        userID++;
        GamePlayerImpl newPlayer = new GamePlayerImpl();
        Player user = new Player();
        user.setName(text);
        user.setId(userID);
        newPlayer.setUser(user);
        playerProvider.getList().add(newPlayer);
    }

    private void addBot(Class<? extends Bot> botType)
    {
        userID++;
        GamePlayerImpl newPlayer = new GamePlayerImpl();
        Bot newBot = GameUtils.createBot(botType, newGame, newPlayer,
                new ClientRandom());
        newBot.setId(userID);
        newPlayer.setBot(newBot);
        playerProvider.getList().add(newPlayer);
    }
}
