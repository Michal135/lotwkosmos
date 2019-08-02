package com.example.lotwkosmos.repository;

import com.example.lotwkosmos.model.Tourist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TouristRepo extends JpaRepository<Tourist, Long> {
        Tourist findTouristById(Long id);
}
