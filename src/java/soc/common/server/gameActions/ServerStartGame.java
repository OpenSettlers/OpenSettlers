package soc.common.server.gameActions;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.actions.gameAction.turns.HostStartsGame;
import soc.common.board.Board;
import soc.common.game.developmentCards.DevelopmentCardList;
import soc.common.game.player.GamePlayerImpl;
import soc.common.server.GameServer;
import soc.common.server.entities.AnonymousUser;

public class ServerStartGame implements ServerAction
{
    HostStartsGame hostStartsGame;
    GameServer gameServer;

    public ServerStartGame(GameServer gameServer, HostStartsGame hostStartsGame)
    {
        super();
        this.hostStartsGame = hostStartsGame;
        this.gameServer = gameServer;
    }

    @Override
    public AbstractGameAction getAction()
    {
        return hostStartsGame;
    }

    @Override
    public void execute()
    {
        // Make sure we have a valid game instance. If not, make a default one.
        if (hostStartsGame.getGame() == null)
            createNewGame();
        else
            hostStartsGame.getGame().initialize();

        gameServer.startGame(hostStartsGame.getGame());
        gameServer.getGame().performAction(hostStartsGame);
    }

    private void createNewGame()
    {
        gameServer.getGame()
                        .getPlayers()
                        .add((GamePlayerImpl) new GamePlayerImpl().setUser(
                                        new AnonymousUser().setId(1).setName(
                                                        "Piet")).setColor(
                                        "yellow"));
        gameServer.getGame()
                        .getPlayers()
                        .add((GamePlayerImpl) new GamePlayerImpl().setUser(
                                        new AnonymousUser().setId(2).setName(
                                                        "Kees")).setColor(
                                        "white"));
        gameServer.getGame()
                        .getPlayers()
                        .add((GamePlayerImpl) new GamePlayerImpl().setUser(
                                        new AnonymousUser().setId(3).setName(
                                                        "Truus")).setColor(
                                        "green"));
        gameServer.getGame()
                        .getPlayers()
                        .add((GamePlayerImpl) new GamePlayerImpl().setUser(
                                        new AnonymousUser().setId(4).setName(
                                                        "Klaas")).setColor(
                                        "red"));
        gameServer.getGame()
                        .getPlayers()
                        .add((GamePlayerImpl) new GamePlayerImpl().setUser(
                                        new AnonymousUser().setId(5).setName(
                                                        "Henk")).setColor(
                                        "blue"));

        // for (GamePlayer player : gameServer.getGame().getPlayers())
        // {
        // player.getResources().add(new Timber());
        // player.getResources().add(new Timber());
        // player.getResources().add(new Timber());
        // player.getResources().add(new Timber());
        // player.getResources().add(new Timber());
        // player.getResources().add(new Timber());
        // player.getResources().add(new Clay());
        // player.getResources().add(new Clay());
        // player.getResources().add(new Clay());
        // player.getResources().add(new Clay());
        // player.getResources().add(new Clay());
        // player.getResources().add(new Clay());
        // }

        gameServer.getGame().setBoard(new Board());
        hostStartsGame.setGame(gameServer.getGame());
    }

    private DevelopmentCardList shuffleDevcardsDeck(DevelopmentCardList devcards)
    {
        // DevelopmentCardList result = new DevelopmentCardList();

        // TODO: reimplement without GWT unsupported Hashtable
        // Create a list to associate random value to each development card
        // Map<Integer, DevelopmentCard> list = new Hashtable<Integer,
        // DevelopmentCard>();

        // Associate the random int value to each development card, put them
        // into sortable treemap
        // /for (DevelopmentCard dev : devcards)
        // list.put(gameServer.getRandom().nextInt(2100000000), dev);

        // Populate result with randomly ordered devcards
        // for (DevelopmentCard dev : list.values())
        // result.add(dev);

        return null;
    }

    @Override
    public AbstractGameAction getOpponentAction()
    {
        return hostStartsGame;
    }
}
