package com.MeetMe.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="", layout = MainLayout.class)
@PageTitle("MeetMe Home")
public class HomeView extends Composite {
    VerticalLayout layout = new VerticalLayout();

    protected Component initContent(){
        layout.add(
                new H2("Click, meet and have fun!")
        );

        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        return layout;
    }
}
