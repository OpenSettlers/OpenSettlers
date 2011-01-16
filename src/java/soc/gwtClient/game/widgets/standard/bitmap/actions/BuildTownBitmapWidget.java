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
import soc.common.game.gamePhase.turnPhase.TurnPhaseChangedEvent;
import soc.common.game.gamePhase.turnPhase.TurnPhaseChangedHandler;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.AbstractActionWidget;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.images.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class BuildTownBitmapWidget extends AbstractActionWidget implements
        ResourcesChangedEventHandler, PiecesChangedEventHandler,
        GamePhaseChangedEventHandler, TurnPhaseChangedHandler
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

    public BuildTownBitmapWidget(final GamePanel gamePanel,
            final GamePlayer player)
    {
        super(gamePanel, player);
        absolutePanel.setSize("60px", "60px");

        buildTown.setPlayer(player);

        player.getResources().addResourcesChangedEventHandler(this);
        player.getStock().addPiecesChangedEventHandler(this);
        gamePanel.getGame().addGamePhaseChangedEventHandler(this);
        gamePanel.getGame().addTurnPhaseChangedHandler(this);

        tradesPanel1.add(trade1);
        tradesPanel1.add(trade2);
        tradesPanel1.add(trade3);
        tradesPanel2.add(trade4);

        absolutePanel.add(btnBuildTown, 0, 0);
        absolutePanel.add(tradesPanel1, 3, 3);
        absolutePanel.add(tradesPanel2, 19, 3);

        btnBuildTown.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                gamePanel.startAction(new BuildTown().setPlayer(player));
            }
        });
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
    public void onTurnPhaseChanged(TurnPhaseChangedEvent event)
    {
        checkEnabled();
    }

    private void checkEnabled()
    {
        setTradesNeededToBuild();
        if (enabled && player.isOnTurn())
        {
            Game game = gamePanel.getGame();

            if (game.isAllowed(buildTown) && // current phase
                    // must be OK
                    town.canBuild(game.getBoard(), player) && // we need space
                    town.canPay(player) && // we need resources
                    game.getBoard().getGraph().getTownCandidatesTurnPhase(
                            player).size() > 0)
            {
                enableUI();
                return;
            }
        }
        disableUI();
    }

    private void setTradesPanelsVisible(boolean visible)
    {
        tradesPanel1.setVisible(visible);
        tradesPanel2.setVisible(visible);
    }

    @Override
    protected void updateEnabled()
    {
        checkEnabled();
    }

    private void enableUI()
    {
        btnBuildTown.setEnabled(true);
        setTradesPanelsVisible(true);
    }

    private void disableUI()
    {
        btnBuildTown.setEnabled(false);
        setTradesPanelsVisible(false);
    }

    private void setTradesNeededToBuild()
    {
        if (town.canPay(player))
        {
            int amountTradesNeeded = player.getResources().getNeededResources(
                    town.getCost()).size();
            trade1.setVisible(amountTradesNeeded >= 1);
            trade2.setVisible(amountTradesNeeded >= 2);
            trade3.setVisible(amountTradesNeeded >= 3);
            trade4.setVisible(amountTradesNeeded >= 4);
        }
        else
        {
            trade1.setVisible(false);
            trade2.setVisible(false);
            trade3.setVisible(false);
            trade4.setVisible(false);
        }
    }
}
