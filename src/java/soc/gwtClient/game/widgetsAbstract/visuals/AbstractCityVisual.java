package soc.gwtClient.game.widgetsAbstract.visuals;

import soc.common.board.pieces.City;
import soc.gwtClient.game.widgetsInterface.visuals.CityVisual;

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
