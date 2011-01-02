package soc.common.board;

import java.util.ArrayList;

import soc.common.annotations.SeaFarers;
import soc.common.server.random.Random;

/*
 * Represents a list of chits
 */
public class ChitList extends ArrayList<Chit>
{
    private static final long serialVersionUID = -6385792842493976623L;

    /*
     * Returns a chitlist from standard settlers ruleset
     */
    public static ChitList getStandardList()
    {
        ChitList result = new ChitList();

        result.add(new Chit(2));
        result.add(new Chit(12));

        result.add(new Chit(3));
        result.add(new Chit(3));
        result.add(new Chit(11));
        result.add(new Chit(11));

        result.add(new Chit(4));
        result.add(new Chit(4));
        result.add(new Chit(10));
        result.add(new Chit(10));

        result.add(new Chit(5));
        result.add(new Chit(5));
        result.add(new Chit(9));
        result.add(new Chit(9));

        result.add(new Chit(6));
        result.add(new Chit(6));
        result.add(new Chit(8));
        result.add(new Chit(8));

        return result;
    }

    /*
     * Returns a Seafarers swapbag for Greater Crouton maps A swapbag has 7
     * numbers: 2,3,4,5, 9,10,11
     */
    @SeaFarers
    public static ChitList getSwapBag()
    {
        ChitList result = new ChitList();

        result.add(new Chit(2));

        result.add(new Chit(3));
        result.add(new Chit(11));

        result.add(new Chit(4));
        result.add(new Chit(10));

        result.add(new Chit(5));
        result.add(new Chit(9));

        return result;
    }

    /*
     * Returns a random instance from this list
     */
    public Chit pickRandomChit(Random random)
    {
        int randomIndex = random.nextInt(size());

        return this.get(randomIndex);
    }

    public Chit pickRandomNon68Chit(Random random)
    {
        ChitList listOf68Chits = get68Chits();
        if (listOf68Chits.size() == 0)
        {
            throw new RuntimeException("No 6/8 chit found whil expected");
        }

        return listOf68Chits.pickRandomChit(random);
    }

    public ChitList get68Chits()
    {
        ChitList result = new ChitList();

        for (Chit chit : this)
        {
            if (chit.getNumber() == 6 || chit.getNumber() == 8)
            {
                result.add(chit);
            }
        }

        return result;
    }

    /*
     * Counts amount of chits with given chitnumber
     */
    public int amount(int number)
    {
        int result = 0;

        for (Chit chit : this)
        {
            if (chit.getNumber() == number)
                result++;
        }

        return result;
    }
}
