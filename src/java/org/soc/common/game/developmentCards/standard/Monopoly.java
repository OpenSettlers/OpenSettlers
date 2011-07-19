package org.soc.common.game.developmentCards.standard;

import org.soc.common.board.resources.Clay;
import org.soc.common.board.resources.Ore;
import org.soc.common.board.resources.Resource;
import org.soc.common.board.resources.ResourceList;
import org.soc.common.board.resources.Sheep;
import org.soc.common.board.resources.Timber;
import org.soc.common.board.resources.Wheat;
import org.soc.common.game.Game;
import org.soc.common.game.developmentCards.AbstractDevelopmentCard;
import org.soc.common.game.phases.turnPhase.TurnPhase;
import org.soc.common.game.player.GamePlayer;
import org.soc.common.internationalization.I18n;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.meta.Meta;
import org.soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidget;
import org.soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidgetFactory;
import org.soc.gwt.client.images.Resources;

/*
 * Standard monopoly
 * Steals all resources of a chosen type from all opponents
 */
public class Monopoly extends AbstractDevelopmentCard
{
    private static final long serialVersionUID = 7740748478927741890L;
    public static ResourceList staticMonoPolyableResources = new ResourceList();
    private Resource resource;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().monopoly16(),
                        Resources.icons().monopoly32(), Resources.icons()
                                        .monopoly48());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "Monopoly";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().monopoly();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().monopolyDescription();
        }
    };

    @Override
    public Meta getMeta()
    {
        return meta;
    }

    static
    {
        staticMonoPolyableResources.add(new Timber());
        staticMonoPolyableResources.add(new Wheat());
        staticMonoPolyableResources.add(new Ore());
        staticMonoPolyableResources.add(new Clay());
        staticMonoPolyableResources.add(new Sheep());
    }

    /** @return the resource */
    public Resource getResource()
    {
        return resource;
    }

    /** @param resource
     *            the resource type to steal all opponents' resources */
    public Monopoly setResource(Resource resource)
    {
        this.resource = resource;
        return this;
    }

    /** @return A ResourceList with unique resource types this monopoly can steal
     *         from other players */
    public ResourceList getMonopolyableResources()
    {
        return staticMonoPolyableResources;
    }

    /*
     * Valid when having non-null resource and resource is of a basic type
     * 
     * @see
     * org.soc.common.game.developmentCards.DevelopmentCard#isValid(org.soc.common.game
     * .Game)
     */
    @Override
    public boolean isValid(Game game)
    {
        if (!(super.isValid(game)))
            return false;

        if (resource == null)
            return false;

        // Allow only one of the 5 basic resources
        if (!staticMonoPolyableResources.contains(resource))
            return false;

        return true;
    }

    /*
     * Steals all resources from opponents with given resource type
     * 
     * @see
     * org.soc.common.game.developmentCards.DevelopmentCard#play(org.soc.common.game
     * .Game, org.soc.common.game.Player)
     */
    @Override
    public void play(Game game, GamePlayer player)
    {
        // TODO: fix message
        // msg.append(String.format("%s stole ", player.getName()));

        for (GamePlayer opponent : game.getPlayers().getOpponents(player))
        {
            // Get the list of resources to steal
            ResourceList opponentResources = opponent.getResources().ofType(
                            resource);

            // steal only if there are resources to steal
            if (opponentResources.size() > 0)
            {
                // msg.append(String.format("%s %s from %s, ",
                // opponentResources.size(), resource.toString(),
                // player.getName()));

                // Move resources from victims to player
                opponent.getResources().moveTo(player.getResources(),
                                opponentResources);
            }
        }

        // remove the trailing ","
        // msg.toString().substring(0,msg.toString().length() - 2);
        message = player.getUser().getName() + "played a monopoly";

        super.play(game, player);
    }

    @Override
    public DevelopmentCardWidget createPlayCardWidget(
                    DevelopmentCardWidgetFactory factory)
    {
        return factory.createMonopolyWidget(this);
    }

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return turnPhase.isBuilding();
    }

}