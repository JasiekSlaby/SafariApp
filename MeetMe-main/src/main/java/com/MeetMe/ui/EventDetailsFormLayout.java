package com.MeetMe.ui;

import com.MeetMe.backend.Event;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.stream.Stream;

public class EventDetailsFormLayout extends FormLayout {
    private final TextField description = new TextField("description");

    public EventDetailsFormLayout() {
        Stream.of(description).forEach(field -> {
            field.setReadOnly(true);
            add(field);
        });

        setResponsiveSteps(new ResponsiveStep("0", 1));
    }

    public void setEvent(Event event) {
        description.setValue(event.getDescription());
    }
}