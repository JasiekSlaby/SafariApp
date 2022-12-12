package com.MeetMe.ui;

import com.MeetMe.backend.DatabaseConnection;
import com.MeetMe.backend.Event;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.Duration;

@PageTitle("MeetMe create event")
@Route(value="add", layout = MainLayout.class)
public class AddView extends Composite {

    protected Component initContent() {
        TextField title = new TextField("Title");
        title.setWidth("25%");
        title.setRequired(true);
        title.setMinHeight("15px");


        TextField localization = new TextField("Localization");
        localization.setWidth("25%");
        localization.setRequired(true);
        localization.setMinHeight("15px");


        TextField description = new TextField("Description");
        description.setWidth("25%");
        description.setRequired(true);
        description.setMinHeight("15px");


        DateTimePicker dateTimePicker = new DateTimePicker();
        dateTimePicker.setLabel("Meeting date and time");
        dateTimePicker.setStep(Duration.ofMinutes(15));
        dateTimePicker.setWidth("25%");

        Button createButton = new Button("Create",buttonClickEvent -> {
            try {
                Event event = new Event(title.getValue(), dateTimePicker.getValue().toString(), localization.getValue(), description.getValue());
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


            title.setValue("");
            dateTimePicker.setValue(null);
            localization.setValue("");
            description.setValue("");
        });
        createButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        VerticalLayout layout = new VerticalLayout(
                new H2("Create your own event!"),
                title,
                dateTimePicker,
                localization,
                description,
                createButton
        );

        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        return layout;
    }
}
