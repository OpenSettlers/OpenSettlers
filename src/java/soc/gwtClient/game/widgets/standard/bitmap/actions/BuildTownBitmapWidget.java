package soc.gwtClient.game.widgets.standard.bitmap.actions;

import soc.common.actions.gameAction.turnActions.standard.BuildTown;
import soc.common.board.pieces.PiecesChangedEvent;
import soc.common.board.pieces.PiecesChangedEventHandler;
import soc.common.board.pieces.Town;
import soc.common.board.resources.ResourcesChangedEvent;
import soc.common.board.resources.ResourcesChangedEventHandler;
import soc.common.game.Game;
import soc.common.game.GamePhaseChangedEvent;
import soc.common.game.GamePhaseChangedEventHandler;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.AbstractActionWidget;
import soc.gwtClient.game.abstractWidgets.IGamePanel;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class BuildTownBitmapWidget extends AbstractActionWidget implements
        ResourcesChangedEventHandler, PiecesChangedEventHandler,
        GamePhaseChangedEventHandler
{
    private AbsolutePanel absolutePanel = new AbsolutePanel();
    private VerticalPanel tradesPanel1 = new VerticalPanel();
    private VerticalPanel tradesPanel2 = new VerticalPanel();
    private PushButton btnBuildTown = new PushButton(new Image(Resources
            .icons().town()));
    private Town town = new Town();
    private Image trade1 = new Image(Resources.icons().trade());
    private Image trade2 = new Image(Resources.icons().trade());
    private Image trade3 = new Image(Resources.icons().trade());
    private Image trade4 = new Image(Resources.icons().trade());
    private BuildTown buildTown = new BuildTown();

    public BuildTownBitmapWidget(IGamePanel gamePanel, GamePlayer player)
    {
        super(gamePanel, player);
        absolutePanel.setSize("60px", "60px");

        buildTown.setPlayer(player);

        player.getResources().addResourcesChangedEventHandler(this);
        player.getStock().addPiecesChangedEventHandler(this);
        gamePanel.getGame().addGamePhaseChangedEventHandler(this);

        tradesPanel1.add(trade1);
        tradesPanel1.add(trade2);
        tradesPanel1.add(trade3);
        tradesPanel2.add(trade4);

        absolutePanel.add(btnBuildTown, 0, 0);
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

    private void checkEnabled()
    {
        if (onTurn)
        {
            Game game = gamePanel.getGame();

            if (game.getCurrentPhase().isAllowed(buildTown) && // current phase
                    // must be OK
                    town.canBuild(game.getBoard(), player) && // we need space
                    town.canPay(player)) // we need resources
            {
                setEnabled(true);
                setTradesPanelsVisible(true);
                setTradesNeededToBuild();
                return;
            }
        }
        setEnabled(false);
        setTradesPanelsVisible(false);
    }

    private void setTradesPanelsVisible(boolean visible)
    {
        tradesPanel1.setVisible(visible);
        tradesPanel2.setVisible(visible);
    }

    @Override
    protected void updateEnabled()
    {
        btnBuildTown.setEnabled(enabled);
    }

    private void setTradesNeededToBuild()
    {
        int amountTradesNeeded = player.getResources().getNeededResources(
                town.getCost()).size();
        trade1.setVisible(amountTradesNeeded >= 1);
        trade2.setVisible(amountTradesNeeded >= 2);
        trade3.setVisible(amountTradesNeeded >= 3);
        trade4.setVisible(amountTradesNeeded >= 4);
    }
}
