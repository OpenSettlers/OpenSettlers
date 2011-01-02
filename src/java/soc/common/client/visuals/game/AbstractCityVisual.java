package soc.common.client.visuals.game;

import soc.common.board.pieces.City;
import soc.common.client.visuals.AbstractPieceVisual;

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
