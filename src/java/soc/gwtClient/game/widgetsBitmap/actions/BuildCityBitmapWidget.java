package soc.gwtClient.game.widgetsBitmap.actions;

import soc.common.actions.gameAction.standard.BuildCity;
import soc.common.board.pieces.City;
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

public class BuildCityBitmapWidget extends AbstractActionWidget implements
                ResourcesChangedEventHandler, GamePhaseChangedEventHandler,
                TurnPhaseChangedHandler,
                PlayerPieceListChangedEventHandler<City>
{
    private AbsolutePanel absolutePanel = new AbsolutePanel();
    private VerticalPanel tradesPanel1 = new VerticalPanel();
    private VerticalPanel tradesPanel2 = new VerticalPanel();
    private PushButton btnCity = new PushButton(new Image(Resources.icons()
                    .city48()));
    private City city = new City();
    private Image trade1 = new Image(Resources.icons().trade16());
    private Image trade2 = new Image(Resources.icons().trade16());
    private Image trade3 = new Image(Resources.icons().trade16());
    private Image trade4 = new Image(Resources.icons().trade16());
    private Image trade5 = new Image(Resources.icons().trade16());
    private BuildCity buildCity = new BuildCity();

    public BuildCityBitmapWidget(final GameWidget gameWidget,
                    final GamePlayer player)
    {
        super(gameWidget, player);

        absolutePanel.setSize("60px", "60px");
        buildCity.setPlayer(player);

        player.getResources().addResourcesChangedEventHandler(this);
        player.getStock().getCities().addCitiesChangedEventHandler(this);
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
        tradesPanel2.add(trade5);

        absolutePanel.add(btnCity, 0, 0);
        absolutePanel.add(tradesPanel1, 3, 3);
        absolutePanel.add(tradesPanel2, 19, 3);

        btnCity.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                gameWidget.startAction(new BuildCity().setPlayer(player));
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
    protected void updateEnabled()
    {
        checkEnabled();
    }

    @Override
    public void onPlayerPieceListChanged(PlayerPieceListChangedEvent<City> event)
    {
        checkEnabled();
    }

    private void enableUI()
    {
        btnCity.setEnabled(true);
        setTradesPanelsVisible(true);
    }

    private void disableUI()
    {
        btnCity.setEnabled(false);
        setTradesPanelsVisible(false);
    }

    private void checkEnabled()
    {
        if (enabled && player.isOnTurn())
        {
            Game game = gameWidget.getGame();

            if (game.getCurrentPhase().isAllowed(buildCity) && // current phase
                            // must be OK
                            city.canBuild(game.getBoard(), player) && // we need space
                            city.canPay(player)) // we need resources
            {
                enableUI();
                setTradesNeededToBuild();
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

    private void setTradesNeededToBuild()
    {
        int amountTradesNeeded = player.getResources()
                        .getNeededResources(city.getCost()).size();
        trade1.setVisible(amountTradesNeeded >= 1);
        trade2.setVisible(amountTradesNeeded >= 2);
        trade3.setVisible(amountTradesNeeded >= 3);
        trade4.setVisible(amountTradesNeeded >= 4);
        trade5.setVisible(amountTradesNeeded >= 5);
    }
}
