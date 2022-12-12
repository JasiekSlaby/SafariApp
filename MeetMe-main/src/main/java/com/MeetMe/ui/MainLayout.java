package com.MeetMe.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {
    private final VerticalLayout root = new VerticalLayout();

    public MainLayout() {
        H1 title = new H1("MeetMe!");
        title.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("left", "var(--lumo-space-l)")
                .set("margin", "0")
                .set("position", "absolute");

        VerticalLayout layout = new VerticalLayout();
        Tabs tabs = getTabs();
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.add(tabs);

        addToNavbar(title, layout);
        /*
        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("MeetMe!");
        title.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        Tabs tabs = getTabs();

        addToDrawer(tabs);
        addToNavbar(toggle, title);

         */
    }

    private Tabs getTabs() {
        Tab events = new Tab(VaadinIcon.USERS.create());
        RouterLink home_link = new RouterLink("Events", EventsView.class);
        events.add(home_link);

        Tab addEvent = new Tab(VaadinIcon.PLUS.create());
        RouterLink add_link = new RouterLink("Add new event", AddView.class);
        addEvent.add(add_link);

        Tab login = new Tab(VaadinIcon.SHARE.create());
        RouterLink login_link = new RouterLink("Sign In", LoginView.class);
        login.add(login_link);

        Tab profile = new Tab(
                VaadinIcon.USER.create(),
                new Span("Profile")
        );
        Tab settings = new Tab(
                VaadinIcon.COG.create(),
                new Span("Settings")
        );
        Tab logout = new Tab(
                VaadinIcon.REPLY.create(),
                new Span("Logout")
        );

        profile.setEnabled(false);
        logout.setEnabled(false);

        Tabs tabs = new Tabs(events,addEvent,login,profile,settings,logout);
        //tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.setOrientation(Tabs.Orientation.HORIZONTAL);
        return tabs;
    }
}