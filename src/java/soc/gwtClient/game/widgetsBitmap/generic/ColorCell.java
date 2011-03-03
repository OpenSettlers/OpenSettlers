package soc.gwtClient.game.widgetsBitmap.generic;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class ColorCell extends AbstractCell<String>
{
    // @Override
    // public void render(com.google.gwt.cell.client.Cell.Context context,
    // String value, SafeHtmlBuilder sb)
    // {
    // /*
    // * Always do a null check on the value. Cell widgets can pass null to
    // * cells if the underlying data contains a null, or if the data arrives
    // * out of order.
    // */
    // if (value == null)
    // {
    // return;
    // }
    // // Append some HTML that sets the text color.
    // sb.appendHtmlConstant("<div style=\"background: " + value
    // + "; border: 2px solid black;\">");
    // sb.appendHtmlConstant(".   .</div>");
    // }

    @Override
    public void render(String value, Object key, SafeHtmlBuilder sb)
    {
        /*
         * Always do a null check on the value. Cell widgets can pass null to
         * cells if the underlying data contains a null, or if the data arrives
         * out of order.
         */
        if (value == null)
        {
            return;
        }
        // Append some HTML that sets the text color.
        sb.appendHtmlConstant("<div style=\"background: " + value
                + "; border: 2px solid black;\">");
        sb.appendHtmlConstant(".   .</div>");
    }
}