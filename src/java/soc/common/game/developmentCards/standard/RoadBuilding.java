package soc.common.game.developmentCards.standard;

import soc.common.game.Game;
import soc.common.game.developmentCards.AbstractDevelopmentCard;
import soc.common.game.phases.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;
import soc.common.internationalization.I18n;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidget;
import soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidgetFactory;
import soc.gwt.client.images.Resources;

public class RoadBuilding extends AbstractDevelopmentCard
{
    private static final long serialVersionUID = 5867545725527745220L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().roadBuilding16(),
                        Resources.icons().roadBuilding32(), Resources.icons()
                                        .roadBuilding48());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "RoadBuilding";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().roadBuilding();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().roadBuildingDescription();
        }
    };

    @Override
    public Meta getMeta()
    {
        return meta;
    }
    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.game.developmentCards.DevelopmentCard#play(soc.common.game
     * .Game, soc.common.game.Player)
     */
    @Override
    public void play(Game game, GamePlayer player)
    {
        int roadBuildingTokens = player.getRoadBuildingTokens();
        player.setRoadBuildingTokens(roadBuildingTokens += 2);

        message = player.getUser().getName() + " played a road building card";

        super.play(game, player);
    }

    @Override
    public DevelopmentCardWidget createPlayCardWidget(
                    DevelopmentCardWidgetFactory factory)
    {
        return factory.createRoadBuildingWidget(this);
    }

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return turnPhase.isBuilding();
    }

}
