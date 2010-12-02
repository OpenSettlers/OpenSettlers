package soc.common.board.resources;

import soc.common.utils.ClassUtils;

/*
 * Enum design pattern for the resource types. 
 * Each enum member is actually implmented as a class definition
 */
public abstract class Resource
{
    final String name;
    
    public Resource()
    {
        name = ClassUtils.getSimpleClassName(this.getClass().getName());
    }
    @Override
    public String toString()
    {
        return "Resource [name=" + name + "]";
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }
    
    public String getColor()
    {
        throw new RuntimeException();
    }
    
    public abstract Resource Copy();
}
