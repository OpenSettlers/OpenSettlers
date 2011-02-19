package soc.gwtClient.game.widgetsBitmap.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.common.game.gamePhase.turnPhase.BeforeDiceRollTurnPhase;
import soc.common.game.gamePhase.turnPhase.BuildingTurnPhase;
import soc.common.game.gamePhase.turnPhase.RollDiceTurnPhase;
import soc.common.game.gamePhase.turnPhase.TradingTurnPhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.game.gamePhase.turnPhase.TurnPhaseChangedEvent;
import soc.common.game.gamePhase.turnPhase.TurnPhaseChangedHandler;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class PlayTurnsBitmapWidget implements GamePhaseStatusWidget,
        TurnPhaseChangedHandler
{
    private VerticalPanel rootPanel = new VerticalPanel();
    private HorizontalPanel turnPhasePanel = new HorizontalPanel();
    private PlayTurnsGamePhase playTurnsGamePhase;
    private ImageResource icon;
    private Label lblTurnPhaseDescription = new Label();
    private GameWidget gamePanel;
    private ArrayList<TurnPhase> turnPhases = new ArrayList<TurnPhase>();
    private Map<TurnPhase, Image> phaseIcons = new HashMap<TurnPhase, Image>();
    private Image currentPhaseIcon;

    public PlayTurnsBitmapWidget(GameWidget gamePanel,
            PlayTurnsGamePhase playTurnsGamePhase)
    {
        super();
        this.gamePanel = gamePanel;
        this.playTurnsGamePhase = playTurnsGamePhase;

        icon = Resources.gamePhase(playTurnsGamePhase);

        rootPanel.add(lblTurnPhaseDescription);
        rootPanel.add(turnPhasePanel);

        turnPhases.add(new BeforeDiceRollTurnPhase());
        turnPhases.add(new RollDiceTurnPhase());
        turnPhases.add(new TradingTurnPhase());
        turnPhases.add(new BuildingTurnPhase());

        for (TurnPhase turnPhase : turnPhases)
        {
            Image icon = new Image(Resources.turnPhase(turnPhase));
            phaseIcons.put(turnPhase, icon);
            turnPhasePanel.add(icon);
        }

        gamePanel.getGame().addTurnPhaseChangedHandler(this);
    }

    @Override
    public GamePhase getGamePhase()
    {
        return playTurnsGamePhase;
    }

    @Override
    public ImageResource getIcon()
    {
        return icon;
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

    @Override
    public void onTurnPhaseChanged(TurnPhaseChangedEvent event)
    {
        if (currentPhaseIcon != null)
            currentPhaseIcon.setStyleName("phaseNotSelectedIcon");

        for (Entry<TurnPhase, Image> entry : phaseIcons.entrySet())
            if (entry.getKey().getClass() == event.getNewPhase().getClass())
                currentPhaseIcon = entry.getValue();

        // currentPhaseIcon = phaseIcons.get(event.getNewPhase());
        currentPhaseIcon.setStyleName("phaseSelectedIcon");
        lblTurnPhaseDescription.setText(event.getNewPhase().getMessage());
    }

}
