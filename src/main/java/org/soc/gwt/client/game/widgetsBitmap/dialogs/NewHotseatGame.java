package org.soc.gwt.client.game.widgetsBitmap.dialogs;

import org.soc.common.game.Game;
import org.soc.common.game.GamePlayer;
import org.soc.common.game.GamePlayer.GamePlayerImpl;
import org.soc.common.game.Random.ClientRandom;
import org.soc.common.game.GameUtils;
import org.soc.common.game.board.Board;
import org.soc.common.game.bots.Bot;
import org.soc.common.server.entities.User.Player;
import org.soc.common.utils.Util;
import org.soc.common.views.widgetsInterface.generic.BoardChangedEvent;
import org.soc.common.views.widgetsInterface.generic.BoardChangedEvent.BoardChangedHandler;
import org.soc.common.views.widgetsInterface.generic.BoardPickerWidget;
import org.soc.common.views.widgetsInterface.generic.LimitedColorPicker;
import org.soc.gwt.client.game.widgetsBitmap.generic.BoardPickerBitmapWidget;
import org.soc.gwt.client.game.widgetsBitmap.generic.LimitedColorPickerBitmapWidget;
import org.soc.gwt.client.game.widgetsBitmap.generic.LimitedColorPickerBitmapWidget.OnColorChanged;
import org.soc.gwt.client.game.widgetsBitmap.generic.PlayerListWidget;
import org.soc.gwt.client.game.widgetsBitmap.generic.PlayerListWidgetImpl;
import org.soc.gwt.client.game.widgetsBitmap.generic.PlayersChangedEvent;
import org.soc.gwt.client.game.widgetsBitmap.generic.PlayersChangedEvent.PlayersChangedHandler;
import org.soc.gwt.client.images.R;
import org.soc.gwt.client.main.MainWindow;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.cellview.client.CellTable;
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

public class NewHotseatGame extends Composite implements OnColorChanged,
        BoardChangedHandler, PlayersChangedHandler
{
  private Game newGame = new Game();
  private int userID = 0;
  private TextBox textboxPlayerName;
  private Button buttonAddPlayer;
  private Button buttonStartGame;
  private MainWindow mainWindow;
  private LimitedColorPicker limitedColorPicker = new LimitedColorPickerBitmapWidget();
  private BoardPickerWidget boardPicker = new BoardPickerBitmapWidget();
  private PlayerListWidget playerListWidget = new PlayerListWidgetImpl();
  private Label lblSelectedBoard;

  /** @wbp.parser.constructor */
  public NewHotseatGame()
  {
    ScrollPanel scrollPanel = new ScrollPanel();
    scrollPanel.setStyleName("newHotseatGame");
    initWidget(scrollPanel);
    scrollPanel.setSize("562px", "100%");
    VerticalPanel panelRoot = new VerticalPanel();
    scrollPanel.setWidget(panelRoot);
    panelRoot.setSize("100%", "100%");
    HorizontalPanel panelTitle = new HorizontalPanel();
    panelRoot.add(panelTitle);
    Label lblNewHotseatGame = new Label("New hotseat game");
    lblNewHotseatGame.setStyleName("label-title");
    panelTitle.add(lblNewHotseatGame);
    VerticalPanel panelBoards = new VerticalPanel();
    panelRoot.add(panelBoards);
    panelBoards.setSpacing(5);
    panelBoards.setWidth("100%");
    HorizontalPanel panelBoardTitle = new HorizontalPanel();
    panelBoardTitle.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
    panelBoards.add(panelBoardTitle);
    Label lblBoard = new Label("Board ");
    panelBoardTitle.add(lblBoard);
    lblBoard.setStyleName("header-label");
    HorizontalPanel panelAddPlayer = new HorizontalPanel();
    panelAddPlayer.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
    panelAddPlayer.setSpacing(5);
    lblSelectedBoard = new Label("[none selected]");
    panelBoardTitle.add(lblSelectedBoard);
    panelRoot.add(boardPicker);
    VerticalPanel verticalPanel_4 = new VerticalPanel();
    panelBoards.add(verticalPanel_4);
    HorizontalPanel panelPlayers = new HorizontalPanel();
    panelRoot.add(panelPlayers);
    VerticalPanel panelPlayersTitle = new VerticalPanel();
    panelPlayersTitle.setSpacing(5);
    panelPlayers.add(panelPlayersTitle);
    panelPlayersTitle.setWidth("100%");
    Label lblPlayers = new Label("Players");
    lblPlayers.setStyleName("header-label");
    panelPlayersTitle.add(lblPlayers);
    panelPlayers.add(playerListWidget);
    // panelPlayersTitle.add(verticalPanel);
    textboxPlayerName = new TextBox();
    textboxPlayerName.setText("Myself");
    textboxPlayerName.addKeyDownHandler(new KeyDownHandler()
    {
      @Override public void onKeyDown(KeyDownEvent event)
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
    VerticalPanel playersPanel = new VerticalPanel();
    Image image = new Image(R.icons().player32());
    panelAddPlayer.add(image);
    panelAddPlayer.add(textboxPlayerName);
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
    panelAddPlayer.add(limitedColorPicker);
    buttonAddPlayer.setText("add player");
    panelAddPlayer.add(buttonAddPlayer);
    HorizontalPanel panelAddBot = new HorizontalPanel();
    panelAddBot.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
    panelAddBot.setSpacing(5);
    Image imageBot = new Image(R.icons().bot32());
    panelAddBot.add(imageBot);
    final ListBox comboBox = new ListBox();
    panelAddBot.add(comboBox);
    comboBox.setWidth("201px");
    Button buttonAddBot = new Button("add bot");
    buttonAddBot.addClickHandler(new ClickHandler()
    {
      public void onClick(ClickEvent event)
      {
        int selectedIndex = comboBox.getSelectedIndex();
        addBot(GameUtils.getAllBots().get(selectedIndex));
      }
    });
    panelAddBot.add(buttonAddBot);
    playersPanel.add(panelAddBot);
    playersPanel.add(panelAddPlayer);
    panelPlayers.add(playersPanel);
    CellTable<GamePlayer> celltablePlayers = new CellTable<GamePlayer>(8);
    celltablePlayers.setFocus(false);
    HorizontalPanel panelOkCancel = new HorizontalPanel();
    panelRoot.add(panelOkCancel);
    panelOkCancel.setWidth("100%");
    panelOkCancel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
    panelOkCancel.setSpacing(10);
    panelOkCancel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
    Button buttonCancel = new Button("New button");
    buttonCancel.setStyleName("cancel-button");
    buttonCancel.setText("Cancel & leave");
    panelOkCancel.add(buttonCancel);
    buttonStartGame = new Button("New button");
    buttonStartGame.setStyleName("ok-button");
    buttonStartGame.setText("OK & start");
    buttonStartGame.addClickHandler(new ClickHandler()
    {
      @Override public void onClick(ClickEvent event)
      {
        if (canStartGame())
          startGame();
      }
    });
    panelOkCancel.add(buttonStartGame);
    boardPicker.addBoardChangedHandler(this);
    for (Class bot : GameUtils.getAllBots())
      comboBox.addItem(Util.shortName(bot.getClass()));
    playerListWidget.addPlayersChangedHandler(this);
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
    playerListWidget.addPlayer(newPlayer);
    limitedColorPicker.setCurrentSelectedColorDisabled();
    limitedColorPicker.selectNextColorIfAvailable();
    updateUI();
  }
  private boolean canAddPlayerOrBot()
  {
    return boardPicker.hasBoardSelected()
            && boardPicker.selectedBoard().getBoardSettings()
                    .amountPlayers().amountPlayers() > playerListWidget
                    .amountPlayers();
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
    playerListWidget.addPlayer(newPlayer);
    limitedColorPicker.disableColor(color);
    limitedColorPicker.selectNextColorIfAvailable();
    updateUI();
  }
  private boolean canAddPlayer()
  {
    // User can be added when there is a name, the name is unique,
    // the color is selected and the color is unique.
    if (textboxPlayerName.getText().length() > 0
            && limitedColorPicker.isColorSelected()
            && isUniqueName() && isSelectedColor()
            && canAddPlayerOrBot())
    {
      buttonAddPlayer.setEnabled(true);
      return true;
    } else
    {
      buttonAddPlayer.setEnabled(false);
      return false;
    }
  }
  private boolean isUniqueName()
  {
    String name = textboxPlayerName.getText().toLowerCase();
    for (GamePlayer player : playerListWidget.getPlayers())
      if (player.user().name().toLowerCase().equals(name))
        return false;
    return true;
  }
  private boolean hasEnoughPlayers()
  {
    int numPlayers = playerListWidget.amountPlayers();
    Board selectedBoard = boardPicker.selectedBoard();
    return selectedBoard.getBoardSettings().amountPlayers()
            .amountPlayers() == numPlayers;
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
    for (GamePlayer player : playerListWidget.getPlayers())
      if (player.color().toLowerCase().equals(color))
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
    newGame.setBoard(boardPicker.selectedBoard());
    for (GamePlayer player : playerListWidget.getPlayers())
      newGame.players().add(player);
    mainWindow.getHotseatGame().startGame(newGame);
    mainWindow.setCurrentWidget(mainWindow.getHotseatGame());
  }
  @Override public void onColorChanged()
  {
    canAddPlayer();
  }
  @Override public void onBoardChanged(BoardChangedEvent event)
  {
    if (event.getNewBoard() != null)
      lblSelectedBoard.setText(event.getNewBoard().name());
    else
      lblSelectedBoard.setText("[none selected]");
    canAddPlayer();
  }
  @Override public void onPlayersChanged(PlayersChangedEvent event)
  {
    if (event.getPlayerRemoved() != null)
      limitedColorPicker.enableColor(event.getPlayerRemoved().color());
    updateUI();
  }
}
