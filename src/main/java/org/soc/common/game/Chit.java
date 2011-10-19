package org.soc.common.game;

import java.util.Random;

import org.soc.common.core.GenericList.HasId;
import org.soc.common.core.OpenZettlers.OsModel;
import org.soc.common.core.Props.IsChild;
import org.soc.common.core.Props.PropertyList.PropertyTypeList;
import org.soc.common.core.property.*;

public interface Chit extends OsModel<Integer> {
  public Chit copy();
  public int number();
  public boolean isRed();
  public int chance();

  public interface Supported {
    public final static Chit2 chit2 = new Chit2();
    public final static Chit3 chit3 = new Chit3();
    public final static Chit4 chit4 = new Chit4();
    public final static Chit5 chit5 = new Chit5();
    public final static Chit6 chit6 = new Chit6();
    public final static Chit8 chit8 = new Chit8();
    public final static Chit9 chit9 = new Chit9();
    public final static Chit10 chit10 = new Chit10();
    public final static Chit11 chit11 = new Chit11();
    public final static Chit12 chit12 = new Chit12();
    public final static RandomChit randomChit = new RandomChit();
  }

  public abstract class AbstractChit implements Chit {
    protected int number;
    protected int chance;

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
    @Override public Integer id() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public HasId setId(Integer id) {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public org.soc.common.core.GenericList.HasId.IdScope scope() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public IsChild getProp(StaticProperty type) {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public PropertyTypeList properties() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public int number() {
      return number;
    }
    @Override public int chance() {
      return chance;
    }
  }

  public class RandomChit implements Chit {
    @Override public int chance() {
      return 0;
    }
    @Override public int number() {
      return 0;
    }
    @Override public boolean isRed() {
      return false;
    }
    @Override public Integer id() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public HasId setId(Integer id) {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public org.soc.common.core.GenericList.HasId.IdScope scope() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public IsChild getProp(StaticProperty type) {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public PropertyTypeList properties() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Chit copy() {
      // TODO Auto-generated method stub
      return null;
    }
  }

  public class Chit2 extends AbstractChit {
    public Chit2() {
      super();
      chance = 1;
      number = 2;
    }
    @Override public Chit copy() {
      return new Chit2();
    }
  }

  public class Chit3 extends AbstractChit {
    @Override public Chit copy() {
      return new Chit3();
    }
    public Chit3() {
      super();
      chance = 2;
      number = 3;
    }
  }

  public class Chit4 extends AbstractChit {
    @Override public Chit copy() {
      return new Chit4();
    }
    public Chit4() {
      super();
      chance = 3;
      number = 4;
    }
  }

  public class Chit5 extends AbstractChit {
    @Override public Chit copy() {
      return new Chit5();
    }
    public Chit5() {
      super();
      chance = 4;
      number = 5;
    }
  }

  public class Chit6 extends AbstractChit {
    @Override public Chit copy() {
      return new Chit6();
    }
    public Chit6() {
      super();
      chance = 5;
      number = 6;
    }
  }

  public class Chit8 extends AbstractChit {
    @Override public Chit copy() {
      return new Chit8();
    }
    public Chit8() {
      super();
      chance = 5;
      number = 8;
    }
  }

  public class Chit9 extends AbstractChit {
    @Override public Chit copy() {
      return new Chit9();
    }
    public Chit9() {
      super();
      chance = 5;
      number = 9;
    }
  }

  public class Chit10 extends AbstractChit {
    @Override public Chit copy() {
      return new Chit10();
    }
    public Chit10() {
      super();
      chance = 3;
      number = 10;
    }
  }

  public class Chit11 extends AbstractChit {
    public Chit11() {
      super();
      number = 11;
      chance = 2;
    }
    @Override public Chit copy() {
      return new Chit11();
    }
  }

  public class Chit12 extends AbstractChit {
    @Override public Chit copy() {
      return new Chit12();
    }
    public Chit12() {
      super();
      chance = 1;
      number = 12;
    }
  }
}
