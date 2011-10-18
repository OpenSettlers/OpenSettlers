package org.soc.gwt.client.game.widgetsAbstract.main;

import org.soc.common.game.*;
import org.soc.common.game.actions.*;
import org.soc.common.server.*;
import org.soc.common.server.GameServer.GameServerCallback;
import org.soc.common.views.widgetsInterface.actions.*;
import org.soc.common.views.widgetsInterface.dialogs.*;
import org.soc.common.views.widgetsInterface.generic.*;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.common.views.widgetsInterface.playerInfo.*;
import org.soc.gwt.client.game.*;
import org.soc.gwt.client.game.widgetsBitmap.main.*;

import com.google.gwt.user.client.ui.*;

public abstract class AbstractGameWidget implements GameWidget, CenterWidget,
        GameServerCallback
{
  // Variables for the game panel
  protected GameServer server;
  protected Game game;
  protected GameBehaviour gameBehaviour;
  protected GamePlayer player;
  // Factory to create various stuff
  protected ClientFactory clientFactory;
  // Left-bottom tab panel
  protected HistoryWidget historyWidget;
  protected ChatWidget chatPanel;
  protected QueueWidget queueWidget;
  protected DebugWidget debugWidget;
  protected GameDetailsWidget gameDetailsWidget;
  protected GamePanelLayoutWidget gameWidgetLayoutWidget;
  // Generic info boxes
  protected PlayersInfoWidget playersWidget;
  protected StatusWidget statusWidget;
  protected BoardVisualWidget boardVisualWidget;
  protected BankStockWidget bankStockPanel;
  // In-game panels to provide turns funtionality
  protected ResourcesGainedWidget resourcesGainedWidget;
  protected StealCardWidget stealCardDialog;
  protected BankTradeWidget bankTradeWidget;
  protected LooseCardsDialog looseCardsDialog;
  protected GameOverDialog gameOverDialog;
  protected TradePlayerDialog tradePlayerDialog;
  protected ActionsWidget actionsWidget;
  protected PlayerStuffWidget playerStuff;
  protected DetailContainerManagerImpl detailContainerManager;
  protected ToolTipManager toolTipManager;

  public AbstractGameWidget()
  {
    clientFactory = new DefaultClientFactory(this);
  }
  protected void initialize()
  {
    player = game.players().get(0);
    gameWidgetLayoutWidget = new DesktopGamePanelLayout(this);
    GameWidgetFactory gameWidgetFactory = clientFactory
            .getGameWidgetFactory();
    bankStockPanel = gameWidgetFactory.createBankStockWidget();
    playersWidget = gameWidgetFactory.createPlayersWidget();
    boardVisualWidget = gameWidgetFactory.createBoardVisualWidget();
    statusWidget = gameWidgetFactory.createStatusDiceWidget();
    historyWidget = gameWidgetFactory.createHistoryWidget();
    chatPanel = gameWidgetFactory.createChatWidget();
    debugWidget = gameWidgetFactory.createDebugWidget();
    queueWidget = gameWidgetFactory.createQueueWidget();
    bankTradeWidget = gameWidgetFactory.createBankTradeWidget();
    stealCardDialog = gameWidgetFactory.createStealCardWidget(player);
    resourcesGainedWidget = gameWidgetFactory.createResourcesGainedWidget();
    gameDetailsWidget = gameWidgetFactory.createDetailsWidget();
    looseCardsDialog = gameWidgetFactory.createLooseCardsDialog();
    chatPanel = gameWidgetFactory.createChatWidget();
    queueWidget = gameWidgetFactory.createQueueWidget();
    debugWidget = gameWidgetFactory.createDebugWidget();
    detailContainerManager = new DetailContainerManagerImpl(this);
    toolTipManager = new ToolTipManagerImpl();
  }
  /* (non-Javadoc)
   * 
   * @see org.soc.gwt.client.main.CenterWidget#getRootWidget() */
  @Override public Widget getRootWidget()
  {
    return gameWidgetLayoutWidget.getRootPanel();
  }
  @Override public HistoryWidget getGameHistoryWidget()
  {
    return historyWidget;
  }
  @Override public BankStockWidget getbankStockPanel()
  {
    return bankStockPanel;
  }
  @Override public DetailContainerManagerImpl detailWidgets()
  {
    return detailContainerManager;
  }
  @Override public ResourcesGainedWidget resourcesGainedWidget()
  {
    return resourcesGainedWidget;
  }
  @Override public StealCardWidget stealCardWidget()
  {
    return stealCardDialog;
  }
  @Override public BankTradeWidget bankTradeDialog()
  {
    return bankTradeWidget;
  }
  @Override public BoardVisualWidget boardVisualWidget()
  {
    return boardVisualWidget;
  }
  @Override public PlayersInfoWidget getPlayersInfoWidget()
  {
    return playersWidget;
  }
  @Override public GameOverDialog getGameOverDialog()
  {
    return gameOverDialog;
  }
  public BankStockWidget getBankStockPanel()
  {
    return bankStockPanel;
  }
  @Override public LooseCardsDialog getLooseCardsDialog()
  {
    return looseCardsDialog;
  }
  @Override public GameDetailsWidget getDetailsWidget()
  {
    return gameDetailsWidget;
  }
  @Override public DebugWidget getDebugPanel()
  {
    return debugWidget;
  }
  @Override public StatusWidget getStatusWidget()
  {
    return statusWidget;
  }
  @Override public ToolTipManager toolTipManager()
  {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public Game game()
  {
    return game;
  }
  public void doAction(GameAction gameAction)
  {
    server.sendAction(gameAction);
  }
  @Override public GamePlayer playingPlayer()
  {
    return player;
  }
  @Override public ActionsWidget actionsWidget()
  {
    return actionsWidget;
  }
  @Override public PlayerStuffWidget getPlayerStuffWidget()
  {
    return playerStuff;
  }
  @Override public TradePlayerDialog getTradePlayerUI()
  {
    return tradePlayerDialog;
  }
  /** @return the clientFactory */
  public ClientFactory clientFactory()
  {
    return clientFactory;
  }
  @Override public void startAction(GameAction action)
  {
    // Check if there is code forgetting to set the player in the spawned
    // GameAction
    if (action.player() == null)
    {
      action.setPlayer(player);
    }
    // Create a behaviour based on our action
    GameBehaviour gameBehaviour = action.begin(null);
    if (gameBehaviour != null)
    {
      // Tell our GameVisual it should display the behaviour
      gameBehaviour.start(this);
      return;
    }
    // Simply send the action
    doAction(action);
  }
  public void showTradeBankPanel()
  {
    Point2D location = playersWidget.getTopRightLocation();
    bankTradeWidget.setPopupPosition(location.getX(), location.getY());
    bankTradeWidget.show();
  }
  /** Receive an action from the game server Check if there's any behaviour associated with receiving */
  @Override public void receive(GameAction gameAction) {
    GameBehaviour newBehaviour;
    // Grab new game behaviour for received action
    if (gameAction.player().equals(player))
      newBehaviour = gameAction.received(this);
    else
      newBehaviour = gameAction.opponentReceived(this);
    if (newBehaviour != null) {
      setNewGameBehaviour(newBehaviour);
      if (!newBehaviour.endsManually())
        setBehaviourForNextAction();
    }
    else
      setBehaviourForNextAction();
  }
  @Override public void doneReceiving() {
    setBehaviourForNextAction();
  }
  /* Checks if another GameAction resides on the queue. If so, it looks for associated behaviour and
   * sets it accordingly. */
  protected void setBehaviourForNextAction() {
    // Grab the next action on the queue
    GameAction next = game.actionsQueue().blockingActions(player);
    // Make sure the next action is meant to be played with the player
    // playing in this GamePanel
    if (next != null && next.player().equals(player)) {
      // Create new behaviour and set it when available
      GameBehaviour newBehaviour = next.next(this);
      if (newBehaviour != null)
        setNewGameBehaviour(newBehaviour);
    }
  }
  /** Updates the current behaviour to given behaviour */
  protected void setNewGameBehaviour(GameBehaviour newGameBehaviour) {
    // Finish old state
    if (gameBehaviour != null)
      gameBehaviour.finish();
    // Make new state
    gameBehaviour = newGameBehaviour;
    // Set state to start
    gameBehaviour.start(this);
  }
  @Override public Point2D getTopRightPlayerInfoBoxPosition(GamePlayer player) {
    return playersWidget.getTopRightLocation(player);
  }
  @Override public void blockUI() {
    actionsWidget().setEnabled(false);
  }
  @Override public void unBlockUI() {
    actionsWidget().setEnabled(true);
  }
}
