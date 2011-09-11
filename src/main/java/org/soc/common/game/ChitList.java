package org.soc.common.game;

import java.util.ArrayList;

import org.soc.common.core.Core;
import org.soc.common.game.Chit.Chit10;
import org.soc.common.game.Chit.Chit11;
import org.soc.common.game.Chit.Chit12;
import org.soc.common.game.Chit.Chit2;
import org.soc.common.game.Chit.Chit3;
import org.soc.common.game.Chit.Chit4;
import org.soc.common.game.Chit.Chit5;
import org.soc.common.game.Chit.Chit6;
import org.soc.common.game.Chit.Chit8;
import org.soc.common.game.Chit.Chit9;

/*
 * Represents a list of chits
 */
public class ChitList extends ArrayList<Chit> {
  private static final long serialVersionUID = -6385792842493976623L;

  /** Returns a chitlist from standard settlers ruleset Returns 18 chits */
  public static ChitList newStandard4p() {
    ChitList result = new ChitList();
    result.add(new Chit2());
    result.add(new Chit12());
    result.add(new Chit3());
    result.add(new Chit3());
    result.add(new Chit11());
    result.add(new Chit11());
    result.add(new Chit4());
    result.add(new Chit4());
    result.add(new Chit10());
    result.add(new Chit10());
    result.add(new Chit5());
    result.add(new Chit5());
    result.add(new Chit9());
    result.add(new Chit9());
    result.add(new Chit6());
    result.add(new Chit6());
    result.add(new Chit8());
    result.add(new Chit8());
    return result;
  }
  /** Returns standard 4vp list, minus one 6/8 and one 3/11 Returns 16 chits */
  public static ChitList newStandard3p() {
    ChitList result = new ChitList();
    result.add(new Chit2());
    result.add(new Chit12());
    result.add(new Chit3());
    result.add(new Chit11());
    if (Core.get().random().nextInt(1, false) == 0)
      result.add(new Chit3());
    else
      result.add(new Chit11());
    result.add(new Chit4());
    result.add(new Chit4());
    result.add(new Chit10());
    result.add(new Chit10());
    result.add(new Chit5());
    result.add(new Chit5());
    result.add(new Chit9());
    result.add(new Chit9());
    result.add(new Chit6());
    result.add(new Chit8());
    if (Core.get().random().nextInt(1, false) == 0)
      result.add(new Chit6());
    else
      result.add(new Chit8());
    return result;
  }
  /** Returns a Seafarers swapbag for Greater Crouton maps A swapbag has 7 numbers: 2,3,4,5, 9,10,11 */
  public static ChitList createSwapBag() {
    ChitList result = new ChitList();
    result.add(new Chit2());
    result.add(new Chit3());
    result.add(new Chit11());
    result.add(new Chit4());
    result.add(new Chit10());
    result.add(new Chit5());
    result.add(new Chit9());
    return result;
  }
  /** Returns a random instance from this list */
  public Chit grabRandom(Random random) {
    int randomIndex = random.nextInt(size(), false);
    Chit chit = get(randomIndex);
    remove(randomIndex);
    return chit;
  }
  /** broken */
  public Chit pickRandomNon68Chit(Random random) {
    ChitList listOf68Chits = get68Chits();
    if (listOf68Chits.size() == 0) {
      throw new RuntimeException("No 6/8 chit found whil expected");
    }
    // TODO: fix
    return null; // listOf68Chits.pickRandomChit(random);
  }
  public ChitList get68Chits() {
    ChitList result = new ChitList();
    for (Chit chit : this)
      if (chit.isRed())
        result.add(chit);
    return result;
  }
  /* Counts amount of chits with given chitnumber */
  public int amount(int number) {
    int result = 0;
    for (Chit chit : this)
      if (chit.number() == number)
        result++;
    return result;
  }
  public ChitList copy() {
    ChitList copy = new ChitList();
    for (Chit chit : this)
      copy.add(chit);
    return copy;
  }
}
