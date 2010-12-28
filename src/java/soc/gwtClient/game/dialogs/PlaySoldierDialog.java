package soc.gwtClient.game.dialogs;

import soc.common.actions.gameAction.turnActions.standard.PlayDevelopmentCard;
import soc.common.game.developmentCards.standard.Soldier;
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

public class PlaySoldierDialog extends DialogBox
{
    private Soldier soldier;
    private IGamePanel gamePanel;
    private PlayDevelopmentCard playDevelopmentCard = new PlayDevelopmentCard();

    public PlaySoldierDialog(Soldier soldier, IGamePanel gamePanel)
    {
        this();
        this.soldier = soldier;
        this.gamePanel = gamePanel;

        playDevelopmentCard.setPlayer(gamePanel.getPlayingPlayer());
        playDevelopmentCard.setDevelopmentcard(soldier);
    }

    /**
     * @wbp.parser.constructor
     */
    public PlaySoldierDialog()
    {
        setText("Play soldier");
        setHTML("New dialog");

        VerticalPanel verticalPanel = new VerticalPanel();
        setWidget(verticalPanel);
        verticalPanel.setSize("234px", "164px");

        HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        horizontalPanel
                .setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        verticalPanel.add(horizontalPanel);

        Image image = new Image(Resources.icons().soldier());
        horizontalPanel.add(image);

        Label lblSoldier = new Label("Soldier");
        horizontalPanel.add(lblSoldier);

        HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
        horizontalPanel_2.setSpacing(10);
        verticalPanel.add(horizontalPanel_2);

        Label lblMoveTheRobber = new Label(
                "Move the robber to a new position when playing this development card");
        horizontalPanel_2.add(lblMoveTheRobber);

        HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
        horizontalPanel_1.setSpacing(10);
        verticalPanel.add(horizontalPanel_1);

        Button btnCancel = new Button("New button");
        btnCancel.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent arg0)
            {
                hide();
            }
        });
        btnCancel.setText("Cancel");
        horizontalPanel_1.add(btnCancel);

        Button btnPlay = new Button("New button");
        btnPlay.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent arg0)
            {
                gamePanel.startAction(playDevelopmentCard);
                hide();
            }
        });
        btnPlay.setText("Play soldier");
        horizontalPanel_1.add(btnPlay);
    }
}
