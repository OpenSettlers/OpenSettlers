package soc.gwtClient.game.abstractWidgets;

import soc.common.board.pieces.abstractPieces.PlayerPiece;
import soc.common.game.player.GamePlayer;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

public abstract class AbstractStockWidget implements StockWidget
{
    protected GamePanel gamePanel;
    protected GamePlayer player;
    protected ComplexPanel rootPanel;

    public AbstractStockWidget(GamePanel gamePanel, GamePlayer player)
    {
        this.gamePanel = gamePanel;
        this.player = player;

        rootPanel = createRootPanel();

        createStockItemWidgetsList();
    }

    private void createStockItemWidgetsList()
    {
        for (PlayerPiece piece : gamePanel.getGame().getRules()
                .getStockPieces())
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
