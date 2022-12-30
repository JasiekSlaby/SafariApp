package com.SafariApp.ui;

import com.SafariApp.backend.Client;
import com.SafariApp.backend.Event;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.stream.Stream;

public class EventDetailsFormLayout extends FormLayout {
    private final TextField description = new TextField("description");
    private final TextField descriptionClient = new TextField("client");

    public EventDetailsFormLayout() {
        Stream.of(description).forEach(field -> {
            field.setReadOnly(true);
            add(field);
        });

        Stream.of(descriptionClient).forEach(fieldClient -> {
            fieldClient.setReadOnly(true);
            add(fieldClient);
        });

        setResponsiveSteps(new ResponsiveStep("0", 1));
    }

    public void setEvent(Event event) {
        description.setValue(event.getDescription());
    }

    public void setClient(Client client) {
        descriptionClient.setValue(client.getName());
        descriptionClient.setValue(client.getSurname());
        descriptionClient.setValue(client.getPhoneNumber());
        descriptionClient.setValue(client.getEmail());
    }

}