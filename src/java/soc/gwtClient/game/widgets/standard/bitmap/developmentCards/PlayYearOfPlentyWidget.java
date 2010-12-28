package soc.gwtClient.game.widgets.standard.bitmap.developmentCards;

import soc.common.actions.gameAction.turnActions.standard.PlayDevelopmentCard;
import soc.common.game.developmentCards.standard.YearOfPlenty;
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

public class PlayYearOfPlentyWidget implements DevelopmentCardWidget
{
    private YearOfPlenty yearOfPlenty;
    private IGamePanel gamePanel;
    private PlayDevelopmentCard playDevelopmentCard = new PlayDevelopmentCard();
    private HorizontalPanel rootPanel = new HorizontalPanel();
    private Button btnPlay = new Button(I18n.get().constants().play());

    public PlayYearOfPlentyWidget(YearOfPlenty yearOfPlenty,
            final IGamePanel gamePanel)
    {
        this.yearOfPlenty = yearOfPlenty;
        this.gamePanel = gamePanel;

        rootPanel.setSpacing(5);
        rootPanel.add(new Image(Resources.icons().yearOfPlenty()));
        rootPanel.add(new Label(I18n.get().constants().yearOfPlenty()));
        rootPanel.add(btnPlay);

        playDevelopmentCard.setDevelopmentcard(yearOfPlenty);

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
