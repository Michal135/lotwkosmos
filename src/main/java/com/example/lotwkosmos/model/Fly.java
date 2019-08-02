package com.example.lotwkosmos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
public class Fly {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private LocalDateTime departureTime = LocalDateTime.now();
    @NotNull
    private LocalDateTime arrivalTime=LocalDateTime.now();
    @NotNull
    private int numberOfPlaces;
    @NotNull
    private double ticketCost;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "tourist_id")
    private List<Tourist> touristListid;

    public void addTouristToFly(Tourist tourist){

        //dodawanie turysty na liste do konkretnego lotu

        boolean isTouristInList = false;
        for (Tourist e: touristListid) {
            if( e.equals(tourist)) {
                isTouristInList = true;
                break;
            }
        }
        if(isTouristInList==false && numberOfPlaces>0){
            touristListid.add(tourist);
            numberOfPlaces--;
        }
    }

    public void removeTouristToFly(Tourist tourist){
        boolean isTouristInList = false;
        for (Tourist e: touristListid) {
            if( e.equals(tourist)) {
                isTouristInList = true;
                break;
            }
        }
        if(isTouristInList==true){
        touristListid.remove(tourist);
        numberOfPlaces++;
        }
    }

    public List<Tourist> getTouristListid() {
        return touristListid;
    }

    public void setTouristListid(List<Tourist> touristListid) {
        this.touristListid = touristListid;
    }

    public Fly() {
    }

    public Fly(LocalDateTime departureTime, LocalDateTime arrivalTime, int numberOfPlaces, double ticketCost) {
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.numberOfPlaces = numberOfPlaces;
        this.ticketCost = ticketCost;
        this.touristListid = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    public double getTicketCost() {
        return ticketCost;
    }

    public void setTicketCost(double ticketCost) {
        this.ticketCost = ticketCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fly fly = (Fly) o;
        return Objects.equals(id, fly.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @JsonIgnore
    public void setArrivalDateWithoutTime(LocalDate localDate){
        this.arrivalTime = LocalDateTime.of(localDate,getArrivalTimeWithoutDate());
    }

    @JsonIgnore
    public LocalTime getArrivalTimeWithoutDate() {
        return arrivalTime.toLocalTime();
    }

    @JsonIgnore
    public void setArrivalTimeWithoutDate(LocalTime localTime){
        this.arrivalTime = LocalDateTime.of(getArrivalDateWithoutTime(),localTime);
    }

    @JsonIgnore
    public LocalDate getArrivalDateWithoutTime() {
        return arrivalTime.toLocalDate();
    }


    @JsonIgnore
    public void setDepartureDateWithoutTime(LocalDate localDate){
        this.departureTime = LocalDateTime.of(localDate,getDepartureTimeWithoutDate());
    }
    @JsonIgnore
    public LocalTime getDepartureTimeWithoutDate() {
        return departureTime.toLocalTime();
    }

    @JsonIgnore
    public void setDepartureTimeWithoutDate(LocalTime localTime){
        this.departureTime = LocalDateTime.of(getDepartureDateWithoutTime(),localTime);
    }

    @JsonIgnore
    public LocalDate getDepartureDateWithoutTime() {
        return departureTime.toLocalDate();
    }

    @Override
    public String toString() {
        return "Fly{" +
                "id=" + id +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", numberOfPlaces=" + numberOfPlaces +
                ", ticketCost=" + ticketCost +
                '}';
    }
}
