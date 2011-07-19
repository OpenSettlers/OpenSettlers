package soc.common.game.phases;

import soc.common.game.Game;
import soc.common.game.Turn;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.common.views.widgetsInterface.main.GamePhaseStatusWidget;
import soc.common.views.widgetsInterface.main.GamePhaseStatusWidgetFactory;
import soc.gwt.client.images.Resources;

public class EndedGamePhase extends AbstractGamePhase
{
    private static final long serialVersionUID = -3286474892624586180L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().endedGamePhase16(),
                        Resources.icons().endedGamePhase32(), Resources.icons()
                                        .endedGamePhase48());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getLocalizedName()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getDescription()
        {
            // TODO Auto-generated method stub
            return null;
        }

    };

    @Override
    public String getMessage()
    {
        // TODO fix message
        return "Game has ended";
    }

    /*
     * Returns null, there is no next turn when game ended
     * 
     * @see
     * soc.common.game.gamePhase.AbstractGamePhase#nextTurn(soc.common.game.
     * Game)
     */
    @Override
    public Turn nextTurn(Game game)
    {
        return null;
    }

    @Override
    public boolean isEnded()
    {
        return true;
    }

    @Override
    public GamePhaseStatusWidget createGamePhaseStatusWidget(
                    GamePhaseStatusWidgetFactory factory)
    {
        return factory.createEndedStatusWidget(this);
    }

    @Override
    public GamePhase next(Game game)
    {
        return null;
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }
}