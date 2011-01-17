package soc.common.actions.gameAction.turnActions.standard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.TurnPhaseEnded;
import soc.common.actions.gameAction.turnActions.AbstractTurnAction;
import soc.common.board.HexLocation;
import soc.common.board.hexes.Hex;
import soc.common.board.hexes.ResourceHex;
import soc.common.board.pieces.Producable;
import soc.common.board.resources.ResourceList;
import soc.common.game.Game;
import soc.common.game.dices.StandardDice;
import soc.common.game.gamePhase.DetermineFirstPlayerGamePhase;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.common.game.gamePhase.turnPhase.BeforeDiceRollTurnPhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;
import soc.common.internationalization.I18n;

public class RollDice extends AbstractTurnAction
{
    private static final long serialVersionUID = -7274119531224635888L;
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

        if (game.getCurrentPhase() instanceof PlayTurnsGamePhase)
        {
            performPlayTurns(game);
        }
        if (game.getCurrentPhase() instanceof DetermineFirstPlayerGamePhase)
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
        {
            playersResources.put(p, new ResourceList());
        }
        HexLocation robber = game.getRobber().getLocation();

        if (dice.getDiceTotal() != 7)
        {
            // gather all resource hexes without the robber
            List<ResourceHex> rolledHexes = new ArrayList<ResourceHex>();
            for (Hex hex : game.getBoard().getHexes())
            {
                if (hex instanceof ResourceHex)
                {
                    ResourceHex resourceHex = (ResourceHex) hex;
                    if (resourceHex.getChit() != null)
                    {
                        if (resourceHex.getChit().getNumber() == dice
                                .getDiceTotal())
                        {
                            rolledHexes.add(resourceHex);
                        }
                    }
                }
            }
            // Iterate over all hexes with resources
            for (ResourceHex hex : rolledHexes)
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
                            entry.getValue().add(
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

            // Add th resources to each player and remove them from the bank
            for (Entry<GamePlayer, ResourceList> entry : playersResources
                    .entrySet())
            {
                ResourceList gainedResources = entry.getValue();
                if (gainedResources.size() > 0)
                {
                    game.getBank().moveTo(gainedResources,
                            player.getResources());
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
        return turnPhase instanceof BeforeDiceRollTurnPhase;
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        return gamePhase instanceof PlayTurnsGamePhase
                || gamePhase instanceof DetermineFirstPlayerGamePhase;
    }

    @Override
    public String getToDoMessage()
    {
        return I18n.get().actions().rollDiceToDo(player.getUser().getName());
    }
}
