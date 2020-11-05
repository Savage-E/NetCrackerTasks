package com.netcracker;

import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;

public final class CellularContract extends Contract {
    private int sms;
    private int mb;
    private int minutes;

    public CellularContract(int id, @NotNull LocalDate startDate, @NotNull LocalDate endDate, @NotNull Client client, int sms, int mb, int minutes) {
        super(id, startDate, endDate, client);
        this.sms = sms;
        this.mb = mb;
        this.minutes = minutes;
    }

    public int getSms() {
        return sms;
    }

    public void setSms(int sms) {
        this.sms = sms;
    }

    public int getMb() {
        return mb;
    }

    public void setMb(int mb) {
        this.mb = mb;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
}
