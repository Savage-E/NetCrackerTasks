package com.netcracker;

import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;

public final class InternetContract extends Contract {
    private int maxSpeed;

    public InternetContract(int id, @NotNull LocalDate startDate, @NotNull LocalDate endDate, @NotNull Client client, int maxSpeed) {
        super(id, startDate, endDate, client);
        this.maxSpeed = maxSpeed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
