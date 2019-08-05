package com.example.lotwkosmos.controller;

import com.example.lotwkosmos.model.Fly;
import com.example.lotwkosmos.model.LongWrapper;
import com.example.lotwkosmos.model.Relationships;
import com.example.lotwkosmos.model.Tourist;
import com.example.lotwkosmos.repository.FlyRepo;
import com.example.lotwkosmos.repository.TouristRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TouristController {

    @Autowired
    TouristRepo touristRepo;

    @Autowired
    FlyRepo flyRepo;

    //dodawanie turysty
    @PostMapping("/addTourist")
    public void addTourist(@RequestBody Tourist tourist){
//        tourist.getListOfFlies().clear();
        touristRepo.save(tourist);
    }

    @PostMapping("/editTourist")
    public void editToutistWithoutFlies(@RequestBody Tourist tourist){
        long touristId = tourist.getId();
//        List<Fly> listofFlies = touristRepo.findTouristById(touristId).getListOfFlies();
        Tourist tourist1 = touristRepo.findTouristById(touristId);

        tourist1.setName(tourist.getName());
        tourist1.setSurname(tourist.getSurname());
        tourist1.setSex(tourist.getSex());
        tourist1.setCountry(tourist.getCountry());
        tourist1.setNotes(tourist.getNotes());
        tourist1.setLocalDate(tourist.getLocalDate());

        touristRepo.save(tourist1);
    }




    //usuwanie turysty
    @PostMapping("/removeTourist")
    public void deleteTourist(@RequestBody Tourist tourist){
        touristRepo.delete(tourist);
    }

    @PostMapping("/removeTouristById")
    public void deleteTouristById(@RequestBody LongWrapper idTourist ) {

//        Long touristIdToDelete = idTourist.getToutistID();
//
//        Tourist tourist = touristRepo.findTouristById(touristIdToDelete);
////        touristRepo.deleteById(touristIdToDelete);
//
//        List<Fly> listOfFlies= tourist.getListOfFlies();
//        for(int i=0; i<listOfFlies.size();i++){
//            listOfFlies.get(i).removeTouristToFly(tourist);
//            Fly fly = listOfFlies.get(i);
//            tourist.removeFlytoTourist(fly);
//            touristRepo.save(tourist);
//            flyRepo.save(fly);
//            i--;
//        }
//        touristRepo.delete(tourist);
//    }
        Tourist tourist = touristRepo.findTouristById(idTourist.getLongNumber());
        tourist.getListOfFlies().forEach(f -> f.removeTouristToFly(tourist));
        touristRepo.delete(tourist);
    }


    //lista wszystkich turystow
    @GetMapping("/tourists")
    public List<Tourist> tourists(){
        return touristRepo.findAll();
    }

    //dodawanie isteniejacego turysty do lotu
    @PostMapping("/addTouristToFly")
    public void addTouristToFly(@RequestBody Relationships relationships){

        Tourist tourist = touristRepo.findTouristById(relationships.getTouristId());
        Fly fly = flyRepo.findFlyById(relationships.getFlyId());

        tourist.addFlyToTourist(fly);
        fly.addTouristToFly(tourist);

        touristRepo.save(tourist);
        flyRepo.save(fly);

    }

    //usuwanie istniejacego turysty z lotu
    @PostMapping("/removeTouristToFly")
    public void removeTouristToFly(@RequestBody Relationships relationships){

        Tourist tourist = touristRepo.findTouristById(relationships.getTouristId());
        Fly fly = flyRepo.findFlyById(relationships.getFlyId());

        tourist.removeFlytoTourist(fly);
        fly.removeTouristToFly(tourist);

        touristRepo.save(tourist);
        flyRepo.save(fly);

    }






}
