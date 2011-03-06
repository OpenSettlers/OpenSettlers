package soc.common.actions.gameAction.standard;

import soc.common.actions.gameAction.turns.AbstractTurnAction;
import soc.common.board.resources.ResourceList;
import soc.common.game.Game;
import soc.common.game.developmentCards.DevelopmentCard;
import soc.common.game.phases.GamePhase;
import soc.common.game.phases.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;
import soc.common.internationalization.I18n;
import soc.common.server.gameActions.GameServerActionFactory;
import soc.common.server.gameActions.ServerAction;
import soc.common.views.behaviour.gameWidget.GameBehaviour;
import soc.common.views.behaviour.gameWidget.factories.GameBehaviourFactory;
import soc.common.views.behaviour.gameWidget.factories.ReceiveGameBehaviourFactory;
import soc.common.views.behaviour.gameWidget.received.ReceiveGameBehaviour;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.common.views.widgetsInterface.actions.ActionWidget;
import soc.common.views.widgetsInterface.actions.ActionWidgetFactory;
import soc.gwtClient.images.Resources;

public class BuyDevelopmentCard extends AbstractTurnAction
{
    private static final long serialVersionUID = -7625851375785728121L;
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons()
                        .buyDvelopmentCard16(), Resources.icons()
                        .buyDvelopmentCard32(), Resources.icons()
                        .buyDvelopmentCard48());

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
    private ResourceList resources;
    private DevelopmentCard devCard;

    /** @return the devCard */
    public DevelopmentCard getDevCard()
    {
        return devCard;
    }

    /** @param devCard
     *            the devCard to set */
    public BuyDevelopmentCard setDevCard(DevelopmentCard devCard)
    {
        this.devCard = devCard;

        return this;
    }

    /** @return the resources */
    public ResourceList getResources()
    {
        return resources;
    }

    /** @param resources
     *            the resources to set */
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
        {
            invalidMessage = "Resources cannot be null";
            return false;
        }

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
        // Perform resources administration
        player.getResources().subtractResources(resources);
        game.getBank().addList(resources);

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
        return turnPhase.isBuilding();
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        return gamePhase.isPlayTurns();
    }

    @Override
    public String getToDoMessage()
    {
        return I18n.get().actions().noToDo();
    }

    @Override
    public ActionWidget createActionWidget(
                    ActionWidgetFactory actionWidgetFactory)
    {
        return actionWidgetFactory.createBuyDevelopmentCardWidget();
    }

    @Override
    public GameBehaviour getNextActionBehaviour(
                    GameBehaviourFactory gameBehaviourFactory)
    {
        return gameBehaviourFactory.createBuyDevelopmentcardBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getOpponentReceiveBehaviour(
                    ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        return receiveGameBehaviourFactory
                        .createPlayDevelopmentCardBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getReceiveBehaviour(
                    ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        return receiveGameBehaviourFactory
                        .createPlayDevelopmentCardBehaviour(this);
    }

    @Override
    public GameBehaviour getSendBehaviour(
                    GameBehaviourFactory gameBehaviourFactory)
    {
        return gameBehaviourFactory.createBuyDevelopmentcardBehaviour(this);
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.actions.gameAction.AbstractGameAction#createServerAction(soc
     * .common.server.actions.ServerActionFactory)
     */
    @Override
    public ServerAction createServerAction(GameServerActionFactory factory)
    {
        return factory.createBuyDevelopmentCardServerAction(this);
    }

}
