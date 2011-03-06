package soc.common.game.developmentCards.standard;

import soc.common.board.resources.ResourceList;
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
import soc.gwtClient.images.Resources;

public class YearOfPlenty extends AbstractDevelopmentCard
{
    private static final long serialVersionUID = 8598985603470688487L;
    // actual picked resources by player
    private ResourceList goldPick;
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().yearOfPlenty16(),
                        Resources.icons().yearOfPlenty32(), Resources.icons()
                                        .yearOfPlenty48());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "YearOfPlenty";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().yearOfPlenty();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().yearOfPlentyDescription();
        }
    };

    @Override
    public Meta getMeta()
    {
        return meta;
    }

    /** @param goldPick
     *            the goldPick to set */
    public YearOfPlenty setGoldPick(ResourceList goldPick)
    {
        this.goldPick = goldPick;

        return this;
    }

    /** @return the two picked resources */
    public ResourceList getGoldPick()
    {
        return goldPick;
    }

    @Override
    public void play(Game game, GamePlayer player)
    {
        // TODO: fix mssage
        // message =
        // String.format("%s gained %s by playing a Year of Plenty card",
        // player.getName(), goldPick.toString());

        // give player the resources
        player.getResources().swapResourcesFrom(goldPick, game.getBank());

        super.play(game, player);
    }

    @Override
    public boolean isValid(Game game)
    {
        if (!super.isValid(game))
            return false;

        if (goldPick == null)
        {
            invalidMessage = "Resources cannot be null";
            return false;
        }

        if (goldPick.size() != 2)
        {
            invalidMessage = "Exactly 2 resources should be picked";
            return false;
        }

        if (!(game.getBank().hasAtLeast(goldPick)))
        {
            invalidMessage = "Bank does not have picked resources";
            return false;
        }

        return true;
    }

    @Override
    public DevelopmentCardWidget createPlayCardWidget(
                    DevelopmentCardWidgetFactory factory)
    {
        return factory.createYearOfPlentyWidget(this);
    }

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return turnPhase.isBuilding();
    }

}
