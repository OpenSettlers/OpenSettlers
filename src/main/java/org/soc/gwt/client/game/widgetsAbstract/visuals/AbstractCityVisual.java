package org.soc.gwt.client.game.widgetsAbstract.visuals;

import org.soc.common.game.pieces.City;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.CityVisual;

public class AbstractCityVisual extends AbstractPieceVisual implements
        CityVisual
{
  protected City city;

  public AbstractCityVisual(City city)
  {
    super();
    this.city = city;
  }
  @Override public City getCity()
  {
    return city;
  }
  /* (non-Javadoc)
   * 
   * @see org.soc.gwt.client.game.widgetsAbstract.visuals.AbstractPieceVisual#getCityVisual () */
  @Override public CityVisual getCityVisual()
  {
    return this;
  }
}
