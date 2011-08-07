package org.soc.common.server.rating;

import org.soc.common.game.variants.Variant;

/*
 * Rating for contained variant
 */
public interface VariantRating extends Rating
{
    public Variant getVariant();
}
