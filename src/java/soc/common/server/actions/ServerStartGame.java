package soc.common.server.actions;

import java.util.ArrayList;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.actions.gameAction.HostStartsGame;
import soc.common.game.Game;
import soc.common.game.GameBoard;
import soc.common.game.GamePlayerImpl;
import soc.common.game.developmentCards.DevelopmentCardList;
import soc.common.game.variants.Standard;
import soc.common.game.variants.Variant;
import soc.common.server.GameServer;
import soc.common.server.data.UnregisteredUser;

public class ServerStartGame implements ServerAction
{
    HostStartsGame hostStartsGame;
    GameServer gameServer;

    public ServerStartGame(HostStartsGame hostStartsGame, GameServer gameServer)
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
        createNewGame();

    }

    private void createNewGame()
    {
        Game result = new Game();
        ArrayList<Variant> rules = new ArrayList<Variant>();
        rules.add(new Standard(result));
        result.getPlayers().add(
                (GamePlayerImpl) new GamePlayerImpl().setUser(
                        new UnregisteredUser().setId(1).setName("Piet"))
                        .setColor("yellow"));
        result.getPlayers().add(
                (GamePlayerImpl) new GamePlayerImpl().setUser(
                        new UnregisteredUser().setId(1).setName("Kees"))
                        .setColor("white"));
        result.getPlayers().add(
                (GamePlayerImpl) new GamePlayerImpl().setUser(
                        new UnregisteredUser().setId(1).setName("Truus"))
                        .setColor("green"));
        result.getPlayers().add(
                (GamePlayerImpl) new GamePlayerImpl().setUser(
                        new UnregisteredUser().setId(1).setName("Klaas"))
                        .setColor("red"));
        result.getPlayers().add(
                (GamePlayerImpl) new GamePlayerImpl().setUser(
                        new UnregisteredUser().setId(1).setName("Henk"))
                        .setColor("blue"));

        result.setBoard(new GameBoard(8, 8));

        hostStartsGame.setGame(result);
    }

    private DevelopmentCardList shuffleDevcardsDeck(DevelopmentCardList devcards)
    {
        DevelopmentCardList result = new DevelopmentCardList();

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
