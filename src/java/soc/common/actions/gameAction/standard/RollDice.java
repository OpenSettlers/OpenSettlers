package soc.common.actions.gameAction.standard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turns.AbstractTurnAction;
import soc.common.actions.gameAction.turns.TurnPhaseEnded;
import soc.common.board.HexLocation;
import soc.common.board.hexes.Hex;
import soc.common.board.pieces.abstractPieces.Producable;
import soc.common.board.resources.ResourceList;
import soc.common.game.Game;
import soc.common.game.dices.StandardDice;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;
import soc.common.internationalization.I18n;
import soc.common.server.actions.GameServerActionFactory;
import soc.common.server.actions.ServerAction;
import soc.common.views.behaviour.gameWidget.GameBehaviour;
import soc.common.views.behaviour.gameWidget.factories.GameBehaviourFactory;
import soc.common.views.behaviour.gameWidget.factories.ReceiveGameBehaviourFactory;
import soc.common.views.behaviour.gameWidget.received.ReceiveGameBehaviour;
import soc.common.views.meta.Graphics;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.common.views.widgetsInterface.actions.ActionDetailWidgetFactory;
import soc.common.views.widgetsInterface.actions.ActionWidget;
import soc.common.views.widgetsInterface.actions.ActionWidgetFactory;
import soc.common.views.widgetsInterface.generic.ToolTip;
import soc.common.views.widgetsInterface.payerInfo.ActionDetailWidget;

public class RollDice extends AbstractTurnAction
{
    private static final long serialVersionUID = -7274119531224635888L;
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(null, null, null, null);

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public Graphics graphics()
        {
            // TODO Auto-generated method stub
            return null;
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

        @Override
        public ToolTip createToolTip()
        {
            // TODO Auto-generated method stub
            return null;
        }
    };
    private StandardDice dice;
    private List<Integer> looserPlayers = new ArrayList<Integer>();
    private List<HexLocation> hexesAffected = new ArrayList<HexLocation>();
    private boolean robberBlockingProduction;
    private HashMap<GamePlayer, ResourceList> playersResources = new HashMap<GamePlayer, ResourceList>();

    /**
     * @return the playersResources
     */
    public HashMap<GamePlayer, ResourceList> getPlayersResources()
    {
        return playersResources;
    }

    /**
     * @return the robberBlockingProduction
     */
    public boolean isRobberBlockingProduction()
    {
        return robberBlockingProduction;
    }

    /**
     * @return the looserPlayers
     */
    public List<Integer> getLooserPlayers()
    {
        return looserPlayers;
    }

    /**
     * @return the dice
     */
    public StandardDice getDice()
    {
        return dice;
    }

    public boolean isRobberRolled()
    {
        return dice.getDiceTotal() == 7;
    }

    /**
     * @return the hexesAffected
     */
    public List<HexLocation> getHexesAffected()
    {
        return hexesAffected;
    }

    /**
     * @param dice
     *            the dice to set
     */
    public RollDice setDice(StandardDice dice)
    {
        this.dice = dice;

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

        if (!game.getCurrentTurn().getPlayer().equals(player))
        {
            invalidMessage = "player not on turn";
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
        game.setCurrentDice(dice);

        if (game.getCurrentPhase().isPlayTurns())
        {
            performPlayTurns(game);
        }
        if (game.getCurrentPhase().isDetermineFirstPlayer())
        {
            performDetermineGameStarter(game);
        }

        super.perform(game);
    }

    private void performDetermineGameStarter(Game game)
    {
        message = I18n.get().actions().rollDice(player.getUser().getName(),
                dice.getDiceTotal());
    }

    private void performPlayTurns(Game game)
    {
        // Switch to new DiceRollGamePhase, end current BeforeDiceRoll
        // turn phase
        TurnPhaseEnded beforeDiceRollEnded = (TurnPhaseEnded) new TurnPhaseEnded()
                .setSender(0);
        game.performAction(beforeDiceRollEnded);

        for (GamePlayer p : game.getPlayers())
            playersResources.put(p, new ResourceList());

        HexLocation robber = game.getRobber().getLocation();

        if (dice.getDiceTotal() != 7)
        {
            // gather all resource hexes without the robber
            List<Hex> rolledHexes = new ArrayList<Hex>();
            for (Hex hex : game.getBoard().getHexes())
            {
                if (hex.canHaveResource() && hex.getChit() != null
                        && hex.getChit().getNumber() == dice.getDiceTotal())
                    rolledHexes.add(hex);
            }
            // Iterate over all hexes with resources
            for (Hex hex : rolledHexes)
            {
                boolean hexIsAffected = false;
                // For normal resources, the location of the robber is omitted.
                if (!hex.getLocation().equals(robber))
                {
                    for (Entry<GamePlayer, ResourceList> entry : playersResources
                            .entrySet())
                    {
                        for (Producable producable : entry.getKey()
                                .getProducables().producablesAtHex(hex))
                        {
                            entry.getValue().addList(
                                    producable.produce(hex, game.getRules()));
                        }

                        if (entry.getValue().size() > 0)
                        {
                            hexIsAffected = true;
                        }
                    } // For players
                } // If robber

                if (hexIsAffected)
                    hexesAffected.add(hex.getLocation());
            } // For rolledHexes

            message = player.getUser().getName() + " rolled " + dice.toString();

            // Add the resources to each player and remove them from the bank
            for (Entry<GamePlayer, ResourceList> entry : playersResources
                    .entrySet())
            {
                ResourceList gainedResources = entry.getValue();
                if (gainedResources.size() > 0)
                {
                    game.getBank().moveTo(entry.getKey().getResources(),
                            gainedResources);
                    message += entry.getKey().getUser().getName() + " gained "
                            + gainedResources.toString();
                }
            }
        }
        else
        {
            // Rolled a 7, create list of players to loose cards
            String playerList = "";

            for (GamePlayer p1 : game.getPlayers())
            {
                if (p1.getResources().size() > p1
                        .getMaximumCardsInHandWhenSeven())
                {
                    looserPlayers.add(p1.getUser().getId());
                    // Add comma and playername to message
                    playerList += looserPlayers.size() > 0 ? ", "
                            + p1.getUser().getName() : p1.getUser().getName();
                }
            }

            // Enqueue all looserplayers
            for (int playerID : looserPlayers)
            {
                GamePlayer player = game.getPlayerByID(playerID);
                game.getActionsQueue().enqueue(
                        new LooseCards().setPlayer(player), false);
            }

            // Enqueue moving the robber
            game.getActionsQueue().enqueue(new PlaceRobber().setPlayer(player),
                    true);

            // Enqueue robbing a player
            game.getActionsQueue().enqueue(new RobPlayer().setPlayer(player),
                    true);

            message = player.getUser().getName() + " rolled a 7. ";

            if (looserPlayers.size() > 0)
            {
                message = message + playerList
                        + " loose half of their resources";
            }
            else
            {
                message += "No players lost any cards.";
            }
        }

        // After rolling the dice and all intermediate actions such as move
        // robber, pick gold, etc we switch to the next turn phase
        game.getActionsQueue().enqueue(
                (GameAction) new TurnPhaseEnded().setSender(0), true);
    }

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return turnPhase.isBeforeDiceRoll();
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        return gamePhase.isPlayTurns() || gamePhase.isDetermineFirstPlayer();
    }

    @Override
    public String getToDoMessage()
    {
        return I18n.get().actions().rollDiceToDo(player.getUser().getName());
    }

    @Override
    public ActionWidget createActionWidget(
            ActionWidgetFactory actionWidgetFactory)
    {
        return actionWidgetFactory.createRollDiceWidget();
    }

    @Override
    public GameBehaviour getNextActionBehaviour(
            GameBehaviourFactory gameBehaviourFactory)
    {
        return gameBehaviourFactory.createRollDiceBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getOpponentReceiveBehaviour(
            ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        return receiveGameBehaviourFactory.createRollDiceBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getReceiveBehaviour(
            ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        return receiveGameBehaviourFactory.createRollDiceBehaviour(this);
    }

    @Override
    public GameBehaviour getSendBehaviour(
            GameBehaviourFactory gameBehaviourFactory)
    {
        return gameBehaviourFactory.createRollDiceBehaviour(this);
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
     * soc.common.actions.gameAction.AbstractGameAction#createActionDetailWidget
     * (soc.common.ui.ActionDetailWidgetFactory)
     */
    @Override
    public ActionDetailWidget createActionDetailWidget(
            ActionDetailWidgetFactory factory)
    {
        return factory.getRollDiceDetailWidget(this);
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
        return factory.createRollDiceServerAction(this);
    }
}
