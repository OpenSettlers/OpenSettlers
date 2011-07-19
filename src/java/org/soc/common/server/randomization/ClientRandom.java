package org.soc.common.server.randomization;

/*
 * Random generator from the browser
 */
public class ClientRandom implements Random
{
    @Override
    public int nextInt(int max, boolean base1)
    {
        int rnd = com.google.gwt.user.client.Random.nextInt(max);
        return base1 ? rnd + 1 : rnd;
    }

}
