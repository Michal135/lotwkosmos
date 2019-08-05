package com.example.lotwkosmos.controller;

import com.example.lotwkosmos.model.Fly;
import com.example.lotwkosmos.model.LongWrapper;
import com.example.lotwkosmos.model.Relationships;
import com.example.lotwkosmos.model.Tourist;
import com.example.lotwkosmos.repository.FlyRepo;
import com.example.lotwkosmos.repository.TouristRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlyController {

        @Autowired
        FlyRepo flyRepo;

        @Autowired
        TouristRepo touristRepo;

        //dodawanie lotu
        @PostMapping("/addFly")
        public void addFly(@RequestBody Fly fly){
            flyRepo.save(fly);
        }


        //usuwanie lotu
        @PostMapping("/deleteFly")
        public void deleteFly(@RequestBody Fly fly){
                flyRepo.delete(fly);
        }

        @PostMapping("/removeFlyById")
        public void deleteTouristById(@RequestBody LongWrapper idFlight ) {
                Fly fly = flyRepo.findFlyById(idFlight.getLongNumber());
                fly.getTouristListid().forEach(f -> f.removeFlytoTourist(fly));
                flyRepo.delete(fly);
        }



        //wszystkie loty
        @GetMapping("/flights")
        public List<Fly> flights(){
                return flyRepo.findAll();
        }


        //w sumie nie wiem czy potrzebne robi to samo co w tourist controllerze
        @PostMapping("/deleteFlyFromTourist")
        public void deleteTouristFromFly(@RequestBody Relationships relationships ){

                Tourist tourist = touristRepo.findTouristById(relationships.getTouristId());
                Fly fly = flyRepo.findFlyById(relationships.getFlyId());

                tourist.addFlyToTourist(fly);
                fly.addTouristToFly(tourist);

                touristRepo.save(tourist);
                flyRepo.save(fly);

        }

        //w sumie nie wiem czy potrzebne robi to samo co w tourist controllerze
        @PostMapping("/removeFlyFromTourist")
        public void removeTouristToFly(@RequestBody Relationships relationships){

                Tourist tourist = touristRepo.findTouristById(relationships.getTouristId());
                Fly fly = flyRepo.findFlyById(relationships.getFlyId());

                tourist.removeFlytoTourist(fly);
                fly.removeTouristToFly(tourist);

                touristRepo.save(tourist);
                flyRepo.save(fly);

        }




}
