package soc.gwtClient.images;

import soc.common.board.resources.Clay;
import soc.common.board.resources.Ore;
import soc.common.board.resources.Resource;
import soc.common.board.resources.Sheep;
import soc.common.board.resources.Timber;
import soc.common.board.resources.Wheat;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;

public class Resources
{
    private static Icons icons = GWT.create(Icons.class);

    public static Icons icons()
    {
        return icons;
    }

    public static ImageResource card(Resource resource)
    {
        if (resource instanceof Wheat)
        {
            return icons.wheatCard();
        }
        if (resource instanceof Timber)
        {
            return icons.timberCard();
        }
        if (resource instanceof Ore)
        {
            return icons.oreCard();
        }
        if (resource instanceof Sheep)
        {
            return icons.sheepCard();
        }
        if (resource instanceof Clay)
        {
            return icons.clayCard();
        }

        return null;
    }

}
