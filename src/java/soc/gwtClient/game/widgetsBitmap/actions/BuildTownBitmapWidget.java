package soc.gwtClient.game.widgetsBitmap.actions;

import soc.common.actions.gameAction.standard.BuildTown;
import soc.common.board.pieces.Town;
import soc.common.board.pieces.pieceLists.PlayerPieceListChangedEvent;
import soc.common.board.pieces.pieceLists.PlayerPieceListChangedEventHandler;
import soc.common.board.resources.ResourcesChangedEvent;
import soc.common.board.resources.ResourcesChangedEventHandler;
import soc.common.game.Game;
import soc.common.game.GamePhaseChangedEvent;
import soc.common.game.GamePhaseChangedEventHandler;
import soc.common.game.phases.turnPhase.TurnPhaseChangedEvent;
import soc.common.game.phases.turnPhase.TurnPhaseChangedHandler;
import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsAbstract.actions.AbstractActionWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class BuildTownBitmapWidget extends AbstractActionWidget implements
        ResourcesChangedEventHandler, GamePhaseChangedEventHandler,
        TurnPhaseChangedHandler, PlayerPieceListChangedEventHandler<Town>
{
    private AbsolutePanel absolutePanel = new AbsolutePanel();
    private VerticalPanel tradesPanel1 = new VerticalPanel();
    private VerticalPanel tradesPanel2 = new VerticalPanel();
    private PushButton btnBuildTown = new PushButton(new Image(Resources
            .icons().town()));
    private Town town = new Town();

    // 4 trades maximum for a town
    private Image trade1 = new Image(Resources.icons().trade());
    private Image trade2 = new Image(Resources.icons().trade());
    private Image trade3 = new Image(Resources.icons().trade());
    private Image trade4 = new Image(Resources.icons().trade());
    private BuildTown buildTown = new BuildTown();

    public BuildTownBitmapWidget(final GameWidget gameWidget,
            final GamePlayer player)
    {
        super(gameWidget, player);
        absolutePanel.setSize("60px", "60px");

        buildTown.setPlayer(player);

        player.getResources().addResourcesChangedEventHandler(this);
        player.getStock().getTowns().addTownsChangedEventHandler(this);
        gameWidget.getGame().addGamePhaseChangedEventHandler(this);
        gameWidget.getGame().addTurnPhaseChangedHandler(this);
        player.getTowns().addTownsChangedEventHandler(
                new PlayerPieceListChangedEventHandler<Town>()
                {
                    @Override
                    public void onPlayerPieceListChanged(
                            PlayerPieceListChangedEvent<Town> event)
                    {
                        checkEnabled();
                    }
                });

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
                gameWidget.startAction(new BuildTown().setPlayer(player));
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
    public void onGamePhaseChanged(GamePhaseChangedEvent event)
    {
        checkEnabled();
    }

    @Override
    public void onTurnPhaseChanged(TurnPhaseChangedEvent event)
    {
        checkEnabled();
    }

    @Override
    public void onPlayerPieceListChanged(PlayerPieceListChangedEvent<Town> event)
    {
        checkEnabled();
    }

    private void checkEnabled()
    {
        setTradesNeededToBuild();
        if (enabled && player.isOnTurn())
        {
            Game game = gameWidget.getGame();

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
