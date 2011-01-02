package soc.gwtClient.game.abstractWidgets;

import soc.common.game.Game;
import soc.common.game.GamePlayer;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractPlayersWidget implements IPlayersWidget
{
    protected ComplexPanel rootPanel;
    protected Game game;
    
    public AbstractPlayersWidget(Game game)
    {
        this.game=game;
        
        rootPanel = createRootPanel();
        
        for (GamePlayer player : game.getPlayers())
        {
            rootPanel.add(createPlayerWidget(game, player));
        }
    }
    
    @Override
    public ComplexPanel createRootPanel()
    {
        return new VerticalPanel();
    }
    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }
}
