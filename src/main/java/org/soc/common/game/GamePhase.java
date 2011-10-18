package org.soc.common.game;

import java.io.*;
import java.util.*;

import org.soc.common.core.GenericList.HasId;
import org.soc.common.core.GenericList.Model;
import org.soc.common.core.OpenZettlers.OsModel;
import org.soc.common.core.Props.PropertyList.PropertyTypeList;
import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.core.property.*;
import org.soc.common.game.InitialPlacement.TwoTowns;
import org.soc.common.game.Turn.TurnImpl;
import org.soc.common.game.TurnPhase.BeforeDiceRollTurnPhase;
import org.soc.common.game.actions.*;
import org.soc.common.game.trading.*;
import org.soc.common.utils.*;
import org.soc.common.views.meta.*;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.common.views.widgetsInterface.main.GamePhaseWidget.GamePhaseStatusWidgetFactory;
import org.soc.gwt.client.images.*;

public interface GamePhase extends Serializable, OsModel<Integer> {
  public void performAction(GameAction action, Game game);
  public void start(Game game);
  public GamePhase next(Game game);
  public Turn nextTurn(Game game);
  public boolean isAllowed(GameAction action);
  public String message();
  public boolean isLobby();
  public boolean isInitialPlacement();
  public boolean isPlayTurns();
  public boolean isEnded();
  public boolean isDetermineFirstPlayer();
  public boolean isPlacePorts();
  public GamePhaseWidget createGamePhaseStatusWidget(GamePhaseStatusWidgetFactory factory);

  /** Represent a phase in the overall game phases A GamePhase ends itself by adding an
   * EndedGamePhase action onto the actionsQueue. */
  public abstract class AbstractGamePhase implements GamePhase {
    private static final long serialVersionUID = -1096857442642768802L;

    public void performAction(GameAction action, Game game) {};
    public Turn nextTurn(Game game) {
      GameAction next = game.actionsQueue().peek();
      if (next != null && notEndTurn(next)) {
        GamePlayer player = null;
        if (next.player().user().id() == 0) {
          player = game.getStartPlayer();
        } else {
          player = next.player();
        }
        return new TurnImpl(player);
      }
      throw new RuntimeException("No expected action, no turn to create");
    }
    public boolean notEndTurn(GameAction action) {
      return !(action instanceof GamePhaseHasEnded);
    }
    public void start(Game game) {};
    public boolean isAllowed(GameAction action) {
      return action.isAllowed(this);
    }
    @Override public Name name() {
      return new Name.Impl(Util.shortName(this.getClass()));
    }
    @Override public boolean isDetermineFirstPlayer() {
      return false;
    }
    @Override public boolean isEnded() {
      return false;
    }
    @Override public boolean isInitialPlacement() {
      return false;
    }
    @Override public boolean isLobby() {
      return false;
    }
    @Override public boolean isPlacePorts() {
      return false;
    }
    @Override public boolean isPlayTurns() {
      return false;
    }
    @Override public Property getProp(Property type) {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public PropertyTypeList properties() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Model copy() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Integer id() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public HasId setId(Integer id) {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public org.soc.common.core.GenericList.HasId.IdScope scope() {
      // TODO Auto-generated method stub
      return null;
    }
  }

  public class DetermineFirstPlayerGamePhase extends AbstractGamePhase
  {
    @Override public Icon icon() {
      return new IconImpl(R.icons()
              .determineFirstPlayerGamePhase16(), R.icons()
              .determineFirstPlayerGamePhase32(), R.icons()
              .determineFirstPlayerGamePhase48());
    }
    @Override public Name name() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Description description() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public void start(Game game)
    {
      // expect each player to roll at least once (first phase: everyone rolls
      // once)
      for (GamePlayer player : game.players())
      {
        game.actionsQueue().enqueue(new RollDice().setPlayer(player),
                true);
      }
      game.actionsQueue()
              .enqueue((GameAction) new GamePhaseHasEnded()
                      .setSender(0),
                      true);
    }
    private int getHighRoll(List<RollDice> rolledDices)
    {
      int result = 2;
      for (RollDice rollDice : rolledDices)
        if (rollDice.getDice().getDiceTotal() > result)
          result = rollDice.getDice().getDiceTotal();
      return result;
    }
    @Override public void performAction(GameAction action, Game game) {
      action.perform(game);
      if (action instanceof RollDice)
      {
        // Check if a phase has ended. If the queue is empty, every player
        // has rolled the dice.
        if (game.actionsQueue().size() == 1) {
          // Make a list of rolls in this round
          List<RollDice> rolledDices = game.log()
                  .currentRoundRolls(game);
          // highroll dice number
          int highRoll = getHighRoll(rolledDices);
          // When starting player is not determined yet, repeat dice roll
          // between winners until
          // winner is determined
          GamePlayer gameStarter = game.log()
                  .firstPlayerIsDetermined(game, highRoll);
          if (gameStarter != null)
          {
            // We have a starting player
            game.actionsQueue()
                    .enqueuePriority(
                            (GameAction) new StartingPlayerDetermined()
                                    // winning dice
                                    .setDiceRoll(highRoll)
                                    // The starter of the
                                    // placement/portplacement/turnactionsgamephase
                                    .setGameStarter(gameStarter)
                                    // Server will send this message
                                    .setSender(0),
                            true);
          } else {
            // Starting player is not determined.
            // Prepare list of same rolling players for RolledSame
            // instance
            List<Integer> sameRollingPlayers = new ArrayList<Integer>();
            // Enqueue each high roller
            for (RollDice sameRoll : rolledDices) {
              if (sameRoll.getDice().getDiceTotal() == highRoll) {
                game.actionsQueue()
                        .enqueuePriority(
                                new RollDice().setPlayer(sameRoll
                                        .player()),
                                true);
                sameRollingPlayers.add(sameRoll.player().user().id());
              }
            }
            // First execute a RolledSame action
            game.actionsQueue()
                    .enqueuePriority(
                            (GameAction) new RolledSame()
                                    // Pass on the highest diceroll
                                    .setHighRoll(highRoll)
                                    .setSameRollingPlayers(
                                            sameRollingPlayers)
                                    // Server says dice rolled the
                                    // same
                                    .setSender(0),
                            true);
          }
        }
      }
      if (game.actionsQueue().peek() instanceof RollDice) {
        game.advanceTurn();
      }
    }
    /* (non-Javadoc)
     * 
     * @see org.soc.common.game.gamePhase.GamePhase#next(org.soc.common.game.Game) */
    @Override public GamePhase next(Game game) {
      return new InitialPlacementGamePhase();
      //
      // // Determine if we should skip placing ports
      // // randomports are assigned at start using the port lists on each
      // // territory.
      // // The remaining ports are placed in the placement phase
      // List<Port> allPorts = new ArrayList<Port>();
      // for (Territory t : game.getBoard().getTerritories())
      // {
      // for (Port p : t.getPorts())
      // {
      // allPorts.add(p);
      // }
      // }
      // if (allPorts.size() == 0)
      // {
      // // We do not have any ports to set, skip to placement phase
      // return new InitialPlacementGamePhase();
      // }
      // else
      // {
      // // players should place ports
      // return new InitialPlacementGamePhase();
      // }
    }
    @Override public String message() {
      // TODO: fix message
      return "Determine game starter";
    }
    @Override public Turn nextTurn(Game game) {
      if (game.getStartPlayer() != null) {
        return new TurnImpl(game.getStartPlayer());
      }
      return super.nextTurn(game);
    }
    @Override public boolean isDetermineFirstPlayer() {
      return true;
    }
    @Override public GamePhaseWidget createGamePhaseStatusWidget(
            GamePhaseStatusWidgetFactory factory) {
      return factory.createDetermineFirstPlayerPhaseWidget(this);
    }
  }

  public class EndedGamePhase extends AbstractGamePhase {
    @Override public Icon icon() {
      return new IconImpl(R.icons().endedGamePhase16(),
              R.icons().endedGamePhase32(), R.icons()
                      .endedGamePhase48());
    }
    @Override public Name name() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Description description() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public String message() {
      // TODO fix message
      return "Game has ended";
    }
    /* Returns null, there is no next turn when game ended
     * 
     * @see org.soc.common.game.gamePhase.AbstractGamePhase#nextTurn(org.soc.common.game. Game) */
    @Override public Turn nextTurn(Game game) {
      return null;
    }
    @Override public boolean isEnded() {
      return true;
    }
    @Override public GamePhaseWidget createGamePhaseStatusWidget(
            GamePhaseStatusWidgetFactory factory)
    {
      return factory.createEndedPhaseWidget(this);
    }
    @Override public GamePhase next(Game game) {
      return null;
    }
  }

  public class InitialPlacementGamePhase extends AbstractGamePhase {
    private InitialPlacement placementStrategy = new TwoTowns();

    @Override public Icon icon() {
      return new IconImpl(R.icons()
              .initialPlacementGamePhase16(), R.icons()
              .initialPlacementGamePhase32(), R.icons()
              .initialPlacementGamePhase48());
    }
    @Override public Name name() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Description description() {
      // TODO Auto-generated method stub
      return null;
    }
    /* Empty default constructor */
    public InitialPlacementGamePhase() {}
    public InitialPlacementGamePhase(InitialPlacement strategy) {
      this.placementStrategy = strategy;
    }
    @Override public void start(Game game) {
      placementStrategy.preparePlacement(game);
      // Set end of phase
      game.actionsQueue()
              .enqueue((GameAction) new GamePhaseHasEnded()
                      .setSender(0),
                      true);
    }
    @Override public void performAction(GameAction gameAction, Game game) {
      gameAction.perform(game);
      // Advance turn when need to
      GameAction next = game.actionsQueue().peek();
      if (!next.isServer()
              && !next.player().equals(
                      game.turn().player()))
        game.advanceTurn();
    }
    @Override public GamePhase next(Game game) {
      return new PlayTurnsGamePhase();
    }
    @Override public String message() {
      // TODO fix message
      return "Place initial towns and roads";
    }
    @Override public boolean isInitialPlacement() {
      return true;
    }
    @Override public GamePhaseWidget createGamePhaseStatusWidget(
            GamePhaseStatusWidgetFactory factory) {
      return factory.createInitialPlacementPhaseWidget(this);
    }
    public InitialPlacement getPlacementStrategy() {
      return placementStrategy;
    }
  }

  public class LobbyGamePhase extends AbstractGamePhase {
    private List<GamePlayer> playersWhoAcceptedSettings = new ArrayList<GamePlayer>();

    @Override public Icon icon() {
      return new IconImpl(R.icons().lobbyGamePhase16(), R.icons().lobbyGamePhase32(), R
              .icons()
              .lobbyGamePhase48());
    }
    @Override public Name name() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Description description() {
      // TODO Auto-generated method stub
      return null;
    }
    public List<GamePlayer> settinggsAccepters() {
      return playersWhoAcceptedSettings;
    }
    @Override public GamePhase next(Game game) {
      return new DetermineFirstPlayerGamePhase();
    }
    @Override public void performAction(GameAction action, Game game) {
      action.perform(game);
    }
    public void resetPlayersWhoAcceptedSettings(GamePlayer playerChangingSettings) {
      // Create a list of players to remove
      GamePlayerList playersToRemove = new GamePlayerList();
      for (GamePlayer player : playersWhoAcceptedSettings)
        if (player != playerChangingSettings)
          playersToRemove.add(player);
      // Invalidate players
      playersWhoAcceptedSettings.clear();
    }
    @Override public String message() {
      // TODO fix message
      return "Wait for players to join";
    }
    /** First on turn is simply the first player */
    @Override public Turn nextTurn(Game game) {
      return new TurnImpl(game.players().get(0));
    }
    @Override public boolean isLobby() {
      return true;
    }
    @Override public GamePhaseWidget createGamePhaseStatusWidget(
            GamePhaseStatusWidgetFactory factory) {
      return factory.createLobbyPhaseWidget(this);
    }
  }

  public class PlacePortsGamePhase extends AbstractGamePhase {
    @Override public Icon icon() {
      return new IconImpl(null);
    }
    @Override public Name name() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Description description() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public void performAction(GameAction action, Game game) {
      action.perform(game);
    }
    /** Adds a PlacePort action for each port on the map, starting with the game starter (highroller
     * player) */
    @Override public void start(Game game) {
      int portCount = 0;
      // Enqueue PlacePort actions for every port found on the board
      for (Territory t : game.board().getTerritories())
        for (@SuppressWarnings("unused")
        Port port : t.ports())
        {
          game.actionsQueue()
                  .enqueue(new PlacePort()
                          /* Placing ports goes chronologically starting with the winner. The first
                           * player always has the advantage: - For example with 5 ports and 4
                           * players, first player may place twice while the rest only once. - First
                           * player may place first, conveniently placing port alongside - Since
                           * port stack is open, first player placing last port is 100% certain
                           * known port */
                          // pass territoryID such that player
                          // knows to expect
                          // possible port locations
                          .setTerritoryID(t.id())
                          .setPlayer(game.players()
                                  .get(portCount
                                          % game.players()
                                                  .size())),
                          // :P nice work autoformat ;)
                          true);
          portCount++;
        }
      // Mark end of this phase
      game.actionsQueue()
              .enqueue((GameAction) new GamePhaseHasEnded()
                      .setSender(0),
                      true);
    }
    @Override public GamePhase next(Game game) {
      return new InitialPlacementGamePhase();
    }
    @Override public String message() {
      // TODO fix message
      return "Place ports";
    }
    @Override public boolean isPlacePorts() {
      return true;
    }
    @Override public GamePhaseWidget createGamePhaseStatusWidget(
            GamePhaseStatusWidgetFactory factory) {
      return factory.createPlacePortsPhaseWidget(this);
    }
  }

  public class PlayTurnsGamePhase extends AbstractGamePhase {
    // Current phase of the player on turn
    private TurnPhase turnPhase = new BeforeDiceRollTurnPhase();

    @Override public Icon icon() {
      return new IconImpl(R.icons()
              .playTurnsGamePhase16(), R.icons()
              .playTurnsGamePhase32(), R.icons()
              .playTurnsGamePhase48());
    }
    @Override public Name name() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Description description() {
      // TODO Auto-generated method stub
      return null;
    }
    public TurnPhase turnPhase() {
      return turnPhase;
    }
    public void setTurnPhase(TurnPhase newPhase) {
      turnPhase = newPhase;
    }
    @Override public GamePhase next(Game game) {
      return new EndedGamePhase();
    }
    @Override public void performAction(GameAction action, Game game) {
      if (turnPhase.isTrading() && !(action instanceof TradeAction)
              && !(action instanceof TurnPhaseEnded))
      {
        // Non-trading action detected in trading turn phase, advance phase
        TurnPhaseEnded endedTradePhase = (TurnPhaseEnded) new TurnPhaseEnded()
                .setSender(0);
        game.performAction(endedTradePhase);
      }
      action.perform(game);
    }
    @Override public void start(Game game) {
      game.advanceTurn();
      super.start(game);
    }
    @Override public boolean isAllowed(GameAction action) {
      return turnPhase.isAllowed(action);
    }
    @Override public String message() {
      // TODO fix message
      return turnPhase.getMessage();
    }
    @Override public Turn nextTurn(Game game) {
      Turn newTurn = null;
      if (game.turn().getID() == 0) {
        // First turn in PlayTurnsGamePhase has always ID=1
        newTurn = new TurnImpl(game.players().get(0), 1, turnPhase);
      } else {
        // Determine index of next player
        int nextPlayerIndex = game.players().indexOf(
                game.turn().player()) + 1;
        if (nextPlayerIndex == game.players().size()) {
          nextPlayerIndex = 0;
        }
        // Grab the player next on turn
        GamePlayer newPlayerOnTurn = game.players().get(nextPlayerIndex);
        // Create a new turn with increased ID numer
        newTurn = new TurnImpl(newPlayerOnTurn, game.turn()
                .getID() + 1, turnPhase);
      }
      return newTurn;
    }
    @Override public boolean isPlayTurns() {
      return true;
    }
    @Override public GamePhaseWidget createGamePhaseStatusWidget(
            GamePhaseStatusWidgetFactory factory) {
      return factory.createPlayTurnsPhaseWidget(this);
    }
  }
}