package soc.common.bots.namingStrategies;

import java.util.ArrayList;
import java.util.List;

import soc.common.server.random.Random;

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
        idioticNames.add("IAmSuchAnIdiot");
        idioticNames.add("Dumber");
        idioticNames.add("Dumb");
        idioticNames.add("LousyBot");
        idioticNames.add("Dimwit");
        idioticNames.add("FoolBot");
        idioticNames.add("JustFunnyHowDumbIAm");
        idioticNames.add("IdiotBot");
        idioticNames.add("StupidityIncorporated");
    }

    @Override
    public String getName()
    {
        return idioticNames.get(random.nextInt(idioticNames.size(), false));
    }

}
