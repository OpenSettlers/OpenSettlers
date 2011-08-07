package org.soc.gwt.client.game.widgetsBitmap.generic;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.soc.common.views.widgetsInterface.generic.LimitedColorPicker;


import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RadioButton;

public class LimitedColorPickerBitmapWidget extends Composite implements
        ValueChangeHandler<Boolean>, LimitedColorPicker
{
    HorizontalPanel rootPanel = new HorizontalPanel();
    private RadioButton radiobuttonYellow;
    private RadioButton radiobuttonOrange;
    private RadioButton radiobuttonBlue;
    private RadioButton radiobuttonGrey;
    private RadioButton radiobuttonGreen;
    private RadioButton radiobuttonRed;
    private Map<String, RadioButton> colorsRadioButtons = new HashMap<String, RadioButton>();
    private OnColorChanged handler;

    public LimitedColorPickerBitmapWidget()
    {
        super();

        initWidget(rootPanel);

        radiobuttonYellow = new RadioButton("color", "");
        radiobuttonYellow.setValue(true);
        radiobuttonYellow.setStyleName("color-option-yellow");
        radiobuttonYellow.addValueChangeHandler(this);
        rootPanel.add(radiobuttonYellow);

        radiobuttonOrange = new RadioButton("color", "");
        radiobuttonOrange.setStyleName("color-option-orange");
        radiobuttonOrange.addValueChangeHandler(this);
        rootPanel.add(radiobuttonOrange);

        radiobuttonBlue = new RadioButton("color", "");
        radiobuttonBlue.setStyleName("color-option-blue");
        radiobuttonBlue.addValueChangeHandler(this);
        rootPanel.add(radiobuttonBlue);

        radiobuttonGrey = new RadioButton("color", "");
        radiobuttonGrey.setStyleName("color-option-white");
        radiobuttonGrey.addValueChangeHandler(this);
        rootPanel.add(radiobuttonGrey);

        radiobuttonGreen = new RadioButton("color", "");
        radiobuttonGreen.setStyleName("color-option-green");
        radiobuttonGreen.addValueChangeHandler(this);
        rootPanel.add(radiobuttonGreen);

        radiobuttonRed = new RadioButton("color", "");
        radiobuttonRed.setStyleName("color-option-red");
        radiobuttonRed.addValueChangeHandler(this);
        rootPanel.add(radiobuttonRed);

        createColorsRadioButtonsMap();
    }

    private void createColorsRadioButtonsMap()
    {
        colorsRadioButtons.put("blue", radiobuttonBlue);
        colorsRadioButtons.put("green", radiobuttonGreen);
        colorsRadioButtons.put("white", radiobuttonGrey);
        colorsRadioButtons.put("orange", radiobuttonOrange);
        colorsRadioButtons.put("red", radiobuttonRed);
        colorsRadioButtons.put("yellow", radiobuttonYellow);
    }

    @Override
    public void onValueChange(ValueChangeEvent<Boolean> event)
    {
        if (handler != null)
            handler.onColorChanged();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.gwt.client.game.widgetsBitmap.generic.LimitedColorPicker#isColorSelected
     * ()
     */
    public boolean isColorSelected()
    {
        for (RadioButton radioButton : colorsRadioButtons.values())
            if (radioButton.getValue() && radioButton.isEnabled())
                return true;

        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.gwt.client.game.widgetsBitmap.generic.LimitedColorPicker#getSelectedColor
     * ()
     */
    public String getSelectedColor()
    {
        for (Entry<String, RadioButton> entry : colorsRadioButtons.entrySet())
            // getValue().getValue(). whoa!. Ugh! *throws up*
            if (entry.getValue().getValue())
                return entry.getKey();

        throw new AssertionError(
                "Expected a radiobutton with a selected value. None found :(");
    }

    @Override
    public void setCurrentSelectedColorDisabled()
    {
        String color = getSelectedColor();
        colorsRadioButtons.get(color).setEnabled(false);
    }

    @Override
    public void enableColor(String color)
    {
        colorsRadioButtons.get(color).setEnabled(true);
    }

    @Override
    public String getAnyAvailableColor()
    {
        for (Entry<String, RadioButton> entry : colorsRadioButtons.entrySet())
            // getValue().getValue(). whoa!. Ugh! *throws up*
            if (entry.getValue().isEnabled())
                return entry.getKey();

        return null;
    }

    @Override
    public void selectNextColorIfAvailable()
    {
        for (Entry<String, RadioButton> entry : colorsRadioButtons.entrySet())
            // getValue().getValue(). whoa!. Ugh! *throws up*
            if (entry.getValue().isEnabled())
            {
                entry.getValue().setValue(true);
            }
    }

    @Override
    public void disableColor(String color)
    {
        colorsRadioButtons.get(color).setEnabled(false);
    }

    public void addColorChangedHandler(OnColorChanged handler)
    {
        this.handler = handler;
    }

    public interface OnColorChanged
    {
        public void onColorChanged();
    }
}
