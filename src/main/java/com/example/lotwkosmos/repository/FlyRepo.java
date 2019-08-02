package com.example.lotwkosmos.repository;

import com.example.lotwkosmos.model.Fly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface FlyRepo extends JpaRepository<Fly,Long> {
    Fly findFlyById(Long id);
}
