package soc.gwtClient.images;

import com.google.gwt.core.client.GWT;

public class Images
{
    private static OpenSettlersImages images = GWT
            .create(OpenSettlersImages.class);

    public static OpenSettlersImages get()
    {
        return images;
    }

}
