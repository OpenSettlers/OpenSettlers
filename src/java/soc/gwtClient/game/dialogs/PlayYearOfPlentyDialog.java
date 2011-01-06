package soc.gwtClient.game.dialogs;

import soc.common.actions.gameAction.turnActions.standard.PlayDevelopmentCard;
import soc.common.game.developmentCards.standard.YearOfPlenty;
import soc.gwtClient.game.abstractWidgets.GamePanel;
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

public class PlayYearOfPlentyDialog extends DialogBox
{
    private YearOfPlenty yearOfPlenty;
    private GamePanel gamePanel;
    private PlayDevelopmentCard playDevelopmentCard = new PlayDevelopmentCard();

    public PlayYearOfPlentyDialog(YearOfPlenty yearOfPlenty,
            GamePanel gamePanel)
    {
        this();
        this.yearOfPlenty = yearOfPlenty;
        this.gamePanel = gamePanel;

        playDevelopmentCard.setPlayer(gamePanel.getPlayingPlayer());
        playDevelopmentCard.setDevelopmentcard(yearOfPlenty);
    }

    /**
     * @wbp.parser.constructor
     */
    public PlayYearOfPlentyDialog()
    {
        setText("Play year of plenty development card");
        setHTML("New dialog");

        VerticalPanel verticalPanel = new VerticalPanel();
        setWidget(verticalPanel);
        verticalPanel.setSize("264px", "147px");

        HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        horizontalPanel
                .setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        verticalPanel.add(horizontalPanel);

        Image image = new Image(Resources.icons().yearOfPlenty());
        horizontalPanel.add(image);

        Label lblYearOfPlenty = new Label("Year of plenty");
        horizontalPanel.add(lblYearOfPlenty);

        HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
        horizontalPanel_2.setSpacing(10);
        verticalPanel.add(horizontalPanel_2);

        Label lblPickAnyTwo = new Label(
                "Pick any two resources to add to your hand resources");
        horizontalPanel_2.add(lblPickAnyTwo);

        HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
        horizontalPanel_1
                .setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        horizontalPanel_1.setSpacing(10);
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
        button_1.setText("Play soldier");
        horizontalPanel_1.add(button_1);
    }
}
