package soc.gwtClient.game.widgetsBitmap.main;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turns.HostStartsGame;
import soc.common.game.Game;
import soc.common.game.TurnChangedEvent;
import soc.common.game.TurnChangedEventHandler;
import soc.common.server.HotSeatServer;
import soc.gwtClient.game.behaviour.gameBoard.DisabledMap;
import soc.gwtClient.game.widgetsAbstract.main.AbstractGameWidget;
import soc.gwtClient.game.widgetsBitmap.actions.HotSeatActionsPlayersWidget;
import soc.gwtClient.game.widgetsInterface.actions.ActionsWidget;
import soc.gwtClient.game.widgetsInterface.dialogs.TradePlayerDialog;
import soc.gwtClient.game.widgetsInterface.main.GameWidgetFactory;
import soc.gwtClient.game.widgetsInterface.main.PlayerStuffWidget;
import soc.gwtClient.main.CenterWidget;

import com.google.gwt.user.client.ui.Panel;

public class HotSeatGameWidget extends AbstractGameWidget implements
        CenterWidget, TurnChangedEventHandler
{
    private HotSeatActionsPlayersWidget playersActionsWidget;
    private HotSeatTradePlayersWidget hotseatTradePlayersWidget;

    public HotSeatGameWidget()
    {
        super();

        server = new HotSeatServer(this);
    }

    public void startGame(Game game)
    {
        HostStartsGame start = new HostStartsGame();
        start.setGame(game);
        start.setPlayer(game.getPlayers().get(0));

        server.sendAction(start);
    }

    @Override
    protected void initialize()
    {
        super.initialize();

        GameWidgetFactory gameWidgetFactory = gameWidgetLayoutWidget
                .getGameWidgetFactory();

        looseCardsDialog = gameWidgetFactory.createHotseatLooseCardsDialog();

        playersActionsWidget = gameWidgetFactory
                .createHotSeatActionsPlayersWidget();

        hotseatTradePlayersWidget = gameWidgetFactory
                .createHotSeatTradePlayersWidget();

        gameWidgetLayoutWidget.initialize();
        game.addTurnChangedEventHandler(this);
        boardVisualWidget.getBoardVisual().setBehaviour(new DisabledMap());
        boardVisualWidget.getBoardVisual().hideTerritories();
    }

    @Override
    public Panel getRootWidget()
    {
        return gameWidgetLayoutWidget.getRootPanel();
    }

    @Override
    public void receive(GameAction gameAction)
    {
        // Only initialize the rest of the ui when host started the game
        if (gameAction instanceof HostStartsGame)
        {
            HostStartsGame hostStartsGame = (HostStartsGame) gameAction;
            game = hostStartsGame.getGame();
            player = game.getPlayers().get(0);
            initialize();
        }

        super.receive(gameAction);
    }

    @Override
    public void onTurnChanged(TurnChangedEvent event)
    {
        // Hotseat, so when the turn changes, the current player is set to the
        // new player on turn
        if (event.getNewTurn().getPlayer().getBot() == null)
            player = event.getNewTurn().getPlayer();

        if (player.getBot() == null)
            playersActionsWidget.setPlayer(player);
    }

    @Override
    public GameWidgetFactory createGameWidgetFactory()
    {
        return new GameBitmapWidgetFactory(this);
    }

    /*
     * Returns the ActionWidget of the player currently on turn
     * 
     * @see soc.gwtClient.game.abstractWidgets.GamePanel#getActionsWidget()
     */
    @Override
    public ActionsWidget getActionsWidget()
    {
        return playersActionsWidget.getCurrentActionsWidget();
    }

    @Override
    public TradePlayerDialog getTradePlayerUI()
    {
        return hotseatTradePlayersWidget.getPlayerTradeUI(player);
    }

    @Override
    public PlayerStuffWidget getPlayerStuffWidget()
    {
        return playersActionsWidget;
    }

}
