package com.SafariApp.ui;

import com.SafariApp.backend.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.time.Duration;

@PageTitle("Safari create reservation")
@Route(value="add", layout = MainLayout.class)
public class AddView extends Composite {

    protected Component initContent() {
//        ComboBox<String> safariType = new ComboBox<>("Safarii type");//        TextField safariType = new TextField("SafariType");
//        safariType.setItems(String.valueOf(DatabaseConnection.getSafari()));
//        safariType.setItemLabelGenerator(Safari::getName);
        ComboBox<Safari> safariType = new ComboBox<>("Safari type");
        ListDataProvider<Safari> dataProvider = DataProvider.ofCollection(DatabaseConnection.getSafari());
        safariType.setDataProvider(dataProvider);
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


//        ComboBox<Guide> guideComboBox = new ComboBox<>("Guide");
//        ListDataProvider<Guide> dataProviderGuide = DataProvider.ofCollection(DatabaseConnection.getGuide());
//        guideComboBox.setDataProvider(dataProviderGuide);
        TextField guideComboBox = new TextField("Guide");
//        guideComboBox.setAllowCustomValue(true);
        guideComboBox.setWidth("25%");
        guideComboBox.setRequiredIndicatorVisible(true);
        guideComboBox.setMinHeight("15px");

        Button createButton = new Button("Create",buttonClickEvent -> {
            try {
                Event event = new Event(safariType.getValue().toString(), dateTimePicker.getValue().toString(), localization.getValue(), description.getValue());
                Client client = new Client(clientName.getValue(), clientSurname.getValue(), clientPhone.getValue(), clientEmail.getValue());
                if(event.isActual()) {
                    DatabaseConnection databaseConnection = new DatabaseConnection();
                    databaseConnection.addToDB(event);
                    databaseConnection.addToDBClient(client);
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
            safariType.setValue(null);
            dateTimePicker.setValue(null);
            localization.setValue("");
            clientName.setValue("");
            clientSurname.setValue("");
            clientPhone.setValue("");
            clientEmail.setValue("");
            guideComboBox.setValue("");

        });
        createButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        VerticalLayout layout = new VerticalLayout(
                new H2("Reserved your future Safari!"),
                description,
                safariType,
                dateTimePicker,
                localization,
                new H2("Enter data about you!"),
                clientName,
                clientSurname,
                clientPhone,
                clientEmail,
                new H2("Choose your guide!"),
                guideComboBox,
                createButton
        );

        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        return layout;
    }
}
