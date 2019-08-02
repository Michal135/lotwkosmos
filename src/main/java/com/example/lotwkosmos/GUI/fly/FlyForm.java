package com.example.lotwkosmos.GUI.fly;


import com.example.lotwkosmos.model.Fly;
import com.example.lotwkosmos.repository.FlyRepo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.Binder;

public class FlyForm extends FormLayout {

    private DatePicker departureDate = new DatePicker("departureDate");
    private TimePicker departureTime = new TimePicker("departureTime");

    private DatePicker arrivalDate = new DatePicker("arrivalDate");
    private TimePicker arrivalTime = new TimePicker("arrivalTime");

    private NumberField numberOfPlaces = new NumberField("numberOfPlaces");

    private NumberField ticketCost = new NumberField("ticketCost");
    private Button addFlightButton = new Button("addFlight");
    private Binder<Fly> binder = new Binder<>();

    FlyRepo flyRepo;


    public FlyForm(FlyRepo flyRepo) {
        this.flyRepo = flyRepo;
        add(departureDate,arrivalDate,numberOfPlaces,ticketCost,addFlightButton);
        setFlight(new Fly());

        binder.forField(departureDate)
                .bind(Fly::getDepartureDateWithoutTime,Fly::setDepartureDateWithoutTime);
        binder.forField(departureTime)
                .bind(Fly::getDepartureTimeWithoutDate,Fly::setDepartureTimeWithoutDate);

        binder.forField(arrivalDate)
                .bind(Fly::getArrivalDateWithoutTime,Fly::setArrivalDateWithoutTime);
        binder.forField(arrivalTime)
                .bind(Fly::getArrivalTimeWithoutDate,Fly::setArrivalTimeWithoutDate);



        binder.forField(numberOfPlaces)
                .bind(flight -> (double) flight.getNumberOfPlaces(),
                        (flight, Double) -> flight.setNumberOfPlaces(numberOfPlaces.getValue().intValue()));

        binder.forField(ticketCost)
                .bind(Fly::getTicketCost,Fly::setTicketCost);

        addFlightButton.addClickListener(event -> save());
    }

    //

    private void save() {
        Fly flight = binder.getBean();
        flyRepo.save(flight);
    }


    public void setFlight(Fly flight){
        binder.setBean(flight);
    }

}
