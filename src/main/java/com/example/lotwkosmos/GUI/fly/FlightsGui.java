package com.example.lotwkosmos.GUI.fly;


import com.example.lotwkosmos.model.Fly;
import com.example.lotwkosmos.repository.FlyRepo;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("FlightsGui")
@StyleSheet("static/FlyGuiForm.css")
public class FlightsGui extends VerticalLayout {

    FlyRepo flyRepo;

    @Autowired
    public FlightsGui(FlyRepo flyRepo) {
        this.flyRepo =  flyRepo;
        Grid<Fly> flyGrid = new Grid<>();
        List<Fly> touristList = flyRepo.findAll();
        FlyForm flyForm = new FlyForm(flyRepo);
        flyGrid.setItems(touristList);
        flyGrid.addColumn(Fly::getDepartureTime).setHeader("departureTime");
        flyGrid.addColumn(Fly::getArrivalTime).setHeader("arrivalTime");
        flyGrid.addColumn(Fly::getNumberOfPlaces).setHeader("numberOfPlaces");
        flyGrid.addColumn(Fly::getTicketCost).setHeader("ticketCost");

        add(flyGrid,flyForm);

        flyGrid.asSingleSelect().addValueChangeListener(e -> {
            flyForm.setFlight(flyGrid.asSingleSelect().getValue());
        });


        Div flyDiv = new Div();

        flyDiv.add(flyGrid);
        flyDiv.add(flyForm);

        add(flyDiv);
    }
}
