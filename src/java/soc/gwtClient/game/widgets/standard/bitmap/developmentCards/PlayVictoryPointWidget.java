package soc.gwtClient.game.widgets.standard.bitmap.developmentCards;

import soc.common.actions.gameAction.turnActions.standard.PlayDevelopmentCard;
import soc.common.game.developmentCards.standard.VictoryPoint;
import soc.common.internationalization.I18n;
import soc.gwtClient.game.abstractWidgets.DevelopmentCardWidget;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.images.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class PlayVictoryPointWidget implements DevelopmentCardWidget
{
    private VictoryPoint victoryPoint;
    private GamePanel gamePanel;
    private PlayDevelopmentCard playDevelopmentCard = new PlayDevelopmentCard();
    private HorizontalPanel rootPanel = new HorizontalPanel();
    private Button btnPlay = new Button(I18n.get().constants().play());

    public PlayVictoryPointWidget(VictoryPoint victoryPoint,
            final GamePanel gamePanel)
    {
        this.victoryPoint = victoryPoint;
        this.gamePanel = gamePanel;

        rootPanel.setSpacing(5);
        rootPanel.add(new Image(Resources.icons().victoryPoint()));
        rootPanel.add(new Label(I18n.get().constants().victoryPoint()));
        rootPanel.add(btnPlay);

        playDevelopmentCard.setDevelopmentcard(victoryPoint);
        playDevelopmentCard.setPlayer(gamePanel.getPlayingPlayer());

        btnPlay.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                gamePanel.sendAction(playDevelopmentCard);
            }
        });
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

}
