package soc.common.board.hexes;

import soc.common.annotations.Sea3D;
import soc.common.board.Chit;
import soc.common.board.resources.Gold;
import soc.common.board.resources.Resource;
import soc.common.utils.ClassUtils;

@Sea3D
public class VolcanoHex extends ResourceHex
{
    private Resource resource = new Gold();

    /* (non-Javadoc)
     * @see soc.common.board.hexes.ResourceHex#getResource()
     */
    @Override
    public Resource getResource()
    {
        // TODO Auto-generated method stub
        return resource;
    }

    /* (non-Javadoc)
     * @see soc.common.board.hexes.Hex#getColor()
     */
    @Override
    public String getColor()
    {
        // TODO Auto-generated method stub
        return "DarkRed";
    }

    /* (non-Javadoc)
     * @see soc.common.board.hexes.ResourceHex#getName()
     */
    @Override
    public String getName()
    {
        // TODO Auto-generated method stub
        return ClassUtils.getSimpleClassName(this.getClass().getName());
    }

    /* (non-Javadoc)
     * @see soc.common.board.hexes.ResourceHex#Copy()
     */
    @Override
    public Hex Copy()
    {
        // TODO Auto-generated method stub
        return new VolcanoHex()
            .setChit(new Chit(5));
    }

}
