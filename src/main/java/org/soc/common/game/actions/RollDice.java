package org.soc.common.game.actions;

import java.util.*;
import java.util.Map.Entry;

import org.soc.common.game.Dice.StandardDice;
import org.soc.common.game.*;
import org.soc.common.game.Resources.MutableResourceList;
import org.soc.common.game.Resources.MutableResourceListImpl;
import org.soc.common.game.Resources.ResourceList;
import org.soc.common.game.actions.Action.ActionPresenter.ActionWidgetFactory;
import org.soc.common.game.actions.TurnAction.AbstractTurnAction;
import org.soc.common.game.board.*;
import org.soc.common.game.hexes.*;
import org.soc.common.game.pieces.Piece.Producer;
import org.soc.common.internationalization.*;
import org.soc.common.server.actions.*;
import org.soc.common.views.widgetsInterface.actions.*;
import org.soc.common.views.widgetsInterface.actions.ActionDetailWidget.ActionDetailWidgetFactory;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.common.views.widgetsInterface.visuals.*;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.HexVisual;

public class RollDice extends AbstractTurnAction {
  private StandardDice dice;
  private List<Integer> looserPlayers = new ArrayList<Integer>();
  private List<HexLocation> hexesAffected = new ArrayList<HexLocation>();
  private boolean robberBlockingProduction;
  private HashMap<GamePlayer, MutableResourceList> playersResources = new HashMap<GamePlayer, MutableResourceList>();

  public HashMap<GamePlayer, MutableResourceList> getPlayersResources() {
    return playersResources;
  }
  public boolean isRobberBlockingProduction() {
    return robberBlockingProduction;
  }
  public List<Integer> getLooserPlayers() {
    return looserPlayers;
  }
  public StandardDice getDice() {
    return dice;
  }
  public boolean isRobberRolled() {
    return dice.getDiceTotal() == 7;
  }
  public List<HexLocation> getHexesAffected() {
    return hexesAffected;
  }
  public RollDice setDice(StandardDice dice) {
    this.dice = dice;
    return this;
  }
  @Override public boolean isValid(Game game) {
    if (!super.isValid(game))
      return false;
    if (!game.turn().player().equals(player)) {
      invalidMessage = "player not on turn";
      return false;
    }
    return true;
  }
  @Override public void perform(Game game) {
    game.setDice(dice);
    if (game.phase().isPlayTurns()) {
      performPlayTurns(game);
    }
    if (game.phase().isDetermineFirstPlayer()) {
      performDetermineGameStarter(game);
    }
    super.perform(game);
  }
  private void performDetermineGameStarter(Game game) {
    message = I.get().actions().rollDice(player.user().name(),
            dice.getDiceTotal());
  }
  /* TODO: chop code and move to API */
  private void performPlayTurns(Game game) {
    // Switch to new DiceRollGamePhase, end current BeforeDiceRoll
    // turn phase
    TurnPhaseEnded beforeDiceRollEnded =
            (TurnPhaseEnded) new TurnPhaseEnded().setSender(0);
    game.performAction(beforeDiceRollEnded);
    for (GamePlayer p : game.players())
      playersResources.put(p, new MutableResourceListImpl());
    HexLocation robberLocation = game.robber().location();
    if (dice.getDiceTotal() != 7) {
      // gather all resource hexes without the robber
      List<Hex> rolledHexes = new ArrayList<Hex>();
      for (Hex hex : game.board().hexes())
        if (hex.producesResource()
                && hex.chit() != null
                && hex.chit().number() == dice
                        .getDiceTotal())
          rolledHexes.add(hex);
      // Iterate over all hexes with resources
      for (Hex hex : rolledHexes) {
        boolean hexIsAffected = false;
        // For normal resources, the location of the robber is omitted.
        if (!hex.location().equals(robberLocation)) {
          for (Entry<GamePlayer, MutableResourceList> entry : playersResources.entrySet()) {
            for (Producer producable : entry.getKey().producers().producersAtHex(hex)) {
              entry.getValue().addList(producable.produce(hex, game.rules()));
            }
            if (entry.getValue().size() > 0) {
              hexIsAffected = true;
            }
          } // For players
        } // If robber
        if (hexIsAffected)
          hexesAffected.add(hex.location());
      } // For rolledHexes
      message = player.user().name() + " rolled " + dice.toString();
      // Add the resources to each player and remove them from the bank
      for (Entry<GamePlayer, MutableResourceList> entry : playersResources.entrySet()) {
        ResourceList gainedResources = entry.getValue();
        if (gainedResources.size() > 0) {
          entry.getKey().resources().moveListFrom(game.bank(), gainedResources);
          message += entry.getKey().user().name() + " gained "
                  + gainedResources.toString();
        }
      }
    } else {
      // Rolled a 7, create list of players to loose cards
      String playerList = "";
      for (GamePlayer p1 : game.players()) {
        if (p1.resources().size() > p1.maximumCardsInHandWhenSeven()) {
          looserPlayers.add(p1.user().id());
          // Add comma and playername to message
          playerList += looserPlayers.size() > 0 ? ", "
                  + p1.user().name() : p1.user()
                  .name();
        }
      }
      // Enqueue all looserplayers
      for (int playerID : looserPlayers) {
        GamePlayer player = game.playerById(playerID);
        game.actionsQueue().enqueue(new LooseCards().setPlayer(player), false);
      }
      // Enqueue moving the robber
      game.actionsQueue().enqueue(new PlaceRobber().setPlayer(player), true);
      // Enqueue robbing a player
      game.actionsQueue().enqueue(new RobPlayer().setPlayer(player), true);
      message = player.user().name() + " rolled a 7. ";
      if (looserPlayers.size() > 0) {
        message = message + playerList
                + " loose half of their resources";
      } else {
        message += "No players lost any cards.";
      }
    }
    // After rolling the dice and all intermediate actions such as move
    // robber, pick gold, etc we switch to the next turn phase
    game.actionsQueue().enqueue(
            (GameAction) new TurnPhaseEnded().setSender(0), true);
  }
  @Override public boolean isAllowed(TurnPhase turnPhase) {
    return turnPhase.isBeforeDiceRoll();
  }
  @Override public boolean isAllowed(GamePhase gamePhase) {
    return gamePhase.isPlayTurns() || gamePhase.isDetermineFirstPlayer();
  }
  @Override public String toDoMessage() {
    return I.get().actions().rollDiceToDo(player.user().name());
  }
  @Override public ActionPresenter createPresenter(
          ActionWidgetFactory actionWidgetFactory)
  {
    return actionWidgetFactory.createRollDiceWidget();
  }
  @Override public GameBehaviour opponentReceived(GameWidget gameWidget) {
    if (gameWidget.game().phase().isDetermineFirstPlayer()) { /* TODO: implement */}
    if (gameWidget.game().phase().isPlayTurns()) {
      return new RollDiceInGame(gameWidget, this);
    }
    return null;
  }
  @Override public GameBehaviour received(GameWidget gameWidget) {
    if (gameWidget.game().phase().isDetermineFirstPlayer()) {}
    if (gameWidget.game().phase().isPlayTurns()) {
      return new RollDiceInGame(gameWidget, this);
    }
    return null;
  }
  @Override public ActionDetailWidget createDetailWidget(ActionDetailWidgetFactory factory) {
    return factory.getRollDiceDetailWidget(this);
  }
  @Override public ServerAction createServerAction(GameServerActionFactory factory) {
    return factory.createRollDice(this);
  }

  public static class RollDiceOnGameBoard implements ActionOnGameBoard {
    private RollDice rollDice;

    public RollDiceOnGameBoard(RollDice rollDice) {
      this.rollDice = rollDice;
    }
    /** Sets all hexes back to not darkened and enabled */
    @Override public void setNeutral(GameBoardVisual visual) {
      for (HexVisual hexVisual : visual.hexVisuals().values()) {
        hexVisual.setDarkened(false);
        hexVisual.setEnabled(true);
      }
    }
    /** Shows affected hexes lit up, robber hex red (disabled) and unaffected hexes darkened */
    @Override public void start(GameBoardVisual gameVisual) {
      for (HexVisual hexVisual : gameVisual.hexVisuals().values()) {
        if (rollDice.getHexesAffected().contains(hexVisual.hex().location())) {
          hexVisual.setDarkened(false); // If the hex is affected by the dice roll, light it up
        } else {
          if (hexVisual.hex().location().equals(
                  gameVisual.game().robber().location())) {
            hexVisual.setDarkened(false); // Robber location, make it look disabled
          }
          else {
            hexVisual.setDarkened(true); // No robber, not affected: set it dark and enabled
          }
        }
      }
    }
    @Override public void clicked(PieceVisual pieceVisual, GameBoardVisual board) {}
    @Override public void mouseEnter(PieceVisual pieceVisual, GameBoardVisual board) {}
    @Override public void mouseOut(PieceVisual pieceVisual, GameBoardVisual board) {}
    @Override public GameAction gameAction() {
      return rollDice;
    }
  }

  public static class RollDiceInGame implements GameBehaviour {
    private RollDice rolledDice;
    private RollDiceOnGameBoard rollDiceBehaviour;
    private GameWidget gameWidget;

    public RollDiceInGame(GameWidget gameWidget, RollDice rolledDice) {
      super();
      this.gameWidget = gameWidget;
      this.rolledDice = rolledDice;
      rollDiceBehaviour = new RollDiceOnGameBoard(rolledDice);
    }
    @Override public void start(GameWidget gameWidget) {
      if (rolledDice.isRobberRolled()) {
        if (rolledDice.getLooserPlayers().size() > 0)
          gameWidget.getLooseCardsDialog().update(rolledDice, this);
        else
          // Rolled 7, nothing to see here, move along.
          gameWidget.doneReceiving();
      } else {
        // Show the hexes which have been rolled
        gameWidget.boardVisualWidget().boardVisual().setBehaviour(rollDiceBehaviour);
        // Grab an ActionDetailWidget
        ActionDetailWidgetFactory factory = gameWidget.clientFactory().actionDetailWidgetFactory();
        ActionDetailWidget widget = rolledDice.createDetailWidget(factory);
        // Show the ActionDetailWidget
        gameWidget.detailWidgets().showActionWidget(widget);
        gameWidget.resourcesGainedWidget().update(this);
        gameWidget.actionsWidget().setEnabled(false);
      }
    }
    @Override public void finish() {}
    public void doneLoosingCards() {
      gameWidget.doneReceiving();
    }
    public void doneResources() {
      rollDiceBehaviour.setNeutral(gameWidget.boardVisualWidget().boardVisual());
      gameWidget.detailWidgets().hideCurrentWidget();
      gameWidget.actionsWidget().setEnabled(true);
      gameWidget.doneReceiving();
    }
    @Override public boolean endsManually() {
      return true;
    }
  }
}
