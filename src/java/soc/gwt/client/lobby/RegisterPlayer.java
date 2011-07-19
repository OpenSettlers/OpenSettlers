package soc.gwt.client.lobby;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class RegisterPlayer extends Composite
{
    private SimpleEventBus eventBus = new SimpleEventBus();
    private Button buttonRegister;
    private Label labelResult;

    public RegisterPlayer()
    {

        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.setSpacing(10);
        initWidget(verticalPanel);
        verticalPanel.setSize("423px", "192px");

        HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_BOTTOM);
        verticalPanel.add(horizontalPanel);

        VerticalPanel verticalPanel_1 = new VerticalPanel();
        horizontalPanel.add(verticalPanel_1);

        Label lblNewLabel = new Label("Nickname:");
        verticalPanel_1.add(lblNewLabel);

        final TextBox textboxNickname = new TextBox();
        verticalPanel_1.add(textboxNickname);

        Label lblOnlyazaz = new Label("only [a-z], [A-Z], [0-9], [-, _]");
        horizontalPanel.add(lblOnlyazaz);

        HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
        verticalPanel.add(horizontalPanel_1);

        VerticalPanel verticalPanel_2 = new VerticalPanel();
        horizontalPanel_1.add(verticalPanel_2);

        Label lblNewLabel_1 = new Label("Password:");
        verticalPanel_2.add(lblNewLabel_1);

        final TextBox textboxPassword = new TextBox();
        verticalPanel_2.add(textboxPassword);

        HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
        horizontalPanel_2
                        .setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        horizontalPanel_2.setSpacing(5);
        horizontalPanel_2
                        .setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        verticalPanel.add(horizontalPanel_2);

        buttonRegister = new Button("Register!");
        buttonRegister.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                eventBus.fireEvent(new RegisterEvent(textboxNickname.getText(),
                                textboxPassword.getText()));
            }
        });
        horizontalPanel_2.add(buttonRegister);

        labelResult = new Label("Working...");
        horizontalPanel_2.add(labelResult);
    }

    public HandlerRegistration addRegisterTryEventHandler(
                    RegisterEventHandler handler)
    {
        return eventBus.addHandler(RegisterEvent.TYPE, handler);
    }

    public void setResult(String resultText)
    {
        labelResult.setText(resultText);
    }
}
