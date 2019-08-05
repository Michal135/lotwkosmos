package com.example.lotwkosmos.model;

import org.springframework.stereotype.Component;

public class LongWrapper {
    Long longNumber;

    public LongWrapper(Long longNumber) {
        this.longNumber = longNumber;
    }

    public LongWrapper() {
    }

    public Long getLongNumber() {
        return longNumber;
    }

    public void setLongNumber(Long longNumber) {
        this.longNumber = longNumber;
    }
}
