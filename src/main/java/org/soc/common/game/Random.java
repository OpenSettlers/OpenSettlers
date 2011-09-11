package org.soc.common.game;

/** Wrapper interface for retrieval of random numbers. Abstracted to support web services providing
 * dice rolls, hardware random generators etc */
public interface Random {
  public int nextInt(int max, boolean base1);

  /** Random generator from the browser */
  public class ClientRandom implements Random {
    @Override public int nextInt(int max, boolean base1) {
      int rnd = com.google.gwt.user.client.Random.nextInt(max);
      return base1 ? rnd + 1 : rnd;
    }
  }

  /** Random generator for server or junit tests */
  public class ServerRandom implements Random {
    java.util.Random random = new java.util.Random();

    @Override public int nextInt(int max, boolean base1) {
      int rnd = random.nextInt(max);
      return base1 ? rnd + 1 : rnd;
    }
  }
}
