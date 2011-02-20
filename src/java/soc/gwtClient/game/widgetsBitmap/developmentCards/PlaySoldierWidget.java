package soc.gwtClient.game.widgetsBitmap.developmentCards;

import soc.common.actions.gameAction.standard.PlayDevelopmentCard;
import soc.common.game.developmentCards.standard.Soldier;
import soc.common.internationalization.I18n;
import soc.gwtClient.game.widgetsInterface.developmentCards.DevelopmentCardWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class PlaySoldierWidget implements DevelopmentCardWidget
{
    private Soldier soldier;
    private GameWidget gamePanel;
    private PlayDevelopmentCard playDevelopmentCard = new PlayDevelopmentCard();
    private HorizontalPanel rootPanel = new HorizontalPanel();
    private Button btnPlay = new Button(I18n.get().constants().play());

    public PlaySoldierWidget(Soldier soldier, final GameWidget gamePanel)
    {
        this.soldier = soldier;
        this.gamePanel = gamePanel;

        rootPanel.setSpacing(5);
        rootPanel.add(new Image(Resources.icons().soldier()));
        rootPanel.add(new Label(I18n.get().constants().soldier()));
        rootPanel.add(btnPlay);

        playDevelopmentCard.setDevelopmentcard(soldier);
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
