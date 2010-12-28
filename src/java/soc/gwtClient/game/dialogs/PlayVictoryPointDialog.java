package soc.gwtClient.game.dialogs;

import soc.common.actions.gameAction.turnActions.standard.PlayDevelopmentCard;
import soc.common.game.developmentCards.standard.VictoryPoint;
import soc.gwtClient.game.abstractWidgets.IGamePanel;
import soc.gwtClient.images.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PlayVictoryPointDialog extends DialogBox
{
    private VictoryPoint victoryPoint;
    private IGamePanel gamePanel;
    private PlayDevelopmentCard playDevelopmentCard = new PlayDevelopmentCard();

    public PlayVictoryPointDialog(VictoryPoint victoryPoint,
            IGamePanel gamePanel)
    {
        this();
        this.victoryPoint = victoryPoint;
        this.gamePanel = gamePanel;

        playDevelopmentCard.setPlayer(gamePanel.getPlayingPlayer());
        playDevelopmentCard.setDevelopmentcard(victoryPoint);
    }

    /**
     * @wbp.parser.constructor
     */
    public PlayVictoryPointDialog()
    {
        setText("Play victorypoint");
        setHTML("New dialog");

        VerticalPanel verticalPanel = new VerticalPanel();
        setWidget(verticalPanel);
        verticalPanel.setSize("211px", "147px");

        HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        horizontalPanel
                .setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        verticalPanel.add(horizontalPanel);

        Image image = new Image(Resources.icons().victoryPoint());
        horizontalPanel.add(image);

        Label lblVictorypoint = new Label("VictoryPoint");
        horizontalPanel.add(lblVictorypoint);

        HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
        horizontalPanel_2.setSpacing(10);
        verticalPanel.add(horizontalPanel_2);

        Label lblGainOneVictorypoint = new Label(
                "Gain one victorypoint by playing this development card");
        horizontalPanel_2.add(lblGainOneVictorypoint);

        HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
        horizontalPanel_1.setSpacing(10);
        horizontalPanel_1
                .setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        verticalPanel.add(horizontalPanel_1);

        Button button = new Button("New button");
        button.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent arg0)
            {
                hide();
            }
        });
        button.setText("Cancel");
        horizontalPanel_1.add(button);

        Button button_1 = new Button("New button");
        button_1.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent arg0)
            {
                gamePanel.startAction(playDevelopmentCard);
                hide();
            }
        });
        button_1.setText("Play victorypoint");
        horizontalPanel_1.add(button_1);
    }
}
