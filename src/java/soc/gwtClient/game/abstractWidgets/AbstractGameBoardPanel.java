package soc.gwtClient.game.abstractWidgets;

import soc.common.client.visuals.game.IGameBoardVisual;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AbstractGameBoardPanel implements IGameBoardWidget
{
    protected ComplexPanel rootPanel;
    protected IGameBoardVisual gameBoard;

    public AbstractGameBoardPanel(IGameBoardVisual gameBoardVisual)
    {
        this.gameBoard = gameBoardVisual;

        rootPanel.add(gameBoardVisual.getBoardVisual().asWidget());
    }

    @Override
    public ComplexPanel createRootPanel()
    {
        return new HorizontalPanel();
    }

    @Override
    public Widget asWidget()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
