package soc.gwtClient.game.widgets.standard.bitmap.developmentCards;

import soc.common.actions.gameAction.turnActions.standard.PlayDevelopmentCard;
import soc.common.game.developmentCards.standard.Monopoly;
import soc.common.internationalization.I18n;
import soc.gwtClient.game.abstractWidgets.DevelopmentCardWidget;
import soc.gwtClient.game.abstractWidgets.IGamePanel;
import soc.gwtClient.images.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class PlayMonopolyWidget implements DevelopmentCardWidget
{
    private Monopoly monopoly;
    private IGamePanel gamePanel;
    private PlayDevelopmentCard playDevelopmentCard = new PlayDevelopmentCard();
    private HorizontalPanel rootPanel = new HorizontalPanel();
    private Button btnPlay = new Button(I18n.get().constants().play());

    public PlayMonopolyWidget(Monopoly monopoly, final IGamePanel gamePanel)
    {
        this.monopoly = monopoly;
        this.gamePanel = gamePanel;

        rootPanel.setSpacing(5);
        rootPanel.add(new Image(Resources.icons().monopoly()));
        rootPanel.add(new Label(I18n.get().constants().monopoly()));
        rootPanel.add(btnPlay);

        playDevelopmentCard.setDevelopmentcard(monopoly);

        btnPlay.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                gamePanel.startAction(playDevelopmentCard);
            }
        });
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

}
