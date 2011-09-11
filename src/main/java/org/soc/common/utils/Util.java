package org.soc.common.utils;

public class Util {
  /** @see http://code.google.com/p/google-web-toolkit/issues/detail?id=3404 */
  public static String shortName(Class clazz) {
    int firstChar;
    firstChar = clazz.getName().lastIndexOf('.') + 1;
    if (firstChar > 0)
      return clazz.getName().substring(firstChar);
    else
      return clazz.getName();
  }
}
