package org.soc.gwt.client.editor;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;

public class BoardSettingsDialog extends DialogBox
{

    public BoardSettingsDialog()
    {
        setHTML("New dialog");

        HorizontalPanel horizontalPanel = new HorizontalPanel();
        setWidget(horizontalPanel);
        horizontalPanel.setSize("662px", "213px");

        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.setSpacing(5);
        horizontalPanel.add(verticalPanel);
        verticalPanel.setWidth("209px");

        CheckBox checkBox = new CheckBox("Standard");
        verticalPanel.add(checkBox);

        CheckBox checkBox_1 = new CheckBox("SeaFarers");
        verticalPanel.add(checkBox_1);

        CheckBox checkBox_2 = new CheckBox("Cities & Knights");
        verticalPanel.add(checkBox_2);

        CheckBox checkBox_3 = new CheckBox("Extended");
        verticalPanel.add(checkBox_3);

        HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
        horizontalPanel_1
                .setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        verticalPanel.add(horizontalPanel_1);

        Image image = new Image((ImageResource) null);
        horizontalPanel_1.add(image);

        Image image_2 = new Image(
                "WEB-INF/classes/org.soc/gwtClient/images/size32/Pioneers.png");
        horizontalPanel_1.add(image_2);

        CheckBox checkBox_4 = new CheckBox("Pioneers");
        horizontalPanel_1.add(checkBox_4);

        HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
        verticalPanel.add(horizontalPanel_2);

        Image image_1 = new Image((ImageResource) null);
        horizontalPanel_2.add(image_1);
        image_1.setSize("32", "32");

        Image image_3 = new Image(
                "WEB-INF/classes/org.soc/gwtClient/images/size32/Sea3D.png");
        horizontalPanel_2.add(image_3);

        CheckBox checkBox_5 = new CheckBox("Sea3D");
        horizontalPanel_2.add(checkBox_5);

        VerticalPanel verticalPanel_1 = new VerticalPanel();
        horizontalPanel.add(verticalPanel_1);

        VerticalPanel verticalPanel_2 = new VerticalPanel();
        horizontalPanel.add(verticalPanel_2);

        VerticalPanel verticalPanel_3 = new VerticalPanel();
        horizontalPanel.add(verticalPanel_3);
    }

}
