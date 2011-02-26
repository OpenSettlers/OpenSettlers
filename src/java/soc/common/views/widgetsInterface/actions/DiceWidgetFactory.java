package soc.common.views.widgetsInterface.actions;


/*
 * Creates an instance of IDiceWidget based upon a dice type
 */
public interface DiceWidgetFactory
{
    public DiceWidget createStandardDiceWidget();

    public DiceWidget createCitiesKnightDiceWidget();

    public DiceWidget createCardsDeckDiceWidget();

    public DiceWidget createVolcanoDiceWidget();
}
