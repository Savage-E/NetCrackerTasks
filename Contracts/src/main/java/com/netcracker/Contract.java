package com.netcracker;

import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;

public class Contract {
    private int id;
    private @NotNull LocalDate startDate;
    private @NotNull LocalDate endDate;
    private @NotNull Client client;


    protected Contract(int id, @NotNull LocalDate startDate, @NotNull LocalDate endDate, @NotNull Client client) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotNull LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(@NotNull LocalDate startDate) {
        this.startDate = startDate;
    }

    public @NotNull LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(@NotNull LocalDate endDate) {
        this.endDate = endDate;
    }

    public @NotNull Client getClient() {
        return client;
    }

    public void setClient(@NotNull Client client) {
        this.client = client;
    }
}