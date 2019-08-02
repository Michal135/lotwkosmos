package com.example.lotwkosmos.model;

import com.example.lotwkosmos.enums.SexType;
import com.example.lotwkosmos.repository.FlyRepo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.*;


@Entity
public class Tourist {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotNull
    private SexType sex;
    @NotNull
    private String country;
    private String notes;
    @NotNull
    private LocalDate localDate;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "fly_id")
    private List<Fly> listOfFlies;

    public void addFlyToTourist(Fly fly){
        boolean isFlyInList = false;
        for (Fly e: listOfFlies) {
            if( e.equals(fly)) {
                isFlyInList = true;
                break;
                }
        }
        if(isFlyInList==false && fly.getNumberOfPlaces()>0){
        listOfFlies.add(fly);
        }

//        if(fly.getNumberOfPlaces()>0)
//            listOfFlies.add(fly);

    }

    public void removeFlytoTourist(Fly fly){
        listOfFlies.remove(fly);
    }

    public Tourist() {
    }

    public Tourist(String name, String surname, SexType sex, String country, String notes, LocalDate localDate) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.country = country;
        this.notes = notes;
        this.localDate = localDate;
        this.listOfFlies = new ArrayList<>();
    }

    public Fly getFlight(Fly flight){
        return flight;
    }



    public void setFlight(Fly flight){
        boolean isFlyInList = false;
        for (Fly e: listOfFlies) {
            if( e.equals(flight)) {
                isFlyInList = true;
                break;
            }
        }
        if(isFlyInList==false)
        listOfFlies.add(flight);
        else
            System.out.println("nie dodaje");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public SexType getSex() {
        return sex;
    }

    public void setSex(SexType sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public List<Fly> getListOfFlies() {
        return listOfFlies;
    }

//    public void setListOfFlies(Set<Fly> listOfFlies) {
//        this.listOfFlies = listOfFlies;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tourist tourist = (Tourist) o;
        return Objects.equals(id, tourist.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Tourist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", sex=" + sex +
                ", country='" + country + '\'' +
                ", notes='" + notes + '\'' +
                ", localDate=" + localDate +
                '}';
    }

    public List<Long> writeFlyIds() {
        List<Long> idList = new ArrayList<>();

        for(int i=0;i<listOfFlies.size();i++){
            idList.add(listOfFlies.get(i).getId());
        }
        return idList;
    }
}
