package soc.common.board.chits;

import java.util.Random;

public abstract class AbstractChit implements Chit
{
    private static final long serialVersionUID = -387353658984520813L;

    public AbstractChit()
    {
    }

    public static Chit pickRandomChit(Random random)
    {
        AbstractChit result = null;
        int chitno = (int) (random.nextInt(10));
        switch (chitno)
        {
        case 0:
            result = new Chit2();
        case 1:
            result = new Chit3();
        case 2:
            result = new Chit4();
        case 3:
            result = new Chit5();
        case 4:
            result = new Chit6();
        case 5:
            result = new Chit8();
        case 6:
            result = new Chit9();
        case 7:
            result = new Chit10();
        case 8:
            result = new Chit11();
        case 9:
            result = new Chit12();
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.chits.Chit#isRed()
     */
    @Override
    public boolean isRed()
    {
        return getNumber() == 6 || getNumber() == 8;
    }
}
