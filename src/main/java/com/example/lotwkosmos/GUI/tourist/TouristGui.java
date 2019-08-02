package com.example.lotwkosmos.GUI.tourist;

import com.example.lotwkosmos.model.Tourist;
import com.example.lotwkosmos.repository.FlyRepo;
import com.example.lotwkosmos.repository.TouristRepo;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route ("touristsGui")
@StyleSheet("static/TouristGui.css")
public class TouristGui extends VerticalLayout {

    @Autowired
    TouristRepo touristRepo;
    @Autowired
    FlyRepo flyRepo;

@Autowired
    public TouristGui(TouristRepo touristRepo, FlyRepo flyRepo) {
        this.touristRepo =  touristRepo;
        this.flyRepo = flyRepo;
        Grid<Tourist> touristGrid = new Grid<>();
        List<Tourist> touristList = touristRepo.findAll();
        TouristForm formGui = new TouristForm(touristRepo,flyRepo);
        touristGrid.setItems(touristList);
        touristGrid.addColumn(Tourist::getName).setHeader("name");
        touristGrid.addColumn(Tourist::getSurname).setHeader("surname");
        touristGrid.addColumn(Tourist::getCountry).setHeader("country");
        touristGrid.addColumn(Tourist::getNotes).setHeader("notes");
        touristGrid.addColumn(Tourist::getLocalDate).setHeader("dateOfBirth");
        touristGrid.addColumn(t -> t.writeFlyIds()).setHeader("Fly id List");

        touristGrid.asSingleSelect().addValueChangeListener(e -> {
            formGui.setTourist(touristGrid.asSingleSelect().getValue());
        });

//        add(touristGrid,formGui);

   Div div = new Div();

   div.add(touristGrid);
   div.add(formGui);

   add(div);

//


    }



}
