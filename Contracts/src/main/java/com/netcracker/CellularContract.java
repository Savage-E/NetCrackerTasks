package com.netcracker;

import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;


/**
 * Represents the entity of cellular contracts.
 * <p>
 * Please see the {@link com.netcracker.Contract} class for true identity
 *
 * @author Vlad Kotov
 */
public final class CellularContract extends Contract {
    private int sms;
    private int mb;
    private int minutes;

    /**
     * Creates a cellular contract.
     *
     * @param id        the contract id
     * @param startDate the date of the beginning of the contract
     * @param endDate   the end date of the contract
     * @param person    the person entity
     * @param sms       the number of sms in the contract
     * @param mb        the number of megabyte in the contract
     * @param minutes   the number of minutes in the contract
     */
    public CellularContract(int id, @NotNull LocalDate startDate, @NotNull LocalDate endDate, @NotNull Person person, int sms, int mb, int minutes) {
        super(id, startDate, endDate, person);
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
