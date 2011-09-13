package org.soc.common.game;

import java.io.Serializable;
import java.util.Random;

import org.soc.common.internationalization.I;
import org.soc.common.utils.Util;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.meta.Meta;
import org.soc.gwt.client.images.R;

public interface Chit extends Serializable, Meta {
  public Chit copy();
  public int number();
  public boolean isRed();
  public int chance();

  public abstract class AbstractChit implements Chit {
    private static final long serialVersionUID = -387353658984520813L;

    public AbstractChit() {}
    public static Chit pickRandomChit(Random random) {
      AbstractChit result = null;
      int chitno = (int) (random.nextInt(10));
      switch (chitno) {
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
    @Override public boolean isRed() {
      return number() == 6 || number() == 8;
    }
    @Override public String name() {
      return Util.shortName(this.getClass());
    }
  }

  public class RandomChit implements Chit {
    @Override public Icon icon() {
      return new IconImpl(R.icons().randomChit16(), R.icons().randomChit32(), R
              .icons().randomChit48());
    }
    @Override public String getLocalizedName() {
      return I.get().constants().chitRandom();
    }
    @Override public String getDescription() {
      return I.get().constants().chitRandomDescription();
    }
    @Override public Chit copy() {
      return new RandomChit();
    }
    @Override public int chance() {
      return 0;
    }
    @Override public int number() {
      return 0;
    }
    @Override public boolean isRed() {
      return false;
    }
    @Override public String name() {
      return "RandomChit";
    }
  }

  public class Chit2 extends AbstractChit {
    @Override public Icon icon() {
      return new IconImpl(R.icons().chit216(), R.icons().chit232(), R.icons().chit248());
    }
    @Override public String getLocalizedName() {
      return I.get().constants().chit2();
    }
    @Override public String getDescription() {
      return I.get().constants().chit2Description();
    }
    @Override public Chit copy() {
      return new Chit2();
    }
    @Override public int chance() {
      return 1;
    }
    @Override public int number() {
      return 2;
    }
  }

  public class Chit3 extends AbstractChit {
    @Override public Icon icon() {
      return new IconImpl(R.icons().chit316(), R.icons().chit332(), R.icons().chit348());
    }
    @Override public String getLocalizedName() {
      return I.get().constants().chit3();
    }
    @Override public String getDescription() {
      return I.get().constants().chit3Description();
    }
    @Override public Chit copy() {
      return new Chit3();
    }
    @Override public int chance() {
      return 2;
    }
    @Override public int number() {
      return 3;
    }
  }

  public class Chit4 extends AbstractChit {
    @Override public Icon icon() {
      return new IconImpl(R.icons().chit416(), R.icons().chit432(), R.icons().chit448());
    }
    @Override public String getLocalizedName() {
      return I.get().constants().chit4();
    }
    @Override public String getDescription() {
      return I.get().constants().chit4Description();
    }
    @Override public Chit copy() {
      return new Chit4();
    }
    @Override public int chance() {
      return 3;
    }
    @Override public int number() {
      return 4;
    }
  }

  public class Chit5 extends AbstractChit {
    @Override public Icon icon() {
      return new IconImpl(R.icons().chit516(), R.icons().chit532(), R.icons().chit548());
    }
    @Override public String getLocalizedName() {
      return I.get().constants().chit5();
    }
    @Override public String getDescription() {
      return I.get().constants().chit5Description();
    }
    @Override public Chit copy() {
      return new Chit5();
    }
    @Override public int chance() {
      return 4;
    }
    @Override public int number() {
      return 5;
    }
  }

  public class Chit6 extends AbstractChit {
    @Override public Icon icon() {
      return new IconImpl(R.icons().chit616(), R.icons().chit632(), R.icons().chit648());
    }
    @Override public String getLocalizedName() {
      return I.get().constants().chit6();
    }
    @Override public String getDescription() {
      return I.get().constants().chit6Description();
    }
    @Override public Chit copy() {
      return new Chit6();
    }
    @Override public int chance() {
      return 5;
    }
    @Override public int number() {
      return 6;
    }
  }

  public class Chit8 extends AbstractChit {
    @Override public Icon icon() {
      return new IconImpl(R.icons().chit816(), R.icons().chit832(), R.icons().chit848());
    }
    @Override public String getLocalizedName() {
      return I.get().constants().chit8();
    }
    @Override public String getDescription() {
      return I.get().constants().chit8Description();
    }
    @Override public Chit copy() {
      return new Chit8();
    }
    @Override public int chance() {
      return 5;
    }
    @Override public int number() {
      return 8;
    }
  }

  public class Chit9 extends AbstractChit {
    @Override public Icon icon() {
      return new IconImpl(R.icons().chit916(), R.icons().chit932(), R.icons().chit948());
    }
    @Override public String getLocalizedName() {
      return I.get().constants().chit9();
    }
    @Override public String getDescription() {
      return I.get().constants().chit9Description();
    }
    @Override public Chit copy() {
      return new Chit9();
    }
    @Override public int number() {
      return 9;
    }
    @Override public int chance() {
      return 4;
    }
  }

  public class Chit10 extends AbstractChit {
    @Override public Icon icon() {
      return new IconImpl(R.icons().chit1016(), R.icons().chit1032(), R.icons().chit1048());
    }
    @Override public String getLocalizedName() {
      return I.get().constants().chit10();
    }
    @Override public String getDescription() {
      return I.get().constants().chit10Description();
    }
    @Override public Chit copy() {
      return new Chit10();
    }
    @Override public int chance() {
      return 3;
    }
    @Override public int number() {
      return 10;
    }
  }

  public class Chit11 extends AbstractChit {
    @Override public Icon icon() {
      return new IconImpl(R.icons().chit1116(), R.icons().chit1132(), R.icons().chit1148());
    }
    @Override public String getLocalizedName() {
      return I.get().constants().chit11();
    }
    @Override public String getDescription() {
      return I.get().constants().chit11Description();
    }
    @Override public Chit copy() {
      return new Chit11();
    }
    @Override public int chance() {
      return 2;
    }
    @Override public int number() {
      return 11;
    }
  }

  public class Chit12 extends AbstractChit {
    @Override public Icon icon() {
      return new IconImpl(R.icons().chit1216(), R.icons().chit1232(), R.icons().chit1248());
    }
    @Override public String getLocalizedName() {
      return I.get().constants().chit12();
    }
    @Override public String getDescription() {
      return I.get().constants().chit12Description();
    }
    @Override public Chit copy() {
      return new Chit12();
    }
    @Override public int chance() {
      return 1;
    }
    @Override public int number() {
      return 12;
    }
  }
}
