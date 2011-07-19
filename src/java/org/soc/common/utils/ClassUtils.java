package org.soc.common.utils;

public class ClassUtils
{
    /*
     * Returns the simple classname
     * @see http://code.google.com/p/google-web-toolkit/issues/detail?id=3404
     */
    public static String getSimpleClassName(String longClassName)
    {
        String result;
        
        int firstChar;
        firstChar = longClassName.lastIndexOf ('.') + 1;
        if ( firstChar > 0 ) 
        {
            result = longClassName.substring(firstChar);
        }
        else
        {
            result = longClassName;
        }
        
        return result;
    }
}
