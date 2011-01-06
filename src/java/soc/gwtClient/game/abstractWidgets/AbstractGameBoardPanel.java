package soc.gwtClient.game.abstractWidgets;

import soc.gwtClient.visuals.abstractVisuals.GameBoardVisual;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AbstractGameBoardPanel implements GameBoardWidget
{
    protected ComplexPanel rootPanel;
    protected GameBoardVisual gameBoard;

    public AbstractGameBoardPanel(GameBoardVisual gameBoardVisual)
    {
        this.gameBoard = gameBoardVisual;

        rootPanel.add(gameBoardVisual.asWidget());
    }

    @Override
    public ComplexPanel createRootPanel()
    {
        return new HorizontalPanel();
    }

    @Override
    public Widget asWidget()
    {
        return gameBoard.asWidget();
    }

}
