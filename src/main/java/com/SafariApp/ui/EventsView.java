package com.SafariApp.ui;

import com.SafariApp.backend.DatabaseConnection;
import com.SafariApp.backend.Event;
import com.SafariApp.backend.Client;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.awt.*;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.stream.Stream;

@Route(value="events", layout = MainLayout.class)
@PageTitle("Safari all reservation")
public class EventsView extends Composite {
    VerticalLayout layout = new VerticalLayout();
    Grid<Event> grid = new Grid<>(Event.class,false);

    Grid<Client> gridClient = new Grid<>(Client.class,false);


    List<Event> EventList = new ArrayList<>();

    List<Event> ClientList = new ArrayList<>();


    protected Component initContent(){
        configureGrid();
        layout.add(
                new H2("Reservations"),
                grid,
                gridClient
        );

        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        return layout;
    }

    private void configureGrid() {
        grid.addColumn(Event::getSafariType).setHeader("Title").setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(Event::getDate).setHeader("Date").setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(Event::getLocation).setHeader("Location").setTextAlign(ColumnTextAlign.CENTER);
        //        grid.addColumn(Guide::getName).setHeader("Guide Name").setTextAlign(ColumnTextAlign.CENTER);
        grid.setItemDetailsRenderer(createPersonDetailsRenderer());
//        gridClient.setItemDetailsRenderer(createPersonDetailsRendererClient());
        grid.setAllRowsVisible(true);

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.addComponentColumn(item -> {
            Button button = new Button("Move");
            button.addClickListener(event -> {
                //Event selectedItem = item;// = item wtedy usuwa wszystkie wiersze
                EventList.remove(item);
                ClientList.add(item);
                grid.setItems(EventList);
            });
            return button;
        }).setHeader("Action");



//        gridClient.setItems((DataProvider<Client, Void>) ClientList);
        //stary przycisk wyswietla sie odnoscnik do przycisku nie zrenderowany kod
//        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
//        grid.setItems(EventList);
//        grid.addColumn(item -> new Button("Move", event -> {
//            Event selectedItem = item;
//            EventList.remove(selectedItem);
//            ClientList.add(selectedItem);
//            grid.setItems(EventList);
//        })).setHeader("Action");
//        gridClient.setItems((Stream<Client>) ClientList);


        DatabaseConnection databaseConnection = new DatabaseConnection();
        List<Event> people = databaseConnection.getEvents();

        grid.setItems(people);
    }


    private static ComponentRenderer<EventDetailsFormLayout, Event> createPersonDetailsRenderer() {
        return new ComponentRenderer<>(EventDetailsFormLayout::new,
                EventDetailsFormLayout::setEvent);
    }

    private static ComponentRenderer<EventDetailsFormLayout, Client> createPersonDetailsRendererClient() {
        return new ComponentRenderer<>(EventDetailsFormLayout::new,
                EventDetailsFormLayout::setClient);
    }
}
