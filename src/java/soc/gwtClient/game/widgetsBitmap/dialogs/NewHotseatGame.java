package soc.gwtClient.game.widgetsBitmap.dialogs;

import soc.common.board.Board;
import soc.common.bots.Bot;
import soc.common.game.Game;
import soc.common.game.GameUtils;
import soc.common.game.player.GamePlayer;
import soc.common.game.player.GamePlayerImpl;
import soc.common.server.entities.Player;
import soc.common.server.randomization.ClientRandom;
import soc.common.utils.ClassUtils;
import soc.gwtClient.game.widgetsBitmap.generic.BoardPickerBitmapWidget;
import soc.gwtClient.game.widgetsBitmap.generic.ColorCell;
import soc.gwtClient.game.widgetsBitmap.generic.LimitedColorPickerBitmapWidget;
import soc.gwtClient.game.widgetsBitmap.generic.LimitedColorPickerBitmapWidget.OnColorChanged;
import soc.gwtClient.game.widgetsInterface.generic.BoardChangedEvent;
import soc.gwtClient.game.widgetsInterface.generic.BoardChangedEventHandler;
import soc.gwtClient.game.widgetsInterface.generic.BoardPicker;
import soc.gwtClient.game.widgetsInterface.generic.LimitedColorPicker;
import soc.gwtClient.images.Resources;
import soc.gwtClient.main.MainWindow;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.ImageResourceCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
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
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

public class NewHotseatGame extends Composite implements OnColorChanged,
        BoardChangedEventHandler
{
    private Game newGame = new Game();
    private ListDataProvider<GamePlayer> playerProvider = new ListDataProvider<GamePlayer>();
    private int userID = 0;
    private TextBox textboxPlayerName;
    private Button buttonAddPlayer;
    private Label lblSelectedBoard;
    private Button buttonStartGame;
    private MainWindow mainWindow;
    private LimitedColorPicker limitedColorPicker = new LimitedColorPickerBitmapWidget();
    private BoardPicker boardPicker = new BoardPickerBitmapWidget();

    /**
     * @wbp.parser.constructor
     */
    public NewHotseatGame()
    {
        ScrollPanel scrollPanel = new ScrollPanel();
        scrollPanel.setStyleName("newHotseatGame");
        initWidget(scrollPanel);
        scrollPanel.setSize("100%", "100%");

        VerticalPanel verticalPanel_3 = new VerticalPanel();
        scrollPanel.setWidget(verticalPanel_3);
        verticalPanel_3.setSize("100%", "100%");

        HorizontalPanel horizontalPanel = new HorizontalPanel();
        verticalPanel_3.add(horizontalPanel);

        Label lblNewHotseatGame = new Label("New hotseat game");
        lblNewHotseatGame.setStyleName("label-title");
        horizontalPanel.add(lblNewHotseatGame);

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

        verticalPanel_3.add(boardPicker);

        VerticalPanel verticalPanel_4 = new VerticalPanel();
        verticalPanel_2.add(verticalPanel_4);

        HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
        verticalPanel_3.add(horizontalPanel_1);

        VerticalPanel verticalPanel_1 = new VerticalPanel();
        verticalPanel_1.setSpacing(5);
        horizontalPanel_1.add(verticalPanel_1);
        verticalPanel_1.setWidth("100%");

        Label lblPlayers = new Label("Players");
        lblPlayers.setStyleName("header-label");
        verticalPanel_1.add(lblPlayers);

        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.setStyleName("common-panel");
        verticalPanel_1.add(verticalPanel);
        verticalPanel.setWidth("100%");

        HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
        horizontalPanel_2
                .setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        verticalPanel.add(horizontalPanel_2);
        horizontalPanel_2.setSpacing(5);

        textboxPlayerName = new TextBox();
        textboxPlayerName.setText("Myself");
        textboxPlayerName.addKeyDownHandler(new KeyDownHandler()
        {
            @Override
            public void onKeyDown(KeyDownEvent event)
            {
                canAddPlayer();
            }
        });
        textboxPlayerName.addKeyUpHandler(new KeyUpHandler()
        {
            public void onKeyUp(KeyUpEvent event)
            {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER
                        && canAddPlayer())
                    addPlayer();
                else
                    canAddPlayer();
            }
        });

        Image image = new Image(Resources.icons().playerMedium());
        horizontalPanel_2.add(image);
        horizontalPanel_2.add(textboxPlayerName);

        buttonAddPlayer = new Button("New button");
        buttonAddPlayer.setEnabled(false);
        buttonAddPlayer.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                if (canAddPlayer())
                    addPlayer();
            }
        });

        horizontalPanel_2.add(limitedColorPicker);

        buttonAddPlayer.setText("add player");
        horizontalPanel_2.add(buttonAddPlayer);

        HorizontalPanel horizontalPanel_4 = new HorizontalPanel();
        horizontalPanel_4
                .setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        verticalPanel.add(horizontalPanel_4);
        horizontalPanel_4.setSpacing(5);

        Image image_1 = new Image(Resources.icons().botMedium());
        horizontalPanel_4.add(image_1);

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

        CellTable<GamePlayer> celltablePlayers = new CellTable<GamePlayer>(8);
        celltablePlayers.setFocus(false);
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

        playerProvider.addDataDisplay(celltablePlayers);

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

        buttonStartGame = new Button("New button");
        buttonStartGame.setStyleName("ok-button");
        buttonStartGame.setText("OK & start");
        buttonStartGame.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                if (canStartGame())
                    startGame();
            }
        });
        panelOkCancel.add(buttonStartGame);

        boardPicker.addBoardChangedHandler(this);

        for (Class bot : GameUtils.getAllBots())
            comboBox.addItem(ClassUtils.getSimpleClassName(bot.getName()));
    }

    public NewHotseatGame(MainWindow mainWindow)
    {
        this();
        this.mainWindow = mainWindow;
    }

    private void addPlayer()
    {
        userID++;
        GamePlayerImpl newPlayer = new GamePlayerImpl();
        newPlayer.setColor(limitedColorPicker.getSelectedColor());
        Player user = new Player();
        user.setName(textboxPlayerName.getText());
        user.setId(userID);
        newPlayer.setUser(user);
        playerProvider.getList().add(newPlayer);

        limitedColorPicker.setCurrentSelectedColorDisabled();
        limitedColorPicker.selectNextColorIfAvailable();
        updateUI();
    }

    private void removePlayer(GamePlayer playerToRemove)
    {
        limitedColorPicker.enableColor(playerToRemove.getColor());
        playerProvider.getList().remove(playerToRemove);

        updateUI();
    }

    private boolean canAddPlayerOrBot()
    {
        return boardPicker.hasBoardSelected()
                && boardPicker.getSelectedBoard().getBoardSettings()
                        .getMaxPlayers() > playerProvider.getList().size();
    }

    private void addBot(Class<? extends Bot> botType)
    {
        userID++;
        GamePlayerImpl newPlayer = new GamePlayerImpl();
        Bot newBot = GameUtils.createBot(botType, newGame, newPlayer,
                new ClientRandom());
        newBot.setId(userID);
        newPlayer.setBot(newBot);

        String color = limitedColorPicker.getAnyAvailableColor();
        newPlayer.setColor(color);
        playerProvider.getList().add(newPlayer);

        limitedColorPicker.disableColor(color);
        limitedColorPicker.selectNextColorIfAvailable();
        updateUI();
    }

    private boolean canAddPlayer()
    {
        // User can be added when there is a name, the name is unique,
        // the color is selected and the color is unique.
        if (textboxPlayerName.getText().length() > 0
                && limitedColorPicker.isColorSelected() && isUniqueName()
                && isSelectedColor() && canAddPlayerOrBot())
        {
            buttonAddPlayer.setEnabled(true);
            return true;
        }
        else
        {
            buttonAddPlayer.setEnabled(false);
            return false;
        }
    }

    private boolean isUniqueName()
    {
        String name = textboxPlayerName.getText().toLowerCase();
        for (GamePlayer player : playerProvider.getList())
            if (player.getUser().getName().toLowerCase().equals(name))
                return false;

        return true;
    }

    private boolean hasEnoughPlayers()
    {
        int numPlayers = playerProvider.getList().size();
        Board selectedBoard = boardPicker.getSelectedBoard();
        return selectedBoard.getBoardSettings().getMinPlayers() >= numPlayers
                && selectedBoard.getBoardSettings().getMaxPlayers() <= numPlayers;
    }

    private boolean canStartGame()
    {
        if (!boardPicker.hasBoardSelected())
            return false;

        if (!hasEnoughPlayers())
            return false;

        return true;
    }

    private boolean isSelectedColor()
    {
        String color = limitedColorPicker.getSelectedColor();
        for (GamePlayer player : playerProvider.getList())
            if (player.getColor().toLowerCase().equals(color))
                return false;

        return true;
    }

    private void updateUI()
    {
        setStartGameButton();
        canAddPlayer();
    }

    private void setStartGameButton()
    {
        buttonStartGame.setEnabled(canStartGame());
    }

    private void startGame()
    {
        newGame.setBoard(boardPicker.getSelectedBoard());
        for (GamePlayer player : playerProvider.getList())
            newGame.getPlayers().add(player);

        mainWindow.getHotseatGame().startGame(newGame);
        mainWindow.setCurrentWidget(mainWindow.getHotseatGame());
    }

    @Override
    public void onColorChanged()
    {
        canAddPlayer();
    }

    @Override
    public void onBoardChanged(BoardChangedEvent event)
    {
        if (event.getNewBoard() != null)
        {
            lblSelectedBoard.setText(event.getNewBoard().getBoardSettings()
                    .getName());
        }
        else
        {
            lblSelectedBoard.setText("[none selected]");
        }
    }
}
