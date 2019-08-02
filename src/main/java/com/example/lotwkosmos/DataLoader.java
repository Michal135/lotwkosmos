package com.example.lotwkosmos;

import com.example.lotwkosmos.enums.SexType;
import com.example.lotwkosmos.model.Fly;
import com.example.lotwkosmos.model.Tourist;
import com.example.lotwkosmos.repository.FlyRepo;
import com.example.lotwkosmos.repository.TouristRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    FlyRepo flyRepo;

    @Autowired
    TouristRepo touristRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Tourist tourist1 = new Tourist("name1","surname1", SexType.MALE,"Poland","none",LocalDate.of(2000,5,5));
        Tourist tourist2 = new Tourist("name2","surname2", SexType.MALE,"Poland","none",LocalDate.of(1990,2,10));
        Tourist tourist3 = new Tourist("name3","surname3", SexType.FEMALE,"Poland","none",LocalDate.of(1995,7,22));
        Tourist tourist4 = new Tourist("name4","surname4",SexType.FEMALE,"Poland","none",LocalDate.of(1994,8,21));
        Tourist tourist5 = new Tourist("name5","surname5",SexType.FEMALE,"Poland","none",LocalDate.of(1992,10,7));



        Fly fly1 = new Fly(LocalDateTime.of(2019,5,5,6,30),LocalDateTime.of(2019,5,5,7,30),50,100);
        Fly fly2 = new Fly(LocalDateTime.of(2019,5,10,9,30),LocalDateTime.of(2019,10,5,10,30),100,80);
        Fly fly3 = new Fly(LocalDateTime.of(2019,5,15,5,30),LocalDateTime.of(2019,5,15,7,30),100,80);
        Fly fly4 = new Fly(LocalDateTime.of(2019,5,15,5,30),LocalDateTime.of(2019,5,15,7,30),3,50);




        touristRepo.save(tourist1);
        touristRepo.save(tourist2);
        touristRepo.save(tourist3);
        touristRepo.save(tourist4);
        touristRepo.save(tourist5);


        flyRepo.save(fly1);
        flyRepo.save(fly2);
        flyRepo.save(fly3);
        flyRepo.save(fly4);

        tourist1.addFlyToTourist(fly1);
        tourist1.addFlyToTourist(fly2);
        fly1.addTouristToFly(tourist1);
        fly2.addTouristToFly(tourist1);


        tourist2.addFlyToTourist(fly2);
        fly2.addTouristToFly(tourist2);

        tourist3.addFlyToTourist(fly3);
        fly3.addTouristToFly(tourist3);

        touristRepo.save(tourist1);
        touristRepo.save(tourist2);
        touristRepo.save(tourist3);
        flyRepo.save(fly1);
        flyRepo.save(fly2);
        flyRepo.save(fly3);

//        tourist1.setFlight(fly1);
//        tourist1.setFlight(fly2);
//        tourist2.setFlight(fly2);
//        tourist3.setFlight(fly3);

    }
}
