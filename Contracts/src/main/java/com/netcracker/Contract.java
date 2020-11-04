package com.netcracker;

import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;

public class Contract {
    protected int id;
    protected @NotNull LocalDate startDate;
    protected @NotNull LocalDate endDate;
    protected @NotNull Client client;


    protected Contract(int id,@NotNull LocalDate startDate, @NotNull LocalDate endDate,@NotNull Client client) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.client = client;
    }

    protected int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    protected LocalDate getStartDate() {
        return startDate;
    }

    protected void setStartDate(@NotNull LocalDate startDate) {
        this.startDate = startDate;
    }

    protected LocalDate getEndDate() {
        return endDate;
    }

    protected void setEndDate(@NotNull LocalDate endDate) {
        this.endDate = endDate;
    }

    protected Client getClient() {
        return client;
    }

    protected void setClient(@NotNull Client client) {
        this.client = client;
    }
}