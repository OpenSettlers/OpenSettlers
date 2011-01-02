package soc.gwtClient.visuals.svg;

import soc.common.client.visuals.game.AbstractGameBoardVisual;
import soc.common.client.visuals.game.PointVisual;
import soc.common.client.visuals.game.SideVisual;
import soc.common.client.visuals.game.VisualFactory;
import soc.common.game.Game;

import com.google.gwt.user.client.ui.Widget;

public class GameBoardSvg extends AbstractGameBoardVisual
{
    BoardSvg boardSvg;

    public GameBoardSvg(Game game, int width, int height)
    {
        super(game);

        boardSvg = new BoardSvg(width, height, game.getBoard());

        for (SideVisual sideVisual : sideVisuals.values())
        {
            boardSvg.getDrawingArea().add(
                    ((SvgVisual) sideVisual).getVectorObject());
        }

        for (PointVisual pointVisual : pointVisuals.values())
        {
            boardSvg.getDrawingArea().add(
                    ((SvgVisual) pointVisual).getVectorObject());
        }
    }

    @Override
    public Widget asWidget()
    {
        return boardSvg.getDrawingArea();
    }

    @Override
    public VisualFactory createVisualFactory()
    {
        return new SvgVisualFactory();
    }
}
