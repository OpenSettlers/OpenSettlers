package soc.gwtClient.visuals.abstractVisuals;

import soc.common.board.pieces.City;

public class AbstractCityVisual extends AbstractPieceVisual implements
        CityVisual
{
    protected City city;

    public AbstractCityVisual(City city)
    {
        super();
        this.city = city;
    }

    @Override
    public City getCity()
    {
        return city;
    }

}
