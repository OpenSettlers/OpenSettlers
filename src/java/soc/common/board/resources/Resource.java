package soc.common.board.resources;

import soc.common.utils.ClassUtils;

/*
 * Enum design pattern for the resource types. 
 * Each enum member is actually implemented as a class definition
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
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        return obj.getClass() == this.getClass();
    }
}
