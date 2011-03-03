package soc.common.game.developmentCards.standard;

import soc.common.board.resources.ResourceList;
import soc.common.game.Game;
import soc.common.game.developmentCards.AbstractDevelopmentCard;
import soc.common.game.phases.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidget;
import soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidgetFactory;

public class YearOfPlenty extends AbstractDevelopmentCard
{
    private static final long serialVersionUID = 8598985603470688487L;
    // actual picked resources by player
    private ResourceList goldPick;

    /**
     * @param goldPick
     *            the goldPick to set
     */
    public YearOfPlenty setGoldPick(ResourceList goldPick)
    {
        this.goldPick = goldPick;

        return this;
    }

    /**
     * @return the two picked resources
     */
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
