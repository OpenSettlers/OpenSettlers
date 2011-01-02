package soc.common.server.random;

/*
 * Random generator from the browser
 */
public class ClientRandom implements Random
{
    @Override
    public int nextInt(int max)
    {
        return com.google.gwt.user.client.Random.nextInt(max);
    }

}
