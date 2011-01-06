package soc.common.actions.gameAction.turnActions.standard;

import soc.common.actions.gameAction.turnActions.AbstractTurnAction;
import soc.common.board.resources.ResourceList;
import soc.common.game.Game;
import soc.common.game.developmentCards.DevelopmentCard;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.common.game.gamePhase.turnPhase.BuildingTurnPhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;
import soc.common.internationalization.I18n;

public class BuyDevelopmentCard extends AbstractTurnAction
{
    private static final long serialVersionUID = -7625851375785728121L;
    private ResourceList resources;
    private DevelopmentCard devCard;

    /**
     * @return the devCard
     */
    public DevelopmentCard getDevCard()
    {
        return devCard;
    }

    /**
     * @param devCard
     *            the devCard to set
     */
    public BuyDevelopmentCard setDevCard(DevelopmentCard devCard)
    {
        this.devCard = devCard;

        return this;
    }

    /**
     * @return the resources
     */
    public ResourceList getResources()
    {
        return resources;
    }

    /**
     * @param resources
     *            the resources to set
     */
    public BuyDevelopmentCard setResources(ResourceList resources)
    {
        this.resources = resources;

        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.actions.gameAction.GameAction#isValid(soc.common.game.Game)
     */
    @Override
    public boolean isValid(Game game)
    {
        if (!super.isValid(game))
            return false;

        // we need resources
        if (resources == null)
            return false;

        // ...and a devcard too.
        if (game.getDevelopmentCardStack().size() == 0)
        {
            invalidMessage = "Development cards are all gone!";
            return false;
        }

        // we need just three resources
        if (resources.size() != 3)
        {
            invalidMessage = "Player needs three resources to buy a development card";
            return false;
        }

        // TODO: check if resources are valid
        // if (resources.ofType(Diamond.class) > 2) return true;

        GamePlayer player = game.getPlayerByID(sender);

        if (!player.getResources().hasAtLeast(resources))
        {
            invalidMessage = "Player does not have given resources";
            return false;
        }

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.actions.gameAction.GameAction#perform(soc.common.game.Game)
     */
    @Override
    public void perform(Game game)
    {
        GamePlayer gamePlayer = game.getPlayerByID(sender);

        // Perform resources administration
        player.getResources().subtractResources(resources);
        game.getBank().add(resources);

        // Player should wait a turn before able to play new devcard
        devCard.setPlayable(!devCard.isHasSummoningSickness());
        devCard.setTurnBought(game.getCurrentTurn().getID());

        // Administer devcards
        player.getDevelopmentCards().add(devCard);
        game.getDevelopmentCardStack().remove(devCard);

        // TODO: fix message
        // message = String.Format("{0} bought a development card",
        // gamePlayer.XmlPlayer.Name);

        super.perform(game);
    }

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return turnPhase instanceof BuildingTurnPhase;
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        return gamePhase instanceof PlayTurnsGamePhase;
    }

    @Override
    public String getToDoMessage()
    {
        return I18n.get().actions().noToDo();
    }

}
