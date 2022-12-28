package com.SafariApp.ui;

import com.SafariApp.backend.DatabaseConnection;
import com.SafariApp.backend.Event;
import com.SafariApp.backend.Guide;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.xml.crypto.Data;
import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

@PageTitle("Safari create reservation")
@Route(value="add", layout = MainLayout.class)
public class AddView extends Composite {

    protected Component initContent() {
        TextField safariType = new TextField("SafariType");//ComboBox<String> safariType = new ComboBox<>("Safarii type");
//        safariType.setItems(DatabaseConnection.getSafari());
//        safariType.setItemLabelGenerator(Safari::getName);
        safariType.setWidth("25%");
        safariType.setRequired(true);
        safariType.setMinHeight("15px");


        TextField localization = new TextField("Localization");
        localization.setWidth("25%");
        localization.setRequired(true);
        localization.setMinHeight("15px");


        TextField description = new TextField("Description");
        description.setWidth("25%");
        description.setRequired(true);
        description.setMinHeight("15px");


        DateTimePicker dateTimePicker = new DateTimePicker();
        dateTimePicker.setLabel("Safari date and time");
        dateTimePicker.setStep(Duration.ofMinutes(15));
        dateTimePicker.setWidth("25%");

        TextField clientName = new TextField("Name");
        clientName.setWidth("25%");
        clientName.setRequired(true);
        clientName.setMinHeight("15px");

        TextField clientSurname = new TextField("Surname");
        clientSurname.setWidth("25%");
        clientSurname.setRequired(true);
        clientSurname.setMinHeight("15px");

        TextField clientPhone = new TextField("Phone number");
        clientPhone.setWidth("25%");
        clientPhone.setRequired(true);
        clientPhone.setMinHeight("15px");

        EmailField clientEmail = new EmailField("Email");
        clientEmail.setWidth("25%");
        clientEmail.setRequiredIndicatorVisible(true);
        clientEmail.setMinHeight("15px");
        clientEmail.setPlaceholder("username@example.com");


        ComboBox<String> guideComboBox = new ComboBox<>("Guide");
        guideComboBox.setItems(String.valueOf(DatabaseConnection.getGuides()));
//        guideComboBox.setItemLabelGenerator(Guide::getName);
        guideComboBox.setAllowCustomValue(true);
        guideComboBox.setWidth("25%");
        guideComboBox.setRequiredIndicatorVisible(true);
        guideComboBox.setMinHeight("15px");

        Button createButton = new Button("Create",buttonClickEvent -> {
            try {
                Event event = new Event(description.getValue(), safariType.getValue(), dateTimePicker.getValue().toString(), localization.getValue());
                if(event.isActual()) {
                    DatabaseConnection databaseConnection = new DatabaseConnection();
                    databaseConnection.addToDB(event);
                    Notification notificationCreate = Notification.show("Event created!", 5000, Notification.Position.BOTTOM_CENTER);
                    notificationCreate.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                }
                else {
                    throw new Exception();
                }
            }
            catch (Exception e) {
                Notification notificationCreate = Notification.show("Filed to create Event!", 5000, Notification.Position.BOTTOM_CENTER);
                notificationCreate.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }

            description.setValue("");
            safariType.setValue("");
            dateTimePicker.setValue(null);
            localization.setValue("");

        });
        createButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        VerticalLayout layout = new VerticalLayout(
                new H2("Reserved your future Safari!"),
                description,
                safariType,
                dateTimePicker,
                localization,
                new H3("Enter data about you!"),
                clientName,
                clientSurname,
                clientPhone,
                clientEmail,
                new H4("Choose your guide!"),
                guideComboBox,
                createButton
        );

        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        return layout;
    }
}
