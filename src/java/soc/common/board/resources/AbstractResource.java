package soc.common.board.resources;

import soc.common.utils.ClassUtils;

/*
 * Enum design pattern for the resource types. 
 * Each enum member is actually implemented as a class definition
 */
public abstract class AbstractResource implements Resource
{
    private static final long serialVersionUID = 9035129584531194942L;
    final String name;

    public AbstractResource()
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        return obj.getClass() == this.getClass();
    }

}
