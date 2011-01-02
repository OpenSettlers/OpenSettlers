package soc.gwtClient.game.widgets.standard.bitmap.actions;

import soc.common.actions.gameAction.turnActions.standard.BuildCity;
import soc.common.board.pieces.City;
import soc.common.board.pieces.PiecesChangedEvent;
import soc.common.board.pieces.PiecesChangedEventHandler;
import soc.common.board.resources.ResourcesChangedEvent;
import soc.common.board.resources.ResourcesChangedEventHandler;
import soc.common.game.Game;
import soc.common.game.GamePhaseChangedEvent;
import soc.common.game.GamePhaseChangedEventHandler;
import soc.common.game.GamePlayer;
import soc.gwtClient.game.abstractWidgets.AbstractActionWidget;
import soc.gwtClient.game.abstractWidgets.IGamePanel;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class BuildCityBitmapWidget extends AbstractActionWidget implements
        ResourcesChangedEventHandler, PiecesChangedEventHandler,
        GamePhaseChangedEventHandler
{
    private AbsolutePanel absolutePanel = new AbsolutePanel();
    private VerticalPanel tradesPanel1 = new VerticalPanel();
    private VerticalPanel tradesPanel2 = new VerticalPanel();
    private PushButton btnCity = new PushButton(new Image(Resources.icons()
            .city()));
    private City city = new City();
    private Image trade1 = new Image(Resources.icons().trade());
    private Image trade2 = new Image(Resources.icons().trade());
    private Image trade3 = new Image(Resources.icons().trade());
    private Image trade4 = new Image(Resources.icons().trade());
    private Image trade5 = new Image(Resources.icons().trade());
    private BuildCity buildCity = new BuildCity();

    public BuildCityBitmapWidget(IGamePanel gamePanel, GamePlayer player)
    {
        super(gamePanel, player);

        absolutePanel.setSize("60px", "60px");
        buildCity.setPlayer(player);

        player.getResources().addResourcesChangedEventHandler(this);
        player.getStock().addPiecesChangedEventHandler(this);
        gamePanel.getGame().addGamePhaseChangedEventHandler(this);

        tradesPanel1.add(trade1);
        tradesPanel1.add(trade2);
        tradesPanel1.add(trade3);
        tradesPanel2.add(trade4);
        tradesPanel2.add(trade5);

        absolutePanel.add(btnCity, 0, 0);
        absolutePanel.add(tradesPanel1, 3, 3);
        absolutePanel.add(tradesPanel2, 19, 3);
    }

    @Override
    public Widget asWidget()
    {
        return absolutePanel;
    }

    @Override
    public void onResourcesChanged(ResourcesChangedEvent resourcesChanged)
    {
        checkEnabled();
    }

    @Override
    public void onPiecesChanged(PiecesChangedEvent list)
    {
        checkEnabled();
    }

    @Override
    public void onGamePhaseChanged(GamePhaseChangedEvent event)
    {
        checkEnabled();
    }

    @Override
    protected void updateEnabled()
    {
        btnCity.setEnabled(enabled);
    }

    private void checkEnabled()
    {
        if (onTurn)
        {
            Game game = gamePanel.getGame();

            if (game.getCurrentPhase().isAllowed(buildCity) && // current phase
                    // must be OK
                    city.canBuild(game.getBoard(), player) && // we need space
                    city.canPay(player)) // we need resources
            {
                setEnabled(true);
                setTradesPanelsVisible(true);
                setTradesNeededToBuild();
                return;
            }
        }
        setTradesPanelsVisible(false);
        setEnabled(false);
    }

    private void setTradesPanelsVisible(boolean visible)
    {
        tradesPanel1.setVisible(visible);
        tradesPanel2.setVisible(visible);
    }

    private void setTradesNeededToBuild()
    {
        int amountTradesNeeded = player.getResources().getNeededResources(
                city.getCost()).size();
        trade1.setVisible(amountTradesNeeded >= 1);
        trade2.setVisible(amountTradesNeeded >= 2);
        trade3.setVisible(amountTradesNeeded >= 3);
        trade4.setVisible(amountTradesNeeded >= 4);
        trade5.setVisible(amountTradesNeeded >= 5);
    }
}
