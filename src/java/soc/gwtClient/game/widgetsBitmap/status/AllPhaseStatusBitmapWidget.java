package soc.gwtClient.game.widgetsBitmap.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import soc.common.game.GamePhaseChangedEvent;
import soc.common.game.GamePhaseChangedEventHandler;
import soc.common.game.gamePhase.GamePhase;
import soc.gwtClient.game.widgetsInterface.main.GamePhaseStatusWidgetFactory;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AllPhaseStatusBitmapWidget implements
        GamePhaseChangedEventHandler, IsWidget
{
    private HorizontalPanel rootPanel = new HorizontalPanel();
    private VerticalPanel phasesRootPanel = new VerticalPanel();
    private DeckPanel currentPhasePanel = new DeckPanel();
    private HorizontalPanel gamePhaseIconsPanel = new HorizontalPanel();
    private List<Image> gamePhaseIcons = new ArrayList<Image>();
    private Map<GamePhase, GamePhaseStatusWidget> phaseWidgets = new HashMap<GamePhase, GamePhaseStatusWidget>();
    private List<GamePhaseStatusWidget> widgets = new ArrayList<GamePhaseStatusWidget>();
    private GameWidget gameWidget;
    private Image currentPhaseIcon;

    public AllPhaseStatusBitmapWidget(GameWidget gameWidget)
    {
        this.gameWidget = gameWidget;

        GamePhaseStatusWidgetFactory factory = gameWidget.getClientFactory()
                .getGamePhaseStatusWidgetFactory();

        for (GamePhase gamePhase : gameWidget.getGame().getRules()
                .getSupportedPhases())
        {
            gamePhaseIcons.add(new Image(Resources.gamePhase(gamePhase)));
            GamePhaseStatusWidget widget = gamePhase
                    .createGamePhaseStatusWidget(factory);
            phaseWidgets.put(gamePhase, widget);
            currentPhasePanel.add(widget);
            widgets.add(widget);
        }

        for (Image icon : gamePhaseIcons)
            gamePhaseIconsPanel.add(icon);

        phasesRootPanel.add(new Label("Game phases"));
        phasesRootPanel.add(gamePhaseIconsPanel);

        rootPanel.add(phasesRootPanel);
        rootPanel.add(currentPhasePanel);

        gameWidget.getGame().addGamePhaseChangedEventHandler(this);
        setCurrentPhase();
        rootPanel.setStyleName("allGamePhases");
        currentPhasePanel.setStyleName("phasePanel");
    }

    @Override
    public void onGamePhaseChanged(GamePhaseChangedEvent event)
    {
        setCurrentPhase();
    }

    private void setCurrentPhase()
    {
        GamePhase newPhase = gameWidget.getGame().getCurrentPhase();

        if (currentPhaseIcon != null)
            currentPhaseIcon.setStyleName("phaseNotSelectedIcon");

        GamePhaseStatusWidget widget = null;
        for (Entry<GamePhase, GamePhaseStatusWidget> entry : phaseWidgets
                .entrySet())
            if (entry.getKey().getClass() == newPhase.getClass())
            {
                widget = entry.getValue();
                break;
            }

        ImageResource newSelectedIcon = widget.getIcon();

        for (Image icon : gamePhaseIcons)
            if (icon.getUrl().equals(newSelectedIcon.getURL()))
                currentPhaseIcon = icon;

        currentPhaseIcon.setStyleName("phaseSelectedIcon");
        currentPhasePanel.showWidget(widgets.indexOf(widget));
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }
}
