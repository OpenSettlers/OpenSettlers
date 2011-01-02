package soc.gwtClient.game.abstractWidgets;

import soc.common.board.pieces.PlayerPiece;
import soc.common.game.Game;
import soc.common.game.GamePlayer;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

public abstract class AbstractStockWidget implements IStockWidget
{
    protected Game game;
    protected GamePlayer player;
    protected ComplexPanel rootPanel;

    public AbstractStockWidget(Game game, GamePlayer player)
    {
        this.game = game;
        this.player = player;

        rootPanel = createRootPanel();

        createStockItemWidgetsList();
    }

    private void createStockItemWidgetsList()
    {
        for (PlayerPiece piece : game.getGameRules().getStockPieces())
        {
            rootPanel.add(createStockItemWidget(piece));
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.gwtClient.client.game.IStockWidget#createRootPanel()
     */
    @Override
    public ComplexPanel createRootPanel()
    {
        return new HorizontalPanel();
    }
}
