package com.example.lotwkosmos.model;

import org.springframework.stereotype.Component;

public class LongWrapper {
    Long toutistID;

    public LongWrapper(Long toutistID) {
        this.toutistID = toutistID;
    }

    public Long getToutistID() {
        return toutistID;
    }

    public void setToutistID(Long toutistID) {
        this.toutistID = toutistID;
    }

    public LongWrapper() {
    }
}
