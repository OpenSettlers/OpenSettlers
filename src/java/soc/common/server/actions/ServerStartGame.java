package soc.common.server.actions;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.user.client.Random;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.actions.gameAction.HostStartsGame;
import soc.common.actions.gameAction.turnActions.StartGame;
import soc.common.board.Board;
import soc.common.game.Game;
import soc.common.game.Player;
import soc.common.game.developmentCards.DevelopmentCard;
import soc.common.game.developmentCards.DevelopmentCardList;
import soc.common.game.variants.IVariant;
import soc.common.game.variants.Standard;

public class ServerStartGame implements ServerAction
{
    HostStartsGame hostStartsGame;
    Random random;
    
    public ServerStartGame(HostStartsGame hostStartsGame, Random random)
    {
        super();
        this.hostStartsGame = hostStartsGame;
        this.random=random;
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
        ArrayList<IVariant> rules = new ArrayList<IVariant>();
        rules.add(new Standard(result));
        result.setRuleSets(rules);
        ArrayList<Player> players = new ArrayList<Player>();
        players.add
        ((Player)
            new Player()
                .setColor("yellow")
                .setId(1)
                .setName("Piet")
        );
        players.add
        ((Player)
            new Player()
                .setColor("white")
                .setId(1)
                .setName("Kees")
        );
        players.add
        ((Player)
            new Player()
                .setColor("green")
                .setId(1)
                .setName("Truus")
        );
        players.add((Player)
            new Player()
                .setColor("red")
                .setId(1)
                .setName("Klaas")
        );
        players.add
        ((Player)
            new Player()
                .setColor("blue")
                .setId(1)
                .setName("Henk")
        );
        
        result.setBoard(new Board(8,8));

        result.setPlayers(players);
        
        result.makeRulesPermanent();
        
        hostStartsGame.setGame(result);
    }

    private DevelopmentCardList shuffleDevcardsDeck(DevelopmentCardList devcards)
    {
        DevelopmentCardList result = new DevelopmentCardList();

        // initialize list
        DevelopmentCardList allDevs = new DevelopmentCardList();

        // Create a list to associate random value to each development card
        HashMap<Integer, DevelopmentCard> list = new HashMap<Integer, DevelopmentCard>();

        // Associate the random int value to each development card
        for (DevelopmentCard dev : allDevs)
            list.put(random.nextInt(), dev);

        // Sort the list by the random number
        // TODO: port to java
        //var sorted = from item in list
        //             orderby item.Key
        //             select item;

        // Copy values to resultlist and add proper ID
        //int id = 0;
        //foreach (KeyValuePair<int, DevelopmentCard> pair in sorted)
        //{
        //    pair.Value.ID = id;
        //    result.Add(pair.Value);
        //    id++;
        //}

        return null;
    }

    @Override
    public AbstractGameAction getOpponentAction()
    {
        // TODO Auto-generated method stub
        return null;
    }
}
