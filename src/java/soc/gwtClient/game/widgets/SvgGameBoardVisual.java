package soc.gwtClient.game.widgets;

import soc.common.client.behaviour.game.GameBehaviour;
import soc.common.client.visuals.game.AbstractGameBoardVisual;
import soc.common.client.visuals.game.GameBoardVisual;
import soc.common.client.visuals.game.VisualFactory;
import soc.common.game.Game;
import soc.gwtClient.visuals.svg.SvgVisualFactory;

import com.google.gwt.user.client.ui.Widget;

public class SvgGameBoardVisual extends AbstractGameBoardVisual implements
        GameBoardVisual
{

    public SvgGameBoardVisual(int widthInPixels, int heightInPixels, Game game)
    {
        super(game);
    }

    @Override
    public void setBehaviour(GameBehaviour gameBehaviour)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public VisualFactory createVisualFactory()
    {
        return new SvgVisualFactory();
    }

    @Override
    public Widget asWidget()
    {
        return boardVisual.asWidget();
    }

}
