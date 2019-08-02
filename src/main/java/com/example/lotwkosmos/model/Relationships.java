package com.example.lotwkosmos.model;

public class Relationships {
    private Long touristId;
    private Long flyId;

    public Relationships() {
    }

    public Relationships(Long touristId, Long flyId) {
        this.touristId = touristId;
        this.flyId = flyId;
    }

    public Long getTouristId() {
        return touristId;
    }

    public void setTouristId(Long touristId) {
        this.touristId = touristId;
    }

    public Long getFlyId() {
        return flyId;
    }

    public void setFlyId(Long flyId) {
        this.flyId = flyId;
    }
}
