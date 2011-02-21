package soc.gwtClient.game.widgetsInterface.generic;

import com.google.gwt.user.client.ui.IsWidget;

public interface LimitedColorPicker extends IsWidget
{

    public boolean isColorSelected();

    public String getSelectedColor();

    public void setCurrentSelectedColorDisabled();

    public void enableColor(String color);

    public String getAnyAvailableColor();

    public void selectNextColorIfAvailable();

    public void disableColor(String color);
}