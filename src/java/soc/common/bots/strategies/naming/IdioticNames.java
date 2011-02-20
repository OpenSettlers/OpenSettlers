package soc.common.bots.strategies.naming;

import java.util.ArrayList;
import java.util.List;

import soc.common.server.randomization.Random;

public class IdioticNames implements NameStrategy
{
    private static List<String> idioticNames = new ArrayList<String>();
    private Random random;

    public IdioticNames(Random random)
    {
        super();
        this.random = random;
    }

    static
    {
        idioticNames.add("SuchAnIdiot");
        idioticNames.add("Dumber");
        idioticNames.add("Dumb");
        idioticNames.add("LousyBot");
        idioticNames.add("Dimwit");
        idioticNames.add("FoolBot");
        idioticNames.add("SoDumbIAm");
        idioticNames.add("IdiotBot");
        idioticNames.add("StupidInc");
    }

    @Override
    public String getName()
    {
        return idioticNames.get(random.nextInt(idioticNames.size(), false));
    }

}
