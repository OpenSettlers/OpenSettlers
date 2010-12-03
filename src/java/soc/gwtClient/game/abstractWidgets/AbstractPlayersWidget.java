package soc.gwtClient.client.game;

import soc.common.game.Game;
import soc.common.game.Player;

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
        
        for (Player player : game.getPlayers())
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
