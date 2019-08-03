package com.example.lotwkosmos.GUI.tourist;


import com.example.lotwkosmos.enums.SexType;
import com.example.lotwkosmos.model.Fly;
import com.example.lotwkosmos.model.Tourist;
import com.example.lotwkosmos.repository.FlyRepo;
import com.example.lotwkosmos.repository.TouristRepo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridSelectionColumn;
import com.vaadin.flow.component.grid.GridSelectionModel;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.selection.MultiSelect;
import com.vaadin.flow.data.selection.SingleSelect;

import java.util.List;



public class TouristForm extends FormLayout {

    private TextField name = new TextField("name");
    private TextField surmane = new TextField("surname");
    private ComboBox<SexType> sex = new ComboBox<>("sex");
    private Button addTourist = new Button("addFlightToTourist");
    private Button addNewTourist = new Button("addNewTourist/changeExistingTourist");
    private Button deleteTourist = new Button("deleteTourist");
    private TextField country = new TextField("country");
    private TextField notes = new TextField("notes");
    private DatePicker birthday = new DatePicker("birthday");
    private Binder<Tourist> binder = new Binder<>();
    private Grid<Fly> flightsGrid = new Grid<>();
    private List<Fly> listOfFlies;
    private SingleSelect<Grid<Fly>,Fly> selectFlight;

    TouristRepo touristRepo;
    FlyRepo flyRepo;

    public TouristForm(TouristRepo touristRepo,FlyRepo flyRepo) {
        this.flyRepo=flyRepo;
        this.touristRepo = touristRepo;

        setTourist(new Tourist());

        addingFlightByTourist();

        sex.setItems(SexType.values());
        add(name,surmane,sex,country,notes,birthday,flightsGrid,addTourist,addNewTourist);
        add(deleteTourist);
        selectFlight = flightsGrid.asSingleSelect();

        binder.forField(name)
                .bind(Tourist::getName,Tourist::setName);
        binder.forField(surmane)
                .bind(Tourist::getSurname, Tourist::setSurname);
        binder.forField(sex)
                .bind(Tourist::getSex, Tourist::setSex);
        binder.forField(country)
                .bind(Tourist::getCountry, Tourist::setCountry);
        binder.forField(notes)
                .bind(Tourist::getNotes, Tourist::setNotes);
        binder.forField(birthday)
                .bind(Tourist::getLocalDate, Tourist::setLocalDate);


        binder.forField(selectFlight)
                .bind(tourist -> selectFlight.getValue(), Tourist::setFlight);


        addTourist.addClickListener(event -> save());
        deleteTourist.addClickListener(event -> delete());
        addNewTourist.addClickListener(event -> addingNewTourist());
    }



    private void delete() {
        Tourist tourist = binder.getBean();
        List<Fly> lista= tourist.getListOfFlies();
        for(int i=0; i<lista.size();i++){
            lista.get(i).removeTouristToFly(tourist);
            Fly fly = lista.get(i);
            tourist.removeFlytoTourist(fly);
            touristRepo.save(tourist);
            flyRepo.save(fly);
            i--;
        }
//tourist
        touristRepo.delete(tourist);
    }

//

    public void setTourist(Tourist tourist){
        binder.setBean(tourist);
    }

    private void addingNewTourist(){
        Tourist tourist = binder.getBean();
//        System.out.println(tourist);
        touristRepo.save(tourist);
    }

    private void save() {

        Tourist tourist = binder.getBean();
//        System.out.println(tourist);

        Fly fly = selectFlight.getValue();
        List<Fly> listOfFlies = touristRepo.findTouristById(tourist.getId()).getListOfFlies();


        boolean isFlyInList = false;
        for (Fly e: listOfFlies) {
            if( e.equals(fly)) {
                isFlyInList = true;
                break;
            }
        }
        if(isFlyInList==true ){}
        else if(fly.getNumberOfPlaces()>0) {
            fly.addTouristToFly(tourist);
            tourist.addFlyToTourist(fly);
            flyRepo.save(fly);
            touristRepo.save(tourist);
        }

    }
    //
    private void setFlightFields() {
        flightsGrid.addColumn(Fly::getId).setHeader("Flight id");
        flightsGrid.addColumn(Fly::getDepartureTime).setHeader("Departure Date and Time");
        flightsGrid.addColumn(Fly::getArrivalTime).setHeader("Arrival Date and Time");
        flightsGrid.addColumn(Fly::getNumberOfPlaces).setHeader("Nubmer of free places");
        flightsGrid.addColumn(Fly::getTicketCost).setHeader("Cost Of the Ticket");
    }
//
    private void addingFlightByTourist(){
        listOfFlies = flyRepo.findAll();
        flightsGrid.setItems(listOfFlies);
        setFlightFields();
        flightsGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
//
    }

}
