package soc.common.server.rating;

import soc.common.game.variants.Variant;

public interface VariantRating extends Rating
{
    public Variant getVariant();
}
